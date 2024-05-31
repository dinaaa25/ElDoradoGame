package org.utwente.Board.Blockade;

import java.awt.*;
import java.awt.geom.Line2D;

public class BlockadeView {
    public void draw(Graphics2D g2d, Point[] vertices, boolean isRemoved) {
        if (!isRemoved) {
            g2d.setColor(Color.RED);
            g2d.setStroke(new BasicStroke(5));
            for (int i = 0; i < vertices.length - 1; i++) {
                g2d.draw(new Line2D.Double(vertices[i].x, vertices[i].y, vertices[i + 1].x, vertices[i + 1].y));
            }
        }
    }
}
