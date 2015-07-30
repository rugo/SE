package de.htwg.blackjack.model.impl;

import de.htwg.blackjack.model.IRank;

import java.util.List;

/**
 * Created by ||USER|| on ||DATE||.
 */
public class Rank implements IRank {
    private final String name;
    private final List<Integer> values;
    private final char unicodeBlockPosition;

    public Rank(String name, List<Integer> values, char unicodeBlockPosition) {
        this.name = name;
        this.values = values;
        this.unicodeBlockPosition = unicodeBlockPosition;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public char getUnicodeBlockPosition() {
        return unicodeBlockPosition;
    }

    @Override
    public List<Integer> getValues() {
        return values;

    }

    @Override
    public String toString() {
        return this.name;
    }
}
