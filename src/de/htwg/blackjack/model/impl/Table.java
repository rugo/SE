package de.htwg.blackjack.model.impl;

import de.htwg.blackjack.model.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by ||USER|| on ||DATE||.
 */
// TODO: Check parameters
public class Table implements ITable {
    private List<IDeck> decks;
    private List<IPlayer> players;


    public Table(int amountOfDecks, List<IRank> ranks, List<IColor> colors) {
        this.decks = new ArrayList<>();
        this.players = new ArrayList<>();

        for (int i = 0; i < amountOfDecks; i++){
            this.decks.add(new Deck(ranks, colors));
        }
    }

    @Override
    public int getAmountOfDecks() {
        return this.decks.size();
    }

    @Override
    public void addPlayer(IPlayer newPlayer) {
        this.players.add(newPlayer);
    }

    @Override
    public boolean removePlayer(IPlayer player) {
        return this.players.remove(player);
    }

    @Override
    public void hit(IPlayer player) {
        player.setStatus(Status.HIT);
    }

    private ICard pullNewCard(){
        Collections.shuffle(this.decks);
        return this.decks.get(0).getNextCard();
    }

    @Override
    public void stand(IPlayer player) {
        player.setStatus(Status.STAND);
        player.addNewCard(this.pullNewCard());
    }
}
