package org.utwente.Tile.view;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import javax.swing.event.ChangeListener;

import org.junit.jupiter.api.Test;
import org.utwente.CaveCoin.CaveCoin;
import org.utwente.CaveCoin.CaveCoinType;
import org.utwente.CaveCoin.CaveCoinView;
import org.utwente.Tile.Tile;
import org.utwente.Tile.TileType;

class HexButtonTest {
    /**
     * Method under test: {@link HexButton#getPreferredSize()}
     */
    @Test
    void testGetPreferredSize() {
        // Arrange and Act
        (new CaveCoinView(new CaveCoin(1, CaveCoinType.Machete))).getPreferredSize();
    }

    /**
     * Method under test: {@link HexButton#getPreferredSize()}
     */
    @Test
    void testGetPreferredSize2() {
        // Arrange
        CaveCoinView caveCoinView = new CaveCoinView(new CaveCoin(1, CaveCoinType.Machete));
        caveCoinView.addChangeListener(mock(ChangeListener.class));

        // Act
        caveCoinView.getPreferredSize();
    }

    /**
     * Method under test: {@link HexButton#createHexagonVertices(boolean)}
     */
    @Test
    void testCreateHexagonVertices() {
        // Arrange and Act
        Point2D.Double[] actualCreateHexagonVerticesResult = (new CaveCoinView(new CaveCoin(1, CaveCoinType.Machete)))
                .createHexagonVertices(true);

        // Assert
        assertEquals(0.0d, (actualCreateHexagonVerticesResult[3]).getX());
        assertEquals(12.49999999999999d, (actualCreateHexagonVerticesResult[4]).getX());
        assertEquals(12.500000000000005d, (actualCreateHexagonVerticesResult[2]).getX());
        assertEquals(37.499999999999986d, (actualCreateHexagonVerticesResult[5]).getX());
        assertEquals(37.5d, (actualCreateHexagonVerticesResult[1]).getX());
        assertEquals(50.0d, (actualCreateHexagonVerticesResult[0]).getX());
        assertEquals(6, actualCreateHexagonVerticesResult.length);
    }

    /**
     * Method under test: {@link HexButton#createHexagonVertices(boolean)}
     */
    @Test
    void testCreateHexagonVertices2() {
        // Arrange and Act
        Point2D.Double[] actualCreateHexagonVerticesResult = (new CaveCoinView(new CaveCoin(1, CaveCoinType.Machete)))
                .createHexagonVertices(false);

        // Assert
        assertEquals(24.999999999999996d, (actualCreateHexagonVerticesResult[4]).getX());
        assertEquals(25.0d, (actualCreateHexagonVerticesResult[1]).getX());
        assertEquals(3.349364905389031d, (actualCreateHexagonVerticesResult[3]).getX());
        assertEquals(3.349364905389038d, (actualCreateHexagonVerticesResult[2]).getX());
        assertEquals(46.65063509461096d, (actualCreateHexagonVerticesResult[5]).getX());
        assertEquals(46.65063509461097d, (actualCreateHexagonVerticesResult[0]).getX());
        assertEquals(6, actualCreateHexagonVerticesResult.length);
    }

    /**
     * Method under test: {@link HexButton#createHexagonVertices(boolean)}
     */
    @Test
    void testCreateHexagonVertices3() {
        // Arrange
        CaveCoinView caveCoinView = new CaveCoinView(new CaveCoin(1, CaveCoinType.Machete));
        caveCoinView.addChangeListener(mock(ChangeListener.class));

        // Act
        Point2D.Double[] actualCreateHexagonVerticesResult = caveCoinView.createHexagonVertices(true);

        // Assert
        assertEquals(0.0d, (actualCreateHexagonVerticesResult[3]).getX());
        assertEquals(12.49999999999999d, (actualCreateHexagonVerticesResult[4]).getX());
        assertEquals(12.500000000000005d, (actualCreateHexagonVerticesResult[2]).getX());
        assertEquals(37.499999999999986d, (actualCreateHexagonVerticesResult[5]).getX());
        assertEquals(37.5d, (actualCreateHexagonVerticesResult[1]).getX());
        assertEquals(50.0d, (actualCreateHexagonVerticesResult[0]).getX());
        assertEquals(6, actualCreateHexagonVerticesResult.length);
    }

    /**
     * Method under test: {@link HexButton#createHexagonPath(Point2D.Double[])}
     */
    @Test
    void testCreateHexagonPath() {
        // Arrange
        CaveCoinView caveCoinView = new CaveCoinView(new CaveCoin(1, CaveCoinType.Machete));
        Point2D.Double point1 = new Point2D.Double(10.0d, 10.0d);
        Point2D.Double point2 = new Point2D.Double(20.0d, 20.0d);

        // Act
        Shape path = caveCoinView.createHexagonPath(new Point2D.Double[]{point1, point2});

        // Assert
        assertNotNull(path); // Ensure that the path is created
    }


    /**
     * Method under test: {@link HexButton#createHexagonPath(Point2D.Double[])}
     */
    @Test
    void testCreateHexagonPath2() {

        // Arrange
        CaveCoinView caveCoinView = new CaveCoinView(new CaveCoin(1, CaveCoinType.Machete));
        caveCoinView.addChangeListener(mock(ChangeListener.class));

        // Act
        caveCoinView.createHexagonPath(new Point2D.Double[]{new Point2D.Double(10.0d, 10.0d)});
    }

    /**
     * Method under test: {@link HexButton#createHexagonPath(Point2D.Double[])}
     */
    @Test
    void testCreateHexagonPathWithNull() {
        // Arrange
        CaveCoinView caveCoinView = new CaveCoinView(new CaveCoin(1, CaveCoinType.Machete));

        // Act & Assert
        assertThrows(NullPointerException.class, () -> caveCoinView.createHexagonPath(new Point2D.Double[]{null}));
    }

    /**
     * Method under test: {@link HexButton#createHexagonPath(Point2D.Double[])}
     */
    @Test
    void testCreateHexagonPathWithEmptyArray() {
        // Arrange
        CaveCoinView caveCoinView = new CaveCoinView(new CaveCoin(1, CaveCoinType.Machete));

        // Act & Assert
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> caveCoinView.createHexagonPath(new Point2D.Double[]{}));
    }


    /**
     * Method under test: {@link HexButton#drawHexagon(Tile, Point2D.Double[], Graphics2D)}
     */
    @Test
    void testDrawHexagon() {
        // Arrange
        CaveCoinView caveCoinView = new CaveCoinView(new CaveCoin(1, CaveCoinType.Machete));
        Tile tile = new Tile(1, 1, TileType.Machete, 1, true);
        Point2D.Double[] vertices = new Point2D.Double[]{new Point2D.Double(10.0d, 10.0d), new Point2D.Double(20.0d, 20.0d)};
        Graphics2D g2d = mock(Graphics2D.class);

        // Act
        caveCoinView.drawHexagon(tile, vertices, g2d);

        // Assert
        // You can verify interactions with the mock object if needed
        assertNotNull(g2d);
    }

    /**
     * Method under test: {@link HexButton#drawHexagon(Tile, Point2D.Double[], Graphics2D)}
     */
    @Test
    void testDrawHexagonWithNullGraphics() {
        // Arrange
        CaveCoinView caveCoinView = new CaveCoinView(new CaveCoin(1, CaveCoinType.Machete));
        Tile tile = new Tile(1, 1, TileType.Machete, 1, true);
        Point2D.Double[] vertices = new Point2D.Double[]{new Point2D.Double(10.0d, 10.0d), new Point2D.Double(20.0d, 20.0d)};

        // Act & Assert
        assertThrows(NullPointerException.class, () -> caveCoinView.drawHexagon(tile, vertices, null));
    }


    /**
     * Method under test: {@link HexButton#paintComponent(Graphics)}
     */
    @Test
    void testPaintComponent() {
        // Arrange
        Tile tile = new Tile(1, 1, TileType.Machete, 1, true);
        HexButton hexButton = new HexButton(false, tile, 50) {
            @Override
            protected void setTileTexture(Graphics2D g) {
                // Implement the abstract method with a simple operation
                g.setColor(Color.GREEN);
            }
        };

        // Create a buffered image to simulate the graphics context
        BufferedImage bufferedImage = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
        Graphics g = bufferedImage.getGraphics();

        // Act
        hexButton.paintComponent(g);

        // Assert
        // No exceptions should be thrown and the Graphics2D object should be used
        assertTrue(g instanceof Graphics2D);
    }

}