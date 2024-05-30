package org.utwente.game;

import java.awt.*;

public class GameController {
    private Game game;
    private GameView gameView;

    public GameController(Game game, GameView gameView) {
        this.game = game;
        this.gameView = gameView;
    }

    public void updateView(Graphics2D g2d) {
        gameView.drawGame(g2d, game);
    }
}
