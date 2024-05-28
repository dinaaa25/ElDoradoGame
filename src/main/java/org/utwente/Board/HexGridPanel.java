package org.utwente.Board;

import org.utwente.Section.Section;
import org.utwente.Section.SectionLoader;
import org.utwente.Section.SectionType;
import org.utwente.Tile.Tile;
import org.utwente.Tile.TileType;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import javax.imageio.ImageIO;

public class HexGridPanel extends JPanel {
    private static final int HEX_SIZE = 40;
    private static final int PADDING = 100;
    private List<Tile> tiles;
    private int panelWidth;
    private int panelHeight;
    private int offsetX;
    private int offsetY;
    private BufferedImage macheteImage;
    private Blockade blockade;
    private boolean flatTop;

    public HexGridPanel(List<Tile> tiles, boolean flatTop) {
        this.tiles = tiles;
        this.flatTop = flatTop;
        loadImages();
        calculatePreferredSize();
        initializeBlockade();
    }

    private void loadImages() {
        try {
            macheteImage = ImageIO.read(getClass().getResource("/images/machete-bg.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void calculatePreferredSize() {
        if (tiles.isEmpty()) {
            setPreferredSize(new Dimension(800, 600));
            return;
        }

        int minQ = Integer.MAX_VALUE, maxQ = Integer.MIN_VALUE;
        int minR = Integer.MAX_VALUE, maxR = Integer.MIN_VALUE;

        for (Tile tile : tiles) {
            int q = tile.getQ();
            int r = tile.getR();
            if (q < minQ) minQ = q;
            if (q > maxQ) maxQ = q;
            if (r < minR) minR = r;
            if (r > maxR) maxR = r;
        }

        Point minPoint = flatTop ? flatTopHexToPixel(minQ, minR) : pointyTopHexToPixel(minQ, minR);
        Point maxPoint = flatTop ? flatTopHexToPixel(maxQ, maxR) : pointyTopHexToPixel(maxQ, maxR);

        panelWidth = maxPoint.x - minPoint.x + 2 * PADDING + HEX_SIZE;
        panelHeight = maxPoint.y - minPoint.y + 2 * PADDING + HEX_SIZE;

        setPreferredSize(new Dimension(panelWidth, panelHeight));

        offsetX = PADDING - minPoint.x;
        offsetY = PADDING - minPoint.y;
    }

    private void initializeBlockade() {
        Point start = new Point(3, -3);
        Point end = new Point(5, -5);
        blockade = new Blockade(TileType.Coin, start, end, 2);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        for (Tile tile : tiles) {
            Point p = flatTop ? flatTopHexToPixel(tile.getQ(), tile.getR()) : pointyTopHexToPixel(tile.getQ(), tile.getR());
            drawHexagon(g2d, p.x + offsetX, p.y + offsetY, tile.getTileType(), tile.getQ(), tile.getR());
        }

        if (blockade != null) {
            Point startPixel = flatTop ? flatTopHexToPixel(blockade.getStart().x, blockade.getStart().y) : pointyTopHexToPixel(blockade.getStart().x, blockade.getStart().y);
            Point endPixel = flatTop ? flatTopHexToPixel(blockade.getEnd().x, blockade.getEnd().y) : pointyTopHexToPixel(blockade.getEnd().x, blockade.getEnd().y);
            Point[] vertices = calculateVertices(startPixel, endPixel);
            blockade.draw(g2d, vertices);
        }
    }

    private Point flatTopHexToPixel(int q, int r) {
        int x = (int) (HEX_SIZE * 3.0 / 2.0 * q);
        int y = (int) (HEX_SIZE * Math.sqrt(3) * (r + q / 2.0));
        return new Point(x, y);
    }

    private Point pointyTopHexToPixel(int q, int r) {
        int x = (int) (HEX_SIZE * Math.sqrt(3) * (q + r / 2.0));
        int y = (int) (HEX_SIZE * 3.0 / 2.0 * r);
        return new Point(x, y);
    }

    private void drawHexagon(Graphics2D g2d, int x, int y, TileType tileType, int q, int r) {
        Path2D hexagon = new Path2D.Double();
        Point[] vertices = new Point[6];
        for (int i = 0; i < 6; i++) {
            double angle = flatTop ? Math.PI / 3 * i : 2 * Math.PI / 6 * (i + 0.5);
            int dx = (int) (x + HEX_SIZE * Math.cos(angle));
            int dy = (int) (y + HEX_SIZE * Math.sin(angle));
            vertices[i] = new Point(dx, dy);
            if (i == 0) {
                hexagon.moveTo(dx, dy);
            } else {
                hexagon.lineTo(dx, dy);
            }
        }
        hexagon.closePath();

        if (tileType == TileType.Machete && macheteImage != null) {
            TexturePaint texturePaint = new TexturePaint(macheteImage, new Rectangle(x - HEX_SIZE, y - HEX_SIZE, 2 * HEX_SIZE, 2 * HEX_SIZE));
            g2d.setPaint(texturePaint);
        } else {
            g2d.setColor(getColorForTileType(tileType));
        }
        g2d.fill(hexagon);

        g2d.setColor(Color.BLACK);
        g2d.draw(hexagon);

        // Draw the coordinates
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 12));
        FontMetrics metrics = g2d.getFontMetrics();
        String text = q + ", " + r;
        int textX = x - metrics.stringWidth(text) / 2;
        int textY = y + metrics.getHeight() / 2 - metrics.getDescent() + HEX_SIZE / 2;
        g2d.drawString(text, textX, textY);

        // Draw text if the tile type is START
        if (tileType == TileType.Start) {
            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("Arial", Font.BOLD, 12));
            metrics = g2d.getFontMetrics();
            String startText = "Start";
            int startTextX = x - metrics.stringWidth(startText) / 2;
            int startTextY = y + metrics.getHeight() / 2 - metrics.getDescent();
            g2d.drawString(startText, startTextX, startTextY);
        }
    }

    private Point[] calculateVertices(Point startPixel, Point endPixel) {
        Point[] vertices = new Point[2];
        vertices[0] = startPixel;
        vertices[1] = endPixel;
        return vertices;
    }

    private Color getColorForTileType(TileType tileType) {
        switch (tileType) {
            case Machete:
                return new Color(101, 140, 35);
            case Paddle:
                return new Color(107, 194, 235);
            case Coin:
                return new Color(241, 215, 95);
            case Basecamp:
                return new Color(203, 85, 35);
            case Mountain:
                return new Color(106, 100, 74);
            case Cave:
                return new Color(224, 159, 65);
            default:
                return Color.LIGHT_GRAY; // Default color
        }
    }

    public void removeBlockade() {
        if (blockade != null) {
            blockade.remove();
            repaint();
        }
    }

    public static void main(String[] args) {
        List<Tile> tiles = new ArrayList<>();

        List<Section> sections = SectionLoader.loadSections();
        Optional<Section> sectionA = sections.stream()
                .filter(s -> s.getSectionType() == SectionType.A)
                .findFirst();

        sectionA.ifPresent(section -> tiles.addAll(section.getTiles()));

        Optional<Section> sectionB = sections.stream()
                .filter(s -> s.getSectionType() == SectionType.B)
                .findFirst();

        if (sectionB.isPresent() && sectionA.isPresent()) {
            List<Tile> sectionBTiles = sectionB.get().getTiles();
            int translationQ = sectionA.get().getMaxQ() + 1 + 3; // Connect to the top right
            int translationR = sectionA.get().getMinR() - 1; // Maybe adjust
            for (Tile tile : sectionBTiles) {
                tile.setQ(tile.getQ() + translationQ);
                tile.setR(tile.getR() + translationR);
            }
            tiles.addAll(sectionBTiles);
        }

        boolean flatTop = false;

        JFrame frame = new JFrame("Hex Grid");
        HexGridPanel panel = new HexGridPanel(tiles, flatTop);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        Timer timer = new Timer(5000, e -> panel.removeBlockade());
        timer.setRepeats(false);
        timer.start();
    }
}