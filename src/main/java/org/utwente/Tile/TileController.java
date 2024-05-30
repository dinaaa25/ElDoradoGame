package org.utwente.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TileController {
    private Tile tile;
    private TileView tileView;

    public TileController(Tile tile, TileView tileView) {
        this.tile = tile;
        this.tileView = tileView;
    }

    public void updateView(Graphics2D g2d, int x, int y, int hexSize, boolean flatTop, BufferedImage image) {
        tileView.drawTile(g2d, tile, x, y, hexSize, flatTop, image);
    }
}
