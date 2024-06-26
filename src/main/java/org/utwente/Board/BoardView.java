package org.utwente.Board;

import org.utwente.Board.Blockade.Blockade;
import org.utwente.Section.Section;
import org.utwente.Tile.Tile;
import org.utwente.Tile.TileView;
import org.utwente.game.view.GameConfig;
import org.utwente.game.model.Phase;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static org.utwente.game.view.GameConfig.TILE_SIZE;
import static org.utwente.game.view.GameConfig.PADDING;

public class BoardView extends JPanel {
    public Board board;
    public Phase phase;

    public BoardView(Board board) {
        this(board, null);
    }

    public BoardView(Board board, Phase phase) {
        this.board = board;
        this.phase = phase;
        this.setLayout(null);
        this.drawBoard(board);
        Dimension preferredSize = calculatePreferredSize(board);
        this.setPreferredSize(preferredSize);
    }


    public Dimension calculatePreferredSize(Board board) {
        if (board.getSections().isEmpty() || board.getSections().get(0).getTiles().isEmpty()) {
            return new Dimension(GameConfig.DEFAULT_BOARD_SIZE_X, GameConfig.DEFAULT_BOARD_SIZE_Y);
        }
        int minQ = Integer.MAX_VALUE, maxQ = Integer.MIN_VALUE;
        int minR = Integer.MAX_VALUE, maxR = Integer.MIN_VALUE;

        for (Section section : board.getSections()) {
            for (Tile tile : section.getTiles()) {
                int q = tile.getQ();
                int r = tile.getR();
                if (q < minQ) minQ = q;
                if (q > maxQ) maxQ = q;
                if (r < minR) minR = r;
                if (r > maxR) maxR = r;
            }
        }

        Point minPoint = board.isFlatTop() ? TileView.flatTopHexToPixel(minQ, minR) : TileView.pointyTopHexToPixel(minQ, minR);
        Point maxPoint = board.isFlatTop() ? TileView.flatTopHexToPixel(maxQ, maxR) : TileView.pointyTopHexToPixel(maxQ, maxR);

        int panelWidth = maxPoint.x - minPoint.x + 2 * PADDING + TILE_SIZE;
        int panelHeight = maxPoint.y - minPoint.y + 2 * PADDING + TILE_SIZE;

        return new Dimension(panelWidth, panelHeight);
    }

    public Point calculateOffsets(Board board) {
        List<Section> sections = board.getSections();
        if (sections.isEmpty() || sections.get(0).getTiles().isEmpty()) {
            return new Point(PADDING, PADDING);
        }
        int minQ = Integer.MAX_VALUE, minR = Integer.MAX_VALUE;

        for (Section section : sections) {
            for (Tile tile : section.getTiles()) {
                int q = tile.getQ();
                int r = tile.getR();
                if (q < minQ) minQ = q;
                if (r < minR) minR = r;
            }
        }

        Point minPoint = board.isFlatTop() ? TileView.flatTopHexToPixel(minQ, minR) : TileView.pointyTopHexToPixel(minQ, minR);

        int offsetX = PADDING - minPoint.x;
        int offsetY = PADDING - minPoint.y;

        return new Point(offsetX, offsetY);
    }

    public void drawBoard(Board board) {
        List<Section> sections = board.getSections();
        List<Blockade> blockades = board.getBlockades();

        Point offsets = calculateOffsets(board);

        for (Section section : sections) {
            for (Tile tile : section.getTiles()) {

                boolean selectedTile = (phase != null && phase.getSelectedTile() == tile);
                TileView tileView = new TileView(tile, board.isFlatTop(), selectedTile);
                this.add(tileView);
                Point tileViewCoords = tileView.hexagonToPixel(board.isFlatTop(), tile);
                tileView.setBounds(tileViewCoords.x + offsets.x, tileViewCoords.y + offsets.y, TILE_SIZE * 2, TILE_SIZE * 2);
            }
        }

        // Uncomment the following lines if you need to draw blockades
        // int counter = 0;
        // for (Blockade blockade : blockades) {
        //     BlockadeController blockadeController = new BlockadeController(blockade, new BlockadeView());
        //     blockadeController.updateView(g2d, counter * 60);
        //     counter++;
        // }
    }
}