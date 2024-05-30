package org.utwente.player;

import java.awt.*;

public class PlayerController {
    private final Player player;
    private final PlayerView playerView;

    public PlayerController(Player player, PlayerView playerView) {
        this.player = player;
        this.playerView = playerView;
    }

    public void updateView(Graphics2D g2d, int x, int y) {
        playerView.drawPlayer(g2d, player, x, y);
    }
}