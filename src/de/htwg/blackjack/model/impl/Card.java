package de.htwg.blackjack.model.impl;

import de.htwg.blackjack.model.ICard;
import de.htwg.blackjack.model.IColor;
import de.htwg.blackjack.model.IRank;

import java.util.List;

/**
 * Created by ||USER|| on ||DATE||.
 */
public final class Card implements ICard {
    private final IColor color;
    private final IRank rank;
    private static final int UNICODE_CARD_BASE = 0x1F000;

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
        return (char) (this.UNICODE_CARD_BASE +
                this.getColor().getUnicodeBlockPosition() +
                this.getRank().getUnicodeBlockPosition());
    }

    @Override
    public List<Integer> getValues() {
        return this.rank.getValues();
    }

    @Override
    public String toString() {
        return this.getColor() + " " + this.getRank();
    }
}
