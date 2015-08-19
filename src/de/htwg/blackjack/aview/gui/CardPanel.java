package de.htwg.blackjack.aview.gui;

import javax.swing.*;

import java.awt.GridBagConstraints;
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

    public CardPanel() {

        GridBagConstraints gridBack = new GridBagConstraints();
        gridBack.weightx = 1.;
        gridBack.fill = GridBagConstraints.BOTH;
        gridBack.gridwidth = GridBagConstraints.REMAINDER;

        drawPanel = new CardDrawPanel();
        this.add(drawPanel);
    }

    protected void setImages(List<BufferedImage> images) {
        this.drawPanel.setImages(images);
        this.drawPanel.repaint();
    }
}
