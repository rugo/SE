package de.htwg.blackjack.model.impl;

import de.htwg.blackjack.model.IColor;
import de.htwg.blackjack.model.IPlayer;
import de.htwg.blackjack.model.IRank;
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
        this.players.add(new Player("Jochen", 2000));

        this.ranks.add(new Rank("Two", 2, 2));
        this.ranks.add(new Rank("Three", 3, 3));
        this.ranks.add(new Rank("Four", 4, 4));
        this.ranks.add(new Rank("Five", 5, 5));
        this.ranks.add(new Rank("Six", 6, 6));
        this.ranks.add(new Rank("Seven", 7, 7));
        this.ranks.add(new Rank("Eight", 8, 8));
        this.ranks.add(new Rank("Nine", 9, 9));
        this.ranks.add(new Rank("Ten", 10, 10));
        this.ranks.add(new Rank("Jack", 0xB, 10));
        this.ranks.add(new Rank("Queen", 0xD, 10));
        this.ranks.add(new Rank("King", 0xE, 10));
        this.ranks.add(new Rank("Ass", 11, 1, 11));

        this.colors.add(new Color("SPADE", 0xA0));
        this.colors.add(new Color("HEART", 0xB0));
        this.colors.add(new Color("DIAMONDS", 0xC0));
        this.colors.add(new Color("CLUBS", 0xD0));

        this.table = new Table(8, this.players, this.ranks, this.colors);

    }

    public void testInitDecks() throws Exception {
        this.table.initDecks(2, this.ranks, this.colors);
        assertEquals(this.table.getAmountOfDecks(), 2);
    }

    public void testGetAmountOfDecks() throws Exception {
        assertEquals(this.table.getAmountOfDecks(), 8);
    }

    public void testAddPlayers() throws Exception {
        List<IPlayer> players = new ArrayList<>(2);
        players.add(new Player("Luise", 50));
        players.add(new Player("Hanelore", 100));
        this.table.addPlayers(players);
        assertEquals(this.table.getAmountOfPlayers(), 5);
    }

    public void testAddPlayer() throws Exception {
        this.table.addPlayer(new Player("Luise", 50));
        assertEquals(this.table.getAmountOfPlayers(), 4);
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
        col.add(new Color("Schippe", 0));
        ranks.add(new Rank("Bube", 0, 10));
        ranks.add(new Rank("Ass", 0, 11, 1));
        this.table.initDecks(1, ranks, col);
        assertEquals(this.table.getNewCard().getColor(), col.get(0));
        assert ranks.contains(this.table.getNewCard().getRank());
    }

    public void testPlaceBet() throws Exception {
        this.table.placeBet(this.hans, 50);
        assertEquals(this.table.getBet(this.hans), Integer.valueOf(50));
    }

    public void testGetBet() throws Exception {
        this.table.placeBet(this.hans, 123);
        assertEquals(this.table.getBet(this.hans), Integer.valueOf(50));
    }
}