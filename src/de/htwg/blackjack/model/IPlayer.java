package de.htwg.blackjack.model;

/**
 * Created by ||USER|| on ||DATE||.
 */

import java.util.List;

public interface IPlayer {
    /**
     * Returns the players name.
     * @return Name of player
     */
    String getName();

    /**
     * Return all cards the player holds.
     * @return All cards of the player in the round.
     */
    List<ICard> getCards();

    /**
     * Returns the overall value of the cards the player has in the round.
     * @return Value of the players cards.
     */
    int getCardsValue();

    /**
     * Adds new card to the players cards.
     * @param card Card to give to the player.
     */
    void addNewCard(ICard card);

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
}
