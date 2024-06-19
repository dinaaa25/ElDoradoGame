package org.utwente.Board;

import org.utwente.Board.Blockade.Blockade;
import org.utwente.Section.Section;
import org.utwente.Tile.Tile;
import org.utwente.Tile.TileView;
import org.utwente.game.view.GameConfig;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import static org.utwente.game.view.GameConfig.HEX_SIZE;
import static org.utwente.game.view.GameConfig.PADDING;

public class BoardView extends JPanel {
    public Board board;

    public BoardView(Board board) {
        this.board = board;
        this.setLayout(null); // Use null layout for custom positioning

        this.setSize(1000, 1000);

        this.drawBoard(board);
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

        int panelWidth = maxPoint.x - minPoint.x + 2 * PADDING + HEX_SIZE;
        int panelHeight = maxPoint.y - minPoint.y + 2 * PADDING + HEX_SIZE;

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

        for (Section section : sections) {
            for (Tile tile : section.getTiles()) {
                TileView tileView = new TileView(tile, board.isFlatTop());
                this.add(tileView);
                Point tileViewCoords = tileView.hexagonToPixel(board.isFlatTop(), tile);
                tileView.setBounds(tileViewCoords.x, tileViewCoords.y, HEX_SIZE * 2, HEX_SIZE * 2);
            }
        }

//         Uncomment the following lines if you need to draw blockades
//         int counter = 0;
//         for (Blockade blockade : blockades) {
//             BlockadeController blockadeController = new BlockadeController(blockade, new BlockadeView());
//             blockadeController.updateView(g2d, counter * 60);
//             counter++;
//         }
    }
}