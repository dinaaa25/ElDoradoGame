package org.utwente.Board;

import org.utwente.Board.Blockade.Blockade;
import org.utwente.Board.Blockade.BlockadeController;
import org.utwente.Board.Blockade.BlockadeView;
import org.utwente.Section.Section;
import org.utwente.Section.SectionLoader;
import org.utwente.Section.SectionType;
import org.utwente.Section.SectionWithRotationPositionSectionDirection;
import org.utwente.Tile.Tile;
import org.utwente.Tile.TileController;
import org.utwente.Tile.TileType;
import org.utwente.Tile.TileView;
import org.utwente.game.GameConfig;
import org.utwente.player.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.utwente.Board.SectionDirectionType.FlatTopSectionDirection.*;
import static org.utwente.Board.SectionDirectionType.PointyTopSectionDirection.*;
import static org.utwente.game.GameConfig.HEX_SIZE;

public class HexGridPanel extends JPanel {
    private static final int PADDING = 100;
    private List<List<Tile>> tileSections;
    private int panelWidth;
    private int panelHeight;
    private int offsetX;
    private int offsetY;
    private BufferedImage macheteImage;
    private BlockadeController blockadeController;
    private boolean flatTop;
    private Board board;

    public HexGridPanel(List<List<Tile>> tileSections, boolean flatTop) {
        this.tileSections = tileSections;
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

    public Dimension calculatePreferredSize() {
        if (tileSections.isEmpty() || tileSections.get(0).isEmpty()) {
            setPreferredSize(new Dimension(GameConfig.DEFAULT_BOARD_SIZE_X, GameConfig.DEFAULT_BOARD_SIZE_Y));
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
        blockadeController = new BlockadeController(new Blockade(TileType.Coin, start, end, 2), new BlockadeView());
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);


        for (List<Tile> section : tileSections) {
            for (Tile tile : section) {
                Point p = flatTop ? flatTopHexToPixel(tile.getQ(), tile.getR()) : pointyTopHexToPixel(tile.getQ(), tile.getR());
                TileController tileController = new TileController(tile, new TileView());
                tileController.updateView(g2d, p.x + offsetX, p.y + offsetY, HEX_SIZE, flatTop, macheteImage);
            }
        }

        if (blockadeController.getBlockade() != null) {
            Blockade blockade = blockadeController.getBlockade();
            Point startPixel = flatTop ? flatTopHexToPixel(blockade.getStart().x, blockade.getStart().y) : pointyTopHexToPixel(blockade.getStart().x, blockade.getStart().y);
            Point endPixel = flatTop ? flatTopHexToPixel(blockade.getEnd().x, blockade.getEnd().y) : pointyTopHexToPixel(blockade.getEnd().x, blockade.getEnd().y);
            Point[] vertices = calculateVertices(startPixel, endPixel);
            blockadeController.getBlockadeView().draw(g2d, vertices, blockade.isRemoved());
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

    private Point[] calculateVertices(Point startPixel, Point endPixel) {
        Point[] vertices = new Point[2];
        vertices[0] = startPixel;
        vertices[1] = endPixel;
        return vertices;
    }

    public void removeBlockade() {
        if (blockadeController.getBlockade() != null) {
            blockadeController.getBlockade().remove();
            repaint();
        }
    }

    public void setTileSections(List<List<Tile>> tileSections) {
        this.tileSections = tileSections;
    }

    private List<List<Tile>> getTileSections() {
        return this.tileSections;
    }

    public void buildPredefinedBoard(Path path) {
        List<SectionWithRotationPositionSectionDirection> sectionWithRotationPositionSectionDirectionList = Board.BoardBuilder.paths.get(path);
        flatTop = sectionWithRotationPositionSectionDirectionList.stream()
                .findFirst()
                .map(section -> section.getSectionDirection() instanceof SectionDirectionType.FlatTopSectionDirection)
                .orElse(false);
        for (SectionWithRotationPositionSectionDirection sectionWithRotationPositionSectionDirection : sectionWithRotationPositionSectionDirectionList) {
            attachBoardSection(sectionWithRotationPositionSectionDirection.getSectionType(), sectionWithRotationPositionSectionDirection.getSectionDirection(), sectionWithRotationPositionSectionDirection.getPlacement(), sectionWithRotationPositionSectionDirection.getRotation());
        }
    }

    public void attachBoardSection(SectionType sectionType, SectionDirectionType.SectionDirection sectionDirection, int placement, int rotation) {
        List<Section> sections = SectionLoader.loadSections();
        Optional<Section> optionalSection = sections.stream()
                .filter(s -> s.getSectionType() == sectionType)
                .findFirst();
        if (optionalSection.isEmpty()) {
            throw new IllegalArgumentException("This section Type does not exist");
        }
        if (tileSections.isEmpty() || tileSections.get(0).isEmpty()) {
            List<Tile> newSection = new ArrayList<>(optionalSection.get().getTiles());
            for (Tile tile : newSection) {
                tile.rotate(rotation);
            }
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

            if (sectionType == SectionType.O) {
                System.out.println("New system detected");
                if (sectionDirection.equals(PT_NORTHEAST)) {
                    if (rotation == 0) {
                        translationQ = maxQ;
                        translationR = minR - 2;
                    } else if (rotation == 1) {
                        translationQ = maxQ + 2;
                        translationR = minR + 1;
                    } else {
                        translationQ = maxQ;
                        translationR = minR;
                    }
                } else if (sectionDirection.equals(PT_NORTH)) {
                    translationQ = maxQ;
                    translationR = minR - 2;
                } else {
                    translationQ = maxQ - minQ;
                    translationR = maxR - minR;
                }
            } else {
                System.out.println("normal else");
                if (sectionDirection.equals(PT_NORTHEAST) || sectionDirection.equals(FT_EAST)) {
                    translationQ = maxQ + 1 + 3;
                    translationR = minR - 1 + placement;
                } else if (sectionDirection.equals(PT_SOUTHEAST) || sectionDirection.equals(FT_SOUTHEAST)) {
                    translationQ = maxQ + placement;
                    translationR = maxR + 1 - placement;
                } else if (sectionDirection.equals(PT_SOUTH) || sectionDirection.equals(FT_SOUTHWEST)) {
                    translationQ = minQ - placement;
                    translationR = maxR + 3 + 1;
                } else if (sectionDirection.equals(PT_SOUTHWEST) || sectionDirection.equals(FT_WEST)) {
                    translationQ = minQ - 3 - 1;
                    translationR = maxR + 1 - placement;
                } else if (sectionDirection.equals(PT_NORTHWEST) || sectionDirection.equals(FT_NORTHWEST)) {
                    translationQ = minQ - placement;
                    translationR = minR - 1 + placement;
                } else if (sectionDirection.equals(PT_NORTH) || sectionDirection.equals(FT_NORTHEAST)) {
                    translationQ = maxQ + placement;
                    translationR = minR - 3 - 1;
                } else {
                    translationQ = maxQ;
                    translationR = minR;
                }
            }
            for (Tile tile : optionalSection.get().getTiles()) {
                tile.rotate(rotation);
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
        panel.buildPredefinedBoard(Path.WitchCauldron);
//        panel.attachBoardSection(SectionType.A, DirectionType.FlatTopDirection.NORTHWEST, 0, 0);
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
                throw new IllegalArgumentException("Moving in that sectionDirection is not valid");
            }

            tileTo.placePlayer(player1);
            playerTile.removePlayer(player1);
            panel.repaint();
        });

        timer.setRepeats(false);
        timer.start();
    }
}