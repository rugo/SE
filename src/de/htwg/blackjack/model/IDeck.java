package de.htwg.blackjack.model;

/**
 * Created by ||USER|| on ||DATE||.
 */


public interface IDeck {
    /**
     * Shuffles the deck.
     */
    void shuffle();

    /**
     * Gets the next card from top of the deck.
     * @return The next card.
     */
    ICard getNextCard();

    /**
     * Checks if the deck has a card left.
     * @return True if a card is left, false if deck is empty.
     */
    boolean hasNextCard();

    /**
     * Returns number of cards left in the deck.
     * @return Number of cards left.
     */
    int cardsLeft();
}
