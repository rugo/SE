package de.htwg.blackjack.model;

/**
 * Created by ||USER|| on ||DATE||.
 */

import java.util.List;

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
}
