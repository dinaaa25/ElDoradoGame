package org.utwente.Section;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

class SectionLoaderTest {
    /**
     * Method under test: {@link SectionLoader#loadSections()}
     */
    @Test
    void testLoadSections() {
        // Arrange and Act
        List<Section> actualLoadSectionsResult = SectionLoader.loadSections();

        // Assert
        assertEquals(20, actualLoadSectionsResult.size());
    }
}
