package org.utwente.Tile;

import org.utwente.util.images.ImageLoader;

public class TileImageLoader extends ImageLoader<TileType> {

    public TileImageLoader() {
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
}