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
    private static final String fileDelimiter = "_of_";
    private static final String fileExtension = ".png";
    private static final String imageBasePath = "/home/gonzalez/IdeaProjects/BlackJack/ressources/card_images_res/";

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
        return imageBasePath + this.rank.getCardImageName() + fileDelimiter + this.color.getCardImageName() +
                fileExtension;
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
