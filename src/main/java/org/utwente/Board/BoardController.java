package org.utwente.Board;

import lombok.Getter;
import org.utwente.Tile.TileImageLoader;

import java.awt.*;

@Getter
public class BoardController {
    private final Board board;
    private final BoardView boardView;

    public BoardController(Board board, BoardView boardView) {
        this.board = board;
        this.boardView = boardView;
    }

    public void updateView(Graphics2D g2d, int offsetX, int offsetY, boolean flatTop, TileImageLoader tileImageLoader) {
        boardView.drawBoard(g2d, board, offsetX, offsetY, flatTop, tileImageLoader);
    }
}