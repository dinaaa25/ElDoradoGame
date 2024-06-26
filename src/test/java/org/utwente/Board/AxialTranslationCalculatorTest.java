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


    @Test
    void testGetTranslationElDoradoWithPT_NORTHEAST() {
        // Arrange
        AxialTranslationCalculator axialTranslationCalculator = new AxialTranslationCalculator();
        SectionWithRotationPositionSectionDirection sectionWithData = new SectionWithRotationPositionSectionDirection(
                SectionType.A, 1, 1, SectionDirectionType.PointyTopSectionDirection.PT_NORTHEAST);
        sectionWithData.setRotation(0);

        // Act
        AxialTranslationCalculator.AxialTranslation actualTranslation = axialTranslationCalculator
                .getTranslationElDorado(sectionWithData, new CoordinateBounds(1, 3, 1, 3));

        // Assert
        assertEquals(4, actualTranslation.q());
        assertEquals(0, actualTranslation.r());
    }

    @Test
    void testGetTranslationElDoradoWithPT_SOUTHEAST() {
        // Arrange
        AxialTranslationCalculator axialTranslationCalculator = new AxialTranslationCalculator();
        SectionWithRotationPositionSectionDirection sectionWithData = new SectionWithRotationPositionSectionDirection(
                SectionType.A, 1, 1, SectionDirectionType.PointyTopSectionDirection.PT_SOUTHEAST);
        sectionWithData.setRotation(1);

        // Act
        AxialTranslationCalculator.AxialTranslation actualTranslation = axialTranslationCalculator
                .getTranslationElDorado(sectionWithData, new CoordinateBounds(1, 3, 1, 3));

        // Assert
        assertEquals(4, actualTranslation.q());
        assertEquals(2, actualTranslation.r());
    }

    @Test
    void testGetTranslationElDoradoWithPT_SOUTH() {
        // Arrange
        AxialTranslationCalculator axialTranslationCalculator = new AxialTranslationCalculator();
        SectionWithRotationPositionSectionDirection sectionWithData = new SectionWithRotationPositionSectionDirection(
                SectionType.A, 1, 1, SectionDirectionType.PointyTopSectionDirection.PT_SOUTH);
        sectionWithData.setRotation(2);

        // Act
        AxialTranslationCalculator.AxialTranslation actualTranslation = axialTranslationCalculator
                .getTranslationElDorado(sectionWithData, new CoordinateBounds(1, 3, 1, 3));

        // Assert
        assertEquals(2, actualTranslation.q());
        assertEquals(4, actualTranslation.r());
    }

    @Test
    void testGetTranslationElDoradoWithPT_SOUTHWEST() {
        // Arrange
        AxialTranslationCalculator axialTranslationCalculator = new AxialTranslationCalculator();
        SectionWithRotationPositionSectionDirection sectionWithData = new SectionWithRotationPositionSectionDirection(
                SectionType.A, 1, 1, SectionDirectionType.PointyTopSectionDirection.PT_SOUTHWEST);
        sectionWithData.setRotation(3);

        // Act
        AxialTranslationCalculator.AxialTranslation actualTranslation = axialTranslationCalculator
                .getTranslationElDorado(sectionWithData, new CoordinateBounds(1, 3, 1, 3));

        // Assert
        assertEquals(0, actualTranslation.q());
        assertEquals(4, actualTranslation.r());
    }

    @Test
    void testGetTranslationElDoradoWithPT_NORTHWEST() {
        // Arrange
        AxialTranslationCalculator axialTranslationCalculator = new AxialTranslationCalculator();
        SectionWithRotationPositionSectionDirection sectionWithData = new SectionWithRotationPositionSectionDirection(
                SectionType.A, 1, 1, SectionDirectionType.PointyTopSectionDirection.PT_NORTHWEST);
        sectionWithData.setRotation(4);

        // Act
        AxialTranslationCalculator.AxialTranslation actualTranslation = axialTranslationCalculator
                .getTranslationElDorado(sectionWithData, new CoordinateBounds(1, 3, 1, 3));

        // Assert
        assertEquals(0, actualTranslation.q());
        assertEquals(2, actualTranslation.r());
    }

    @Test
    void testGetTranslationElDoradoWithPT_NORTH() {
        // Arrange
        AxialTranslationCalculator axialTranslationCalculator = new AxialTranslationCalculator();
        SectionWithRotationPositionSectionDirection sectionWithData = new SectionWithRotationPositionSectionDirection(
                SectionType.A, 1, 1, SectionDirectionType.PointyTopSectionDirection.PT_NORTH);
        sectionWithData.setRotation(5);

        // Act
        AxialTranslationCalculator.AxialTranslation actualTranslation = axialTranslationCalculator
                .getTranslationElDorado(sectionWithData, new CoordinateBounds(1, 3, 1, 3));

        // Assert
        assertEquals(2, actualTranslation.q());
        assertEquals(0, actualTranslation.r());
    }
    @Test
    void testGetTranslationElDoradoWithFT_NORTHEAST() {
        // Arrange
        AxialTranslationCalculator axialTranslationCalculator = new AxialTranslationCalculator();
        SectionWithRotationPositionSectionDirection sectionWithData = new SectionWithRotationPositionSectionDirection(
                SectionType.A, 1, 1, SectionDirectionType.FlatTopSectionDirection.FT_NORTHEAST);
        sectionWithData.setRotation(0);

        // Act
        AxialTranslationCalculator.AxialTranslation actualTranslation = axialTranslationCalculator
                .getTranslationElDorado(sectionWithData, new CoordinateBounds(1, 3, 1, 3));

        // Assert
        assertEquals(4, actualTranslation.q());
        assertEquals(0, actualTranslation.r());
    }

    @Test
    void testGetTranslationElDoradoWithFT_EAST() {
        // Arrange
        AxialTranslationCalculator axialTranslationCalculator = new AxialTranslationCalculator();
        SectionWithRotationPositionSectionDirection sectionWithData = new SectionWithRotationPositionSectionDirection(
                SectionType.A, 1, 1, SectionDirectionType.FlatTopSectionDirection.FT_EAST);
        sectionWithData.setRotation(1);

        // Act
        AxialTranslationCalculator.AxialTranslation actualTranslation = axialTranslationCalculator
                .getTranslationElDorado(sectionWithData, new CoordinateBounds(1, 3, 1, 3));

        // Assert
        assertEquals(4, actualTranslation.q());
        assertEquals(2, actualTranslation.r());
    }

    @Test
    void testGetTranslationElDoradoWithFT_SOUTHEAST() {
        // Arrange
        AxialTranslationCalculator axialTranslationCalculator = new AxialTranslationCalculator();
        SectionWithRotationPositionSectionDirection sectionWithData = new SectionWithRotationPositionSectionDirection(
                SectionType.A, 1, 1, SectionDirectionType.FlatTopSectionDirection.FT_SOUTHEAST);
        sectionWithData.setRotation(2);

        // Act
        AxialTranslationCalculator.AxialTranslation actualTranslation = axialTranslationCalculator
                .getTranslationElDorado(sectionWithData, new CoordinateBounds(1, 3, 1, 3));

        // Assert
        assertEquals(2, actualTranslation.q());
        assertEquals(4, actualTranslation.r());
    }

    @Test
    void testGetTranslationElDoradoWithFT_SOUTHWEST() {
        // Arrange
        AxialTranslationCalculator axialTranslationCalculator = new AxialTranslationCalculator();
        SectionWithRotationPositionSectionDirection sectionWithData = new SectionWithRotationPositionSectionDirection(
                SectionType.A, 1, 1, SectionDirectionType.FlatTopSectionDirection.FT_SOUTHWEST);
        sectionWithData.setRotation(3);

        // Act
        AxialTranslationCalculator.AxialTranslation actualTranslation = axialTranslationCalculator
                .getTranslationElDorado(sectionWithData, new CoordinateBounds(1, 3, 1, 3));

        // Assert
        assertEquals(0, actualTranslation.q());
        assertEquals(4, actualTranslation.r());
    }

    @Test
    void testGetTranslationElDoradoWithFT_WEST() {
        // Arrange
        AxialTranslationCalculator axialTranslationCalculator = new AxialTranslationCalculator();
        SectionWithRotationPositionSectionDirection sectionWithData = new SectionWithRotationPositionSectionDirection(
                SectionType.A, 1, 1, SectionDirectionType.FlatTopSectionDirection.FT_WEST);
        sectionWithData.setRotation(4);

        // Act
        AxialTranslationCalculator.AxialTranslation actualTranslation = axialTranslationCalculator
                .getTranslationElDorado(sectionWithData, new CoordinateBounds(1, 3, 1, 3));

        // Assert
        assertEquals(0, actualTranslation.q());
        assertEquals(2, actualTranslation.r());
    }

    @Test
    void testGetTranslationElDoradoWithFT_NORTHWEST() {
        // Arrange
        AxialTranslationCalculator axialTranslationCalculator = new AxialTranslationCalculator();
        SectionWithRotationPositionSectionDirection sectionWithData = new SectionWithRotationPositionSectionDirection(
                SectionType.A, 1, 1, SectionDirectionType.FlatTopSectionDirection.FT_NORTHWEST);
        sectionWithData.setRotation(5);

        // Act
        AxialTranslationCalculator.AxialTranslation actualTranslation = axialTranslationCalculator
                .getTranslationElDorado(sectionWithData, new CoordinateBounds(1, 3, 1, 3));

        // Assert
        assertEquals(2, actualTranslation.q());
        assertEquals(0, actualTranslation.r());
    }
    @Test
    void testGetTranslationSmallRectangleWithPT_NORTH_Rotation0() {
        // Arrange
        AxialTranslationCalculator axialTranslationCalculator = new AxialTranslationCalculator();
        SectionWithRotationPositionSectionDirection sectionWithData = new SectionWithRotationPositionSectionDirection(
                SectionType.A, 0, 1, SectionDirectionType.PointyTopSectionDirection.PT_NORTH);

        // Act
        AxialTranslationCalculator.AxialTranslation actualTranslation = axialTranslationCalculator
                .getTranslationSmallRectangle(sectionWithData, new CoordinateBounds(1, 3, 1, 3));

        // Assert
        assertEquals(2, actualTranslation.q());
        assertEquals(-1, actualTranslation.r());
    }

    @Test
    void testGetTranslationSmallRectangleWithPT_NORTH_Rotation3() {
        // Arrange
        AxialTranslationCalculator axialTranslationCalculator = new AxialTranslationCalculator();
        SectionWithRotationPositionSectionDirection sectionWithData = new SectionWithRotationPositionSectionDirection(
                SectionType.A, 3, 1, SectionDirectionType.PointyTopSectionDirection.PT_NORTH);

        // Act
        AxialTranslationCalculator.AxialTranslation actualTranslation = axialTranslationCalculator
                .getTranslationSmallRectangle(sectionWithData, new CoordinateBounds(1, 3, 1, 3));

        // Assert
        assertEquals(2, actualTranslation.q());
        assertEquals(-1, actualTranslation.r());
    }

    @Test
    void testGetTranslationSmallRectangleWithPT_NORTHEAST_Rotation1() {
        // Arrange
        AxialTranslationCalculator axialTranslationCalculator = new AxialTranslationCalculator();
        SectionWithRotationPositionSectionDirection sectionWithData = new SectionWithRotationPositionSectionDirection(
                SectionType.A, 1, 1, SectionDirectionType.PointyTopSectionDirection.PT_NORTHEAST);

        // Act
        AxialTranslationCalculator.AxialTranslation actualTranslation = axialTranslationCalculator
                .getTranslationSmallRectangle(sectionWithData, new CoordinateBounds(1, 3, 1, 3));

        // Assert
        assertEquals(5, actualTranslation.q());
        assertEquals(1, actualTranslation.r());
    }

    @Test
    void testGetTranslationSmallRectangleWithPT_NORTHEAST_Rotation4() {
        // Arrange
        AxialTranslationCalculator axialTranslationCalculator = new AxialTranslationCalculator();
        SectionWithRotationPositionSectionDirection sectionWithData = new SectionWithRotationPositionSectionDirection(
                SectionType.A, 4, 1, SectionDirectionType.PointyTopSectionDirection.PT_NORTHEAST);

        // Act
        AxialTranslationCalculator.AxialTranslation actualTranslation = axialTranslationCalculator
                .getTranslationSmallRectangle(sectionWithData, new CoordinateBounds(1, 3, 1, 3));

        // Assert
        assertEquals(5, actualTranslation.q());
        assertEquals(1, actualTranslation.r());
    }

    @Test
    void testGetTranslationSmallRectangleWithPT_SOUTHEAST_Rotation2() {
        // Arrange
        AxialTranslationCalculator axialTranslationCalculator = new AxialTranslationCalculator();
        SectionWithRotationPositionSectionDirection sectionWithData = new SectionWithRotationPositionSectionDirection(
                SectionType.A, 2, 1, SectionDirectionType.PointyTopSectionDirection.PT_SOUTHEAST);

        // Act
        AxialTranslationCalculator.AxialTranslation actualTranslation = axialTranslationCalculator
                .getTranslationSmallRectangle(sectionWithData, new CoordinateBounds(1, 3, 1, 3));

        // Assert
        assertEquals(3, actualTranslation.q());
        assertEquals(2, actualTranslation.r());
    }

    @Test
    void testGetTranslationSmallRectangleWithPT_SOUTHEAST_Rotation5() {
        // Arrange
        AxialTranslationCalculator axialTranslationCalculator = new AxialTranslationCalculator();
        SectionWithRotationPositionSectionDirection sectionWithData = new SectionWithRotationPositionSectionDirection(
                SectionType.A, 5, 1, SectionDirectionType.PointyTopSectionDirection.PT_SOUTHEAST);

        // Act
        AxialTranslationCalculator.AxialTranslation actualTranslation = axialTranslationCalculator
                .getTranslationSmallRectangle(sectionWithData, new CoordinateBounds(1, 3, 1, 3));

        // Assert
        assertEquals(3, actualTranslation.q());
        assertEquals(2, actualTranslation.r());
    }

    @Test
    void testGetTranslationSmallRectangleWithPT_SOUTH_Rotation0() {
        // Arrange
        AxialTranslationCalculator axialTranslationCalculator = new AxialTranslationCalculator();
        SectionWithRotationPositionSectionDirection sectionWithData = new SectionWithRotationPositionSectionDirection(
                SectionType.A, 0, 1, SectionDirectionType.PointyTopSectionDirection.PT_SOUTH);

        // Act
        AxialTranslationCalculator.AxialTranslation actualTranslation = axialTranslationCalculator
                .getTranslationSmallRectangle(sectionWithData, new CoordinateBounds(1, 3, 1, 3));

        // Assert
        assertEquals(1, actualTranslation.q());
        assertEquals(5, actualTranslation.r());
    }

    @Test
    void testGetTranslationSmallRectangleWithPT_SOUTH_Rotation3() {
        // Arrange
        AxialTranslationCalculator axialTranslationCalculator = new AxialTranslationCalculator();
        SectionWithRotationPositionSectionDirection sectionWithData = new SectionWithRotationPositionSectionDirection(
                SectionType.A, 3, 1, SectionDirectionType.PointyTopSectionDirection.PT_SOUTH);

        // Act
        AxialTranslationCalculator.AxialTranslation actualTranslation = axialTranslationCalculator
                .getTranslationSmallRectangle(sectionWithData, new CoordinateBounds(1, 3, 1, 3));

        // Assert
        assertEquals(1, actualTranslation.q());
        assertEquals(5, actualTranslation.r());
    }

    @Test
    void testGetTranslationSmallRectangleWithPT_SOUTHWEST_Rotation4() {
        // Arrange
        AxialTranslationCalculator axialTranslationCalculator = new AxialTranslationCalculator();
        SectionWithRotationPositionSectionDirection sectionWithData = new SectionWithRotationPositionSectionDirection(
                SectionType.A, 4, 1, SectionDirectionType.PointyTopSectionDirection.PT_SOUTHWEST);

        // Act
        AxialTranslationCalculator.AxialTranslation actualTranslation = axialTranslationCalculator
                .getTranslationSmallRectangle(sectionWithData, new CoordinateBounds(1, 3, 1, 3));

        // Assert
        assertEquals(-1, actualTranslation.q());
        assertEquals(3, actualTranslation.r());
    }

    @Test
    void testGetTranslationSmallRectangleWithPT_SOUTHWEST_Rotation1() {
        // Arrange
        AxialTranslationCalculator axialTranslationCalculator = new AxialTranslationCalculator();
        SectionWithRotationPositionSectionDirection sectionWithData = new SectionWithRotationPositionSectionDirection(
                SectionType.A, 1, 1, SectionDirectionType.PointyTopSectionDirection.PT_SOUTHWEST);

        // Act
        AxialTranslationCalculator.AxialTranslation actualTranslation = axialTranslationCalculator
                .getTranslationSmallRectangle(sectionWithData, new CoordinateBounds(1, 3, 1, 3));

        // Assert
        assertEquals(-1, actualTranslation.q());
        assertEquals(3, actualTranslation.r());
    }

    @Test
    void testGetTranslationSmallRectangleWithPT_NORTHWEST_Rotation5() {
        // Arrange
        AxialTranslationCalculator axialTranslationCalculator = new AxialTranslationCalculator();
        SectionWithRotationPositionSectionDirection sectionWithData = new SectionWithRotationPositionSectionDirection(
                SectionType.A, 5, 1, SectionDirectionType.PointyTopSectionDirection.PT_NORTHWEST);

        // Act
        AxialTranslationCalculator.AxialTranslation actualTranslation = axialTranslationCalculator
                .getTranslationSmallRectangle(sectionWithData, new CoordinateBounds(1, 3, 1, 3));

        // Assert
        assertEquals(1, actualTranslation.q());
        assertEquals(2, actualTranslation.r());
    }

    @Test
    void testGetTranslationSmallRectangleWithPT_NORTHWEST_Rotation2() {
        // Arrange
        AxialTranslationCalculator axialTranslationCalculator = new AxialTranslationCalculator();
        SectionWithRotationPositionSectionDirection sectionWithData = new SectionWithRotationPositionSectionDirection(
                SectionType.A, 2, 1, SectionDirectionType.PointyTopSectionDirection.PT_NORTHWEST);

        // Act
        AxialTranslationCalculator.AxialTranslation actualTranslation = axialTranslationCalculator
                .getTranslationSmallRectangle(sectionWithData, new CoordinateBounds(1, 3, 1, 3));

        // Assert
        assertEquals(1, actualTranslation.q());
        assertEquals(2, actualTranslation.r());
    }

}
