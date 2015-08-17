package de.htwg.blackjack.model;

/**
 * Created by ||USER|| on ||DATE||.
 */

import java.util.List;

public interface IRank {

    /**
     * Returns the rank part of the filename to show in gui
     * @return Rank part to show.
     */
    String getCardImageName();

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
