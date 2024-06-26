package org.utwente.Section;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.utwente.Tile.TileImageLoader;

class SectionViewTest {
    /**
     * Method under test:
     * {@link SectionView#drawSection(Graphics2D, Section, int, int, boolean, TileImageLoader)}
     */
    @Test
    void testDrawSection() {
        // Arrange
        SectionView sectionView = new SectionView();
        Section section = new Section(new ArrayList<>(), SectionType.A);

        // Act
        sectionView.drawSection(section, true);

        // Assert that nothing has changed
        assertNull(section.getDirectionType());
    }


}
