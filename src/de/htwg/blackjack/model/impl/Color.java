package de.htwg.blackjack.model.impl;

import de.htwg.blackjack.model.IColor;

/**
 * Created by ||USER|| on ||DATE||.
 */
public final class Color implements IColor{
    private final String name;
    private final char unicodeBlockPosition;

    public Color(String name, char unicodeBlockPosition) {
        this.name = name;
        this.unicodeBlockPosition = unicodeBlockPosition;
    }

    @Override
    public char getUnicodeBlockPosition() {
        return this.unicodeBlockPosition;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
