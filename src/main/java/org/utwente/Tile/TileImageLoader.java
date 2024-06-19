package org.utwente.Tile;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import javax.imageio.ImageIO;

public class TileImageLoader {

    private static final Map<TileType, Map<Integer, BufferedImage>> tileImages = new EnumMap<>(TileType.class);

    // Private constructor to prevent instantiation
    private TileImageLoader() {
        loadTileImages();
    }

    // Inner static helper class responsible for holding the Singleton instance
    private static class SingletonHelper {
        private static final TileImageLoader INSTANCE = new TileImageLoader();
    }

    // Public method to provide access to the Singleton instance
    public static TileImageLoader getInstance() {
        return SingletonHelper.INSTANCE;
    }

    // Method to load tile images
    private void loadTileImages() {
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

    // Method to get a tile image
    public static BufferedImage getTileImage(TileType tileType, int power) {
        getInstance();
        return tileImages.getOrDefault(tileType, new HashMap<>()).get(power);
    }
}