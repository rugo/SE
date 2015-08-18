package de.htwg.blackjack.model.impl;

import de.htwg.blackjack.model.*;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ||USER|| on ||DATE||.
 */
public class TableTest extends TestCase {
    private Table table;
    private List<IPlayer> players;
    private List<IRank> ranks;
    private List<IColor> colors;
    private Player dealer;
    private IPlayer hans;

    public void setUp() throws Exception {
        super.setUp();
        this.dealer = new Player("Dealer", 0);
        this.players = new ArrayList<>(2);
        this.ranks = new ArrayList<>();
        this.colors = new ArrayList<>(4);

        this.players.add(this.dealer);
        this.hans = new Player("Hans", 1500);
        this.players.add(this.hans);

        ranks.add(new Rank("Two", "two", 2, 2));
        ranks.add(new Rank("Three", "three", 3, 3));
        ranks.add(new Rank("Four", "four", 4, 4));
        ranks.add(new Rank("Five", "five", 5, 5));
        ranks.add(new Rank("Six", "six", 6, 6));
        ranks.add(new Rank("Seven", "seven", 7, 7));
        ranks.add(new Rank("Eight", "eight", 8, 8));
        ranks.add(new Rank("Nine", "nine", 9, 9));
        ranks.add(new Rank("Ten", "ten", 10, 10));
        ranks.add(new Rank("Jack", "jack", 0xB, 10));
        ranks.add(new Rank("Queen", "queen", 0xD, 10));
        ranks.add(new Rank("King", "king", 0xE, 10));
        ranks.add(new Rank("Ace", "ace", 11, 1, 11));

        colors.add(new Color("SPADES", "french_spades"));
        colors.add(new Color("HEARTS", "french_hearts"));
        colors.add(new Color("DIAMONDS", "french_diamonds"));
        colors.add(new Color("CLUBS", "french_clubs"));


        IDeckFactory df = new DeckFactory();
        this.table = new Table(df.createFrenchDeck(8), this.dealer, this.hans);

    }

    public void testGetAmountOfDecks() throws Exception {
        assertEquals(this.table.getAmountOfDecks(), 8);
    }

    public void testAddPlayers() throws Exception {
        List<IPlayer> players = new ArrayList<>(2);
        players.add(new Player("Luise", 50));
        players.add(new Player("Hanelore", 100));
        this.table.addPlayers(players);
        assertEquals(this.table.getAmountOfPlayers(), 4);
    }

    public void testAddPlayer() throws Exception {
        this.table.addPlayer(new Player("Luise", 50));
        assertEquals(this.table.getAmountOfPlayers(), 3);
    }

    public void testRemovePlayer() throws Exception {
        for (IPlayer player: this.players) {
            assert this.table.removePlayer(player);
        }
        assertEquals(this.table.getAmountOfPlayers(), 0);
    }

    public void testGetNewCard() throws Exception {
        List<IColor> col = new ArrayList<>(1);
        List<IRank> ranks = new ArrayList<>(2);
        col.add(new Color("Schippe", null));
        ranks.add(new Rank("Bube", null, 0, 10));
        ranks.add(new Rank("Ass", null, 0, 11, 1));
        IDeck deck = new Deck(ranks, col);
        List<IDeck> decks = new ArrayList<>();
        decks.add(deck);
        table = new Table(decks, this.players.get(0));
        assertEquals(this.table.getNewCard().getColor(), col.get(0));
        assert ranks.contains(this.table.getNewCard().getRank());
    }

    public void testPlaceBet() throws Exception {
        this.table.placeBet(this.hans, 50);
        assertEquals(this.table.getBet(this.hans), Integer.valueOf(50));
    }

    public void testGetBet() throws Exception {
        this.table.placeBet(this.hans, 123);
        assertEquals(this.table.getBet(this.hans), Integer.valueOf(123));
    }
}