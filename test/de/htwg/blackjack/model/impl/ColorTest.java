package de.htwg.blackjack.model.impl;

import junit.framework.TestCase;

/**
 * Created by ||USER|| on ||DATE||.
 */
public class ColorTest extends TestCase {
    private Color color = new Color("SPADE", 0xA);

    public void testGetUnicodeBlockPosition() throws Exception {
        assertEquals(this.color.getUnicodeBlockPosition(), 0xA);
    }

    public void testToString() throws Exception {

    }
}