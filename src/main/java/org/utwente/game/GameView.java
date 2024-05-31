package org.utwente.game;

import org.utwente.Board.BoardController;
import org.utwente.Board.BoardView;

import java.awt.*;
import java.awt.image.BufferedImage;

public class GameView {
    public void drawGame(Graphics2D g2d, int offsetX, int offsetY, Game game, BufferedImage macheteImage) {
        BoardController boardController = new BoardController(game.getBoard(), new BoardView());
        boardController.updateView(g2d, offsetX, offsetY, game.getBoard().isFlatTop(), macheteImage);
    }
}