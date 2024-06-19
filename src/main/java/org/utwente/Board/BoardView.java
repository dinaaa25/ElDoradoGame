package org.utwente.Board;

import org.utwente.Board.Blockade.Blockade;
import org.utwente.Board.Blockade.BlockadeController;
import org.utwente.Board.Blockade.BlockadeView;
import org.utwente.Section.Section;
import org.utwente.Section.SectionController;
import org.utwente.Section.SectionView;
import org.utwente.Tile.Tile;
import org.utwente.Tile.TileImageLoader;
import org.utwente.Tile.TileType;
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

        JButton b1 = new JButton("one");
        JButton b2 = new JButton("two");
        JButton b3 = new JButton("three");

        this.add(b1);
        this.add(b2);
        this.add(b3);

        Insets insets = this.getInsets();
        Dimension size = b1.getPreferredSize();
        b1.setBounds(0, 0, size.width, size.height);
        size = b2.getPreferredSize();
        b2.setBounds(60, 20, size.width, size.height);
        size = b3.getPreferredSize();
        b3.setBounds(500, 15, size.width + 50, size.height + 20);

        this.setSize(1000, 1000);

        TileImageLoader loader = new TileImageLoader();
        loader.loadTileImages();

        Tile tile1 = new Tile(0, 0, TileType.Machete, 1, null, false);
        Tile tile2 = new Tile(0, 1, TileType.Paddle, 1, null, false);
        TileView tileView = new TileView(tile1, false, loader);
        TileView tileView2 = new TileView(tile2, false, loader);
        this.add(tileView);
        this.add(tileView2);

        // Get pixel coordinates from TileView
        Point tileViewCoords = tileView.hexagonToPixel(false, tile1);
        Point tileView2Coords = tileView2.hexagonToPixel(false, tile2);

        // Set bounds for TileView using the pixel coordinates
        tileView.setBounds(tileViewCoords.x, tileViewCoords.y, HEX_SIZE * 2, HEX_SIZE * 2);
        tileView2.setBounds(tileView2Coords.x, tileView2Coords.y, HEX_SIZE * 2, HEX_SIZE * 2);

        // Debug print to verify bounds
        System.out.println("TileView bounds: " + tileView.getBounds());
        System.out.println("TileView2 bounds: " + tileView2.getBounds());

        this.draw
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
        TileImageLoader loader = new TileImageLoader();
        loader.loadTileImages();
        List<Section> sections = board.getSections();
        List<Blockade> blockades = board.getBlockades();
        for (Section section : sections) {
            System.out.println("Section drawing");
            for (Tile tile : section.getTiles()) {
                System.out.println("Drawing tile");
                this.add(new TileView(tile, false, loader));
            }
//            SectionController sectionController = new SectionController(section, new SectionView());
//            sectionController.updateView(flatTop, tileImageLoader);
        }
//        int counter = 0;
//        for (Blockade blockade : blockades) {
//            BlockadeController blockadeController = new BlockadeController(blockade, new BlockadeView());
//            blockadeController.updateView(g2d, counter * 60);
//            counter++;
//        }
    }
}