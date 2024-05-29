package org.utwente.Board;

import org.utwente.Section.Section;
import org.utwente.Section.SectionLoader;
import org.utwente.Section.SectionType;
import org.utwente.Tile.Tile;
import org.utwente.Tile.TileType;
import org.utwente.player.Player;

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
    private List<List<Tile>> tileSections;
    private int panelWidth;
    private int panelHeight;
    private int offsetX;
    private int offsetY;
    private BufferedImage macheteImage;
    private Blockade blockade;
    private boolean flatTop;
    private Board board;

    public HexGridPanel(List<List<Tile>> tileSections, boolean flatTop) {
        this.tileSections = tileSections;
        this.flatTop = flatTop;
        loadImages();
        calculatePreferredSize();
        initializeBlockade();
        initializeGame();
    }

    private void initializeGame() {
        // this.board = new Board.BoardBuilder().addSection();
    }

    private void loadImages() {
        try {
            macheteImage = ImageIO.read(getClass().getResource("/images/machete-bg.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Dimension calculatePreferredSize() {
        if (tileSections.isEmpty() || tileSections.get(0).isEmpty()) {
            setPreferredSize(new Dimension(800, 600));
            return null;
        }

        int minQ = Integer.MAX_VALUE, maxQ = Integer.MIN_VALUE;
        int minR = Integer.MAX_VALUE, maxR = Integer.MIN_VALUE;

        for (List<Tile> section : tileSections) {
            for (Tile tile : section) {
                int q = tile.getQ();
                int r = tile.getR();
                if (q < minQ) minQ = q;
                if (q > maxQ) maxQ = q;
                if (r < minR) minR = r;
                if (r > maxR) maxR = r;
            }
        }

        Point minPoint = flatTop ? flatTopHexToPixel(minQ, minR) : pointyTopHexToPixel(minQ, minR);
        Point maxPoint = flatTop ? flatTopHexToPixel(maxQ, maxR) : pointyTopHexToPixel(maxQ, maxR);

        panelWidth = maxPoint.x - minPoint.x + 2 * PADDING + HEX_SIZE;
        panelHeight = maxPoint.y - minPoint.y + 2 * PADDING + HEX_SIZE;

        setPreferredSize(new Dimension(panelWidth, panelHeight));

        offsetX = PADDING - minPoint.x;
        offsetY = PADDING - minPoint.y;
        return new Dimension(panelWidth, panelHeight);
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

        for (List<Tile> section : tileSections) {
            for (Tile tile : section) {
                Point p = flatTop ? flatTopHexToPixel(tile.getQ(), tile.getR()) : pointyTopHexToPixel(tile.getQ(), tile.getR());
                drawHexagon(g2d, p.x + offsetX, p.y + offsetY, tile);
            }
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

    private void drawHexagon(Graphics2D g2d, int x, int y, Tile tile) {
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

        if (tile.getTileType() == TileType.Machete && macheteImage != null) {
            TexturePaint texturePaint = new TexturePaint(macheteImage, new Rectangle(x - HEX_SIZE, y - HEX_SIZE, 2 * HEX_SIZE, 2 * HEX_SIZE));
            g2d.setPaint(texturePaint);
        } else {
            g2d.setColor(getColorForTileType(tile.getTileType()));
        }
        g2d.fill(hexagon);

        g2d.setColor(Color.BLACK);
        g2d.draw(hexagon);

        // Draw the coordinates
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 12));
        FontMetrics metrics = g2d.getFontMetrics();
        String text = tile.getQ() + ", " + tile.getR();
        int textX = x - metrics.stringWidth(text) / 2;
        int textY = y + metrics.getHeight() / 2 - metrics.getDescent() + HEX_SIZE / 2;
        g2d.drawString(text, textX, textY);

        // Draw the power
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 12));
        metrics = g2d.getFontMetrics();
        String powerText = String.valueOf(tile.getPower());
        int powerTextX = (int) (x - HEX_SIZE / 1.3);
        int powerTextY = y - HEX_SIZE / 2 + metrics.getAscent();
        g2d.drawString(powerText, powerTextX, powerTextY);

        // Draw player
        if (!tile.getPlayers().isEmpty()) {
            int playerYOffset = y - HEX_SIZE / 2;
            g2d.setColor(Color.WHITE);
            g2d.setFont(new Font("Arial", Font.BOLD, 12));
            metrics = g2d.getFontMetrics();
            for (Player player : tile.getPlayers()) {
                int playerTextX = x - metrics.stringWidth(player.getName()) / 2;
                int playerTextY = playerYOffset + metrics.getHeight() / 2 - metrics.getDescent();
                g2d.drawString(player.getName(), playerTextX, playerTextY);
                playerYOffset += metrics.getHeight(); // Move down for the next player
            }
        }

        // Draw text if the tile type is START
        if (tile.getTileType() == TileType.Start) {
            g2d.setColor(Color.BLACK);
            g2d.setFont(new Font("Arial", Font.BOLD, 12));
            metrics = g2d.getFontMetrics();
            String startText = "Start";
            int startTextX = x - metrics.stringWidth(startText) / 2;
            int startTextY = y + metrics.getHeight() / 2 - metrics.getDescent();
            g2d.drawString(startText, startTextX, startTextY);
        }
    }

    private void drawPlayerIcon(Graphics2D g2d, int x, int y, String playerName) {
        int headRadius = 5;
        int bodyWidth = 10;
        int bodyHeight = 15;
        int capWidth = 10;
        int capHeight = 5;

        // Draw head
        g2d.setColor(Color.PINK);
        g2d.fillOval(x - headRadius, y - headRadius, 2 * headRadius, 2 * headRadius);

        // Draw body
        g2d.setColor(Color.BLUE);
        g2d.fillRect(x - bodyWidth / 2, y, bodyWidth, bodyHeight);

        // Draw cap
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRect(x - capWidth / 2, y - headRadius - capHeight, capWidth, capHeight);

        // Draw player name below the icon
        g2d.setColor(Color.WHITE);
        g2d.setFont(new Font("Arial", Font.BOLD, 10));
        FontMetrics metrics = g2d.getFontMetrics();
        int textX = x - metrics.stringWidth(playerName) / 2;
        int textY = y + bodyHeight + metrics.getHeight();
        g2d.drawString(playerName, textX, textY);
    }

    private Point[] calculateVertices(Point startPixel, Point endPixel) {
        Point[] vertices = new Point[2];
        vertices[0] = startPixel;
        vertices[1] = endPixel;
        return vertices;
    }

    private Color getColorForTileType(TileType tileType) {
        return switch (tileType) {
            case Machete -> new Color(101, 140, 35);
            case Paddle -> new Color(107, 194, 235);
            case Coin -> new Color(241, 215, 95);
            case Basecamp -> new Color(203, 85, 35);
            case Mountain -> new Color(106, 100, 74);
            case Cave -> new Color(224, 159, 65);
            default -> Color.LIGHT_GRAY; // Default color
        };
    }

    public void removeBlockade() {
        if (blockade != null) {
            blockade.remove();
            repaint();
        }
    }

    public void setTileSections(List<List<Tile>> tileSections) {
        this.tileSections = tileSections;
    }

    private List<List<Tile>> getTileSections() {
        return this.tileSections;
    }

    public void attachBoardSection(SectionType sectionType, DirectionType.FlatTopDirection direction, int placement) {
        List<Section> sections = SectionLoader.loadSections();
        Optional<Section> optionalSection = sections.stream()
                .filter(s -> s.getSectionType() == sectionType)
                .findFirst();
        if (optionalSection.isEmpty()) {
            throw new IllegalArgumentException("This section Type does not exist");
        }
        if (tileSections.isEmpty() || tileSections.get(0).isEmpty()) {
            List<Tile> newSection = new ArrayList<>(optionalSection.get().getTiles());
            tileSections.add(newSection);
        } else {
            List<Tile> newTiles = new ArrayList<>();
            List<Tile> lastSection = tileSections.get(tileSections.size() - 1);
            int minQ = lastSection.stream().mapToInt(Tile::getQ).min().orElse(0);
            int maxQ = lastSection.stream().mapToInt(Tile::getQ).max().orElse(0);
            int minR = lastSection.stream().mapToInt(Tile::getR).min().orElse(0);
            int maxR = lastSection.stream().mapToInt(Tile::getR).max().orElse(0);

            int translationQ;
            int translationR;
            switch (direction) {
                case NORTHEAST:
                    translationQ = maxQ + 1 + 3;
                    translationR = minR - 1 + placement;
                    break;
                case SOUTHEAST:
                    translationQ = maxQ + placement;
                    translationR = maxR + 1 - placement;
                    break;
                case SOUTH:
                    translationQ = minQ - placement;
                    translationR = maxR + 3 + 1;
                    break;
                case SOUTHWEST:
                    translationQ = minQ - 3 - 1;
                    translationR = maxR + 1 - placement;
                    break;
                case NORTHWEST:
                    translationQ = minQ - placement;
                    translationR = minR - 1 + placement;
                    break;
                case NORTH:
                    translationQ = minQ + placement;
                    translationR = maxR + placement;
                    break;
                default:
                    translationQ = maxQ + placement;
                    translationR = minR - 3 - 1;
            }
            for (Tile tile : optionalSection.get().getTiles()) {
                tile.setQ(tile.getQ() + translationQ);
                tile.setR(tile.getR() + translationR);
                newTiles.add(tile);
            }
            tileSections.add(newTiles);
        }
    }

    public static void main(String[] args) {
        List<List<Tile>> tileSections = new ArrayList<>();
        // Add player to Tile to visualize
        Player player1 = new Player("Stijn");
        Player player2 = new Player("Mark");

        boolean flatTop = false;

        JFrame frame = new JFrame("Hex Grid");
        HexGridPanel panel = new HexGridPanel(tileSections, flatTop);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setPreferredSize(panel.calculatePreferredSize());

        panel.attachBoardSection(SectionType.A, DirectionType.FlatTopDirection.NORTHEAST, 0);
        panel.attachBoardSection(SectionType.C, DirectionType.FlatTopDirection.NORTHEAST, 0);
        panel.attachBoardSection(SectionType.D, DirectionType.FlatTopDirection.SOUTHEAST, 0);
        panel.attachBoardSection(SectionType.E, DirectionType.FlatTopDirection.SOUTH, 0);
        panel.attachBoardSection(SectionType.C, DirectionType.FlatTopDirection.SOUTH, 1);
        tileSections.stream()
                .flatMap(List::stream)
                .filter(t -> t.getTileType() == TileType.Start)
                .findFirst()
                .ifPresent(tile -> {
                    tile.placePlayer(player1);
                    tile.placePlayer(player2);
                });
        panel.setTileSections(tileSections);
        panel.calculatePreferredSize();
        frame.add(scrollPane);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        Timer timer = new Timer(2000, e -> {
            panel.removeBlockade();
            // panel.setTiles(panel.getTiles().subList(0, panel.getTiles().size() - 1));

            DirectionType.Direction direction = panel.flatTop ? DirectionType.FlatTopDirection.NORTH : DirectionType.PointyTopDirection.NORTHEAST;
            Tile playerTile = tileSections.stream()
                    .flatMap(List::stream)
                    .filter(tile -> tile.getPlayers().stream().anyMatch(player -> player.equals(player1)))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Player not found in any tile"));

            int q = playerTile.getQ() + direction.getDq();
            int r = playerTile.getR() + direction.getDr();
            Tile tileTo = tileSections.stream()
                    .flatMap(List::stream)
                    .filter(tile -> tile.getQ() == q && tile.getR() == r)
                    .findFirst()
                    .orElse(null);

            if (tileTo == null) {
                throw new IllegalArgumentException("Moving in that direction is not valid");
            }

            tileTo.placePlayer(player1);
            playerTile.removePlayer(player1);
            panel.repaint();
        });

        timer.setRepeats(false);
        timer.start();
    }
}