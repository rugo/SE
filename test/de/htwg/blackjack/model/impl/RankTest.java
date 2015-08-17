package de.htwg.blackjack.model.impl;

import junit.framework.TestCase;

/**
 * Created by ||USER|| on ||DATE||.
 */
public class RankTest extends TestCase {
    private Rank rank;
    private Integer[] values = {1, 2, 3, 4};

    public void setUp(){
        this.rank = new Rank("ASS", "ace_", values);
    }

    public void testGetName() throws Exception {
        assertEquals(this.rank.getName(), "ASS");
    }

    public void testGetUnicodeBlockPosition() throws Exception {
        assertEquals(this.rank.getCardImageName(), "ace_");
    }

    public void testGetValues() throws Exception {
        for (Integer i: this.values) {
            this.rank.getValues().contains(i);
        }
    }

    public void testToString() throws Exception {
        assertEquals(this.rank.toString(), "ASS");
    }
}