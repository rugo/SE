package de.htwg.blackjack.model;

/**
 * Created by ||USER|| on ||DATE||.
 */
public enum Status {
    HIT("hit"), // player hits
    STAND("stand"), // player stands
    BUSTED("busted"), // player is busted
    READY("ready to play"), // player is ready to play
    BETTED("bet"), // player has placed a bet
    ENTERED("enter"), // Player just entered the game
    DOUBLE("double");  // player doubled

    String name;
    Status(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
