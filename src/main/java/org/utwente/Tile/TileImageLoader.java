package org.utwente.Tile;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;

public class TileImageLoader {

    private final Map<TileType, Map<Integer, BufferedImage>> tileImages = new EnumMap<>(TileType.class);

    public void loadTileImages() {
        for (TileType tileType : TileType.values()) {
            Map<Integer, BufferedImage> imagesForTileType = new HashMap<>();
            for (int power : tileType.getPowerRange().getRange()) {
                String imagePath = String.format("/images/tiles/%s-%d.png", tileType.name(), power);
                try {
                    BufferedImage image = ImageIO.read(Objects.requireNonNull(getClass().getResource(imagePath)));
                    imagesForTileType.put(power, image);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            tileImages.put(tileType, imagesForTileType);
        }
    }

    public BufferedImage getTileImage(TileType tileType, int power) {
        return tileImages.getOrDefault(tileType, new HashMap<>()).get(power);
    }
}
