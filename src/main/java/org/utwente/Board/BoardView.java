package org.utwente.Board;

import org.utwente.Board.Blockade.Blockade;
import org.utwente.Board.Blockade.BlockadeController;
import org.utwente.Board.Blockade.BlockadeView;
import org.utwente.Section.Section;
import org.utwente.Section.SectionController;
import org.utwente.Section.SectionView;
import org.utwente.Tile.Tile;
import org.utwente.Tile.TileImageLoader;
import org.utwente.game.view.GameConfig;

import java.awt.*;
import java.util.List;

import static org.utwente.game.view.GameConfig.HEX_SIZE;
import static org.utwente.game.view.GameConfig.PADDING;

public class BoardView {
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

        SectionView sectionView = new SectionView();

        Point minPoint = board.isFlatTop() ? sectionView.flatTopHexToPixel(minQ, minR) : sectionView.pointyTopHexToPixel(minQ, minR);
        Point maxPoint = board.isFlatTop() ? sectionView.flatTopHexToPixel(maxQ, maxR) : sectionView.pointyTopHexToPixel(maxQ, maxR);

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

        SectionView sectionView = new SectionView();

        Point minPoint = board.isFlatTop() ? sectionView.flatTopHexToPixel(minQ, minR) : sectionView.pointyTopHexToPixel(minQ, minR);

        int offsetX = PADDING - minPoint.x;
        int offsetY = PADDING - minPoint.y;

        return new Point(offsetX, offsetY);
    }

    public void drawBoard(Graphics2D g2d, Board board, int offsetX, int offsetY, boolean flatTop, TileImageLoader tileImageLoader) {
        List<Section> sections = board.getSections();
        List<Blockade> blockades = board.getBlockades();
        for (Section section : sections) {
            SectionController sectionController = new SectionController(section, new SectionView());
            sectionController.updateView(g2d, offsetX, offsetY, flatTop, tileImageLoader);
        }
        int counter = 0;
        for (Blockade blockade : blockades) {
            BlockadeController blockadeController = new BlockadeController(blockade, new BlockadeView());
            blockadeController.updateView(g2d, counter * 60);
            counter++;
        }
    }
}