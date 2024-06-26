package org.utwente.Tile.view;

import javax.swing.*;

import org.utwente.Board.Blockade.Blockade;
import org.utwente.Board.Blockade.BlockadeView;
import org.utwente.Section.Section;
import org.utwente.Tile.Tile;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

public abstract class HexButton extends JButton {
  protected int hexagonRadius;
  protected boolean flatTop;
  protected Tile tile;
  protected boolean selected;

  public HexButton(boolean flatTop, Tile tile, int hexagonRadius, boolean selected) {
    this.flatTop = flatTop;
    this.tile = tile;
    this.hexagonRadius = hexagonRadius;
    this.selected = selected;
    this.setPreferredSize(getPreferredSize());
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(hexagonRadius * 2, hexagonRadius * 2);
  }

  public Point2D.Double[] createHexagonVertices(boolean flatTop) {
    Point2D.Double[] vertices = new Point2D.Double[6];
    for (int i = 0; i < 6; i++) {
      double angle = flatTop ? Math.PI / 3 * i : 2 * Math.PI / 6 * (i + 0.5);
      vertices[i] = new Point2D.Double(
          hexagonRadius + hexagonRadius * Math.cos(angle),
          hexagonRadius + hexagonRadius * Math.sin(angle));
    }
    return vertices;
  }

  public Path2D.Double createHexagonPath(Point2D.Double[] vertices) {
    Path2D.Double hexagon = new Path2D.Double();
    hexagon.moveTo(vertices[0].x, vertices[0].y);
    for (int i = 1; i < vertices.length; i++) {
      hexagon.lineTo(vertices[i].x, vertices[i].y);
    }
    hexagon.closePath();
    return hexagon;
  }

  protected void drawHexagon(Point2D.Double[] vertices, Graphics2D g2d) {
    BlockadeView blockadeView = new BlockadeView();
    Path2D.Double hexagonPath = createHexagonPath(vertices);
    g2d.fill(hexagonPath);

    BasicStroke basicStroke = new BasicStroke((2));
    BasicStroke thickStroke = new BasicStroke(8);
    BasicStroke selectedStroke = new BasicStroke(6);

    java.util.List<Integer> edges = Objects.requireNonNullElse(
            Optional.ofNullable(tile.getBlockade())
                    .map(Blockade::getSection2)
                    .map(Section::getDirectionType)
                    .map(EdgeSectionDirection::getEdgesForSectionDirection)
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
        if (selected) {
          g2d.setStroke(selectedStroke);
          g2d.setColor(Color.RED);
        } else {
          g2d.setStroke(basicStroke);
          g2d.setColor(Color.BLACK);
        }
      }

      g2d.draw(new Line2D.Double(start, end));
    }
  }

  protected abstract void setTileTexture(Graphics2D g);

  @Override
  protected void paintComponent(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    Point2D.Double[] hexagon = createHexagonVertices(this.flatTop);

    setTileTexture(g2d);
    drawHexagon(hexagon, g2d);
  }
}
