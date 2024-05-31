package org.utwente.player;

import org.utwente.game.GameConfig;

import java.awt.*;

public class PlayerView {
    public void drawPlayer(Graphics2D g2d, Player player, int x, int y) {
        g2d.setColor(Color.WHITE);
        g2d.setFont(GameConfig.TILE_FONT);
        FontMetrics metrics = g2d.getFontMetrics();
        int playerTextX = x - metrics.stringWidth(player.getName()) / 2;
        int playerTextY = y + metrics.getHeight() / 2 - metrics.getDescent();
        g2d.drawString(player.getName(), playerTextX, playerTextY);
    }
}
