package de.htwg.blackjack.aview.gui;

import com.google.inject.Inject;
import de.htwg.blackjack.controller.IBlackJackController;
import de.htwg.blackjack.controller.impl.TriBlackJackController;
import de.htwg.blackjack.util.observer.Event;
import de.htwg.blackjack.util.observer.IObserver;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.IOException;

/**
 * BlackJack
 * Created by ||USER|| on ||DATE||.
 */
public class BlackJackFrame extends JFrame implements IObserver{
    private transient IBlackJackController controller;
    private CardPanel dealerPanel;
    private CardPanel playerPanel;
    private CenterPanel centerPanel;
    private Color backgroundColor = new Color(0, 153, 0);
    private static final int DEF_USER_BET = 50;
    private static final Logger LOG = Logger.getLogger("de.htwg.blackjack.aview.gui.blackjackframe");

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
                        break;
                    case KeyEvent.VK_B:
                        this.controller.userBet(DEF_USER_BET);
                        break;
                    default:
                        this.controller.notifyObservers(new Event("Illegal key, use d(double), h(hit) or s(stand)"));
                }
            }
            return false;
        }
    }

    @Inject
    public BlackJackFrame(IBlackJackController controller) {
        this.controller = controller;
        controller.addObserver(this);
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new KeyDispatcher(controller));
        this.setBackground(backgroundColor);


        this.dealerPanel = new CardPanel("Dealer", BorderLayout.PAGE_START);
        this.playerPanel = new CardPanel("Player", BorderLayout.PAGE_END);
        this.dealerPanel.setBackground(backgroundColor);
        this.playerPanel.setBackground(backgroundColor);

        this.centerPanel = new CenterPanel(this.dealerPanel, this.playerPanel);
        this.centerPanel.setBackground(backgroundColor);
        BetPanel betPanel = new BetPanel(controller);
        betPanel.setBackground(backgroundColor);

        this.add(centerPanel, BorderLayout.CENTER);
        this.add(betPanel, BorderLayout.PAGE_END);

        this.pack();
        this.setVisible(true);
    }

    @Override
    public void update(Event e) {
        if (e != null) {
            this.centerPanel.setErrorMessage(e.getMessage());
        }
        try {
            this.dealerPanel.setImages(this.controller.getDealerCardImages());
            this.playerPanel.setImages(this.controller.getUserCardImages());
            this.dealerPanel.setCardValue(this.controller.getDealerValue());
            this.playerPanel.setCardValue(this.controller.getPlayerValue());
            this.playerPanel.setMoney(this.controller.getPlayerMoneyString());
        } catch (IOException ex) {
            LOG.error(ex);
        }
    }

    public static void main(String[] args) {
        BlackJackFrame f = new BlackJackFrame(new TriBlackJackController());
        f.setVisible(true);
    }
}
