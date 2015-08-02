package de.htwg.blackjack.model.impl;

import de.htwg.blackjack.model.*;

import java.util.*;

/**
 * Created by ||USER|| on ||DATE||.
 */
// TODO: Check parameters
public final class Table implements ITable {
    private final List<IDeck> decks;
    private final Map<IPlayer, Integer> bets; // todo: make map only


    public  Table(int amountOfDecks, List<IPlayer> players, List<IRank> ranks, List<IColor> colors) {
        this.decks = new ArrayList<>();
        this.bets = new TreeMap<>();

        this.addPlayers(players);
        this.initDecks(amountOfDecks, ranks, colors);
    }

    @Override
    public void initDecks(int amount, List<IRank> ranks, List<IColor> colors) {
        for (int i = 0; i < amount; i++){
            this.decks.add(new Deck(ranks, colors));
        }
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
