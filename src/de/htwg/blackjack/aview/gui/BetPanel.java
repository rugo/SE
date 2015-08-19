package de.htwg.blackjack.aview.gui;

import de.htwg.blackjack.Blackjack;
import de.htwg.blackjack.controller.IBlackJackController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * BlackJack
 * Created by ||USER|| on ||DATE||.
 */
public class BetPanel extends JPanel implements ActionListener {
    private static final String CHIPS_PATH = "/resources/chip_images/";
    private static final Integer[] CHIP_VALS = {5, 10, 25, 100};
    private static final String CHIP_FILE_EXT = "_res.png";
    private transient IBlackJackController controller;

    public BetPanel(IBlackJackController controller) {
        this.controller = controller;

        for (Integer i: CHIP_VALS) {
            JButton but = new JButton();
            but.setName(i.toString());
            but.setIcon(new ImageIcon(Blackjack.class.getResource(CHIPS_PATH + i + CHIP_FILE_EXT).getPath()));
            but.setOpaque(false);
            but.setFocusPainted(false);
            but.setBorderPainted(false);
            but.setContentAreaFilled(false);
            setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
            but.addActionListener(this);

            this.add(but);
        }
    }

    @Override
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
