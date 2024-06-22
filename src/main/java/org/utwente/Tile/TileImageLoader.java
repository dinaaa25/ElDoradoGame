package org.utwente.Tile;

import org.utwente.util.ImageLoader;

import java.awt.image.BufferedImage;
import java.util.*;

public class TileImageLoader extends ImageLoader<TileType> {
    private static TileImageLoader instance;

    private final Map<TileType, Map<Integer, BufferedImage>> tileImages = new EnumMap<>(TileType.class);

    private TileImageLoader() {
        super();
    }

    @Override
    protected Class<TileType> getEnumType() {
        return TileType.class;
    }

    @Override
    protected String getImagePath(TileType type, int power) {
        return String.format("/images/tiles/%s-%d.png", type.name(), power);
    }

    public static TileImageLoader getInstance() {
        if (instance == null) {
            instance = new TileImageLoader();
        }
        return instance;
    }
}