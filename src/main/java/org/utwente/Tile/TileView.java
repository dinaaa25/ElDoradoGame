package org.utwente.Tile;

import org.utwente.game.GameConfig;
import org.utwente.player.Player;
import org.utwente.player.PlayerController;
import org.utwente.player.PlayerView;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Set;

import static org.utwente.game.GameConfig.HEX_SIZE;


public class TileView {
    private BufferedImage macheteImage;

    private void loadImages() {
        try {
            macheteImage = ImageIO.read(getClass().getResource("/images/machete-bg.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawTile(Graphics2D g2d, Tile tile, int x, int y, int hexSize, boolean flatTop) {
        Path2D hexagon = new Path2D.Double();
        Point[] vertices = new Point[6];
        for (int i = 0; i < 6; i++) {
            double angle = flatTop ? Math.PI / 3 * i : 2 * Math.PI / 6 * (i + 0.5);
            int dx = (int) (x + HEX_SIZE * Math.cos(angle));
            int dy = (int) (y + HEX_SIZE * Math.sin(angle));
            vertices[i] = new Point(dx, dy);
            if (i == 0) {
                hexagon.moveTo(dx, dy);
            } else {
                hexagon.lineTo(dx, dy);
            }
        }
        hexagon.closePath();

        if (tile.getTileType() == TileType.Machete && macheteImage != null) {
            TexturePaint texturePaint = new TexturePaint(macheteImage, new Rectangle(x - HEX_SIZE, y - HEX_SIZE, 2 * HEX_SIZE, 2 * HEX_SIZE));
            g2d.setPaint(texturePaint);
        } else {
            g2d.setColor(tile.getTileColor());
        }
        g2d.fill(hexagon);

        g2d.setColor(Color.BLACK);
        g2d.draw(hexagon);

        // Draw the coordinates
        g2d.setColor(Color.WHITE);
        g2d.setFont(GameConfig.TILE_FONT);
        FontMetrics metrics = g2d.getFontMetrics();
        String text = tile.getQ() + ", " + tile.getR();
        int textX = x - metrics.stringWidth(text) / 2;
        int textY = y + metrics.getHeight() / 2 - metrics.getDescent() + HEX_SIZE / 2;
        g2d.drawString(text, textX, textY);

        // Draw the power
        g2d.setColor(Color.WHITE);
        g2d.setFont(GameConfig.TILE_FONT);
        metrics = g2d.getFontMetrics();
        String powerText = String.valueOf(tile.getPower());
        int powerTextX = (int) (x - HEX_SIZE / 1.3);
        int powerTextY = y - HEX_SIZE / 2 + metrics.getAscent();
        g2d.drawString(powerText, powerTextX, powerTextY);
        drawPlayers(g2d, tile.getPlayers(), x, y, hexSize);
    }

    private void drawPlayers(Graphics2D g2d, Set<Player> players, int x, int y, int hexSize) {
        if (!players.isEmpty()) {
            int playerYOffset = y - hexSize / 2;
            FontMetrics metrics = g2d.getFontMetrics();
            for (Player player : players) {
                PlayerController playerController = new PlayerController(player, new PlayerView());
                playerController.updateView(g2d, x, playerYOffset + metrics.getHeight() / 2 - metrics.getDescent());
                playerYOffset += metrics.getHeight(); // Move down for the next player
            }
        }
    }
}
