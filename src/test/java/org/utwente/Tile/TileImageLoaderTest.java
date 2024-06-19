package org.utwente.Tile;

import org.junit.jupiter.api.Test;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

public class TileImageLoaderTest {

    @Test
    public void testLoadImage() {
        for (TileType tileType : TileType.values()) {
            for (int power : tileType.getPowerRange().getRange()) {
                BufferedImage image = TileImageLoader.getTileImage(tileType, power);
                assertNotNull(image, "Image should not be null for " + tileType + " with power " + power);
            }
        }
    }
}