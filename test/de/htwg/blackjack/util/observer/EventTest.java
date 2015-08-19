package de.htwg.blackjack.util.observer;

import junit.framework.TestCase;

/**
 * BlackJack
 * Created by ||USER|| on ||DATE||.
 */
public class EventTest extends TestCase {

    public void testGetMessage() throws Exception {
        Event e = new Event("Hallo");
        assertEquals(e.getMessage(), "Hallo");
    }
}