package de.htwg.blackjack.aview.gui;

import javax.swing.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * BlackJack
 * Created by ||USER|| on ||DATE||.
 * JPanel panel = new JPanel(new GridBagLayout());
 GridBagConstraints gbc = new GridBagConstraints();
 gbc.weightx = 1.0;
 gbc.fill = GridBagConstraints.BOTH;
 gbc.gridwidth = GridBagConstraints.REMAINDER;
 panel.add(new GraphicComponent(images), gbc);
 return panel;
 */
public class CardPanel extends JPanel {
    private CardDrawPanel drawPanel;
    private JLabel nameLabel;
    private String username;

    public CardPanel(String username, String labelPosition) {
        this.username = username;
        BorderLayout borderLayout = new BorderLayout();
        this.setLayout(borderLayout);

        this.nameLabel = new JLabel();
        this.setCardValue(0);
        nameLabel.setVerticalAlignment(JLabel.BOTTOM);
        nameLabel.setHorizontalAlignment(JLabel.CENTER);

        this.add(nameLabel, labelPosition);
        drawPanel = new CardDrawPanel();
        this.add(drawPanel, borderLayout.CENTER);
    }

    public void setCardValue(int value) {
        String msg = this.username + "s cards";
        if (value > 0) {
            msg += " (" + value + ")";
        }
        this.nameLabel.setText(msg);
    }

    protected void setImages(List<BufferedImage> images) {
        this.drawPanel.setImages(images);
        this.drawPanel.repaint();
    }
}
