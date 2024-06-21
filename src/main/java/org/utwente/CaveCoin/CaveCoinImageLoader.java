package org.utwente.CaveCoin;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

public class CaveCoinImageLoader {
    private static CaveCoinImageLoader instance;

    private final Map<CaveCoinType, BufferedImage> caveCoinImages = new EnumMap<>(CaveCoinType.class);
    private BufferedImage defaultImage;

    private CaveCoinImageLoader() {
        loadTileImages();
    }

    public static CaveCoinImageLoader getInstance() {
        if (instance == null) {
            instance = new CaveCoinImageLoader();
        }
        return instance;
    }

    private void loadTileImages() {
        for (CaveCoinType caveCoinType : CaveCoinType.values()) {
            String imagePath = String.format("/images/cavecoins/%s.png", caveCoinType.name());
            try {
                BufferedImage image = ImageIO.read(Objects.requireNonNull(getClass().getResource(imagePath)));
                caveCoinImages.put(caveCoinType, image);
            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
            }
        }

        try {
            defaultImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("/images/tiles/Cave-0.png")));
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
            defaultImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB);
        }
    }

    public BufferedImage getCaveCoinImage(CaveCoinType tileType) {
        return caveCoinImages.getOrDefault(tileType, defaultImage);
    }
}