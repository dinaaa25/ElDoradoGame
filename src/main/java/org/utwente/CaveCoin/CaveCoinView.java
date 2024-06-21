package org.utwente.CaveCoin;

import org.utwente.Tile.Tile;
import org.utwente.util.event.EventManager;
import org.utwente.util.event.EventType;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import static org.utwente.game.view.GameConfig.HEX_SIZE;

public class CaveCoinView extends JButton {
    public CaveCoin caveCoin;

    public CaveCoinView(CaveCoin caveCoin) {
        this.caveCoin = caveCoin;

        this.addActionListener(
                e -> EventManager.getInstance().notifying(EventType.ClickCaveCoin, new CaveCoinClickEvent(caveCoin)));

        this.setPreferredSize(new Dimension(HEX_SIZE * 2, HEX_SIZE * 2));
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(HEX_SIZE * 2, HEX_SIZE * 2);
    }

    private Point2D.Double[] createHexagonVertices() {
        Point2D.Double[] vertices = new Point2D.Double[6];
        for (int i = 0; i < 6; i++) {
            double angle = 2 * Math.PI / 6 * (i + 0.5);
            vertices[i] = new Point2D.Double(
                    HEX_SIZE + HEX_SIZE * Math.cos(angle),
                    HEX_SIZE + HEX_SIZE * Math.sin(angle));
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

    private void drawHexagon(Point2D.Double[] vertices, Graphics2D g2d) {
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

    private void setTileTexture(Graphics2D g2d) {
        BufferedImage caveCoinImage = CaveCoinImageLoader.getInstance().getCaveCoinImage(caveCoin.caveCoinType(), caveCoin.power());
        if (caveCoinImage != null) {
            TexturePaint texturePaint = new TexturePaint(caveCoinImage, new Rectangle(0, 0, 2 * HEX_SIZE, 2 * HEX_SIZE));
            g2d.setPaint(texturePaint);
        } else {
            g2d.setColor(Color.PINK);
        }
    }

//    public static Point flatTopHexToPixel(int q, int r) {
//        int x = (int) (HEX_SIZE * 3.0 / 2.0 * q);
//        int y = (int) (HEX_SIZE * Math.sqrt(3) * (r + q / 2.0));
//        return new Point(x, y);
//    }
//
//    public static Point pointyTopHexToPixel(int q, int r) {
//        int x = (int) (HEX_SIZE * Math.sqrt(3) * (q + r / 2.0));
//        int y = (int) (HEX_SIZE * 3.0 / 2.0 * r);
//        return new Point(x, y);
//    }
//
//    public Point hexagonToPixel(boolean flatTop, Tile tile) {
//        return flatTop ? flatTopHexToPixel(tile.getQ(), tile.getR()) : pointyTopHexToPixel(tile.getQ(), tile.getR());
//    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Point2D.Double[] hexagon = createHexagonVertices();

        setTileTexture(g2d);
        drawHexagon(hexagon, g2d);
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
}