package de.htwg.blackjack.model;

/**
 * Created by ||USER|| on ||DATE||.
 */

import java.util.List;

public interface ICard {
    /**
     * Returns the color of the card.
     * @return Color of the card.
     */
    IColor getColor();

    /**
     * Returns the rank of the card.
     * @return Rank of the card.
     */
    IRank getRank();

    /**
     * Returns the unicode char of the card.
     * @return UTF-16 character of the card.
     */
    char getUnicodeSymbol();

    /**
     * Returns all possible values of the card.
     * @return All possible values of the card (e.g. Ass 1 and 11).
     */
    List<Integer> getValues();
}
