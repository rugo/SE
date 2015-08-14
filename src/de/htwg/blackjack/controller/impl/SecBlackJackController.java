package de.htwg.blackjack.controller.impl;

import de.htwg.blackjack.controller.IBlackJackController;
import de.htwg.blackjack.model.*;
import de.htwg.blackjack.model.impl.DeckFactory;
import de.htwg.blackjack.model.impl.Player;
import de.htwg.blackjack.model.impl.Table;
import de.htwg.blackjack.util.observer.Event;
import de.htwg.blackjack.util.observer.Observable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ||USER|| on ||DATE||.
 */
public class SecBlackJackController extends Observable implements IBlackJackController {

    private IPlayer player;
    private IPlayer dealer;

    private ITable table;
    private IDeckFactory deckFactory;

    private static final int AMOUNT_OF_DECKS = 8;

    private static final int BLACKJACK = 21;

    /**
     * This will be multiplied with the bet if the player wins.
     */
    private static final double WIN_FACTOR = 1.5;

    private static final  String CURRENCY_SIGN = "€";

    private static final String NEWLINE = System.getProperty("line.separator");

    public SecBlackJackController() {
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
        this.dealer = new Player("Dealer", 0);
        this.player = new Player(playerName, playerMoney);
        this.table = new Table(
                deckFactory.createFrenchDeck(AMOUNT_OF_DECKS),
                this.player,
                this.dealer
        );
    }

    private void notifyYouCantDo(String cantDo) {
        this.notifyWithMessage("You can't " + cantDo + " now!");
    }

    @Override
    public void userDouble() {
        if (this.player.getStatus() != Status.HIT) {
            this.notifyYouCantDo("double");
        } else {
            player.setStatus(Status.STAND);
            this.doubleBet(this.player);
            notifyObservers();
        }
    }

    private void notifyWithMessage(String message) {
        notifyObservers(new Event(message));
    }

    @Override
    public void userHit() {
        if (this.player.getStatus() != Status.ENTERED &&
                this.player.getStatus() != Status.HIT) {
            this.notifyYouCantDo("hit");
        } else {
            this.hit(this.player);
            if (!checkStatus(this.player)) {
                restartGame();
                return;
            }
            this.player.setStatus(Status.HIT);
        }
        notifyObservers();
    }

    private void bet(IPlayer player, int amount) {
        this.table.placeBet(player, amount);
    }

    private void doubleBet(IPlayer player) {
        this.bet(player, this.table.getBet(player));
        this.hit(this.player);
        this.stand();
    }
    private void hit(IPlayer player) {
        pullCard(player);
    }

    private int chooseCardValue(IPlayer player, ICard card) {
        int bestFit = card.getValues().get(0);
        int bestScore = 21;
        for (Integer val: card.getValues()) {
            int score = Math.abs(BLACKJACK - (player.getCardsValue() + val));
            if (bestScore > score) {
                bestFit = val;
                bestScore = score;
            }
        }
        return bestFit;
    }

    private void pullCard(IPlayer player) {
        ICard newCard = this.table.getNewCard();
        this.notifyWithMessage(player.getName() + " pulls card " + newCard);
        player.addNewCard(newCard, chooseCardValue(player, newCard));
    }

    @Override
    public void userStand() {
        this.player.setStatus(Status.STAND);
        this.stand();
    }

    @Override
    public void userBet(int amount) {
        if (this.player.getStatus() == Status.ENTERED) {
            this.bet(this.player, amount);
        } else {
            this.notifyYouCantDo("bet");
        }
        notifyObservers();
    }

    private void runDealer() {
        while (this.dealer.getCardsValue() < this.player.getCardsValue()) {
            this.hit(this.dealer);
            if (!checkStatus(this.dealer)) {
                return;
            }
        }
        if (this.player.getCardsValue() == this.dealer.getCardsValue()) {
            payoutPlayer(this.player, 1);
            this.notifyWithMessage("Nobody wins.");
        } else {
            this.notifyWithMessage("Dealer wins.");
        }
    }

    private void stand() {
        runDealer();
        notifyObservers();
        restartGame();
    }

    private void restartGame() {
        this.notifyWithMessage("A new round begins.");
        this.createGame(this.player.getName(), this.player.getAmountOfMoney());
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
    public List<String> getUserCardImagePaths() {
        return new ArrayList<String>();
    }

    @Override
    public List<String> getDealerCardImagePaths() {
        return new ArrayList<String>();
    }

    private void payoutPlayer(IPlayer player, double factor) {
        int newAmount = (int) Math.round(this.table.getBet(player) * factor);
        player.addMoney(newAmount);
        this.notifyWithMessage(player.getName() + " receives " + newAmount + CURRENCY_SIGN);
    }

    private boolean checkStatus(IPlayer player) {
        if (player.getCardsValue() == BLACKJACK) {
            this.notifyWithMessage(player.getName() + " has a Blackjack!");
            payoutPlayer(player, WIN_FACTOR);
            return false;
        }
        if (player.getCardsValue() > BLACKJACK) {
            this.notifyWithMessage(player.getName() + " is busted!");
            if (player == this.dealer) {
                payoutPlayer(this.player, WIN_FACTOR);
            }
            return false;
        }
        return true; // game not over
    }
}
