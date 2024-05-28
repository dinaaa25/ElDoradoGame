package org.utwente.Board;

import lombok.Getter;
import lombok.Setter;
import org.utwente.Tile.TileType;

import java.awt.*;
import java.awt.geom.Line2D;

@Getter
@Setter
public class Blockade {
    private TileType tileType;
    private int power;
    private Point start;
    private Point end;
    private boolean isRemoved;

    public Blockade(TileType tileType, Point start, Point end, int power) {
        this.tileType = tileType;
        this.start = start;
        this.end = end;
        this.power = power;
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    public boolean isRemoved() {
        return isRemoved;
    }

    public int getPower() {
        return this.power;
    }

    public void remove() {
        isRemoved = true;
    }

    public void draw(Graphics2D g2d, Point[] vertices) {
        if (!isRemoved) {
            g2d.setColor(Color.RED);
            g2d.setStroke(new BasicStroke(5));
            for (int i = 0; i < vertices.length - 1; i++) {
                g2d.draw(new Line2D.Double(vertices[i].x, vertices[i].y, vertices[i + 1].x, vertices[i + 1].y));
            }
        }
    }


}


