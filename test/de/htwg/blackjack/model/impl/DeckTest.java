package de.htwg.blackjack.model.impl;

import de.htwg.blackjack.model.IColor;
import de.htwg.blackjack.model.IRank;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ||USER|| on ||DATE||.
 */
public class DeckTest extends TestCase {
    private Deck deck;
    private List<IColor> colors = new ArrayList<>();
    private List<IRank> ranks = new ArrayList<>();

    public DeckTest() {
        // Load standard french deck
        ranks.add(new Rank("Two", "two", 2, 2));
        ranks.add(new Rank("Three", "three", 3, 3));
        ranks.add(new Rank("Four", "four", 4, 4));
        ranks.add(new Rank("Five", "five", 5, 5));
        ranks.add(new Rank("Six", "six", 6, 6));
        ranks.add(new Rank("Seven", "seven", 7, 7));
        ranks.add(new Rank("Eight", "eight", 8, 8));
        ranks.add(new Rank("Nine", "nine", 9, 9));
        ranks.add(new Rank("Ten", "ten", 10, 10));
        ranks.add(new Rank("Jack", "jack", 0xB, 10));
        ranks.add(new Rank("Queen", "queen", 0xD, 10));
        ranks.add(new Rank("King", "king", 0xE, 10));
        ranks.add(new Rank("Ace", "ace", 11, 1, 11));

        colors.add(new Color("SPADES", "french_spades"));
        colors.add(new Color("HEARTS", "french_hearts"));
        colors.add(new Color("DIAMONDS", "french_diamonds"));
        colors.add(new Color("CLUBS", "french_clubs"));

    }

    public void setUp() {
        this.deck = new Deck(this.ranks, this.colors);
        this.deck.shuffle();
    }

    public void testShuffle() throws Exception {
        this.deck.shuffle();
    }

    public void testGetNextCard() throws Exception {
        while (this.deck.hasNextCard()) {
            this.deck.getNextCard();
        }
        assertEquals(this.deck.cardsLeft(), 0);
    }

    public void testHasNextCard() throws Exception {
        assert this.deck.hasNextCard();
        for (int i = 0; i < 52; i++) {
            this.deck.getNextCard();
        }
        assert !this.deck.hasNextCard();
    }


    public void testCardsLeft() throws Exception {
        for (int i = 52; i > 0; i--) {
            assertEquals(this.deck.cardsLeft(), i);
            this.deck.getNextCard();
        }
    }

    public void testToString() throws Exception {
        assertEquals(this.deck.toString(), "Deck with 52 left.");
    }
}