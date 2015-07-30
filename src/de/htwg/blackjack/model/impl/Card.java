package de.htwg.blackjack.model.impl;

import de.htwg.blackjack.model.ICard;
import de.htwg.blackjack.model.IColor;
import de.htwg.blackjack.model.IRank;

import java.util.List;

/**
 * Created by ||USER|| on ||DATE||.
 */
public class Card implements ICard {
    private IColor color;
    private IRank rank;

    public Card(IColor color, IRank rank) {
        this.color = color;
        this.rank = rank;
    }
    @Override
    public IColor getColor() {
        return this.color;
    }

    @Override
    public IRank getRank() {
        return this.rank;
    }

    @Override
    public char getUnicodeSymbol() {
        return 0;
    }

    @Override
    public List<Integer> getValues() {
        return this.rank.getValues();
    }
}
