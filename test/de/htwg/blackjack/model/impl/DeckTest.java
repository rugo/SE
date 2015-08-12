package de.htwg.blackjack.model.impl;

import de.htwg.blackjack.model.IColor;
import de.htwg.blackjack.model.IRank;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by ||USER|| on ||DATE||.
 */
public class DeckTest extends TestCase {
    private Deck deck;
    private List<IColor> colors = new ArrayList<>();
    private List<IRank> ranks = new ArrayList<>();

    public DeckTest() {
        // Load standard french deck
        this.ranks.add(new Rank("Two", 2, 2));
        this.ranks.add(new Rank("Three", 3, 3));
        this.ranks.add(new Rank("Four", 4, 4));
        this.ranks.add(new Rank("Five", 5, 5));
        this.ranks.add(new Rank("Six", 6, 6));
        this.ranks.add(new Rank("Seven", 7, 7));
        this.ranks.add(new Rank("Eight", 8, 8));
        this.ranks.add(new Rank("Nine", 9, 9));
        this.ranks.add(new Rank("Ten", 10, 10));
        this.ranks.add(new Rank("Jack", 0xB, 10));
        this.ranks.add(new Rank("Queen", 0xD, 10));
        this.ranks.add(new Rank("King", 0xE, 10));
        this.ranks.add(new Rank("Ass", 11, 1, 11));

        this.colors.add(new Color("SPADE", 0xA0));
        this.colors.add(new Color("HEART", 0xB0));
        this.colors.add(new Color("DIAMONDS", 0xC0));
        this.colors.add(new Color("CLUBS", 0xD0));
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