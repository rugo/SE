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
        ranks.add(new Rank("Two", "two", 2));
        ranks.add(new Rank("Three", "three", 3));
        ranks.add(new Rank("Four", "four", 4));
        ranks.add(new Rank("Five", "five", 5));
        ranks.add(new Rank("Six", "six", 6));
        ranks.add(new Rank("Seven", "seven", 7));
        ranks.add(new Rank("Eight", "eight", 8));
        ranks.add(new Rank("Nine", "nine", 9));
        ranks.add(new Rank("Ten", "ten", 10));
        ranks.add(new Rank("Jack", "jack", 10));
        ranks.add(new Rank("Queen", "queen", 10));
        ranks.add(new Rank("King", "king", 10));
        ranks.add(new Rank("Ace", "ace", 1, 11));

        colors.add(new Color("SPADES", "french_spades"));
        colors.add(new Color("HEARTS", "french_hearts"));
        colors.add(new Color("DIAMONDS", "french_diamonds"));
        colors.add(new Color("CLUBS", "french_clubs"));

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
