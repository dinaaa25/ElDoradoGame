package org.utwente.market.view.gui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.awt.GridBagConstraints;

import org.junit.jupiter.api.Test;

class GridCoordinateTest {
    /**
     * Method under test: {@link GridCoordinate#nextRow()}
     */
    @Test
    void testNextRow() {
        // Arrange
        GridCoordinate gridCoordinate = new GridCoordinate(2, 3);

        // Act
        gridCoordinate.nextRow();

        // Assert
        assertEquals(0, gridCoordinate.x);
        assertEquals(4, gridCoordinate.y);
    }

    /**
     * Methods under test:
     * <ul>
     *   <li>{@link GridCoordinate#GridCoordinate(int, int)}
     *   <li>{@link GridCoordinate#toString()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange, Act and Assert
        assertEquals("Coordinate[x=2,y=3]", (new GridCoordinate(2, 3)).toString());
        assertEquals("Coordinate[x=2,y=3]", (new GridCoordinate(2, 3, 3)).toString());
    }

    /**
     * Method under test: {@link GridCoordinate#nextColumn()}
     */
    @Test
    void testNextColumn() {
        // Arrange
        GridCoordinate gridCoordinate = new GridCoordinate(2, 3);

        // Act
        gridCoordinate.nextColumn();

        // Assert
        assertEquals(3, gridCoordinate.x);
    }

    /**
     * Method under test: {@link GridCoordinate#nextColumn()}
     */
    @Test
    void testNextColumn2() {
        // Arrange
        GridCoordinate gridCoordinate = new GridCoordinate(3, 3);

        // Act
        gridCoordinate.nextColumn();

        // Assert
        assertEquals(0, gridCoordinate.x);
        assertEquals(4, gridCoordinate.y);
    }

    /**
     * Method under test: {@link GridCoordinate#toGridBagConstraints(int)}
     */
    @Test
    void testToGridBagConstraints() {
        // Arrange and Act
        GridBagConstraints actualToGridBagConstraintsResult = (new GridCoordinate(2, 3)).toGridBagConstraints(1);

        // Assert
        assertEquals(1, actualToGridBagConstraintsResult.gridwidth);
        assertEquals(2, actualToGridBagConstraintsResult.fill);
        assertEquals(2, actualToGridBagConstraintsResult.gridx);
        assertEquals(3, actualToGridBagConstraintsResult.gridy);
    }
}
