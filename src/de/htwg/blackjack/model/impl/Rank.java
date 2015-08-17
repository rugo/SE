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
    private final String cardImageName;

    public Rank(String name, String cardImageName, Integer... values) {
        this.name = name;
        this.cardImageName = cardImageName;
        this.values = Arrays.asList(values);
    }

    @Override
    public String getName() {
        return name;
    }

    public String getCardImageName() {
        return cardImageName;
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
