package org.utwente.Tile;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Color;
import java.awt.color.ColorSpace;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.utwente.Board.AxialTranslationCalculator;
import org.utwente.Board.Blockade.Blockade;
import org.utwente.CaveCoin.CaveCoin;
import org.utwente.CaveCoin.CaveCoinType;
import org.utwente.player.model.Player;

class TileTest {
    /**
     * Method under test: {@link Tile#setBlockade(Blockade)}
     */
    @Test
    void testSetBlockade() {
        // Arrange
        Tile tile = new Tile(1, 1, TileType.Machete, 1, true);
        Blockade blockade = new Blockade(TileType.Machete, 1, 1);

        // Act
        tile.setBlockade(blockade);

        // Assert
        assertTrue(tile.isBlockadeTile());
        assertSame(blockade, tile.getBlockade());
    }

    /**
     * Method under test: {@link Tile#rotate(int)}
     */
    @Test
    void testRotate() {
        // Arrange
        Tile tile = new Tile(1, 1, TileType.Machete, 1, true);

        // Act
        tile.rotate(1);

        // Assert
        assertEquals(-1, tile.getQ());
        assertEquals(2, tile.getR());
    }

    /**
     * Method under test: {@link Tile#getTileColor()}
     */
    @Test
    void testGetTileColor() {
        // Arrange and Act
        Color actualTileColor = (new Tile(1, 1, TileType.Machete, 1, true)).getTileColor();

        // Assert
        ColorSpace expectedColorSpace = actualTileColor.getColorSpace();
        assertSame(expectedColorSpace, actualTileColor.darker().getColorSpace());
    }

    /**
     * Method under test: {@link Tile#hasCaveCoins()}
     */
    @Test
    void testHasCaveCoins() {
        // Arrange, Act and Assert
        assertFalse((new Tile(1, 1, TileType.Machete, 1, true)).hasCaveCoins());
    }

    /**
     * Method under test: {@link Tile#hasCaveCoins()}
     */
    @Test
    void testHasCaveCoins2() {
        // Arrange
        ArrayList<CaveCoin> caveCoins = new ArrayList<>();
        caveCoins.add(new CaveCoin(1, CaveCoinType.Machete));

        // Act and Assert
        assertTrue((new Tile(1, 1, TileType.Machete, 1, caveCoins, true)).hasCaveCoins());
    }

    /**
     * Method under test: {@link Tile#retrieveCoin()}
     */
    @Test
    void testRetrieveCoin() {
        // Arrange, Act and Assert
        assertFalse((new Tile(1, 1, TileType.Machete, 1, true)).retrieveCoin().isPresent());
    }

    /**
     * Method under test: {@link Tile#retrieveCoin()}
     */

    @Test
    void testRetrieveCoin2(){
        // Arrange
        ArrayList<CaveCoin> caveCoins = new ArrayList<>();
        caveCoins.add(new CaveCoin(1, CaveCoinType.Machete));

        Tile tile = new Tile(1, 1, TileType.Machete, 1, true);
        tile.setCaveCoins(caveCoins);

        // Act
        Optional<CaveCoin> actualRetrieveCoinResult = tile.retrieveCoin();

        // Assert
        assertEquals(0, tile.getCaveCoinCount());
        assertTrue(actualRetrieveCoinResult.isPresent());
    }

    /**
     * Method under test: {@link Tile#getCaveCoinCount()}
     */
    @Test
    void testGetCaveCoinCount() {
        // Arrange, Act and Assert
        assertEquals(0, (new Tile(1, 1, TileType.Machete, 1, true)).getCaveCoinCount());
    }

    /**
     * Method under test: {@link Tile#isStartingTile()}
     */
    @Test
    void testIsStartingTile() {
        // Arrange, Act and Assert
        assertFalse((new Tile(1, 1, TileType.Machete, 1, true)).isStartingTile());
        assertTrue((new Tile(1, 1, TileType.Start, 1, true)).isStartingTile());
    }

    /**
     * Method under test: {@link Tile#isEndTile()}
     */
    @Test
    void testIsEndTile() {
        // Arrange, Act and Assert
        assertFalse((new Tile(1, 1, TileType.Machete, 1, true)).isEndTile());
        assertTrue((new Tile(1, 1, TileType.ElDorado, 1, true)).isEndTile());
    }

    /**
     * Method under test: {@link Tile#placePlayer(Player)}
     */
    @Test
    void testPlacePlayer() {
        // Arrange
        Tile tile = new Tile(1, 1, TileType.Machete, 1, true);

        // Act
        tile.placePlayer(new Player("Name"));

        // Assert
        assertFalse(tile.isEmpty());
    }

    /**
     * Method under test: {@link Tile#isEmpty()}
     */
    @Test
    void testIsEmpty() {
        // Arrange, Act and Assert
        assertTrue((new Tile(1, 1, TileType.Machete, 1, true)).isEmpty());
    }

    /**
     * Method under test: {@link Tile#removePlayer(Player)}
     */
    @Test
    void testRemovePlayer() {
        // Arrange
        Tile tile = new Tile(1, 1, TileType.Machete, 1, true);

        // Act
        tile.removePlayer(new Player("Name"));

        // Assert
        assertTrue(tile.isEmpty());
    }

    /**
     * Method under test: {@link Tile#isNeighbor(Tile)}
     */
    @Test
    void testIsNeighbor() {
        // Arrange
        Tile tile = new Tile(1, 1, TileType.Machete, 1, true);

        // Act and Assert
        assertFalse(tile.isNeighbor(new Tile(1, 1, TileType.Machete, 1, true)));
    }

    /**
     * Method under test: {@link Tile#isNeighbor(Tile)}
     */
    @Test
    void testIsNeighbor2() {
        // Arrange, Act and Assert
        assertFalse((new Tile(1, 1, TileType.Machete, 1, true)).isNeighbor(null));
    }

    /**
     * Method under test: {@link Tile#isNeighbor(Tile)}
     */
    @Test
    void testIsNeighbor3() {
        // Arrange
        Tile tile = new Tile(2, 1, TileType.Machete, 1, true);

        // Act and Assert
        assertTrue(tile.isNeighbor(new Tile(1, 1, TileType.Machete, 1, true)));
    }

    /**
     * Method under test:
     * {@link Tile#translate(AxialTranslationCalculator.AxialTranslation)}
     */
    @Test
    void testTranslate() {
        // Arrange
        Tile tile = new Tile(1, 1, TileType.Machete, 1, true);

        // Act
        tile.translate(new AxialTranslationCalculator.AxialTranslation(1, 1));

        // Assert
        assertEquals(2, tile.getQ());
        assertEquals(2, tile.getR());
    }

    /**
     * Methods under test:
     * <ul>
     *   <li>{@link Tile#toString()}
     *   <li>{@link Tile#getPlayers()}
     *   <li>{@link Tile#getTileType()}
     *   <li>{@link Tile#isLastWaitingTile()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        Tile tile = new Tile(1, 1, TileType.Machete, 1, true);

        // Act
        String actualToStringResult = tile.toString();
        tile.getPlayers();
        TileType actualTileType = tile.getTileType();

        // Assert
        assertEquals("Tile{x=1, y=1, tileType=Machete, power=1, caveCoins=[]}", actualToStringResult);
        assertEquals(TileType.Machete, actualTileType);
        assertTrue(tile.isLastWaitingTile());
    }

    /**
     * Method under test:
     * {@link Tile#Tile(int, int, TileType, int, ArrayList, boolean)}
     */
    @Test
    void testNewTile() {
        // Arrange, Act and Assert
        Color tileColor = (new Tile(1, 1, TileType.Machete, 1, new ArrayList<>(), true)).getTileColor();
        ColorSpace expectedColorSpace = tileColor.getColorSpace();
        assertSame(expectedColorSpace, tileColor.darker().getColorSpace());
    }

    /**
     * Method under test:
     * {@link Tile#Tile(int, int, TileType, int, ArrayList, boolean)}
     */
    @Test
    void testNewTile2() {
        // Arrange, Act and Assert
        Color tileColor = (new Tile(1, 1, TileType.Machete, 1, null, true)).getTileColor();
        ColorSpace expectedColorSpace = tileColor.getColorSpace();
        assertSame(expectedColorSpace, tileColor.darker().getColorSpace());
    }

    /**
     * Method under test:
     * {@link Tile#Tile(int, int, TileType, int, ArrayList, boolean)}
     */
    @Test
    void testNewTile3() {
        // Arrange, Act and Assert
        Color tileColor = (new Tile(1, 1, TileType.Paddle, 1, new ArrayList<>(), true)).getTileColor();
        ColorSpace expectedColorSpace = tileColor.getColorSpace();
        assertSame(expectedColorSpace, tileColor.darker().getColorSpace());
    }

    /**
     * Method under test:
     * {@link Tile#Tile(int, int, TileType, int, ArrayList, boolean)}
     */
    @Test
    void testNewTile4() {
        // Arrange, Act and Assert
        Color tileColor = (new Tile(1, 1, TileType.Coin, 1, new ArrayList<>(), true)).getTileColor();
        ColorSpace expectedColorSpace = tileColor.getColorSpace();
        assertSame(expectedColorSpace, tileColor.darker().getColorSpace());
    }

    /**
     * Method under test:
     * {@link Tile#Tile(int, int, TileType, int, ArrayList, boolean)}
     */
    @Test
    void testNewTile5() {
        // Arrange, Act and Assert
        Color tileColor = (new Tile(1, 1, TileType.Basecamp, 1, new ArrayList<>(), true)).getTileColor();
        ColorSpace expectedColorSpace = tileColor.getColorSpace();
        assertSame(expectedColorSpace, tileColor.darker().getColorSpace());
    }

    /**
     * Method under test:
     * {@link Tile#Tile(int, int, TileType, int, ArrayList, boolean)}
     */
    @Test
    void testNewTile6() {
        // Arrange
        ArrayList<CaveCoin> caveCoins = new ArrayList<>();
        caveCoins.add(new CaveCoin(1, CaveCoinType.Machete));

        // Act and Assert
        Color tileColor = (new Tile(1, 1, TileType.Machete, 1, caveCoins, true)).getTileColor();
        ColorSpace expectedColorSpace = tileColor.getColorSpace();
        assertSame(expectedColorSpace, tileColor.darker().getColorSpace());
    }

    /**
     * Method under test:
     * {@link Tile#Tile(int, int, TileType, int, ArrayList, boolean)}
     */
    @Test
    void testNewTile7() {
        // Arrange
        ArrayList<CaveCoin> caveCoins = new ArrayList<>();
        caveCoins.add(new CaveCoin(1, CaveCoinType.Machete));
        caveCoins.add(new CaveCoin(1, CaveCoinType.Machete));

        // Act and Assert
        Color tileColor = (new Tile(1, 1, TileType.Machete, 1, caveCoins, true)).getTileColor();
        ColorSpace expectedColorSpace = tileColor.getColorSpace();
        assertSame(expectedColorSpace, tileColor.darker().getColorSpace());
    }

    /**
     * Method under test:
     * {@link Tile#Tile(int, int, TileType, int, ArrayList, boolean)}
     */
    @Test
    void testNewTile8() {
        // Arrange, Act and Assert
        Color tileColor = (new Tile(1, 1, TileType.Discard, 1, new ArrayList<>(), true)).getTileColor();
        ColorSpace expectedColorSpace = tileColor.getColorSpace();
        assertSame(expectedColorSpace, tileColor.darker().getColorSpace());
    }

    /**
     * Method under test:
     * {@link Tile#Tile(int, int, TileType, int, ArrayList, boolean)}
     */
    @Test
    void testNewTile9() {
        // Arrange, Act and Assert
        Color tileColor = (new Tile(1, 1, TileType.Mountain, 1, new ArrayList<>(), true)).getTileColor();
        ColorSpace expectedColorSpace = tileColor.getColorSpace();
        assertSame(expectedColorSpace, tileColor.darker().getColorSpace());
    }

    /**
     * Method under test:
     * {@link Tile#Tile(int, int, TileType, int, ArrayList, boolean)}
     */
    @Test
    void testNewTile10() {
        // Arrange, Act and Assert
        Color tileColor = (new Tile(1, 1, TileType.Cave, 1, new ArrayList<>(), true)).getTileColor();
        ColorSpace expectedColorSpace = tileColor.getColorSpace();
        assertSame(expectedColorSpace, tileColor.darker().getColorSpace());
    }

    /**
     * Method under test:
     * {@link Tile#Tile(int, int, TileType, int, ArrayList, boolean)}
     */
    @Test
    void testNewTile11() {
        // Arrange, Act and Assert
        Color tileColor = (new Tile(1, 1, TileType.ElDorado, 1, new ArrayList<>(), true)).getTileColor();
        ColorSpace expectedColorSpace = tileColor.getColorSpace();
        assertSame(expectedColorSpace, tileColor.darker().getColorSpace());
    }

    /**
     * Method under test:
     * {@link Tile#Tile(int, int, TileType, int, ArrayList, boolean)}
     */
    @Test
    void testNewTile12() {
        // Arrange, Act and Assert
        Color tileColor = (new Tile(1, 1, TileType.Start, 1, new ArrayList<>(), true)).getTileColor();
        ColorSpace expectedColorSpace = tileColor.getColorSpace();
        assertSame(expectedColorSpace, tileColor.darker().getColorSpace());
    }

    /**
     * Method under test: {@link Tile#Tile(int, int, TileType, int, boolean)}
     */
    @Test
    void testNewTile13() {
        // Arrange, Act and Assert
        Color tileColor = (new Tile(1, 1, TileType.Machete, 1, true)).getTileColor();
        ColorSpace expectedColorSpace = tileColor.getColorSpace();
        assertSame(expectedColorSpace, tileColor.darker().getColorSpace());
    }

    /**
     * Method under test: {@link Tile#Tile(int, int, TileType, int, boolean)}
     */
    @Test
    void testNewTile14() {
        // Arrange, Act and Assert
        Color tileColor = (new Tile(1, 1, TileType.Paddle, 1, true)).getTileColor();
        ColorSpace expectedColorSpace = tileColor.getColorSpace();
        assertSame(expectedColorSpace, tileColor.darker().getColorSpace());
    }

    /**
     * Method under test: {@link Tile#Tile(int, int, TileType, int, boolean)}
     */
    @Test
    void testNewTile15() {
        // Arrange, Act and Assert
        Color tileColor = (new Tile(1, 1, TileType.Coin, 1, true)).getTileColor();
        ColorSpace expectedColorSpace = tileColor.getColorSpace();
        assertSame(expectedColorSpace, tileColor.darker().getColorSpace());
    }

    /**
     * Method under test: {@link Tile#Tile(int, int, TileType, int, boolean)}
     */
    @Test
    void testNewTile16() {
        // Arrange, Act and Assert
        Color tileColor = (new Tile(1, 1, TileType.Basecamp, 1, true)).getTileColor();
        ColorSpace expectedColorSpace = tileColor.getColorSpace();
        assertSame(expectedColorSpace, tileColor.darker().getColorSpace());
    }

    /**
     * Method under test: {@link Tile#Tile(int, int, TileType, int, boolean)}
     */
    @Test
    void testNewTile17() {
        // Arrange, Act and Assert
        Color tileColor = (new Tile(1, 1, TileType.Discard, 1, true)).getTileColor();
        ColorSpace expectedColorSpace = tileColor.getColorSpace();
        assertSame(expectedColorSpace, tileColor.darker().getColorSpace());
    }

    /**
     * Method under test: {@link Tile#Tile(int, int, TileType, int, boolean)}
     */
    @Test
    void testNewTile18() {
        // Arrange, Act and Assert
        Color tileColor = (new Tile(1, 1, TileType.Mountain, 1, true)).getTileColor();
        ColorSpace expectedColorSpace = tileColor.getColorSpace();
        assertSame(expectedColorSpace, tileColor.darker().getColorSpace());
    }

    /**
     * Method under test: {@link Tile#Tile(int, int, TileType, int, boolean)}
     */
    @Test
    void testNewTile19() {
        // Arrange, Act and Assert
        Color tileColor = (new Tile(1, 1, TileType.Cave, 1, true)).getTileColor();
        ColorSpace expectedColorSpace = tileColor.getColorSpace();
        assertSame(expectedColorSpace, tileColor.darker().getColorSpace());
    }

    /**
     * Method under test: {@link Tile#Tile(int, int, TileType, int, boolean)}
     */
    @Test
    void testNewTile20() {
        // Arrange, Act and Assert
        Color tileColor = (new Tile(1, 1, TileType.ElDorado, 1, true)).getTileColor();
        ColorSpace expectedColorSpace = tileColor.getColorSpace();
        assertSame(expectedColorSpace, tileColor.darker().getColorSpace());
    }

    /**
     * Method under test: {@link Tile#Tile(int, int, TileType, int, boolean)}
     */
    @Test
    void testNewTile21() {
        // Arrange, Act and Assert
        Color tileColor = (new Tile(1, 1, TileType.Start, 1, true)).getTileColor();
        ColorSpace expectedColorSpace = tileColor.getColorSpace();
        assertSame(expectedColorSpace, tileColor.darker().getColorSpace());
    }
}
