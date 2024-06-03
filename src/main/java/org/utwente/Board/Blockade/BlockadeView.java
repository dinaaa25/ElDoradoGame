package org.utwente.Board.Blockade;

import java.awt.*;
import java.awt.geom.Line2D;

public class BlockadeView {
    public void draw(Graphics2D g2d, Blockade blockade, int yOffset) {
        int padding = 20;
        int lineLength = 100;

        g2d.setColor(blockade.isRemoved() ? Color.GRAY : Color.RED);
        g2d.setStroke(new BasicStroke(5));
        g2d.draw(new Line2D.Double(padding, padding + yOffset, padding + lineLength, padding + yOffset));

        g2d.setColor(Color.BLACK);
        g2d.setFont(new Font("Arial", Font.BOLD, 12));
        g2d.drawString("Points: " + blockade.getPoints(), padding + lineLength + 10, padding + yOffset - 10);
        g2d.drawString("Power: " + blockade.getPower(), padding + lineLength + 10, padding + yOffset);
        g2d.drawString("Removed: " + blockade.isRemoved(), padding + lineLength + 10, padding + yOffset + 10);
        g2d.drawString("BlockadeType: " + blockade.getTileType(), padding + lineLength + 10, padding + yOffset + 20);
        g2d.drawString("SectionType: " + blockade.getSection1().getSectionType(), padding + lineLength + 10, padding + yOffset + 30);
    }
}
