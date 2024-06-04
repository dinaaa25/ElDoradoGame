package org.utwente.Board.Blockade;

import java.awt.*;
import java.awt.geom.Line2D;

import static org.utwente.game.GameConfig.INFO_FONT;

public class BlockadeView {
    public void draw(Graphics2D g2d, Blockade blockade, int yOffset) {
        int padding = 20;
        int lineLength = 100;

        g2d.setColor(blockade.isRemoved() ? Color.WHITE : blockade.getBlockadeColor());
        g2d.setStroke(new BasicStroke(5));
        g2d.draw(new Line2D.Double(padding, padding + yOffset, padding + lineLength, padding + yOffset));

        g2d.setColor(Color.BLACK);
        g2d.setFont(INFO_FONT);
        g2d.drawString("Points: " + blockade.getPoints(), padding + lineLength + 10, padding + yOffset - 10);
        g2d.drawString("Power: " + blockade.getPower(), padding + lineLength + 10, padding + yOffset);
        g2d.drawString("Removed: " + blockade.isRemoved(), padding + lineLength + 10, padding + yOffset + 10);
        g2d.drawString("BlockadeType: " + blockade.getTileType(), padding + lineLength + 10, padding + yOffset + 20);
        g2d.drawString("SectionType: " + blockade.getSection1().getSectionType(), padding + lineLength + 10, padding + yOffset + 30);
    }
}
