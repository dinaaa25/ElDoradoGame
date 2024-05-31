package org.utwente.Board;

import lombok.Getter;

import java.awt.*;
import java.awt.image.BufferedImage;

@Getter
public class BoardController {
    private final Board board;
    private final BoardView boardView;

    public BoardController(Board board, BoardView boardView) {
        this.board = board;
        this.boardView = boardView;
    }

    public void updateView(Graphics2D g2d, int offsetX, int offsetY, int hexSize, boolean flatTop, BufferedImage image) {
        boardView.drawBoard(g2d, board, offsetX, offsetY, hexSize, flatTop, image);
    }
}