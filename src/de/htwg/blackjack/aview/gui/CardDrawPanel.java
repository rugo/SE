package de.htwg.blackjack.aview.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

/**
 * BlackJack
 * Created by ||USER|| on ||DATE||.
 */
public class CardDrawPanel extends JPanel {
    private transient List<BufferedImage> images;

    public void setImages(List<BufferedImage> images) {
        this.images = images;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (images == null || images.isEmpty()) {
            return;
        }
        int w = getWidth();
        int h = getHeight();
        int iw = images.get(0).getWidth();
        int ih = images.get(0).getHeight();
        int overlap = iw/4;
        int x0 = (w - iw - (images.size()-1)*overlap)/2;
        int y = (h - ih)/2;
        for(int i = 0; i < images.size(); i++) {
            int x = x0 + i*overlap;
            g.drawImage(images.get(i), x, y, this);
        }
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(700, 400);
    }

}
