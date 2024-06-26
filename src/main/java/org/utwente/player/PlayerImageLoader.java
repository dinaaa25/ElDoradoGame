package org.utwente.player;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PlayerImageLoader {

    private static final Logger LOGGER = Logger.getLogger(PlayerImageLoader.class.getName());
    private final Map<PlayerColor, BufferedImage> playerImages = new EnumMap<>(PlayerColor.class);

    public PlayerImageLoader loadPlayerImages() {
        for (PlayerColor playerColor : PlayerColor.values()) {
            // Convert the enum name RED -.> Red to match file name
            String colorName = playerColor.name().charAt(0) + playerColor.name().substring(1).toLowerCase();
            String imagePath = String.format("/images/players/%s.png", colorName);
            try {
                BufferedImage image = ImageIO.read(getClass().getResource(imagePath));
                if (image != null) {
                    playerImages.put(playerColor, image);
                } else {
                    LOGGER.log(Level.WARNING, "Image for {0} not found at path: {1}", new Object[]{playerColor, imagePath});
                }
            } catch (IllegalArgumentException | IOException e) {
                LOGGER.log(Level.SEVERE, "Error loading image for " + playerColor + " from path: " + imagePath, e);
            }
        }
        return this;
    }

    public BufferedImage getPlayerImage(PlayerColor playerColor) {
        return playerImages.get(playerColor);
    }
}