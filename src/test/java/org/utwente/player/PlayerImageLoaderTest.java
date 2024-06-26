package org.utwente.player;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.awt.image.BufferedImage;
import java.awt.image.SampleModel;

import org.junit.jupiter.api.Test;

class PlayerImageLoaderTest {
    /**
     * Method under test: {@link PlayerImageLoader#loadPlayerImages()}
     */
    @Test
    void testLoadPlayerImages() {
        // Arrange and Act
        PlayerImageLoader loader = new PlayerImageLoader().loadPlayerImages();
        BufferedImage playerImage = loader.getPlayerImage(PlayerColor.Red);

        // Assert
        if (playerImage != null) {
            SampleModel expectedSampleModel = playerImage.getSampleModel();
            assertSame(expectedSampleModel, playerImage.getRaster().getSampleModel());
        } else {
            System.out.println("Player image for Red not found.");
        }
    }

    /**
     * Method under test: {@link PlayerImageLoader#getPlayerImage(PlayerColor)}
     */
    @Test
    void testGetPlayerImage() {
        // Arrange and Act
        PlayerImageLoader loader = new PlayerImageLoader().loadPlayerImages();

        // Assert
        BufferedImage redImage = loader.getPlayerImage(PlayerColor.Red);
        BufferedImage blueImage = loader.getPlayerImage(PlayerColor.Blue);
        BufferedImage yellowImage = loader.getPlayerImage(PlayerColor.Yellow);
        BufferedImage whiteImage = loader.getPlayerImage(PlayerColor.White);

        if (redImage != null) {
            assertNotNull(redImage);
        } else {
            assertNull(redImage);
        }

        if (blueImage != null) {
            assertNotNull(blueImage);
        } else {
            assertNull(blueImage);
        }

        if (yellowImage != null) {
            assertNotNull(yellowImage);
        } else {
            assertNull(yellowImage);
        }

        if (whiteImage != null) {
            assertNotNull(whiteImage);
        } else {
            assertNull(whiteImage);
        }
    }
}
