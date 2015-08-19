package de.htwg.blackjack;

import com.google.inject.Guice;
import com.google.inject.Injector;
import de.htwg.blackjack.aview.gui.BlackJackFrame;
import de.htwg.blackjack.aview.tui.TextUI;


import de.htwg.blackjack.controller.IBlackJackController;
import org.apache.log4j.PropertyConfigurator;

public class Blackjack {

    private static IBlackJackController controller;

    private Blackjack() {

    }

    public static void main(String[] args){
        // Set up logging through log4j
        PropertyConfigurator.configure("log4j.properties");
        Injector inject = Guice.createInjector(new BlackJackModule());

        controller = inject.getInstance(IBlackJackController.class);
        TextUI tui = inject.getInstance(TextUI.class);
        @SuppressWarnings("unused")
        BlackJackFrame gui = inject.getInstance(BlackJackFrame.class);

        controller.createGame("Hans", 5000);

        tui.loop();
    }

}

