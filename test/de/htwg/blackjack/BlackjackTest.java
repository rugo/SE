package de.htwg.blackjack;

import junit.framework.TestCase;


public class BlackjackTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();

    }

    public void tearDown() throws Exception {

    }

    public void testTestMe() throws Exception {
        assertEquals(Blackjack.testMe(), 5);
    }
}