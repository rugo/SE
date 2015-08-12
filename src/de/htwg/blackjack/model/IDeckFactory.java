package de.htwg.blackjack.model;

import de.htwg.blackjack.model.IDeck;

import java.util.List;

/**
 * Created by ||USER|| on ||DATE||.
 */
public interface IDeckFactory {
    /**
     * Creates a french stack of cards.
     * @param amount How many decks the stack should contain.
     * @return Stack of decks.
     */
    List<IDeck> createFrenchDeck(int amount);
}
