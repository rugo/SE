package de.htwg.blackjack.model.impl;

import de.htwg.blackjack.model.*;

import java.util.*;

/**
 * Created by ||USER|| on ||DATE||.
 */
// TODO: Check parameters
public final class Table implements ITable {
    private List<IDeck> decks;
    private final Map<IPlayer, Integer> bets;


    public  Table(List<IDeck> decks, IPlayer... players) {
        this.bets = new HashMap<>();

        this.addPlayers(Arrays.asList(players));
        this.decks = decks;
    }

    @Override
    public int getAmountOfDecks() {
        return this.decks.size();
    }

    public void addPlayers(List<IPlayer> players) {
        for (IPlayer player: players) {
            this.addPlayer(player);
        }
    }

    @Override
    public int getAmountOfPlayers() {
        return this.bets.keySet().size();
    }

    @Override
    public void addPlayer(IPlayer newPlayer) {
        this.bets.put(newPlayer, 0);
    }

    @Override
    public boolean removePlayer(IPlayer player) {
        return this.bets.remove(player) != null;
    }

    @Override
    public ICard getNewCard() {
        return this.pullNewCard();
    }

    private ICard pullNewCard(){
        Collections.shuffle(this.decks);

        for (IDeck deck: this.decks) {
            if (deck.hasNextCard()){
                return deck.getNextCard();
            }
        }
        throw new IllegalStateException("No Cards left in the stack.");
    }

    public void placeBet(IPlayer player, Integer amount) {
        player.reduceMoney(amount);
        this.bets.put(player, this.bets.get(player) + amount);
    }

    public Integer getBet(IPlayer player) {
        return this.bets.get(player);
    }
}
