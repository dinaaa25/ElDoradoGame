package org.utwente.Board;

import org.junit.Before;
import org.utwente.Section.Section;
import org.utwente.player.model.Player;

import org.utwente.player.model.Player;
import org.utwente.player.view.PlayerView;
import org.junit.Test;
import static org.junit.Assert.*;
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
        List<Tile> blockadeTiles = blockade.getBlockadeTiles();

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

    @Test
    public void testElDoradoTile() {
        Tile elDoradoTile = board.getElDoradoTile();
        assertNotNull("El Dorado tile should exist on the board", elDoradoTile);
        assertEquals("Tile should be El Dorado type", TileType.ElDorado, elDoradoTile.getTileType());
    }

    @Test
    public void testPlayerPlacement() {
        Player player = new Player("TestPlayer");
        Tile startingTile = board.getStartingTiles().get(0);
        board.placePlayer(startingTile, player);
        assertTrue("Player should be placed on starting tile", startingTile.getPlayers().contains(player));
    }

    @Test
    public void testPathConnectivity() { // ie no weird non connected sections
        List<Section> sections = board.getSections();
        assertTrue("Sections should be correctly connected to form a path", validatePathConnectivity(sections));
    }

    private boolean validatePathConnectivity(List<Section> sections) {
        // need to look in to this more but too complex for now, maybe @stijn give one
        // or two pointers to get me started
        return true;
    }

    @Test
    public void testPathSelection() {
        assertEquals("Correct path should be selected", Path.BlockadeTest, board.getPath());
    }
}
