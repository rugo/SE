package de.htwg.blackjack.model.impl;

import junit.framework.TestCase;

/**
 * Created by ||USER|| on ||DATE||.
 */
public class ColorTest extends TestCase {
    private Color color = new Color("SPADE", "spades");

    public void testGetUnicodeBlockPosition() throws Exception {
        assertEquals(this.color.getCardImageName(), "spades");
    }

    public void testToString() throws Exception {
        assertEquals(this.color.toString(), "SPADE");
    }
}