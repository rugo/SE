package de.htwg.blackjack.model.impl;

import de.htwg.blackjack.model.ICard;
import de.htwg.blackjack.model.IColor;
import de.htwg.blackjack.model.IDeck;
import de.htwg.blackjack.model.IRank;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by ||USER|| on ||DATE||.
 */
public final class Deck implements IDeck {
    private final List<ICard> cards;

    public Deck(List<IRank> ranks, List<IColor> colors){
        this.cards = new LinkedList<>();
        for (IColor color: colors) {
            for (IRank rank: ranks) {
                this.cards.add(new Card(color, rank));
            }
        }
    }

    private ICard popCard() {
        ICard card = this.cards.get(0);
        this.cards.remove(0);
        return card;
    }

    @Override
    public void shuffle() {
        Collections.shuffle(this.cards);
    }

    @Override
    public ICard getNextCard() {
        return this.popCard();
    }

    @Override
    public boolean hasNextCard() {
        return !this.cards.isEmpty();
    }

    @Override
    public int cardsLeft() {
        return this.cards.size();
    }

    @Override
    public String toString() {
        return "Deck with " + this.cards.size() + " left.";
    }
}
