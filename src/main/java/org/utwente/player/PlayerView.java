package org.utwente.player;

import org.utwente.game.view.GameConfig;
import org.utwente.player.model.Player;

import java.awt.*;
import java.awt.image.BufferedImage;

public class PlayerView {
    public void drawPlayer(Graphics2D g2d, Player player, int x, int y) {
        g2d.setColor(Color.WHITE);
        g2d.setFont(GameConfig.TILE_FONT);
        FontMetrics metrics = g2d.getFontMetrics();
        int playerTextX = x - metrics.stringWidth(player.getName()) / 2;
        int playerTextY = y + metrics.getHeight() / 2 - metrics.getDescent();
        BufferedImage image = new PlayerImageLoader().loadPlayerImages().getPlayerImage(player.getColor());
        g2d.drawImage(image, playerTextX, playerTextY, 27, 45, null);
    }
}
