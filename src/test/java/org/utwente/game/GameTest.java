package org.utwente.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.utwente.Board.Blockade;
import org.utwente.Board.Board;
import org.utwente.Board.Path;
import org.utwente.Tile.Tile;
import org.utwente.Tile.TileType;
import org.utwente.player.Player;

import java.awt.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {

    Game game;
    Board board;
    Player dina;
    Player mark;
    Player stijn;
    Player finn;

    @BeforeEach
    public void setUp() {
        Board.BoardBuilder boardBuilder = new Board.BoardBuilder();
        board = boardBuilder.selectPath(Path.HillsOfGold).build();
        dina = new Player("Dina");
        mark = new Player("Mark");
        stijn = new Player("Stijn");
        finn = new Player("Finn");
        List<Player> players = List.of(dina, mark, stijn, finn);
        game = new Game("El Dorado", "Welcome to the game!", board, players);
    }

    @Test
    public void testIsFinishedAtStart(){
        assertFalse(game.isFinished());
    }

    @Test
    public void testPlayerAtEnd() {
        // put the first player at the end
        List<Tile> lastTiles = board.getLastWaitingTiles();
        board.placePlayer(lastTiles.get(0), dina);

        // wait one round for 3 players
        for(int index = 0; index < 4; index++) {
            game.nextPlayer();
        }

        // assert winner
        assertTrue(game.isFinished());
        assertEquals("Dina", game.getFinalWinner().getName());
    }

    @Test
    public void testTwoPlayersAtEnd() {
        // put two players at the end
        List<Tile> lastTiles = board.getLastWaitingTiles();
        board.placePlayer(lastTiles.get(0), dina);
        board.placePlayer(lastTiles.get(1), mark);

        // wait one round for 3 players
        for(int index = 0; index < 4; index++) {
            game.nextPlayer();
        }

        // first one reaches win
        assertTrue(game.isFinished());
        assertEquals("Dina", game.getFinalWinner().getName());
    }

    @Test
    public void testIsInWaitingState() {
        assertFalse(game.isInWaitingState());
        List<Tile> lastTiles = board.getLastWaitingTiles();
        board.placePlayer(lastTiles.get(0), dina);
        assertTrue(game.isInWaitingState());
    }

    @Test
    public void testThreePlayersAtEnd() {
        // put two players at the end
        List<Tile> tiles = board.getLastWaitingTiles();
        board.placePlayer(tiles.get(0), dina);
        board.placePlayer(tiles.get(0), mark);
        board.placePlayer(tiles.get(1), stijn);

        // wait one round for 3 players
        for(int index = 0; index < 4; index++) {
            game.nextPlayer();
        }

        // first one reaches win
        assertTrue(game.isFinished());
        assertEquals("Dina", game.getFinalWinner().getName());
    }

    @Test
    public void testFourPlayersAtEnd() {
        // put two players at the end
        List<Tile> lastTiles = board.getLastWaitingTiles();
        board.placePlayer(lastTiles.get(0), dina);
        board.placePlayer(lastTiles.get(0), mark);
        board.placePlayer(lastTiles.get(1), stijn);
        board.placePlayer(lastTiles.get(2), finn);

        // wait one round for 3 players
        for(int index = 0; index < 4; index++) {
            game.nextPlayer();
        }

        // first one reaches win
        assertEquals(game.isFinished(), true);
        assertEquals("Dina", game.getFinalWinner().getName());
    }

    @Test
    public void testTwoPlayersAtEndWithBlockades() {
        // put two players at the end
        List<Tile> lastTiles = board.getLastWaitingTiles();
        Blockade blockade = new Blockade(TileType.Coin, new Point(3, -3), new Point(3, -2), 2);
        dina.addBlockade(blockade);
        board.placePlayer(lastTiles.get(0), dina);
        game.nextPlayer();
        board.placePlayer(lastTiles.get(1), mark);

        // wait one round for 3 players
        for(int index = 0; index < 3; index++) {
            game.nextPlayer();
        }

        // first one reaches win.
        assertTrue(game.isFinished());
        assertEquals("Dina", game.getFinalWinner().getName());
    }


    @Test
    public void testNextAndCurrentPlayer() {
        assertEquals("Dina", game.getCurrentPlayer().getName());
        game.nextPlayer();
        assertEquals("Mark", game.getCurrentPlayer().getName());
        game.nextPlayer();
        assertEquals("Stijn", game.getCurrentPlayer().getName());
        game.nextPlayer();
        assertEquals("Finn", game.getCurrentPlayer().getName());
    }


    @Test
    public void testGetWinner() {
        assertEquals(game.getFinalWinner(), null);
    }

    @Test
    public void testPlacePlayersStart() {
        List<Tile> startTiles = game.placePlayersStart();
        assertEquals(startTiles.size(), 4);
        for(Tile tile : startTiles) {
            assertNotNull(tile);
            assertEquals(tile.isStartingTile(), true);
        }
    }

}

