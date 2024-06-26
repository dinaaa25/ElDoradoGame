package org.utwente.Tile.view;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.utwente.Board.SectionDirectionType;

class EdgeSectionDirectionTest {
    /**
     * Method under test:
     * {@link EdgeSectionDirection#getEdgesForSectionDirection(SectionDirectionType.SectionDirection)}
     */
    @Test
    void testGetEdgesForSectionDirection() {
        // Arrange and Act
        List<Integer> actualEdgesForSectionDirection = EdgeSectionDirection
                .getEdgesForSectionDirection(SectionDirectionType.FlatTopSectionDirection
                        .fromFlatTopDirection(SectionDirectionType.FlatTopSectionDirection.FT_NORTHEAST));

        // Assert
        assertEquals(2, actualEdgesForSectionDirection.size());
        assertEquals(3, actualEdgesForSectionDirection.get(0).intValue());
        assertEquals(4, actualEdgesForSectionDirection.get(1).intValue());
    }

    /**
     * Method under test:
     * {@link EdgeSectionDirection#getEdgesForSectionDirection(SectionDirectionType.SectionDirection)}
     */
    @Test
    void testGetEdgesForSectionDirection2() {
        // Arrange and Act
        List<Integer> actualEdgesForSectionDirection = EdgeSectionDirection
                .getEdgesForSectionDirection(SectionDirectionType.FlatTopSectionDirection.FT_NORTHEAST);

        // Assert
        assertEquals(2, actualEdgesForSectionDirection.size());
        assertEquals(4, actualEdgesForSectionDirection.get(0).intValue());
        assertEquals(5, actualEdgesForSectionDirection.get(1).intValue());
    }

    /**
     * Method under test:
     * {@link EdgeSectionDirection#getEdgesForSectionDirection(SectionDirectionType.SectionDirection)}
     */
    @Test
    void testGetEdgesForSectionDirection3() {
        // Arrange and Act
        List<Integer> actualEdgesForSectionDirection = EdgeSectionDirection
                .getEdgesForSectionDirection(SectionDirectionType.FlatTopSectionDirection.FT_EAST);

        // Assert
        assertEquals(2, actualEdgesForSectionDirection.size());
        assertEquals(0, actualEdgesForSectionDirection.get(1).intValue());
        assertEquals(5, actualEdgesForSectionDirection.get(0).intValue());
    }

    /**
     * Method under test:
     * {@link EdgeSectionDirection#getEdgesForSectionDirection(SectionDirectionType.SectionDirection)}
     */
    @Test
    void testGetEdgesForSectionDirection4() {
        // Arrange and Act
        List<Integer> actualEdgesForSectionDirection = EdgeSectionDirection
                .getEdgesForSectionDirection(SectionDirectionType.FlatTopSectionDirection.FT_SOUTHEAST);

        // Assert
        assertEquals(2, actualEdgesForSectionDirection.size());
        assertEquals(0, actualEdgesForSectionDirection.get(0).intValue());
        assertEquals(1, actualEdgesForSectionDirection.get(1).intValue());
    }

    /**
     * Method under test:
     * {@link EdgeSectionDirection#getEdgesForSectionDirection(SectionDirectionType.SectionDirection)}
     */
    @Test
    void testGetEdgesForSectionDirection5() {
        // Arrange and Act
        List<Integer> actualEdgesForSectionDirection = EdgeSectionDirection
                .getEdgesForSectionDirection(SectionDirectionType.FlatTopSectionDirection.FT_SOUTHWEST);

        // Assert
        assertEquals(2, actualEdgesForSectionDirection.size());
        assertEquals(1, actualEdgesForSectionDirection.get(0).intValue());
        assertEquals(2, actualEdgesForSectionDirection.get(1).intValue());
    }

    /**
     * Method under test:
     * {@link EdgeSectionDirection#getEdgesForSectionDirection(SectionDirectionType.SectionDirection)}
     */
    @Test
    void testGetEdgesForSectionDirection6() {
        // Arrange and Act
        List<Integer> actualEdgesForSectionDirection = EdgeSectionDirection
                .getEdgesForSectionDirection(SectionDirectionType.FlatTopSectionDirection.FT_WEST);

        // Assert
        assertEquals(2, actualEdgesForSectionDirection.size());
        assertEquals(2, actualEdgesForSectionDirection.get(0).intValue());
        assertEquals(3, actualEdgesForSectionDirection.get(1).intValue());
    }

    /**
     * Method under test:
     * {@link EdgeSectionDirection#getEdgesForSectionDirection(SectionDirectionType.SectionDirection)}
     */
    @Test
    void testGetEdgesForSectionDirection7() {
        // Arrange and Act
        List<Integer> actualEdgesForSectionDirection = EdgeSectionDirection
                .getEdgesForSectionDirection(SectionDirectionType.FlatTopSectionDirection.FT_NORTHWEST);

        // Assert
        assertEquals(2, actualEdgesForSectionDirection.size());
        assertEquals(3, actualEdgesForSectionDirection.get(0).intValue());
        assertEquals(4, actualEdgesForSectionDirection.get(1).intValue());
    }

    /**
     * Method under test:
     * {@link EdgeSectionDirection#getEdgesForSectionDirection(SectionDirectionType.SectionDirection)}
     */
    @Test
    void testGetEdgesForSectionDirection8() {
        // Arrange and Act
        List<Integer> actualEdgesForSectionDirection = EdgeSectionDirection
                .getEdgesForSectionDirection(SectionDirectionType.PointyTopSectionDirection.PT_NORTHEAST);

        // Assert
        assertEquals(2, actualEdgesForSectionDirection.size());
        assertEquals(4, actualEdgesForSectionDirection.get(0).intValue());
        assertEquals(5, actualEdgesForSectionDirection.get(1).intValue());
    }

    /**
     * Method under test:
     * {@link EdgeSectionDirection#getEdgesForSectionDirection(SectionDirectionType.SectionDirection)}
     */
    @Test
    void testGetEdgesForSectionDirection9() {
        // Arrange and Act
        List<Integer> actualEdgesForSectionDirection = EdgeSectionDirection
                .getEdgesForSectionDirection(SectionDirectionType.PointyTopSectionDirection.PT_SOUTHEAST);

        // Assert
        assertEquals(2, actualEdgesForSectionDirection.size());
        assertEquals(0, actualEdgesForSectionDirection.get(1).intValue());
        assertEquals(5, actualEdgesForSectionDirection.get(0).intValue());
    }

    /**
     * Method under test:
     * {@link EdgeSectionDirection#getEdgesForSectionDirection(SectionDirectionType.SectionDirection)}
     */
    @Test
    void testGetEdgesForSectionDirection10() {
        // Arrange and Act
        List<Integer> actualEdgesForSectionDirection = EdgeSectionDirection
                .getEdgesForSectionDirection(SectionDirectionType.PointyTopSectionDirection.PT_SOUTH);

        // Assert
        assertEquals(2, actualEdgesForSectionDirection.size());
        assertEquals(0, actualEdgesForSectionDirection.get(0).intValue());
        assertEquals(1, actualEdgesForSectionDirection.get(1).intValue());
    }

    /**
     * Method under test:
     * {@link EdgeSectionDirection#getEdgesForSectionDirection(SectionDirectionType.SectionDirection)}
     */
    @Test
    void testGetEdgesForSectionDirection11() {
        // Arrange and Act
        List<Integer> actualEdgesForSectionDirection = EdgeSectionDirection
                .getEdgesForSectionDirection(SectionDirectionType.PointyTopSectionDirection.PT_SOUTHWEST);

        // Assert
        assertEquals(2, actualEdgesForSectionDirection.size());
        assertEquals(1, actualEdgesForSectionDirection.get(0).intValue());
        assertEquals(2, actualEdgesForSectionDirection.get(1).intValue());
    }

    /**
     * Method under test:
     * {@link EdgeSectionDirection#getEdgesForSectionDirection(SectionDirectionType.SectionDirection)}
     */
    @Test
    void testGetEdgesForSectionDirection12() {
        // Arrange and Act
        List<Integer> actualEdgesForSectionDirection = EdgeSectionDirection
                .getEdgesForSectionDirection(SectionDirectionType.PointyTopSectionDirection.PT_NORTHWEST);

        // Assert
        assertEquals(2, actualEdgesForSectionDirection.size());
        assertEquals(2, actualEdgesForSectionDirection.get(0).intValue());
        assertEquals(3, actualEdgesForSectionDirection.get(1).intValue());
    }

    /**
     * Method under test:
     * {@link EdgeSectionDirection#getEdgesForSectionDirection(SectionDirectionType.SectionDirection)}
     */
    @Test
    void testGetEdgesForSectionDirection13() {
        // Arrange and Act
        List<Integer> actualEdgesForSectionDirection = EdgeSectionDirection.getEdgesForSectionDirection(null);

        // Assert
        assertEquals(2, actualEdgesForSectionDirection.size());
        assertEquals(0, actualEdgesForSectionDirection.get(0).intValue());
        assertEquals(0, actualEdgesForSectionDirection.get(1).intValue());
    }
}
