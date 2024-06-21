package org.utwente.CaveCoin;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class CaveCoinImageLoader {
    private static CaveCoinImageLoader instance;

    private final Map<CaveCoinType, Map<Integer, BufferedImage>> caveCoinImages = new EnumMap<>(CaveCoinType.class);

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
            Map<Integer, BufferedImage> imagesForCaveCoinType = new HashMap<>();
            for (int power : caveCoinType.getPowerRange().getRange()) {
                String imagePath = String.format("/images/cavecoins/%s-%d.png", caveCoinType.name(), power);
                try {
                    BufferedImage image = ImageIO.read(Objects.requireNonNull(getClass().getResource(imagePath)));
                    imagesForCaveCoinType.put(power, image);
                } catch (IOException | NullPointerException e) {
                    e.printStackTrace();
                }
            }
            caveCoinImages.put(caveCoinType, imagesForCaveCoinType);
        }
    }

    public BufferedImage getCaveCoinImage(CaveCoinType caveCoinType, int power) {
        return caveCoinImages.getOrDefault(caveCoinType, new HashMap<>()).get(power);
    }
}