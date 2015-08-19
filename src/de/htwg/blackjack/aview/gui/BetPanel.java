package de.htwg.blackjack.aview.gui;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * BlackJack
 * Created by ||USER|| on ||DATE||.
 */
public class BetPanel extends JPanel {
    private static final String chipsPath = "/home/gonzalez/IdeaProjects/BlackJack/ressources/chip_images/";
    private static final Integer[] chipVals = {5, 10, 25, 100};
    private static final String chipFileExt = "_res.png";

    public BetPanel() {
        GridLayout grid = new GridLayout(1, chipVals.length);

        List<JButton> chipButtons = new ArrayList<>();
        for (Integer i: chipVals) {
            JButton but = new JButton();
            but.setIcon(new ImageIcon(chipsPath + i + chipFileExt));
            but.setOpaque(false);
            but.setFocusPainted(false);
            but.setBorderPainted(false);
            but.setContentAreaFilled(false);
            setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0)); // Especially important
            this.add(but);
            chipButtons.add(but);
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(100, 100);
    }
}
