package de.htwg.blackjack.model.impl;

import de.htwg.blackjack.model.IColor;

/**
 * Created by ||USER|| on ||DATE||.
 */
public final class Color implements IColor{
    private final String name;
    private final int unicodeBlockPosition;

    public Color(String name, int unicodeBlockPosition) {
        this.name = name;
        this.unicodeBlockPosition = unicodeBlockPosition;
    }

    @Override
    public int getUnicodeBlockPosition() {
        return this.unicodeBlockPosition;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
