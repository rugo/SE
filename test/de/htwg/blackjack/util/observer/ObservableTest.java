package de.htwg.blackjack.util.observer;

import junit.framework.TestCase;

/**
 * BlackJack
 * Created by ||USER|| on ||DATE||.
 */
public class ObservableTest extends TestCase {
    private TestOberserver tob;
    private Observable ob;

    private class TestOberserver implements IObserver {
        public Event e = new Event("leer");

        @Override
        public void update(Event e) {
            this.e = e;
        }
    }

    public void setUp() throws Exception {
        super.setUp();
        this.tob = new TestOberserver();
        this.ob = new Observable();
        this.ob.addObserver(this.tob);
    }

    public void testAddObserver() throws Exception {
        this.ob.addObserver(this.tob);
        this.ob.notifyObservers(new Event("nix"));
        assertEquals(this.tob.e.getMessage(), "nix");
    }

    public void testRemoveObserver() throws Exception {
        this.ob.removeObserver(this.tob);
        this.ob.notifyObservers(new Event("HALLO"));
        assertEquals(this.tob.e.getMessage(), "leer");
    }

    public void testRemoveAllObservers() throws Exception {
        this.ob.removeObserver(this.tob);
        this.ob.notifyObservers(new Event("HALLO"));
        assertEquals(this.tob.e.getMessage(), "leer");
    }

    public void testNotifyObservers() throws Exception {
        this.ob.notifyObservers();
        assertEquals(this.tob.e, null);
    }

    public void testNotifyObservers1() throws Exception {
        this.ob.notifyObservers(new Event("nix"));
        assertEquals(this.tob.e.getMessage(), "nix");
    }
}