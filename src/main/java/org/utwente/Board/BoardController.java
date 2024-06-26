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

    public void updateView() {
        boardView.drawBoard(board);
    }
}