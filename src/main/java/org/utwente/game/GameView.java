package org.utwente.game;

import org.utwente.Board.BoardController;
import org.utwente.Board.BoardView;

import java.awt.*;

public class GameView {
    public void drawGame(Graphics2D g2d, Game game) {
        BoardController boardController = new BoardController(game.getBoard(), new BoardView());
        boardController.getBoard().updateView();
    }
}
