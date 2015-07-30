package de.htwg.blackjack.model;

/**
 * Created by ||USER|| on ||DATE||.
 */

import java.util.List;

public interface IRank {

    /**
     * Returns the last digit of the HEX unicode position of the
     * unicode block of playing cards.
     * As documented here: https://en.wikipedia.org/wiki/Playing_cards_in_Unicode#Block
     * @return Last HEX digit for unicode char of card.
     */
    byte getUnicodeBlockPosition();

    /**
     * Returns the name of the rank.
     * @return Name of the rank of the card.
     */
    String getName();

    /**
     * Returns possible values of the card.
     * Usually this is only one value, with ASS its two (1 or 11).
     * @return All possible values of the card.
     */
    List<Integer> getValues();
}
