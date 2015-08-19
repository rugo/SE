package de.htwg.blackjack.aview.gui;

import com.google.inject.Inject;
import de.htwg.blackjack.controller.IBlackJackController;
import de.htwg.blackjack.controller.impl.TriBlackJackController;
import de.htwg.blackjack.util.observer.Event;
import de.htwg.blackjack.util.observer.IObserver;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * BlackJack
 * Created by ||USER|| on ||DATE||.
 */
public class BlackJackFrame extends JFrame implements IObserver {
    private IBlackJackController controller;
    private CardPanel dealerPanel;
    private CardPanel playerPanel;
    // TODO:
    // http://openbook.rheinwerk-verlag.de/javainsel9/javainsel_19_005.htm#mjf840b5b26b3d17378f356327ff528f45
    // For betting

    @Inject
    public BlackJackFrame(IBlackJackController controller) {
        this.controller = controller;
        controller.addObserver(this);

        GridLayout grid = new GridLayout(2, 1);
        this.setLayout(grid);

        this.dealerPanel = new CardPanel();
        this.playerPanel = new CardPanel(); // TODO: Display players Name

        this.add(this.dealerPanel);
        this.add(this.playerPanel);

        this.pack();
        this.setVisible(true);
    }

    @Override
    public void update(Event e) {
        if (e != null) {
            // Print message
        } else {
            try {
                this.dealerPanel.setImages(this.controller.getDealerCardImages());
                this.playerPanel.setImages(this.controller.getUserCardImages());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        BlackJackFrame f = new BlackJackFrame(new TriBlackJackController());
        f.setVisible(true);
    }
}
