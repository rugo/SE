package de.htwg.blackjack.aview.gui;

import javax.swing.*;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;


/**
 * BlackJack
 * Created by ||USER|| on ||DATE||.
 */
public class CenterPanel extends JPanel {
    private JLabel errorLabel;
    private List<String> lastMessages;
    private static final int NUM_MSG_SHOWN = 3;

    public CenterPanel(CardPanel dealerPanel, CardPanel playerPanel) {
        BoxLayout boxLayout = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(boxLayout);
        this.lastMessages = new ArrayList<>();

        this.errorLabel = new JLabel("", SwingConstants.CENTER);
        this.errorLabel.setAlignmentX(this.CENTER_ALIGNMENT);

        this.add(dealerPanel);
        this.add(this.errorLabel);
        this.add(playerPanel);
    }

    public void setErrorMessage(String message) {
        this.lastMessages.add(message);
        ListIterator li = lastMessages.listIterator(lastMessages.size());
        int i = 0;
        String errorMessage = "<html><center>";
        while(li.hasPrevious() && i < NUM_MSG_SHOWN) {
            int fontfactor = NUM_MSG_SHOWN - i;
            errorMessage += "<font size='" + fontfactor + "'>" + li.previous() + "</font><br/>";
            i++;
        }
        errorMessage += "</center></html>";
        this.errorLabel.setText(errorMessage);
    }
}
