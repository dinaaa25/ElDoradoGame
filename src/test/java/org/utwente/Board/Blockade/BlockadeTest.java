package org.utwente.Board.Blockade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.utwente.Section.Section;
import org.utwente.Section.SectionType;
import org.utwente.Tile.TileType;

class BlockadeTest {
    /**
     * Method under test: {@link Blockade#initialize(Section, Section)}
     */
    @Test
    void testInitialize() {
        // Arrange
        Blockade blockade = new Blockade(TileType.Machete, 1, 1);
        Section s1 = new Section(new ArrayList<>(), SectionType.A);

        Section s2 = new Section(new ArrayList<>(), SectionType.A);

        // Act
        blockade.initialize(s1, s2);

        // Assert
        assertTrue(blockade.getBlockadeTiles().isEmpty());
        assertSame(s2, blockade.getSection2());
    }

    /**
     * Method under test: {@link Blockade#getBlockadeTiles()}
     */
    @Test
    void testGetBlockadeTiles() {
        // Arrange
        Blockade blockade = new Blockade(TileType.Machete, 1, 1);
        blockade.setSection1(new Section(new ArrayList<>(), SectionType.A));

        // Act and Assert
        assertTrue(blockade.getBlockadeTiles().isEmpty());
    }

    /**
     * Methods under test:
     * <ul>
     *   <li>{@link Blockade#Blockade(TileType, int, int)}
     *   <li>{@link Blockade#remove()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange and Act
        Blockade actualBlockade = new Blockade(TileType.Machete, 1, 1);
        actualBlockade.remove();

        // Assert
        assertNull(actualBlockade.getSection1());
        assertNull(actualBlockade.getSection2());
        assertEquals(1, actualBlockade.getPoints());
        assertEquals(1, actualBlockade.getPower());
        assertEquals(TileType.Machete, actualBlockade.getTileType());
        assertTrue(actualBlockade.isRemoved());
    }
}
