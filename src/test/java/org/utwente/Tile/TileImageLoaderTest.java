package org.utwente.Tile;

import org.junit.jupiter.api.Test;
import org.utwente.util.images.ImageRepository;

import java.awt.image.BufferedImage;

import static org.junit.jupiter.api.Assertions.*;

public class TileImageLoaderTest {

    @Test
    public void testLoadImage() {
        for (TileType tileType : TileType.values()) {
            for (int power : tileType.getPowerRange().getRange()) {
                BufferedImage image = ImageRepository.getTileImageLoader().getImage(tileType, power);
                assertNotNull(image, "Image should not be null for " + tileType + " with power " + power);
            }
        }
    }
    @Test
    void testGetTileImage() {
        assertNotNull((new TileImageLoader()).getImage(TileType.Machete, 1));
        assertNotNull((new TileImageLoader()).getImage(TileType.Paddle, 1));
        assertNotNull((new TileImageLoader()).getImage(TileType.Coin, 1));
        assertNotNull((new TileImageLoader()).getImage(TileType.Basecamp, 1));
        assertNotNull((new TileImageLoader()).getImage(TileType.Discard, 1));
        assertNotNull((new TileImageLoader()).getImage(TileType.Mountain,0));
        assertNotNull((new TileImageLoader()).getImage(TileType.Cave, 0));
        assertNotNull((new TileImageLoader()).getImage(TileType.ElDorado, 0));
        assertNotNull((new TileImageLoader()).getImage(TileType.Start, 0));
    }
}
