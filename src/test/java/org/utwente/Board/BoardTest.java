package org.utwente.Board;

import org.junit.Before;
import org.junit.Test;
import org.utwente.Board.Blockade.Blockade;
import org.utwente.Tile.Tile;
import org.utwente.Tile.TileType;

import java.util.Arrays;
import java.util.List;

public class BoardTest {
    Board board;

    @Before
    public void setUp() {
        board = new Board.BoardBuilder().selectPath(Path.BlockadeTest).buildPath().addBlockades().build();
    }

    @Test
    public void testBlockadeTilesRetrieval() {
        Blockade blockade = board.getBlockades().get(0);
        List<Tile> blockadeTiles =  board.getBlockadeTiles(blockade);

        Tile tile1 = new Tile(0, -3, TileType.Machete, 1, false);
        Tile tile2 = new Tile(1, -3, TileType.Basecamp, 1, false);
        Tile tile3 = new Tile(2, -3, TileType.Machete, 1, false);
        Tile tile4 = new Tile(3, -3, TileType.Machete, 1, false);

        List<Tile> expectedTiles = Arrays.asList(tile1, tile2, tile3, tile4);
        for (Tile expectedTile : expectedTiles) {
            boolean found = blockadeTiles.stream().anyMatch(tile -> tile.toString().equals(expectedTile.toString()));
            assert found : "Missing tile: " + expectedTile;
        }
    }
}
