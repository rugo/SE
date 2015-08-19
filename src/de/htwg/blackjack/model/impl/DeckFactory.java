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
        ranks.add(new Rank("Two", "2", 2));
        ranks.add(new Rank("Three", "3", 3));
        ranks.add(new Rank("Four", "4", 4));
        ranks.add(new Rank("Five", "5", 5));
        ranks.add(new Rank("Six", "6", 6));
        ranks.add(new Rank("Seven", "7", 7));
        ranks.add(new Rank("Eight", "8", 8));
        ranks.add(new Rank("Nine", "9", 9));
        ranks.add(new Rank("Ten", "10", 10));
        ranks.add(new Rank("Jack", "jack", 10));
        ranks.add(new Rank("Queen", "queen", 10));
        ranks.add(new Rank("King", "king", 10));
        ranks.add(new Rank("Ace", "ace", 1, 11));

        colors.add(new Color("SPADES", "spades"));
        colors.add(new Color("HEARTS", "hearts"));
        colors.add(new Color("DIAMONDS", "diamonds"));
        colors.add(new Color("CLUBS", "clubs"));

        for (int i = 0; i < amount; i++) {
            decks.add(new Deck(ranks, colors));
        }

        return decks;
    }

    public List<IDeck> createTestDeck() {
        List<IRank> ranks = new ArrayList<>();
        List<IColor> colors = new ArrayList<>();
        List<IDeck> decks = new ArrayList<>();
        ranks.add(new Rank("JOKER", "jokerpic", 1));
        colors.add(new Color("Schippe", "test_schippe"));
        colors.add(new Color("Hertz", "test_hertz"));
        colors.add(new Color("Schelle", "test_schelle"));
        colors.add(new Color("Kreuz", "test_kreuz"));
        decks.add(new Deck(ranks, colors));
        return decks;
    }
}
