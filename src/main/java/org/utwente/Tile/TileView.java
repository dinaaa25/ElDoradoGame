package org.utwente.Tile;

import org.utwente.Board.Blockade.Blockade;
import org.utwente.Board.Blockade.BlockadeView;
import org.utwente.Board.SectionDirectionType;
import org.utwente.Section.Section;
import org.utwente.Tile.view.HexButton;
import org.utwente.game.view.GameConfig;
import org.utwente.player.PlayerController;
import org.utwente.player.PlayerView;
import org.utwente.player.model.Player;
import org.utwente.util.event.EventManager;
import org.utwente.util.event.EventType;
import org.utwente.util.images.ImageRepository;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

import static org.utwente.game.view.GameConfig.TILE_SIZE;

public class TileView extends HexButton {

    public TileView(Tile tile, boolean flatTop) {
        super(flatTop, tile);

        this.addActionListener(
                e -> EventManager.getInstance().notifying(EventType.ClickTile, new TileClickEvent(tile)));
    }

    private List<Integer> getEdgesForSectionDirection(SectionDirectionType.SectionDirection sectionDirection) {
        if (sectionDirection instanceof SectionDirectionType.FlatTopSectionDirection flatTopSectionDirection) {
            return switch (flatTopSectionDirection) {
                case FT_NORTHEAST -> List.of(4, 5);
                case FT_EAST -> List.of(5, 0);
                case FT_SOUTHEAST -> List.of(0, 1);
                case FT_SOUTHWEST -> List.of(1, 2);
                case FT_WEST -> List.of(2, 3);
                case FT_NORTHWEST -> List.of(3, 4);
            };
        } else if (sectionDirection instanceof SectionDirectionType.PointyTopSectionDirection pointyTopSectionDirection) {
            return switch (pointyTopSectionDirection) {
                case PT_NORTH -> List.of(3, 4);
                case PT_NORTHEAST -> List.of(4, 5);
                case PT_SOUTHEAST -> List.of(5, 0);
                case PT_SOUTH -> List.of(0, 1);
                case PT_SOUTHWEST -> List.of(1, 2);
                case PT_NORTHWEST -> List.of(2, 3);
            };
        }
        return List.of(0, 0);
    }

    @Override
    protected void drawHexagon(Tile tile, Point2D.Double[] vertices, Graphics2D g2d) {
        BlockadeView blockadeView = new BlockadeView();
        Path2D.Double hexagonPath = createHexagonPath(vertices);
        g2d.fill(hexagonPath);

        BasicStroke basicStroke = new BasicStroke((2));
        BasicStroke thickStroke = new BasicStroke(8);

        List<Integer> edges = Objects.requireNonNullElse(
                Optional.ofNullable(tile.getBlockade())
                        .map(Blockade::getSection2)
                        .map(Section::getDirectionType)
                        .map(this::getEdgesForSectionDirection)
                        .orElse(null),
                Collections.emptyList());

        for (int i = 0; i < vertices.length; i++) {
            g2d.setStroke(basicStroke);

            Point2D.Double start = vertices[i];
            Point2D.Double end = vertices[(i + 1) % vertices.length];

            if (tile.isBlockadeTile() && edges.contains(i)) {
                g2d.setStroke(thickStroke);
                g2d.setColor(blockadeView.getBlockadeColor(tile.getBlockade().getTileType()));
                g2d.setStroke(new BasicStroke(14));
            } else {
                g2d.setStroke(basicStroke);
                g2d.setColor(Color.BLACK);
            }

            g2d.draw(new Line2D.Double(start, end));
        }
    }

    private void drawCoordinates(Graphics2D g2d, Tile tile) {
        g2d.setColor(Color.WHITE);
        g2d.setFont(GameConfig.TILE_FONT);
        FontMetrics metrics = g2d.getFontMetrics();
        String text = tile.getQ() + ", " + tile.getR();
        int textX = TILE_SIZE - metrics.stringWidth(text) / 2;
        int textY = TILE_SIZE + metrics.getHeight() / 2 - metrics.getDescent() + TILE_SIZE / 2;
        g2d.drawString(text, textX, textY);
    }

    @Override
    protected void setTileTexture(Graphics2D g2d) {
        BufferedImage tileImage = ImageRepository.getTileImageLoader().getImage(tile.getTileType(),
                tile.getPower());
        if (tileImage != null) {
            TexturePaint texturePaint = new TexturePaint(tileImage, new Rectangle(0, 0, 2 * TILE_SIZE, 2 * TILE_SIZE));
            g2d.setPaint(texturePaint);
        } else {
            g2d.setColor(tile.getTileColor());
        }
    }

    private void drawPlayers(Graphics2D g2d, Set<Player> players) {
        if (!players.isEmpty()) {
            int playerXOffset = TILE_SIZE - ((players.size() - 1) * 25) / 2;
            for (Player player : players) {
                PlayerController playerController = new PlayerController(player, new PlayerView());
                playerController.updateView(g2d, playerXOffset, TILE_SIZE / 2);
                playerXOffset += 25;
            }
        }
    }

    private void drawCaveCoinCount(Graphics2D g2d, Tile tile) {
        if (tile.getTileType() == TileType.Cave) {
            g2d.setColor(Color.WHITE);
            g2d.setFont(GameConfig.TILE_FONT);
            FontMetrics metrics = g2d.getFontMetrics();
            String caveCoinCountText = String.valueOf(tile.getCaveCoinCount());
            int caveCoinCountTextX = (int) (TILE_SIZE - TILE_SIZE / 1.3);
            int caveCoinCountTextY = TILE_SIZE / 2 + metrics.getAscent();
            g2d.drawString(caveCoinCountText, caveCoinCountTextX, caveCoinCountTextY);
        }
    }

    public static Point flatTopHexToPixel(int q, int r) {
        int x = (int) (TILE_SIZE * 3.0 / 2.0 * q);
        int y = (int) (TILE_SIZE * Math.sqrt(3) * (r + q / 2.0));
        return new Point(x, y);
    }

    public static Point pointyTopHexToPixel(int q, int r) {
        int x = (int) (TILE_SIZE * Math.sqrt(3) * (q + r / 2.0));
        int y = (int) (TILE_SIZE * 3.0 / 2.0 * r);
        return new Point(x, y);
    }

    public Point hexagonToPixel(boolean flatTop, Tile tile) {
        return flatTop ? flatTopHexToPixel(tile.getQ(), tile.getR()) : pointyTopHexToPixel(tile.getQ(), tile.getR());
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        super.paintComponent(g2d);
        drawCoordinates(g2d, tile);
        drawCaveCoinCount(g2d, tile);
        drawPlayers(g2d, tile.getPlayers());
    }
}