package org.utwente.player;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

public class PlayerImageLoader {

    private final Map<PlayerColor, BufferedImage> playerImages = new EnumMap<>(PlayerColor.class);

    public PlayerImageLoader loadPlayerImages() {
        for (PlayerColor playerColor : PlayerColor.values()) {
                String imagePath = String.format("/images/players/%s.png", playerColor);
                try {
                    BufferedImage image = ImageIO.read(Objects.requireNonNull(getClass().getResource(imagePath)));
                    playerImages.put(playerColor, image);
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return this;
    }

    public BufferedImage getPlayerImage(PlayerColor playerColor) {
        return playerImages.getOrDefault(playerColor, null);
    }
}
