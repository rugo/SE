package de.htwg.blackjack.model.impl;

import de.htwg.blackjack.model.IRank;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ||USER|| on ||DATE||.
 */
public final class Rank implements IRank {
    private final String name;
    private final List<Integer> values;
    private final int unicodeBlockPosition;

    public Rank(String name, int unicodeBlockPosition, Integer... values) {
        this.name = name;
        this.values = Arrays.asList(values);
        this.unicodeBlockPosition = unicodeBlockPosition;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getUnicodeBlockPosition() {
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
