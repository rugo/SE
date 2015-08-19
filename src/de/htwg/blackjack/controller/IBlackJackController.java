package de.htwg.blackjack.controller;

import de.htwg.blackjack.util.observer.IObservable;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

/**
 * Created by ||USER|| on ||DATE||.
 */
public interface IBlackJackController extends IObservable {
    /**
     * Returns the full information about the table.
     * Including all cards of the players and all
     * bets on the table.
     * @return Single string with all information.
     */
    String getTableInfoString();

    /**
     * Creates a new game.
     * @param playerName Name of the player.
     * @param playerMoney Amount of money the player has.
     */
    void createGame(String playerName, int playerMoney);

    /**
     * User wants to perform a double.
     */
    void userDouble();

    /**
     * User wants to hit.
     */
    void userHit();

    /**
     * User wants to stand.
     */
    void userStand();

    /**
     * User wants to bet.
     * @param amount Amount of money the user wants to bet.
     */
    void userBet(int amount);

    /**
     * Amount the user put in the pot.
     * @return Money in the pot.
     */
    int getPlayerBet();

    /**
     * Get the value of the cards the player holds.
     * @return Sum of value of cards.
     */
    int getPlayerValue();

    /**
     * Get the value of the cards the dealer holds.
     * @return Sum of value of cards.
     */
    int getDealerValue();

    /**
     * Gives the cards images for the user.
     * @return List of card images.
     */
    List<BufferedImage> getUserCardImages() throws IOException;

    /**
     * Same as getUserCardImagePaths but for the dealer.
     * @return List of card images.
     */
    List<BufferedImage> getDealerCardImages() throws IOException;
}
