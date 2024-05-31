package org.utwente.Section;

import org.utwente.Tile.Tile;
import org.utwente.Tile.TileController;
import org.utwente.Tile.TileView;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

import static org.utwente.game.GameConfig.HEX_SIZE;

public class SectionView {
    public void drawSection(Graphics2D g2d, Section section, int offsetX, int offsetY, int hexSize, boolean flatTop, BufferedImage image) {
        List<Tile> tiles = section.getTiles();
        for (Tile tile : tiles) {
            TileController tileController = new TileController(tile, new TileView());
            Point tilePosition = flatTop ? flatTopHexToPixel(tile.getQ(), tile.getR()) : pointyTopHexToPixel(tile.getQ(), tile.getR());
            tileController.updateView(g2d, tilePosition.x + offsetX, tilePosition.y + offsetY, hexSize, flatTop, image);
        }
    }

    public Point flatTopHexToPixel(int q, int r) {
        int x = (int) (HEX_SIZE * 3.0 / 2.0 * q);
        int y = (int) (HEX_SIZE * Math.sqrt(3) * (r + q / 2.0));
        return new Point(x, y);
    }

    public Point pointyTopHexToPixel(int q, int r) {
        int x = (int) (HEX_SIZE * Math.sqrt(3) * (q + r / 2.0));
        int y = (int) (HEX_SIZE * 3.0 / 2.0 * r);
        return new Point(x, y);
    }
}