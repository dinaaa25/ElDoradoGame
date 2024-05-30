package org.utwente.Board;

import lombok.Getter;

import java.awt.*;

@Getter
public class BoardController {
    private Board board;
    private BoardView boardView;

    public BoardController(Board board, BoardView boardView) {
        this.board = board;
        this.boardView = boardView;
    }

    public void updateView(Graphics2D g2d) {
        boardView.drawBoard(g2d, board);
    }
}
