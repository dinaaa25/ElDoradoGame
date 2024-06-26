package org.utwente.Section;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.awt.Graphics2D;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.utwente.Tile.TileImageLoader;
import org.utwente.Tile.TileType;

class SectionControllerTest {

    /**
     * Method under test:
     * {@link SectionController#updateView(Graphics2D, int, int, boolean, TileImageLoader)}
     */


        /**
         * Methods under test:
         * <ul>
         *   <li>{@link SectionController#SectionController(Section, SectionView)}
         *   <li>{@link SectionController#getSection()}
         *   <li>{@link SectionController#getSectionView()}
         * </ul>
         */
        @Test
        void testGettersAndSetters() {
            // Arrange
            Section section = new Section(new ArrayList<>(), SectionType.A);

            SectionView sectionView = new SectionView();

            // Act
            SectionController actualSectionController = new SectionController(section, sectionView);
            Section actualSection = actualSectionController.getSection();

            // Assert
            assertSame(section, actualSection);
            assertSame(sectionView, actualSectionController.getSectionView());
        }

        /**
         * Method under test:
         * {@link SectionController#updateView(Graphics2D, int, int, boolean, TileImageLoader)}
         */
        @Test
        void testUpdateView() {
            // Arrange
            Section section = new Section(new ArrayList<>(), SectionType.A);

            SectionController sectionController = new SectionController(section, new SectionView());
            TileImageLoader tileImageLoader = new TileImageLoader();

            // Act
            sectionController.updateView(true );

            // Assert that something has changed
            assertNotNull(tileImageLoader.getImage(TileType.Machete, 1));
        }
    }
