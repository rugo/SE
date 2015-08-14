package de.htwg.blackjack;

import de.htwg.blackjack.aview.tui.TextUI;

public class Blackjack {

    private Blackjack() {

    }

    public static void main(String[] args){
        TextUI ui = new TextUI();
        ui.loop();
    }

}

