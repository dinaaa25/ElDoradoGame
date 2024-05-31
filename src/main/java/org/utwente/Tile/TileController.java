package org.utwente.Tile;

import lombok.Getter;

import java.awt.*;
import java.awt.image.BufferedImage;

@Getter
public class TileController {
    private Tile tile;
    private TileView tileView;

    public TileController(Tile tile, TileView tileView) {
        this.tile = tile;
        this.tileView = tileView;
    }

    public void updateView(Graphics2D g2d, int x, int y, boolean flatTop, BufferedImage image) {
        tileView.drawTile(g2d, tile, x, y, flatTop, image);
    }
}
