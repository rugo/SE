package de.htwg.blackjack.model.impl;

import de.htwg.blackjack.model.ICard;
import de.htwg.blackjack.model.IColor;
import de.htwg.blackjack.model.Status;
import junit.framework.TestCase;

/**
 * Created by ||USER|| on ||DATE||.
 */
public class PlayerTest extends TestCase {
    private Player player;
    private ICard[] cards;
    public void setUp() {
        this.player = new Player("Andrea", 125);
        this.cards = new ICard[4];
        IColor color = new Color("Schippe", "german_schippe");

        this.cards[0] = new Card(color, new Rank("Bube", "_bube", 0, 10));
        this.cards[1] = new Card(color, new Rank("Dame", "_dame", 0, 10));
        this.cards[2] = new Card(color, new Rank("König", "_koenig", 0, 10));
        this.cards[3] = new Card(color, new Rank("Ass", "_ass", 0, 1, 11));
    }

    public void testGetStatus() throws Exception {
        assertEquals(this.player.getStatus(), Status.READY);
    }

    public void testSetStatus() throws Exception {
        this.player.setStatus(Status.BUSTED);
        assertEquals(this.player.getStatus(), Status.BUSTED);
    }

    public void testGetName() throws Exception {
        assertEquals(this.player.getName(), "Andrea");
    }

    public void testGetCards() throws Exception {
        assertEquals(this.player.getCards().size(), 0);
        this.player.addNewCard(this.cards[0], this.cards[0].getValues().get(0));
        assert this.player.getCards().containsKey(this.cards[0]);
        assertEquals(this.player.getCards().get(this.cards[0]),
                this.cards[0].getValues().get(0));
    }

    public void testGetCardsValue() throws Exception {
        this.player.addNewCard(this.cards[0], 10);
        this.player.addNewCard(this.cards[3], 1);
        assertEquals(this.player.getCardsValue(), 11);
    }

    public void testAddNewCard() throws Exception {
        this.player.addNewCard(this.cards[1], 10);
        assert this.player.getCards().containsKey(this.cards[1]);
    }

    public void testGetAmountOfMoney() throws Exception {
        assertEquals(this.player.getAmountOfMoney(), 125);
    }

    public void testReduceMoney() throws Exception {
        this.player.reduceMoney(25);
        assertEquals(this.player.getAmountOfMoney(), 100);
        try {
            this.player.reduceMoney(1000);
            assert false; // This code should not be reached!
        } catch (IllegalArgumentException ex) {
            // everything good
        }
    }
}