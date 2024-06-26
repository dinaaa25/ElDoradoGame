package org.utwente.Tile;

import java.awt.*;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.utwente.Board.SectionDirectionType;
import org.utwente.CaveCoin.CaveCoin;
import org.utwente.CaveCoin.CaveCoinType;

import javax.swing.event.ChangeListener;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.utwente.game.view.GameConfig.TILE_SIZE;


class TileViewTest {
    private TileView tileView;
    private Graphics2D g2d;
    private Tile tile;

    @BeforeEach
    void setUp() {
        int q = 1;
        int r = 1;
        TileType tileType = TileType.Paddle;
        int power = 3;
        ArrayList<CaveCoin> caveCoins = new ArrayList<>();
        boolean isLastWaitingTile = false;

        tile = new Tile(q, r, tileType, power, caveCoins, isLastWaitingTile);
        tileView = new TileView(tile, true); // Provide the required parameters to the constructor.
    }

    /**
     * Method under test: {@link TileView#createHexagonVertices(boolean, int, int)}
     */
    @Test
    void testCreateHexagonVertices() {
        // Arrange and Act
        Point2D.Double[] actualCreateHexagonVerticesResult = (new TileView(new Tile(1, 1, TileType.Machete, 1, true), true))
                .createHexagonVertices(true);

        // Assert
        assertEquals(0.0d, (actualCreateHexagonVerticesResult[3]).getX(), 0.01d);
        assertEquals(100d, (actualCreateHexagonVerticesResult[0]).getX(), 0.01d);
        assertEquals(24.99999999999999d, (actualCreateHexagonVerticesResult[4]).getX(), 0.01d);
        assertEquals(25.00000000000001d, (actualCreateHexagonVerticesResult[2]).getX(), 0.01d);
        assertEquals(6, actualCreateHexagonVerticesResult.length);
        assertEquals(74.99999999999997d, (actualCreateHexagonVerticesResult[5]).getX(), 0.01d);
        assertEquals(75.0d, (actualCreateHexagonVerticesResult[1]).getX(), 0.01d);
    }

    /**
     * Method under test: {@link TileView#createHexagonVertices(boolean)}
     */
    @Test
    void testCreateHexagonVertices2() {
        // Arrange and Act
        Point2D.Double[] actualCreateHexagonVerticesResult = (new TileView(new Tile(1, 1, TileType.Machete, 1, true), true))
                .createHexagonVertices(false);

        // Assert
        assertEquals(49.99999999999999d, (actualCreateHexagonVerticesResult[4]).getX(), 0.01d);
        assertEquals(50.0d, (actualCreateHexagonVerticesResult[1]).getX(), 0.01d);
        assertEquals(6, actualCreateHexagonVerticesResult.length);
        assertEquals(6.698729810778062d, (actualCreateHexagonVerticesResult[3]).getX(), 0.01d);
        assertEquals(6.698729810778076d, (actualCreateHexagonVerticesResult[2]).getX(), 0.01d);
        assertEquals(93.30127018922192d, (actualCreateHexagonVerticesResult[5]).getX(), 0.01d);
        assertEquals(93.30127018922194d, (actualCreateHexagonVerticesResult[0]).getX(), 0.01d);
    }

    /**
     * Method under test: {@link TileView#createHexagonVertices(boolean)}
     */
    @Test
    void testCreateHexagonVertices3() {
        // Arrange
        TileView tileView = new TileView(new Tile(1, 1, TileType.Machete, 1, true), true);
        tileView.addChangeListener(mock(ChangeListener.class));

        // Act
        Point2D.Double[] actualCreateHexagonVerticesResult = tileView.createHexagonVertices(true);

        // Assert
        assertEquals(0.0d, (actualCreateHexagonVerticesResult[3]).getX(), 0.01d);
        assertEquals(100.0d, (actualCreateHexagonVerticesResult[0]).getX(), 0.01d);
        assertEquals(24.99999999999998d, (actualCreateHexagonVerticesResult[4]).getX(), 0.01d);
        assertEquals(25.00000000000001d, (actualCreateHexagonVerticesResult[2]).getX(), 0.01d);
        assertEquals(6, actualCreateHexagonVerticesResult.length);
        assertEquals(74.99999999999997d, (actualCreateHexagonVerticesResult[5]).getX(), 0.01d);
        assertEquals(75.0d, (actualCreateHexagonVerticesResult[1]).getX(), 0.01d);
    }



        @Test
        void testEmptyCoins() {
            Tile tile = new Tile(2, 3, TileType.Coin, 3, null, false);
            assertFalse(tile.hasCaveCoins());
        }

        @Test
        void testGetCaveCoin() {
            Tile tile = new Tile(2, 3, TileType.Cave, 3, new ArrayList<>(List.of(new CaveCoin(1, CaveCoinType.Draw), new CaveCoin(2, CaveCoinType.Paddle))), false);
            tile.retrieveCoin();
            assertTrue(tile.hasCaveCoins());
            tile.retrieveCoin();
            assertFalse(tile.hasCaveCoins());
        }

        @Test
        void testGetEmptyCaveCoinsOptional() {
            Tile tile = new Tile(2, 3, TileType.Cave, 3, null, false);
            Optional<CaveCoin> coin = tile.retrieveCoin();
            assertEquals(coin, Optional.empty());
        }

        @Test
        void testGetCaveCoinCount() {
            Tile tile = new Tile(2, 3, TileType.Cave, 3, new ArrayList<>(List.of(new CaveCoin(1, CaveCoinType.Draw), new CaveCoin(2, CaveCoinType.Paddle))), false);
            assertEquals(2, tile.getCaveCoinCount());
            tile.retrieveCoin();
            assertEquals(1, tile.getCaveCoinCount());
            tile.retrieveCoin();
            assertEquals(0, tile.getCaveCoinCount());
            tile.retrieveCoin();
            assertEquals(0, tile.getCaveCoinCount());
        }

        @Test
        void testNeighbourTiles() {
            Tile tileTo = new Tile(0, 1, TileType.Machete, 2, new ArrayList<>(), false);
            Tile tileFrom = new Tile(0, 0, TileType.Paddle, 2, new ArrayList<>(), false);
            assertTrue(tileFrom.isNeighbor(tileTo), tileFrom + " should be neighbour of " + tileTo);
        }

        @Test
        void testNonNeighbourTiles() {
            Tile tileTo = new Tile(0, -2, TileType.Machete, 2, new ArrayList<>(), false);
            Tile tileFrom = new Tile(0, 0, TileType.Paddle, 2, new ArrayList<>(), false);
            assertFalse(tileFrom.isNeighbor(tileTo), tileFrom + " should not be neighbour of " + tileTo);
        }

        @Test
        public void testRotate() {
            Tile tile = new Tile(2, -1, TileType.Coin, 2, new ArrayList<>(), false);

            // Rotate 1 turn (60 degrees clockwise)
            tile.rotate(1);
            assertEquals(1, tile.getQ());
            assertEquals(1, tile.getR());

            // Rotate 2 more turns (120 degrees clockwise, total 3 turns)
            tile.rotate(2);
            assertEquals(-2, tile.getQ());
            assertEquals(1, tile.getR());

            // Rotate 3 more turns (180 degrees clockwise, total 6 turns)
            tile.rotate(3);
            assertEquals(2, tile.getQ());
            assertEquals(-1, tile.getR());

            // Rotate -1 turn (60 degrees counter-clockwise)
            tile.rotate(-1);
            assertEquals(1, tile.getQ());
            assertEquals(-2, tile.getR());

            // Rotate -5 more turns (300 degrees counter-clockwise, total -6 turns)
            tile.rotate(-5);
            assertEquals(2, tile.getQ());
            assertEquals(-1, tile.getR());
        }



        private FontMetrics mockFontMetrics(Graphics2D g2d) {
            FontMetrics metrics = mock(FontMetrics.class);
            when(g2d.getFontMetrics()).thenReturn(metrics);
            when(metrics.stringWidth(anyString())).thenReturn(50); // mock the width of any string
            when(metrics.getHeight()).thenReturn(20); // mock the height of the font
            when(metrics.getDescent()).thenReturn(5); // mock the descent of the font
            when(metrics.getAscent()).thenReturn(15); // mock the ascent of the font
            return metrics;
        }


        @Test
        public void testCreateHexagonVertices_FlatTop() {
            int x = 100;
            int y = 100;
            boolean flatTop = true;

            Point2D.Double[] vertices = tileView.createHexagonVertices(flatTop);

            assertEquals(6, vertices.length);
            // Check some known points
            assertEquals(x  , vertices[0].x, 0.01);
            assertEquals(y - TILE_SIZE, vertices[0].y, 0.01);
        }


        @Test
        public void testCreateHexagonPath() {
            Point2D.Double[] vertices = new Point2D.Double[] {
                    new Point2D.Double(0, 0),
                    new Point2D.Double(1, 0),
                    new Point2D.Double(1, 1),
                    new Point2D.Double(0, 1),
                    new Point2D.Double(-1, 1),
                    new Point2D.Double(-1, 0)
            };

            Path2D.Double path = tileView.createHexagonPath(vertices);

            assertNotNull(path);
            assertTrue(path.contains(0, 0));
            assertFalse(path.contains(2, 2));
        }


    }