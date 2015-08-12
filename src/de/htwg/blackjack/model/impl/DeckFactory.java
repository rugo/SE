package de.htwg.blackjack.model.impl;

import de.htwg.blackjack.model.IColor;
import de.htwg.blackjack.model.IDeck;
import de.htwg.blackjack.model.IDeckFactory;
import de.htwg.blackjack.model.IRank;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ||USER|| on ||DATE||.
 */
public class DeckFactory implements IDeckFactory {
    @Override
    public List<IDeck> createFrenchDeck(int amount) {
        List<IRank> ranks = new ArrayList<>();
        List<IColor> colors = new ArrayList<>();
        List<IDeck> decks = new ArrayList<>(amount);
        ranks.add(new Rank("Two", 2, 2));
        ranks.add(new Rank("Three", 3, 3));
        ranks.add(new Rank("Four", 4, 4));
        ranks.add(new Rank("Five", 5, 5));
        ranks.add(new Rank("Six", 6, 6));
        ranks.add(new Rank("Seven", 7, 7));
        ranks.add(new Rank("Eight", 8, 8));
        ranks.add(new Rank("Nine", 9, 9));
        ranks.add(new Rank("Ten", 10, 10));
        ranks.add(new Rank("Jack", 0xB, 10));
        ranks.add(new Rank("Queen", 0xD, 10));
        ranks.add(new Rank("King", 0xE, 10));
        ranks.add(new Rank("Ass", 11, 1, 11));

        colors.add(new Color("SPADE", 0xA0));
        colors.add(new Color("HEART", 0xB0));
        colors.add(new Color("DIAMONDS", 0xC0));
        colors.add(new Color("CLUBS", 0xD0));

        decks.add(new Deck(ranks, colors));

        return decks;
    }
}
