package org.utwente.util;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public abstract class ImageLoader<T extends Enum<T> & ImageLoadable> {
    private final Map<T, Map<Integer, BufferedImage>> images = new EnumMap<>(getEnumType());

    protected ImageLoader() {
        loadImages();
    }

    protected abstract Class<T> getEnumType();

    protected abstract String getImagePath(T type, int power);

    private void loadImages() {
        for (T type : getEnumType().getEnumConstants()) {
            Map<Integer, BufferedImage> imagesForType = new HashMap<>();
            for (int power : type.getPowerRange().getRange()) {
                String imagePath = getImagePath(type, power);
                try {
                    BufferedImage image = ImageIO.read(Objects.requireNonNull(getClass().getResource(imagePath)));
                    imagesForType.put(power, image);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            images.put(type, imagesForType);
        }
    }

    public BufferedImage getImage(T type, int power) {
        return images.getOrDefault(type, new HashMap<>()).get(power);
    }
}

