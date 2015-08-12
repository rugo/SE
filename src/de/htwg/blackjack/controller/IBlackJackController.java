package de.htwg.blackjack.controller;

import de.htwg.blackjack.util.observer.IObservable;

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
    String getTableInfos();

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
}
