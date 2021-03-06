package de.htwg.blackjack.controller.impl;

import de.htwg.blackjack.controller.IBlackJackController;
import de.htwg.blackjack.model.*;
import de.htwg.blackjack.model.impl.DeckFactory;
import de.htwg.blackjack.model.impl.Player;
import de.htwg.blackjack.model.impl.Table;
import de.htwg.blackjack.util.observer.Event;
import de.htwg.blackjack.util.observer.Observable;

import javax.inject.Singleton;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by ||USER|| on ||DATE||.
 */
@Singleton
public class TriBlackJackController extends Observable implements IBlackJackController {

    private IPlayer dealer;
    private IPlayer player;
    private ITable table;
    private IDeckFactory deckFactory;
    private static final int AMOUNT_OF_DECKS = 8;
    private static final int BLACKJACK = 21;
    private static final double PLAYER_MONEY_WIN_FACTOR = 1.5;
    public static final String CURRENCY_SIGN = "€";
    private static final String NEWLINE = System.getProperty("line.separator");

    /**
     * This stores what state is legal.
     * Contains:
     * key -> Status the UI requests
     * value -> List of statuses that can lead to the UI requested one
     */
    private final Map<Status, List<Status>> legalStatuses;

    public TriBlackJackController() {
        legalStatuses = new HashMap<>();
        legalStatuses.put(Status.HIT, Arrays.asList(Status.BETTED, Status.HIT));
        legalStatuses.put(Status.STAND, Arrays.asList(Status.BETTED, Status.HIT));
        legalStatuses.put(Status.DOUBLE, Arrays.asList(Status.BETTED, Status.HIT));
        legalStatuses.put(Status.BETTED, Arrays.asList(Status.ENTERED));
        this.deckFactory = new DeckFactory();
    }

    @Override
    public String getTableInfoString() {
        return String.format(NEWLINE + NEWLINE + "POT: %d%s" + NEWLINE
                        + "The dealer has cards: %s (Value: %d)" + NEWLINE
                        + "%s [%d%s] has cards: %s (Value :%d)",
                this.table.getBet(this.player),
                CURRENCY_SIGN,
                this.dealer.getCardsString(),
                this.dealer.getCardsValue(),
                this.player.getName(),
                this.player.getAmountOfMoney(),
                CURRENCY_SIGN,
                this.player.getCardsString(),
                this.player.getCardsValue()
        );
    }

    @Override
    public void createGame(String playerName, int playerMoney) {
        this.player = new Player(playerName, playerMoney);
        this.dealer = new Player("Dealer", 0);
        this.setDecks(deckFactory.createFrenchDeck(AMOUNT_OF_DECKS));
    }

    public void setDecks(List<IDeck> decks) {
        this.table = new Table(decks, this.dealer, this.player);
    }

    private void initGame() {
        pullCard(this.dealer);
        pullCard(this.player);
        pullCard(this.player);
    }

    /**
     * Sets the status of the player if the action is legal.
     * @param newStatus New status the player should get.
     * @return True if Status was set, false if not.
     */
    private boolean setStatusIfLegal(Status newStatus) {
        if (this.legalStatuses.get(newStatus).contains(this.player.getStatus())) {
            this.player.setStatus(newStatus);
            return true;
        }
        notifyWithMessage("You cant " + newStatus.toString() + " now.");
        return false;
    }

    private void notifyWithMessage(String message) {
        notifyObservers(new Event(message));
    }

    @Override
    public void userDouble() {
        if (setStatusIfLegal(Status.DOUBLE)) {
            this.doDouble();
        }
    }

    private void doDouble() {
        doBet(getPlayerBet());
        if (pullCard(this.player)){
            this.runDealer();
        }
        startNewRound();
    }

    @Override
    public void userHit() {
        if (setStatusIfLegal(Status.HIT)) {
            this.doHit();
        }
    }

    private void doHit() {
        if (!pullCard(this.player)) {
            startNewRound();
        }
    }

    /**
     * Pull new card with player.
     * @param player Player that should pull the card.
     * @return true if round continues false if round is over
     */
    private boolean pullCard(IPlayer player) {
        assignNewCard(player);
        notifyObservers();
        if (checkBlackJack(player)) {
            payoutIfPlayer(player);
            return false;
        } else if (checkBusted(player)) {
            payoutIfNotPlayer(player);
            return false;
        }
        return true;
    }

    private void payoutIfPlayer(IPlayer player) {
        if (player == this.player) {
            payoutWinner(player);
        }
    }

    private void payoutIfNotPlayer(IPlayer player) {
        if (player != this.player) {
            payoutWinner(this.player);
        }
    }

    private void payoutWinner(IPlayer player) {
        double factor = 1;
        if (player == this.player) {
            factor = PLAYER_MONEY_WIN_FACTOR;
        }
        transferMoney(player, (int) Math.round(factor * this.table.getBet(player)));
    }

    private void startNewRound() {
        notifyWithMessage("A new round started.");
        createGame(player.getName(), player.getAmountOfMoney());
    }

    private void transferMoney(IPlayer player, int amount) {
        this.notifyWithMessage(String.format("%d%s go to %s", amount, CURRENCY_SIGN, player.getName()));
        this.player.addMoney(amount);
    }

    private void assignNewCard(IPlayer player) {
        ICard card = this.table.getNewCard();
        player.addNewCard(card, getBestFittingVal(player, card));
    }

    private boolean checkBusted(IPlayer player) {
        return notifyMessageIfTrue(player.getCardsValue() > BLACKJACK, player.getName() + " is busted!");

    }

    private boolean notifyMessageIfTrue(boolean statement, String msg) {
        if (statement) {
            notifyWithMessage(msg);
        }
        return statement;
    }

    private boolean checkBlackJack(IPlayer player) {
        return this.notifyMessageIfTrue(player.getCardsValue() == 21, player.getName() + " has a BlackJack!");
    }

    private int getBestFittingVal(IPlayer player, ICard card) {
        int playerVal = player.getCardsValue();
        int score = 21;
        int pickedVal = card.getValues().get(0);
        for (int val: card.getValues()) {
            int sScore = playerVal + val;
            if (sScore <= BLACKJACK) {
                sScore = BLACKJACK - sScore;
                if (sScore < score) {
                    score = sScore;
                    pickedVal = val;
                }
            }
        }
        return pickedVal;
    }

    @Override
    public void userStand() {
        if (setStatusIfLegal(Status.STAND)) {
            this.doStand();
            startNewRound();
        }
    }

    private void doStand() {
        this.runDealer();
    }

    private void runDealer() {
        while (this.dealer.getCardsValue() < this.player.getCardsValue()) {
            if (!pullCard(this.dealer)) {
                return;
            }
            notifyObservers();
        }
        if (this.dealer.getCardsValue() > this.player.getCardsValue()) {
            notifyWithMessage("Dealer wins.");
        }
        if (this.dealer.getCardsValue() == this.player.getCardsValue()) {
            this.notifyWithMessage("Nobody wins.");
            this.transferMoney(this.player, this.table.getBet(this.player));
        }
    }

    @Override
    public void userBet(int amount) {
        if (setStatusIfLegal(Status.BETTED)) {
            this.doBet(amount);
            initGame();
            notifyObservers();
        }
    }

    @Override
    public int getPlayerBet() {
        return this.table.getBet(this.player);
    }

    private void doBet(int amount) {
        this.table.placeBet(this.player, amount);
    }

    @Override
    public int getPlayerValue() {
        return this.player.getCardsValue();
    }

    @Override
    public int getDealerValue() {
        return this.dealer.getCardsValue();
    }

    @Override
    public List<BufferedImage> getUserCardImages() throws IOException {
        return this.formList(this.player.getCards().keySet());
    }

    private List<BufferedImage> formList(Set<ICard> cards) throws IOException {
        List<BufferedImage> l = new ArrayList<>();
        for (ICard card: cards) {
            l.add(javax.imageio.ImageIO.read(new File(card.getImagePath())));
        }
        return l;
    }

    @Override
    public List<BufferedImage> getDealerCardImages() throws IOException {
        return this.formList(this.dealer.getCards().keySet());
    }

    @Override
    public String getPlayerName() {
        return this.player.getName();
    }

    @Override
    public String getPlayerMoneyString() {
        return "" + this.player.getAmountOfMoney() + CURRENCY_SIGN;
    }
}
