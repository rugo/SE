package de.htwg.blackjack;

import de.htwg.blackjack.aview.tui.TextUI;

import org.apache.log4j.PropertyConfigurator;

public class Blackjack {

    private Blackjack() {

    }

    public static void main(String[] args){
        // Set up logging through log4j
        PropertyConfigurator.configure("log4j.properties");
        TextUI ui = new TextUI();
        ui.loop();
    }

}

