package org.utwente.CaveCoin;

import org.utwente.Tile.view.HexButton;
import org.utwente.util.event.EventManager;
import org.utwente.util.event.EventType;
import org.utwente.util.images.ImageRepository;
import java.awt.*;
import java.awt.image.BufferedImage;

import static org.utwente.game.view.GameConfig.CAVECOIN_SIZE;

public class CaveCoinView extends HexButton {
    public CaveCoin caveCoin;

    public CaveCoinView(CaveCoin caveCoin) {
        super(false, null);
        this.caveCoin = caveCoin;

        this.addActionListener(
                e -> EventManager.getInstance().notifying(EventType.ClickCaveCoin, new CaveCoinClickEvent(caveCoin)));
    }

    @Override
    protected void setTileTexture(Graphics2D g2d) {
        BufferedImage caveCoinImage = ImageRepository.getCaveCoinLoader().getImage(caveCoin.caveCoinType(),
                caveCoin.power());
        if (caveCoinImage != null) {
            TexturePaint texturePaint = new TexturePaint(caveCoinImage,
                    new Rectangle(0, 0, 2 * CAVECOIN_SIZE, 2 * CAVECOIN_SIZE));
            g2d.setPaint(texturePaint);
        } else {
            g2d.setColor(Color.PINK);
        }
    }
}