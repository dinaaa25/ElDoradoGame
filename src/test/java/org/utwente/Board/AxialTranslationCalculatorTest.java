package org.utwente.Board;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.utwente.Board.AxialTranslationCalculator.AxialTranslation;
import org.utwente.Section.SectionType;
import org.utwente.Section.SectionWithRotationPositionSectionDirection;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AxialTranslationCalculatorTest {

    /**
     * Method under test:
     * {@link AxialTranslationCalculator#getTranslation(SectionWithRotationPositionSectionDirection, CoordinateBounds)}
     */
    @Test
    void testGetTranslation() {
        // Arrange
        AxialTranslationCalculator axialTranslationCalculator = new AxialTranslationCalculator();
        SectionWithRotationPositionSectionDirection sectionWithData = new SectionWithRotationPositionSectionDirection(
                SectionType.A, 1, 1, SectionDirectionType.FlatTopSectionDirection
                .fromFlatTopDirection(SectionDirectionType.FlatTopSectionDirection.FT_NORTHEAST));

        // Act
        AxialTranslationCalculator.AxialTranslation actualTranslation = axialTranslationCalculator
                .getTranslation(sectionWithData, new CoordinateBounds(1, 3, 1, 3));

        // Assert
        assertEquals(-3, actualTranslation.r());
        assertEquals(4, actualTranslation.q());
    }

    /**
     * Method under test:
     * {@link AxialTranslationCalculator#getTranslation(SectionWithRotationPositionSectionDirection, CoordinateBounds)}
     */
    @Test
    void testGetTranslationWithPT_NORTH() {
        // Arrange
        AxialTranslationCalculator axialTranslationCalculator = new AxialTranslationCalculator();
        SectionWithRotationPositionSectionDirection sectionWithData = new SectionWithRotationPositionSectionDirection(
                SectionType.A, 1, 1, SectionDirectionType.FlatTopSectionDirection
                .fromFlatTopDirection(SectionDirectionType.FlatTopSectionDirection.FT_NORTHEAST));
        sectionWithData.setRotation(0);
        sectionWithData.setSectionDirection(SectionDirectionType.PointyTopSectionDirection.PT_NORTH);
        sectionWithData.setPlacement(-1);

        // Act
        AxialTranslationCalculator.AxialTranslation actualTranslation = axialTranslationCalculator
                .getTranslation(sectionWithData, new CoordinateBounds(1, 3, 1, 3));

        // Assert
        assertEquals(-3, actualTranslation.r());
        assertEquals(2, actualTranslation.q());
    }

    /**
     * Method under test:
     * {@link AxialTranslationCalculator#getTranslation(SectionWithRotationPositionSectionDirection, CoordinateBounds)}
     */
    @Test
    void testGetTranslationWithPT_NORTHEAST() {
        // Arrange
        AxialTranslationCalculator axialTranslationCalculator = new AxialTranslationCalculator();
        SectionWithRotationPositionSectionDirection sectionWithData = new SectionWithRotationPositionSectionDirection(
                SectionType.A, 1, 1, SectionDirectionType.FlatTopSectionDirection
                .fromFlatTopDirection(SectionDirectionType.FlatTopSectionDirection.FT_NORTHEAST));
        sectionWithData.setRotation(0);
        sectionWithData.setSectionDirection(SectionDirectionType.PointyTopSectionDirection.PT_NORTHEAST);
        sectionWithData.setPlacement(-1);

        // Act
        AxialTranslationCalculator.AxialTranslation actualTranslation = axialTranslationCalculator
                .getTranslation(sectionWithData, new CoordinateBounds(1, 3, 1, 3));

        // Assert
        assertEquals(-1, actualTranslation.r());
        assertEquals(7, actualTranslation.q());
    }

    /**
     * Method under test:
     * {@link AxialTranslationCalculator#getTranslation(SectionWithRotationPositionSectionDirection, CoordinateBounds)}
     */
    @Test
    void testGetTranslationWithPT_SOUTHEAST() {
        // Arrange
        AxialTranslationCalculator axialTranslationCalculator = new AxialTranslationCalculator();
        SectionWithRotationPositionSectionDirection sectionWithData = new SectionWithRotationPositionSectionDirection(
                SectionType.A, 1, 1, SectionDirectionType.FlatTopSectionDirection
                .fromFlatTopDirection(SectionDirectionType.FlatTopSectionDirection.FT_NORTHEAST));
        sectionWithData.setRotation(0);
        sectionWithData.setSectionDirection(SectionDirectionType.PointyTopSectionDirection.PT_SOUTHEAST);
        sectionWithData.setPlacement(-1);

        // Act
        AxialTranslationCalculator.AxialTranslation actualTranslation = axialTranslationCalculator
                .getTranslation(sectionWithData, new CoordinateBounds(1, 3, 1, 3));

        // Assert
        assertEquals(3, actualTranslation.q());
        assertEquals(3, actualTranslation.r());
    }

    /**
     * Method under test:
     * {@link AxialTranslationCalculator#getTranslation(SectionWithRotationPositionSectionDirection, CoordinateBounds)}
     */
    @Test
    void testGetTranslationWithPT_SOUTH() {
        // Arrange
        AxialTranslationCalculator axialTranslationCalculator = new AxialTranslationCalculator();
        SectionWithRotationPositionSectionDirection sectionWithData = new SectionWithRotationPositionSectionDirection(
                SectionType.A, 1, 1, SectionDirectionType.FlatTopSectionDirection
                .fromFlatTopDirection(SectionDirectionType.FlatTopSectionDirection.FT_NORTHEAST));
        sectionWithData.setRotation(0);
        sectionWithData.setSectionDirection(SectionDirectionType.PointyTopSectionDirection.PT_SOUTH);
        sectionWithData.setPlacement(-1);

        // Act
        AxialTranslationCalculator.AxialTranslation actualTranslation = axialTranslationCalculator
                .getTranslation(sectionWithData, new CoordinateBounds(1, 3, 1, 3));

        // Assert
        assertEquals(2, actualTranslation.q());
        assertEquals(7, actualTranslation.r());
    }

    /**
     * Method under test:
     * {@link AxialTranslationCalculator#getTranslation(SectionWithRotationPositionSectionDirection, CoordinateBounds)}
     */
    @Test
    void testGetTranslationWithPT_SOUTHWEST() {
        // Arrange
        AxialTranslationCalculator axialTranslationCalculator = new AxialTranslationCalculator();
        SectionWithRotationPositionSectionDirection sectionWithData = new SectionWithRotationPositionSectionDirection(
                SectionType.A, 1, 1, SectionDirectionType.FlatTopSectionDirection
                .fromFlatTopDirection(SectionDirectionType.FlatTopSectionDirection.FT_NORTHEAST));
        sectionWithData.setRotation(0);
        sectionWithData.setSectionDirection(SectionDirectionType.PointyTopSectionDirection.PT_SOUTHWEST);
        sectionWithData.setPlacement(-1);

        // Act
        AxialTranslationCalculator.AxialTranslation actualTranslation = axialTranslationCalculator
                .getTranslation(sectionWithData, new CoordinateBounds(1, 3, 1, 3));

        // Assert
        assertEquals(-3, actualTranslation.q());
        assertEquals(5, actualTranslation.r());
    }

    /**
     * Method under test:
     * {@link AxialTranslationCalculator#getTranslation(SectionWithRotationPositionSectionDirection, CoordinateBounds)}
     */
    @Test
    void testGetTranslationWithPT_NORTHWEST() {
        // Arrange
        AxialTranslationCalculator axialTranslationCalculator = new AxialTranslationCalculator();
        SectionWithRotationPositionSectionDirection sectionWithData = new SectionWithRotationPositionSectionDirection(
                SectionType.A, 1, 1, SectionDirectionType.FlatTopSectionDirection
                .fromFlatTopDirection(SectionDirectionType.FlatTopSectionDirection.FT_NORTHEAST));
        sectionWithData.setRotation(0);
        sectionWithData.setSectionDirection(SectionDirectionType.PointyTopSectionDirection.PT_NORTHWEST);
        sectionWithData.setPlacement(-1);

        // Act
        AxialTranslationCalculator.AxialTranslation actualTranslation = axialTranslationCalculator
                .getTranslation(sectionWithData, new CoordinateBounds(1, 3, 1, 3));

        // Assert
        assertEquals(1, actualTranslation.q());
        assertEquals(1, actualTranslation.r());
    }

    /**
     * Method under test:
     * {@link AxialTranslationCalculator#getTranslation(SectionWithRotationPositionSectionDirection, CoordinateBounds)}
     */
    @Test
    void testGetTranslationWithFT_SOUTHEAST() {
        // Arrange
        AxialTranslationCalculator axialTranslationCalculator = new AxialTranslationCalculator();
        SectionWithRotationPositionSectionDirection sectionWithData = new SectionWithRotationPositionSectionDirection(
                SectionType.A, 1, 1, SectionDirectionType.FlatTopSectionDirection
                .fromFlatTopDirection(SectionDirectionType.FlatTopSectionDirection.FT_SOUTHEAST));

        // Act
        AxialTranslationCalculator.AxialTranslation actualTranslation = axialTranslationCalculator
                .getTranslation(sectionWithData, new CoordinateBounds(1, 3, 1, 3));

        // Assert
        assertEquals(3, actualTranslation.q());
        assertEquals(4, actualTranslation.r());
    }

    /**
     * Method under test:
     * {@link AxialTranslationCalculator#getTranslation(SectionWithRotationPositionSectionDirection, CoordinateBounds)}
     */
    @Test
    void testGetTranslationWithPT_NORTHWEST_NoRotationNoPlacement() {
        // Arrange
        AxialTranslationCalculator axialTranslationCalculator = new AxialTranslationCalculator();
        SectionWithRotationPositionSectionDirection sectionWithData = new SectionWithRotationPositionSectionDirection(
                SectionType.A, 1, 1, SectionDirectionType.PointyTopSectionDirection.PT_NORTHWEST);

        // Act
        AxialTranslationCalculator.AxialTranslation actualTranslation = axialTranslationCalculator
                .getTranslation(sectionWithData, new CoordinateBounds(1, 3, 1, 3));

        // Assert
        assertEquals(0, actualTranslation.q());
        assertEquals(1, actualTranslation.r());
    }
}
