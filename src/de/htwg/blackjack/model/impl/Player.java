package de.htwg.blackjack.model.impl;

import de.htwg.blackjack.model.ICard;
import de.htwg.blackjack.model.IPlayer;
import de.htwg.blackjack.model.Status;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by ||USER|| on ||DATE||.
 */
public final class Player implements IPlayer {
    private final String name;
    private Status status;
    private int money;
    private final Map<ICard, Integer> cards;

    public Player(String name, Integer money) {
        this.name = name;
        this.money = money;
        this.cards = new LinkedHashMap<>();
        this.setStatus(Status.ENTERED);
    }

    @Override
    public Status getStatus() {
        return status;
    }

    @Override
    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Map<ICard, Integer> getCards() {
        return cards;
    }

    public String getCardsString() {
        StringBuilder sb = new StringBuilder();
        for (ICard card: this.cards.keySet()) {
            sb.append("[").append(card.toString()).append("] ");
        }
        return sb.toString();
    }
    @Override
    public int getCardsValue() {
        Integer value = 0;
        for (Integer pickedCardVal: this.cards.values()) {
            value += pickedCardVal;
        }
        return value;
    }

    @Override
    public void addNewCard(ICard card, Integer pickedValue) {
        this.cards.put(card, pickedValue);
    }


    @Override
    public int getAmountOfMoney() {
        return this.money;
    }

    @Override
    public int reduceMoney(int amount) {
        int moneyT = this.money - amount;
        if (moneyT < 0) {
            throw new IllegalArgumentException("Player doesn't have enough money!");
        }
        this.money = moneyT;
        return this.money;
    }

    public void addMoney(int amount) {
        this.money += amount;
    }
}
