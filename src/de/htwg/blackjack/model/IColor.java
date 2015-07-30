package de.htwg.blackjack.model;

/**
 * Created by ||USER|| on ||DATE||.
 */
public interface IColor {
    /**
     * Returns the second last digit of the HEX unicode position of the
     * unicode block of playing cards.
     * As documented here: https://en.wikipedia.org/wiki/Playing_cards_in_Unicode#Block
     * @return Second last HEX digit for unicode char of card.
     */
    char getUnicodeBlockPosition();
}
