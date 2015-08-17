package de.htwg.blackjack.model;

/**
 * Created by ||USER|| on ||DATE||.
 */

import com.google.inject.ImplementedBy;
import de.htwg.blackjack.model.impl.Player;

import java.util.Map;

@ImplementedBy(Player.class)
public interface IPlayer {
    /**
     * Returns the players name.
     * @return Name of player
     */
    String getName();

    /**
     * Return all cards the player holds.
     * @return All cards the player holds (with given value) in the current round.
     */
    Map<ICard, Integer> getCards();

    /**
     * Returns the overall value of the cards the player has in the round.
     * @return Value of the players cards.
     */
    int getCardsValue();

    /**
     * Calculates the String for all cards of the player.
     * @return All cards of the player in one string.
     */
    String getCardsString();

    /**
     * Adds new card to the players cards.
     * @param card Card to give to the player
     * @param value Value the player gave the card (e.g. an ass can be 1 or 11).
     */
    void addNewCard(ICard card, Integer value);

    /**
     * Sets the status of the player.
     * If status is set to HIT, the player does not accept cards anymore.
     * @param status New status of the player.
     */
    void setStatus(Status status);

    /**
     * Returns the current status of the player.
     * @return Status of the player.
     */
    Status getStatus();

    /**
     * Gets the amount of money the player has left.
     * @return Amount of money player has.
     */
    int getAmountOfMoney();

    /**
     * Place a bet.
     * @param amount Amount of money you want to bet.
     * @return New amount of money.
     */
    int reduceMoney(int amount);

    /**
     * Add money to the player.
     * @param amount How much to add to players account.
     */
    void addMoney(int amount);
}
