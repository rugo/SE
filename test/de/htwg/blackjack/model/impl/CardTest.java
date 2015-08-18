package de.htwg.blackjack.model.impl;

import junit.framework.TestCase;

/**
 * Created by ||USER|| on ||DATE||.
 */
public class CardTest extends TestCase {
    // Card of a German deck
    private Color color = new Color("Schippe", "german_schippe");
    private Rank rank = new Rank("Bube", "bube", 10);
    private Card card = new Card(color, rank);

    public void testToString() throws Exception {
        assertEquals(this.card.toString(), "Schippe Bube");
    }

    public void testGetColor() throws Exception {
        assertEquals(this.card.getColor(), color);
    }

    public void testGetRank() throws Exception {
        assertEquals(this.card.getRank(), rank);

    }

    public void testGetImagePath() throws Exception {
        assertEquals(this.card.getImagePath(), "german_schippe_bube");
    }

    public void testGetValues() throws Exception {
        assertEquals(this.card.getValues().size(), 1);
        assertEquals(this.card.getValues().get(0), Integer.valueOf(10));
    }
}