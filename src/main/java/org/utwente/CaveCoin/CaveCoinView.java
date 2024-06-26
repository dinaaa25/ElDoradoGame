package org.utwente.CaveCoin;

import org.utwente.Tile.view.HexButton;
import org.utwente.util.event.EventManager;
import org.utwente.util.event.EventType;
import org.utwente.util.images.ImageRepository;

import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import static org.utwente.game.view.GameConfig.CAVECOIN_SIZE;

public class CaveCoinView extends HexButton {
    public CaveCoin caveCoin;

    public CaveCoinView(CaveCoin caveCoin) {
        this(caveCoin, true);
    }

    public CaveCoinView(CaveCoin caveCoin, boolean selected) {
        super(false, null, CAVECOIN_SIZE, selected);
        this.caveCoin = caveCoin;
        this.addActionListener(
                e -> EventManager.getInstance().notifying(EventType.ClickCaveCoin, new CaveCoinClickEvent(caveCoin)));
    }

    public void drawHexagon(Point2D.Double[] vertices, Graphics2D g2d) {
        Path2D.Double hexagonPath = createHexagonPath(vertices);
        g2d.fill(hexagonPath);

        BasicStroke stroke = selected ? new BasicStroke(4) :  new BasicStroke((2));
        g2d.setStroke(stroke);
        g2d.setColor(selected ? Color.RED : Color.BLACK);
        for (int i = 0; i < vertices.length; i++) {
            Point2D.Double start = vertices[i];
            Point2D.Double end = vertices[(i + 1) % vertices.length];
            g2d.draw(new Line2D.Double(start, end));
        }
    }

    @Override
    protected void setTileTexture(Graphics2D g2d) {
        BufferedImage caveCoinImage = ImageRepository.getCaveCoinLoader().getImage(caveCoin.caveCoinType(),
                caveCoin.getPower());
        if (caveCoinImage != null) {
            TexturePaint texturePaint = new TexturePaint(caveCoinImage,
                    new Rectangle(0, 0, 2 * hexagonRadius, 2 * hexagonRadius));
            g2d.setPaint(texturePaint);
        } else {
            g2d.setColor(Color.PINK);
        }
    }
}