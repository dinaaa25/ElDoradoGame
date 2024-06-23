package org.utwente.Tile.view;

import javax.swing.*;

import org.utwente.Tile.Tile;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;

import static org.utwente.game.view.GameConfig.HEX_SIZE;

public abstract class HexButton extends JButton {
  protected boolean flatTop;
  protected Tile tile;

  public HexButton(boolean flatTop, Tile tile) {
    this.flatTop = flatTop;
    this.tile = tile;
    this.setPreferredSize(getPreferredSize());
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(HEX_SIZE * 2, HEX_SIZE * 2);
  }

  protected Point2D.Double[] createHexagonVertices(boolean flatTop) {
    Point2D.Double[] vertices = new Point2D.Double[6];
    for (int i = 0; i < 6; i++) {
      double angle = flatTop ? Math.PI / 3 * i : 2 * Math.PI / 6 * (i + 0.5);
      vertices[i] = new Point2D.Double(
          HEX_SIZE + HEX_SIZE * Math.cos(angle),
          HEX_SIZE + HEX_SIZE * Math.sin(angle));
    }
    return vertices;
  }

  protected Path2D.Double createHexagonPath(Point2D.Double[] vertices) {
    Path2D.Double hexagon = new Path2D.Double();
    hexagon.moveTo(vertices[0].x, vertices[0].y);
    for (int i = 1; i < vertices.length; i++) {
      hexagon.lineTo(vertices[i].x, vertices[i].y);
    }
    hexagon.closePath();
    return hexagon;
  }

  protected void drawHexagon(Tile tile, Point2D.Double[] vertices, Graphics2D g2d) {
    Path2D.Double hexagonPath = createHexagonPath(vertices);
    g2d.fill(hexagonPath);

    BasicStroke basicStroke = new BasicStroke((2));

    for (int i = 0; i < vertices.length; i++) {
      g2d.setStroke(basicStroke);
      g2d.setColor(Color.BLACK);

      Point2D.Double start = vertices[i];
      Point2D.Double end = vertices[(i + 1) % vertices.length];
      g2d.draw(new Line2D.Double(start, end));
    }
  }

  protected abstract void setTileTexture(Graphics2D g);

  @Override
  protected void paintComponent(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    Point2D.Double[] hexagon = createHexagonVertices(this.flatTop);

    setTileTexture(g2d);
    drawHexagon(this.tile, hexagon, g2d);
  }
}
