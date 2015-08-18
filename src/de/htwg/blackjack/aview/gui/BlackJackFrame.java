package de.htwg.blackjack.aview.gui;

import com.google.inject.Inject;
import de.htwg.blackjack.controller.IBlackJackController;
import de.htwg.blackjack.util.observer.Event;
import de.htwg.blackjack.util.observer.IObserver;

import javax.swing.*;

/**
 * BlackJack
 * Created by ||USER|| on ||DATE||.
 */
public class BlackJackFrame extends JFrame implements IObserver {
    private IBlackJackController controller;
    @Inject
    public BlackJackFrame(IBlackJackController controller) {
        this.controller = controller;
        controller.addObserver(this);
    }

    @Override
    public void update(Event e) {
        
    }
}
