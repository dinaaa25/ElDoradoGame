package org.utwente.Tile;

import org.utwente.game.GameConfig;
import org.utwente.player.Player;
import org.utwente.player.PlayerController;
import org.utwente.player.PlayerView;

import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.util.Set;

import static org.utwente.game.GameConfig.HEX_SIZE;


public class TileView {
    private Path2D.Double createHexagon(boolean flatTop, int x, int y) {
        Path2D.Double hexagon = new Path2D.Double();
        for (int i = 0; i < 6; i++) {
            double angle = flatTop ? Math.PI / 3 * i : 2 * Math.PI / 6 * (i + 0.5);
            int dx = (int) (x + HEX_SIZE * Math.cos(angle));
            int dy = (int) (y + HEX_SIZE * Math.sin(angle));
            if (i == 0) {
                hexagon.moveTo(dx, dy);
            } else {
                hexagon.lineTo(dx, dy);
            }
        }
        hexagon.closePath();
        return hexagon;
    }

    private void drawHexagon(Path2D.Double hexagon, Graphics2D g2d) {
        g2d.fill(hexagon);
        g2d.setColor(Color.BLACK);
        g2d.draw(hexagon);
    }

    private void drawCoordinates(Graphics2D g2d, int x, int y, Tile tile) {
        g2d.setColor(Color.WHITE);
        g2d.setFont(GameConfig.TILE_FONT);
        FontMetrics metrics = g2d.getFontMetrics();
        String text = tile.getQ() + ", " + tile.getR();
        int textX = x - metrics.stringWidth(text) / 2;
        int textY = y + metrics.getHeight() / 2 - metrics.getDescent() + HEX_SIZE / 2;
        g2d.drawString(text, textX, textY);
    }

    private void drawPower(Graphics2D g2d, int x, int y, Tile tile) {
        g2d.setColor(Color.WHITE);
        g2d.setFont(GameConfig.TILE_FONT);
        FontMetrics metrics = g2d.getFontMetrics();
        String powerText = String.valueOf(tile.getPower());
        int powerTextX = (int) (x - HEX_SIZE / 1.3);
        int powerTextY = y - HEX_SIZE / 2 + metrics.getAscent();
        g2d.drawString(powerText, powerTextX, powerTextY);
    }

    private void setTileTexture(Graphics2D g2d, int x, int y, Tile tile, BufferedImage image) {
        if (tile.getTileType() == TileType.Machete && image != null) {
            TexturePaint texturePaint = new TexturePaint(image, new Rectangle(x - HEX_SIZE, y - HEX_SIZE, 2 * HEX_SIZE, 2 * HEX_SIZE));
            g2d.setPaint(texturePaint);
        } else {
            g2d.setColor(tile.getTileColor());
        }
    }

    private void drawPlayers(Graphics2D g2d, Set<Player> players, int x, int y) {
        if (!players.isEmpty()) {
            int playerYOffset = y - HEX_SIZE / 2;
            FontMetrics metrics = g2d.getFontMetrics();
            for (Player player : players) {
                PlayerController playerController = new PlayerController(player, new PlayerView());
                playerController.updateView(g2d, x, playerYOffset + metrics.getHeight() / 2 - metrics.getDescent());
                playerYOffset += metrics.getHeight();
            }
        }
    }

    public void drawTile(Graphics2D g2d, Tile tile, int x, int y, boolean flatTop, BufferedImage image) {
        Path2D.Double hexagon = createHexagon(flatTop, x, y);
        setTileTexture(g2d, x, y, tile, image);
        drawHexagon(hexagon, g2d);
        drawCoordinates(g2d, x, y, tile);
        drawPower(g2d, x, y, tile);
        drawPlayers(g2d, tile.getPlayers(), x, y);
    }
}
