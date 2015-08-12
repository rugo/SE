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
    public String getTableInfos() {
        String msg =  String.format("The dealers has cards: %s [%d]" + NEWLINE
                + "Your Cards are: %s [%d]",
                this.dealer.getCardsString(),
                this.dealer.getCardsValue(),
                this.user.getCardsString(),
                this.user.getCardsValue()
        );
        if (!this.message.isEmpty()) {
            msg += NEWLINE + this.message;
            this.message = "";
        }
        return msg;
    }

    @Override
    public void createGame(String playerName, int playerMoney) {
        this.user = new Player(playerName, playerMoney);
        this.dealer = new Player("Dealer", 0);
        List<IPlayer> players = new ArrayList<>();
        players.add(this.user);
        players.add(this.dealer);
        this.table = new Table(players,
                deckFactory.createFrenchDeck(STD_AMOUNT_OF_DECKS)
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
    }

    @Override
    public void userDouble() {
        this.table.placeBet(this.user, this.table.getBet(this.user));
        this.userHit();
        this.userStand();
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
        notifyObservers();
    }

    @Override
    public void userStand() {
        runDealer();
    }

    @Override
    public void userBet(int amount) {
        this.table.placeBet(this.user, amount);
    }

    private void payoutPlayer(IPlayer player, double factor) {
        player.addMoney((int) Math.round(this.table.getBet(player) * factor));
    }

    private void restartGame() {
        this.message = "A new round begins.";
        notifyObservers();
        this.createGame(this.user.getName(), this.user.getAmountOfMoney());
    }

    private void runDealer() {
        while (this.dealer.getCardsValue() < this.user.getCardsValue()) {
            this.hit(this.dealer);
        }
        if (this.user.getCardsValue() == this.dealer.getCardsValue()) {
            payoutPlayer(this.user, 1);
            this.message = "Nobody wins.";
        } else {
            this.message = "Dealer wins.";
        }
        notifyObservers();
        restartGame();
    }

    /**
     * Returns
     * @param player
     * @return False if game is over
     */
    private boolean handleStatus(IPlayer player) {
        if (player.getCardsValue() == BLACKJACK) {
            this.message = player.getName() + " has a Blackjack!";
            payoutPlayer(player, WIN_FACTOR);
            notifyObservers();
            return false;
        }
        if (player.getCardsValue() > BLACKJACK) {
            this.message = player.getName() + " is busted!";
            if (player == this.dealer) {
                payoutPlayer(this.user, WIN_FACTOR);
            }
            notifyObservers();
            return false;
        }
        return true;
    }
}
