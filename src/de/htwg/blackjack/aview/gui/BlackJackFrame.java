package de.htwg.blackjack.aview.gui;

import com.google.inject.Inject;
import de.htwg.blackjack.controller.IBlackJackController;
import de.htwg.blackjack.controller.impl.TriBlackJackController;
import de.htwg.blackjack.util.observer.Event;
import de.htwg.blackjack.util.observer.IObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

/**
 * BlackJack
 * Created by ||USER|| on ||DATE||.
 */
public class BlackJackFrame extends JFrame implements IObserver, KeyListener {
    private IBlackJackController controller;
    private CardPanel dealerPanel;
    private CardPanel playerPanel;
    private CenterPanel centerPanel;

    private class KeyDispatcher implements KeyEventDispatcher {
        IBlackJackController controller;

        public KeyDispatcher(IBlackJackController controller) {
            this.controller = controller;
        }

        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
            if (e.getID() == KeyEvent.KEY_PRESSED) {
                switch (e.getKeyCode()){
                    case KeyEvent.VK_H:
                        this.controller.userHit();
                        break;
                    case KeyEvent.VK_D:
                        this.controller.userDouble();
                        break;
                    case KeyEvent.VK_S:
                        this.controller.userStand();
                }
            }
            return false;
        }
    }

    @Inject
    public BlackJackFrame(IBlackJackController controller) {
        this.controller = controller;
        controller.addObserver(this);
        BorderLayout borderLayout = new BorderLayout();
        this.addKeyListener(this);  // I'm my own key listener, bitch!
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new KeyDispatcher(controller));


        this.dealerPanel = new CardPanel("Dealer", BorderLayout.PAGE_START);
        this.playerPanel = new CardPanel("Player", BorderLayout.PAGE_END);

        this.centerPanel = new CenterPanel(this.dealerPanel, this.playerPanel);
        BetPanel betPanel = new BetPanel();

        this.add(centerPanel, borderLayout.CENTER);
        this.add(betPanel, borderLayout.PAGE_END);

        this.pack();
        this.setVisible(true);
    }

    @Override
    public void update(Event e) {
        if (e != null) {
            this.centerPanel.setErrorMessage(e.getMessage());
        } else {
            this.centerPanel.clearErrorMessage();
            try {
                this.dealerPanel.setImages(this.controller.getDealerCardImages());
                this.playerPanel.setImages(this.controller.getUserCardImages());
                this.dealerPanel.setCardValue(this.controller.getDealerValue());
                this.playerPanel.setCardValue(this.controller.getPlayerValue());
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        BlackJackFrame f = new BlackJackFrame(new TriBlackJackController());
        f.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {
        switch (keyEvent.getKeyCode()){
            case KeyEvent.VK_H:
                this.controller.userHit();
                break;
            case KeyEvent.VK_D:
                this.controller.userDouble();
                break;
            case KeyEvent.VK_S:
                this.controller.userStand();
        }
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
