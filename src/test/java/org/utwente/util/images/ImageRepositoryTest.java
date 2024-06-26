package org.utwente.util.images;

import static org.junit.jupiter.api.Assertions.assertSame;

import java.awt.image.BufferedImage;
import java.awt.image.SampleModel;

import org.junit.jupiter.api.Test;
import org.utwente.CaveCoin.CaveCoinType;
import org.utwente.Tile.TileType;

class ImageRepositoryTest {
    /**
     * Method under test: {@link ImageRepository#getCaveCoinLoader()}
     */
    @Test
    void testGetCaveCoinLoader() {
        // Arrange, Act and Assert
        BufferedImage image = ImageRepository.getCaveCoinLoader().getImage(CaveCoinType.Machete, 1);
        SampleModel expectedSampleModel = image.getSampleModel();
        assertSame(expectedSampleModel, image.getRaster().getSampleModel());
    }

    /**
     * Method under test: {@link ImageRepository#getTileImageLoader()}
     */
    @Test
    void testGetTileImageLoader() {
        // Arrange, Act and Assert
        BufferedImage image = ImageRepository.getTileImageLoader().getImage(TileType.Machete, 1);
        SampleModel expectedSampleModel = image.getSampleModel();
        assertSame(expectedSampleModel, image.getRaster().getSampleModel());
    }
}
