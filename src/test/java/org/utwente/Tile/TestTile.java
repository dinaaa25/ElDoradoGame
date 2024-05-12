package org.utwente.Tile;

import org.junit.jupiter.api.Test;
import org.utwente.CaveCoin.CaveCoin;
import org.utwente.CaveCoin.CaveCoinType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TestTile {
    @Test
    void testEmptyCoins() {
        Tile tile = new Tile(2, 3, TileType.Coin, 3, null);
        assertFalse(tile.hasCaveCoins());
    }

    @Test
    void testGetCaveCoin() {
        Tile tile = new Tile(2, 3, TileType.Cave, 3, new ArrayList<>(List.of(new CaveCoin(1, CaveCoinType.Draw), new CaveCoin(2, CaveCoinType.Paddle))));
        tile.retreiveCoin();
        assertTrue(tile.hasCaveCoins());
        tile.retreiveCoin();
        assertFalse(tile.hasCaveCoins());
    }

    @Test
    void testGetEmptyCaveCoinsOptional() {
        Tile tile = new Tile(2, 3, TileType.Cave, 3, null);
        Optional<CaveCoin> coin = tile.retreiveCoin();
        assertEquals(coin, Optional.empty());
    }

    @Test
    void testGetCaveCoinCount() {
        Tile tile = new Tile(2, 3, TileType.Cave, 3, new ArrayList<>(List.of(new CaveCoin(1, CaveCoinType.Draw), new CaveCoin(2, CaveCoinType.Paddle))));
        assertEquals(2, tile.getCaveCoinCount());
        tile.retreiveCoin();
        assertEquals(1, tile.getCaveCoinCount());
        tile.retreiveCoin();
        assertEquals(0, tile.getCaveCoinCount());
        tile.retreiveCoin();
        assertEquals(0, tile.getCaveCoinCount());
    }
}
