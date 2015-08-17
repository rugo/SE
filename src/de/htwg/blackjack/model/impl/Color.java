package de.htwg.blackjack.model.impl;

import de.htwg.blackjack.model.IColor;

/**
 * Created by ||USER|| on ||DATE||.
 */
public final class Color implements IColor{
    private final String name;
    private final String cardImageName;

    public Color(String name, String cardImageName) {
        this.name = name;
        this.cardImageName = cardImageName;
    }

    @Override
    public String getCardImageName() {
        return cardImageName;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
