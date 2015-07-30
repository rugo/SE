package de.htwg.blackjack.model.impl;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by ||USER|| on ||DATE||.
 */
public class RankTest extends TestCase {
    private Rank rank;
    private Integer[] values = {1, 2, 3, 4};

    public void setUp(){
        this.rank = new Rank("ASS", 0xE, values);
    }

    public void testGetName() throws Exception {
        assertEquals(this.rank.getName(), "ASS");
    }

    public void testGetUnicodeBlockPosition() throws Exception {
        assertEquals(this.rank.getUnicodeBlockPosition(), 0xE);
    }

    public void testGetValues() throws Exception {
        for (Integer i: this.values) {
            this.rank.getValues().contains(i);
        }
    }

    public void testToString() throws Exception {

    }
}