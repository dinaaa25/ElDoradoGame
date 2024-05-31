package org.utwente.game;

import lombok.Getter;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameController {
    @Getter
    private final Game game;
    private final GameView gameView;

    public GameController(Game game, GameView gameView) {
        this.game = game;
        this.gameView = gameView;
    }

    public void updateView(Graphics2D g2d, BufferedImage macheteImage) {
        gameView.drawGame(g2d, game, macheteImage);
    }
}