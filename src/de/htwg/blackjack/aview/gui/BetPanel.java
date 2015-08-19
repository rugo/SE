package de.htwg.blackjack.aview.gui;

import de.htwg.blackjack.Blackjack;
import de.htwg.blackjack.controller.IBlackJackController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * BlackJack
 * Created by ||USER|| on ||DATE||.
 */
public class BetPanel extends JPanel implements ActionListener {
    private static final String chipsPath = "/resources/chip_images/";
    private static final Integer[] chipVals = {5, 10, 25, 100};
    private static final String chipFileExt = "_res.png";
    private final IBlackJackController controller;

    public BetPanel(IBlackJackController controller) {
        this.controller = controller;
        this.setBackground(Color.GREEN);
        GridLayout grid = new GridLayout(1, chipVals.length);

        for (Integer i: chipVals) {
            JButton but = new JButton();
            but.setName(i.toString());
            but.setIcon(new ImageIcon(Blackjack.class.getResource(chipsPath + i + chipFileExt).getPath()));
            but.setOpaque(false);
            but.setFocusPainted(false);
            but.setBorderPainted(false);
            but.setContentAreaFilled(false);
            setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            but.addActionListener(this);

            this.add(but);
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(100, 100);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        this.controller.userBet(
                Integer.valueOf(
                        (
                                (JButton) actionEvent.getSource()
                        ).getName()
                )
        );
    }
}
