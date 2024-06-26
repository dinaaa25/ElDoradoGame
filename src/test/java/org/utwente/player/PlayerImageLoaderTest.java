package org.utwente.player;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.awt.image.BufferedImage;
import java.awt.image.SampleModel;

import org.junit.jupiter.api.Test;

class PlayerImageLoaderTest {
    /**
     * Method under test: {@link PlayerImageLoader#loadPlayerImages()}
     */
    @Test
    void testLoadPlayerImages() {
        // Arrange, Act and Assert
        BufferedImage playerImage = (new PlayerImageLoader()).loadPlayerImages().getPlayerImage(PlayerColor.Red);
        SampleModel expectedSampleModel = playerImage.getSampleModel();
        assertSame(expectedSampleModel, playerImage.getRaster().getSampleModel());
    }

    /**
     * Method under test: {@link PlayerImageLoader#getPlayerImage(PlayerColor)}
     */
    @Test
    void testGetPlayerImage() {
        // Arrange, Act and Assert
        assertNull((new PlayerImageLoader()).getPlayerImage(PlayerColor.Red));
        assertNull((new PlayerImageLoader()).getPlayerImage(PlayerColor.Blue));
        assertNull((new PlayerImageLoader()).getPlayerImage(PlayerColor.Yellow));
        assertNull((new PlayerImageLoader()).getPlayerImage(PlayerColor.White));
    }
}
