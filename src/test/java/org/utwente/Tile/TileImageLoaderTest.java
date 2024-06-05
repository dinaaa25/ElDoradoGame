package org.utwente.Tile;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

public class TileImageLoaderTest {

    private TileImageLoader tileImageLoader;

    @BeforeEach
    public void setUp() {
        tileImageLoader = new TileImageLoader();
        tileImageLoader.loadTileImages();
    }

    @Test
    public void testLoadImage() {
        for (TileType tileType : TileType.values()) {
            for (int power : tileType.getPowerRange().getRange()) {
                BufferedImage image = tileImageLoader.getTileImage(tileType, power);
                assertNotNull(image, "Image should not be null for " + tileType + " with power " + power);
            }
        }
    }
}