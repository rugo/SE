package de.htwg.blackjack.model.impl;

import de.htwg.blackjack.Blackjack;
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
    private static final String FILE_DELIMITER = "_of_";
    private static final String FILE_EXTENSION = ".png";
    private static final String IMAGE_BASE_PATH = "/resources/card_images_res/";

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
    public String getImagePath() {
        return Blackjack.class.getResource(
                IMAGE_BASE_PATH + this.rank.getCardImageName() + FILE_DELIMITER + this.color.getCardImageName() +
                        FILE_EXTENSION
        ).getPath();
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
