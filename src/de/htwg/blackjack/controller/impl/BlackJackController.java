package de.htwg.blackjack.controller.impl;

import de.htwg.blackjack.controller.IBlackJackController;
import de.htwg.blackjack.model.*;
import de.htwg.blackjack.model.impl.DeckFactory;
import de.htwg.blackjack.model.impl.Player;
import de.htwg.blackjack.model.impl.Table;
import de.htwg.blackjack.util.observer.Observable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by ||USER|| on ||DATE||.
 */
public class BlackJackController extends Observable implements IBlackJackController {
    /**
     * Amount of decks per stack.
     */
    private final int STD_AMOUNT_OF_DECKS = 8;

    private final int BLACKJACK = 21;

    /**
     * This will be multiplied with the bet if the player wins.
     */
    private final double WIN_FACTOR = 1.5;

    private final static String CURRENCY_SIGN = "€";

    private static final String NEWLINE = System.getProperty("line.separator");

    private final Random rnd;

    private IPlayer user;
    private IPlayer dealer;
    private ITable table;
    private final IDeckFactory deckFactory;
    public String message = "";

    public BlackJackController() {
        this.rnd = new Random();
        this.deckFactory = new DeckFactory();
    }

    // TODO: dep injection

    @Override
    public String getTableInfoString() {
        String msg;
        if (!this.message.isEmpty()) {
            msg = this.message;
            this.message = "";
        } else {
            msg = String.format("The dealers has cards: %s (Value: %d)" + NEWLINE
                            + "%s [%d%s] Cards are: %s (Value :%d)",
                    this.dealer.getCardsString(),
                    this.dealer.getCardsValue(),
                    this.user.getName(),
                    this.user.getAmountOfMoney(),
                    CURRENCY_SIGN,
                    this.user.getCardsString(),
                    this.user.getCardsValue()
            );
        }
        return msg;
    }

    @Override
    public void createGame(String playerName, int playerMoney) {
        this.user = new Player(playerName, playerMoney);
        this.dealer = new Player("Dealer", 0);

        this.table = new Table(
                deckFactory.createFrenchDeck(STD_AMOUNT_OF_DECKS),
                this.user,
                this.dealer
        );
    }



    /**
     * The card ass can have two different values (1 or 11).
     * This method decides which value is better.
     * @return The value to be chosen.
     */
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
        player.addNewCard(newCard, chooseCardValue(player, newCard));
        notifyObservers();
    }

    @Override
    public void userDouble() {
        this.table.placeBet(this.user, this.table.getBet(this.user));
        this.hit(this.user);
        this.userStand();
        notifyObservers();
    }

    @Override
    public void userHit() {
        this.hit(this.user);
    }

    private void hit(IPlayer player) {
        pullCard(player);
        if (!handleStatus(player)) {
            restartGame();
        }
    }

    @Override
    public void userStand() {
        while(this.dealer.getCardsValue() < this.user.getCardsValue()) {
            runDealer();
            notifyObservers();
        }
    }

    @Override
    public void userBet(int amount) {
        this.table.placeBet(this.user, amount);
    }

    @Override
    public int getPlayerValue() {
        return 0;
    }

    @Override
    public int getDealerValue() {
        return 0;
    }

    @Override
    public List<String> getUserCardImagePaths() {
        return null;
    }

    @Override
    public List<String> getDealerCardImagePaths() {
        return null;
    }

    private void payoutPlayer(IPlayer player, double factor) {
        int newAmount = (int) Math.round(this.table.getBet(player) * factor);
        this.message = player.getName() + " receives " + newAmount + CURRENCY_SIGN;
        player.addMoney(newAmount);
    }

    private void restartGame() {
        this.notifyWithMessage("A new round begins.");
        this.createGame(this.user.getName(), this.user.getAmountOfMoney());
    }

    private void notifyWithMessage(String message) {
        this.message = message;
        notifyObservers();
    }

    private void runDealer() {
        this.hit(this.dealer);
        if (this.user.getCardsValue() == this.dealer.getCardsValue()) {
            payoutPlayer(this.user, 1);
            this.notifyWithMessage("Nobody wins.");
        } else {
            this.notifyWithMessage("Dealer wins.");
        }
    }

    /**
     * Returns
     * @param player
     * @return False if game is over
     */
    private boolean handleStatus(IPlayer player) {
        if (player.getCardsValue() == BLACKJACK) {
            this.notifyWithMessage(player.getName() + " has a Blackjack!");
            payoutPlayer(player, WIN_FACTOR);
            notifyObservers();
            return false;
        }
        if (player.getCardsValue() > BLACKJACK) {
            this.notifyWithMessage(player.getName() + " is busted!");
            if (player == this.dealer) {
                payoutPlayer(this.user, WIN_FACTOR);
            }
            return false;
        }
        return true; // game not over
    }
}
