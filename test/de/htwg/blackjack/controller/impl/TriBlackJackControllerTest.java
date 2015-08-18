package de.htwg.blackjack.controller.impl;

import de.htwg.blackjack.model.impl.DeckFactory;
import junit.framework.TestCase;

/**
 * BlackJack
 * Created by ||USER|| on ||DATE||.
 */
public class TriBlackJackControllerTest extends TestCase {
    private TriBlackJackController con;

    public void setUp() throws Exception {
        super.setUp();
        this.con = new TriBlackJackController();
        this.con.createGame("Hans", 100);
        this.con.setDecks(new DeckFactory().createTestDeck());
    }

    public void testGetTableInfoString() throws Exception {
        this.con.getTableInfoString();
    }

    public void testCreateGame() throws Exception {
        this.con.createGame("a", 100);
        assertEquals(this.con.getPlayerValue(), 0);
    }

    public void testUserDouble() throws Exception {
        this.con.userDouble();
        assertEquals(this.con.getPlayerValue(), 0);
    }

    public void testUserHit() throws Exception {
        this.con.userBet(50);
        this.con.userHit();
        assertEquals(this.con.getPlayerValue(), 3);  // All cards in test deck have value 3
    }

    public void testUserStand() throws Exception {
        this.con.userBet(50);
        this.con.userStand();
        assertEquals(this.con.getPlayerValue(), 0);
    }

    public void testUserBet() throws Exception {
        this.con.userHit();  // not allowed yet
        assertEquals(this.con.getPlayerValue(), 0);
        this.con.userBet(50);
        assertEquals(this.con.getPlayerValue(), 2);
    }

    public void testGetPlayerValue() throws Exception {
        assertEquals(this.con.getPlayerValue(), 0);
        this.con.userBet(50);
        this.con.userHit();
        assertEquals(this.con.getPlayerValue(), 3);
    }

    public void testGetDealerValue() throws Exception {
        assertEquals(this.con.getDealerValue(), 0);
        this.con.userBet(50);
        assertEquals(this.con.getDealerValue(), 1);
    }

    public void testGetUserCardImagePaths() throws Exception {
        assertEquals(this.con.getUserCardImagePaths().size(), 0);
    }

    public void testGetDealerCardImagePaths() throws Exception {
        assertEquals(this.con.getDealerCardImagePaths().size(), 0);
    }

    public void testGetPlayerBet() throws Exception {
        this.con.userBet(12);
        assertEquals(this.con.getPlayerBet(), 12);
    }
}