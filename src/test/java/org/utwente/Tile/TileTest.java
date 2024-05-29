package org.utwente.Tile;

import org.junit.jupiter.api.Test;
import org.utwente.CaveCoin.CaveCoin;
import org.utwente.CaveCoin.CaveCoinType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

//@Testcontainers
class TileTest {

//    @Container
//    // wont actually do anything but will show we can load external containers and
//    // test against them
//    private static final GenericContainer<?> redisContainer = new GenericContainer<>("redis:6.2.6")
//            .withExposedPorts(6379);

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
        assertTrue(tileFrom.isNeighbor(tileTo), tileFrom.toString() + " should be neighbour of " + tileTo.toString());
    }

    @Test
    void testNonNeighbourTiles() {
        Tile tileTo = new Tile(0, -2, TileType.Machete, 2, new ArrayList<>(), false);
        Tile tileFrom = new Tile(0, 0, TileType.Paddle, 2, new ArrayList<>(), false);
        assertFalse(tileFrom.isNeighbor(tileTo), tileFrom.toString() + " should not be neighbour of " + tileTo.toString());
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
}

