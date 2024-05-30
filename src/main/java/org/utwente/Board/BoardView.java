package org.utwente.Board;

import java.awt.*;

public class BoardView {
    public void drawBoard(Graphics2D g2d, Board board) {
        BoardController boardController = new BoardController(board, new BoardView());
        boardController.getBoardView().drawBoard(g2d, board);
    }
}
