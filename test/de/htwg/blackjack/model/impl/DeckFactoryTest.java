package de.htwg.blackjack.model.impl;

import junit.framework.TestCase;

/**
 * BlackJack
 * Created by ||USER|| on ||DATE||.
 */
public class DeckFactoryTest extends TestCase {
    private DeckFactory fac;

    public void setUp() throws Exception {
        super.setUp();
        this.fac = new DeckFactory();
    }

    public void testCreateFrenchDeck() throws Exception {
        assertEquals(this.fac.createFrenchDeck(1).size(), 1);
        assertEquals(this.fac.createFrenchDeck(1).get(0).cardsLeft(), 52);
    }
}