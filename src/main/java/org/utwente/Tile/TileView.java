package org.utwente.Tile;

import org.utwente.Tile.view.HexButton;
import org.utwente.game.view.GameConfig;
import org.utwente.player.PlayerController;
import org.utwente.player.PlayerView;
import org.utwente.player.model.Player;
import org.utwente.util.event.EventManager;
import org.utwente.util.event.EventType;
import org.utwente.util.images.ImageRepository;

import java.awt.*;
import java.awt.image.*;
import java.util.Set;

import static org.utwente.game.view.GameConfig.TILE_SIZE;

public class TileView extends HexButton {
    public Tile tile;
    public boolean flatTop;

    public TileView(Tile tile, boolean flatTop) {
        this(tile, flatTop, false);
    }

    public TileView(Tile tile, boolean flatTop, boolean selected) {
        super(flatTop, tile, TILE_SIZE, selected);
        this.tile = tile;
        this.flatTop = flatTop;
        this.addActionListener(
                e -> EventManager.getInstance().notifying(EventType.ClickTile, new TileClickEvent(tile)));
    }

    private void drawCoordinates(Graphics2D g2d, Tile tile) {
        g2d.setColor(Color.WHITE);
        g2d.setFont(GameConfig.TILE_FONT);
        FontMetrics metrics = g2d.getFontMetrics();
        String text = tile.getQ() + ", " + tile.getR();
        int textX = hexagonRadius - metrics.stringWidth(text) / 2;
        int textY = hexagonRadius + metrics.getHeight() / 2 - metrics.getDescent() + hexagonRadius / 2;
        g2d.drawString(text, textX, textY);
    }

    @Override
    protected void setTileTexture(Graphics2D g2d) {
        BufferedImage tileImage = ImageRepository.getTileImageLoader().getImage(tile.getTileType(),
                tile.getPower());
        if (tileImage != null) {
            TexturePaint texturePaint = new TexturePaint(tileImage,
                    new Rectangle(0, 0, 2 * hexagonRadius, 2 * hexagonRadius));
            g2d.setPaint(texturePaint);
        } else {
            g2d.setColor(tile.getTileColor());
        }
    }

    private void drawPlayers(Graphics2D g2d, Set<Player> players) {
        if (!players.isEmpty()) {
            int playerXOffset = hexagonRadius - ((players.size() - 1) * 25) / 2;
            for (Player player : players) {
                PlayerController playerController = new PlayerController(player, new PlayerView());
                playerController.updateView(g2d, playerXOffset, hexagonRadius / 2);
                playerXOffset += 25;
            }
        }
    }

    private void drawCaveCoinCount(Graphics2D g2d, Tile tile) {
        if (tile.getTileType() == TileType.Cave) {
            g2d.setColor(Color.WHITE);
            g2d.setFont(GameConfig.TILE_FONT);
            FontMetrics metrics = g2d.getFontMetrics();
            String caveCoinCountText = String.valueOf(tile.getCaveCoinCount());
            int caveCoinCountTextX = (int) (hexagonRadius - hexagonRadius / 1.3);
            int caveCoinCountTextY = hexagonRadius / 2 + metrics.getAscent();
            g2d.drawString(caveCoinCountText, caveCoinCountTextX, caveCoinCountTextY);
        }
    }

    public static Point flatTopHexToPixel(int q, int r) {
        int x = (int) (TILE_SIZE * 3.0 / 2.0 * q);
        int y = (int) (TILE_SIZE * Math.sqrt(3) * (r + q / 2.0));
        return new Point(x, y);
    }

    public static Point pointyTopHexToPixel(int q, int r) {
        int x = (int) (TILE_SIZE * Math.sqrt(3) * (q + r / 2.0));
        int y = (int) (TILE_SIZE * 3.0 / 2.0 * r);
        return new Point(x, y);
    }

    public Point hexagonToPixel(boolean flatTop, Tile tile) {
        return flatTop ? flatTopHexToPixel(tile.getQ(), tile.getR()) : pointyTopHexToPixel(tile.getQ(), tile.getR());
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);
        drawCoordinates(g2d, tile);
        drawCaveCoinCount(g2d, tile);
        drawPlayers(g2d, tile.getPlayers());
    }
}