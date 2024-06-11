package org.utwente.Tile;

import org.utwente.Board.Blockade.Blockade;
import org.utwente.Board.Blockade.BlockadeView;
import org.utwente.Board.SectionDirectionType;
import org.utwente.Section.Section;
import org.utwente.game.view.GameConfig;
import org.utwente.player.Player;
import org.utwente.player.PlayerController;
import org.utwente.player.PlayerView;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.*;
import java.util.List;

import static org.utwente.game.view.GameConfig.HEX_SIZE;


public class TileView {
    private Point2D.Double[] createHexagonVertices(boolean flatTop, int x, int y) {
        Point2D.Double[] vertices = new Point2D.Double[6];
        for (int i = 0; i < 6; i++) {
            double angle = flatTop ? Math.PI / 3 * i : 2 * Math.PI / 6 * (i + 0.5);
            vertices[i] = new Point2D.Double(
                    x + HEX_SIZE * Math.cos(angle),
                    y + HEX_SIZE * Math.sin(angle)
            );
        }
        return vertices;
    }

    private Path2D.Double createHexagonPath(Point2D.Double[] vertices) {
        Path2D.Double hexagon = new Path2D.Double();
        hexagon.moveTo(vertices[0].x, vertices[0].y);
        for (int i = 1; i < vertices.length; i++) {
            hexagon.lineTo(vertices[i].x, vertices[i].y);
        }
        hexagon.closePath();
        return hexagon;
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

    private void drawHexagon(Tile tile, Point2D.Double[] vertices, Graphics2D g2d) {
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
                Collections.emptyList()
        );

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

    private void drawCoordinates(Graphics2D g2d, int x, int y, Tile tile) {
        g2d.setColor(Color.WHITE);
        g2d.setFont(GameConfig.TILE_FONT);
        FontMetrics metrics = g2d.getFontMetrics();
        String text = tile.getQ() + ", " + tile.getR();
        int textX = x - metrics.stringWidth(text) / 2;
        int textY = y + metrics.getHeight() / 2 - metrics.getDescent() + HEX_SIZE / 2;
        g2d.drawString(text, textX, textY);
    }

    private void drawPower(Graphics2D g2d, int x, int y, Tile tile) {
        g2d.setColor(Color.WHITE);
        g2d.setFont(GameConfig.TILE_FONT);
        FontMetrics metrics = g2d.getFontMetrics();
        String powerText = String.valueOf(tile.getPower());
        int powerTextX = (int) (x - HEX_SIZE / 1.3);
        int powerTextY = y - HEX_SIZE / 2 + metrics.getAscent();
        g2d.drawString(powerText, powerTextX, powerTextY);
    }

    private void setTileTexture(Graphics2D g2d, int x, int y, Tile tile, TileImageLoader tileImageLoader) {
        BufferedImage tileImage = tileImageLoader.getTileImage(tile.getTileType(), tile.getPower());
        if (tileImage != null) {
            TexturePaint texturePaint = new TexturePaint(tileImage, new Rectangle(x - HEX_SIZE, y - HEX_SIZE, 2 * HEX_SIZE, 2 * HEX_SIZE));
            g2d.setPaint(texturePaint);
        } else {
            g2d.setColor(tile.getTileColor());
        }
    }

    private void drawPlayers(Graphics2D g2d, Set<Player> players, int x, int y) {
        if (!players.isEmpty()) {
            int playerXOffset = x - ((players.size() - 1) * 25) / 2;
            for (Player player : players) {
                PlayerController playerController = new PlayerController(player, new PlayerView());
                playerController.updateView(g2d, playerXOffset, y - HEX_SIZE / 2);
                playerXOffset += 25;
            }
        }
    }

    private void drawCaveCoinCount(Graphics2D g2d, int x, int y, Tile tile) {
        if (tile.getTileType() == TileType.Cave) {
            g2d.setColor(Color.WHITE);
            g2d.setFont(GameConfig.TILE_FONT);
            FontMetrics metrics = g2d.getFontMetrics();
            String caveCoinCountText = String.valueOf(tile.getCaveCoinCount());
            int caveCoinCountTextX = (int) (x - HEX_SIZE / 1.3);
            int caveCoinCountTextY = y - HEX_SIZE / 2 + metrics.getAscent();
            g2d.drawString(caveCoinCountText, caveCoinCountTextX, caveCoinCountTextY);
        }
    }

    public void drawTile(Graphics2D g2d, Tile tile, int x, int y, boolean flatTop, TileImageLoader tileImageLoader) {
        Point2D.Double[] hexagon = createHexagonVertices(flatTop, x, y);
        setTileTexture(g2d, x, y, tile, tileImageLoader);
        drawHexagon(tile, hexagon, g2d);
        drawCoordinates(g2d, x, y, tile);
//        drawPower(g2d, x, y, tile);
        drawCaveCoinCount(g2d, x, y, tile);
        drawPlayers(g2d, tile.getPlayers(), x, y);
    }
}
