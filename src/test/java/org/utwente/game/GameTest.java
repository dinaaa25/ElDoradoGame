package org.utwente.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.utwente.Board.Blockade;
import org.utwente.Board.Board;
import org.utwente.Board.Path;
import org.utwente.Tile.Tile;
import org.utwente.player.Player;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
        assertEquals(game.isFinished(), false);
    }

    @Test
    public void testPlayerAtEnd() {
        // put the first player at the end
        Tile lastTile = board.getElDoradoTile();
        board.placePlayer(lastTile, dina);

        // wait one round for 3 players
        for(int index = 0; index < 4; index++) {
            game.nextPlayer();
        }

        // assert winner
        assertEquals(game.isFinished(), true);
        assertEquals(game.getFinalWinner().getName(), "Dina");
    }

    @Test
    public void testTwoPlayersAtEnd() {
        // put two players at the end
        Tile lastTile = board.getElDoradoTile();
        board.placePlayer(lastTile, dina);
        board.placePlayer(lastTile, mark);

        // wait one round for 3 players
        for(int index = 0; index < 4; index++) {
            game.nextPlayer();
        }

        // first one reaches win
        assertEquals(game.isFinished(), true);
        assertEquals(game.getFinalWinner().getName(), "Dina");
    }

    @Test
    public void testThreePlayersAtEnd() {
        // put two players at the end
        Tile lastTile = board.getElDoradoTile();
        board.placePlayer(lastTile, dina);
        board.placePlayer(lastTile, mark);
        board.placePlayer(lastTile, stijn);

        // wait one round for 3 players
        for(int index = 0; index < 4; index++) {
            game.nextPlayer();
        }

        // first one reaches win
        assertEquals(game.isFinished(), true);
        assertEquals(game.getFinalWinner().getName(), "Dina");
    }

    @Test
    public void testFourPlayersAtEnd() {
        // put two players at the end
        Tile lastTile = board.getElDoradoTile();
        board.placePlayer(lastTile, dina);
        board.placePlayer(lastTile, mark);
        board.placePlayer(lastTile, stijn);
        board.placePlayer(lastTile, finn);

        // wait one round for 3 players
        for(int index = 0; index < 4; index++) {
            game.nextPlayer();
        }

        // first one reaches win
        assertEquals(game.isFinished(), true);
        assertEquals(game.getFinalWinner().getName(), "Dina");
    }

    @Test
    public void testTwoPlayersAtEndWithBlockades() {
        // put two players at the end
        Tile lastTile = board.getElDoradoTile();
        Blockade blockade = new Blockade();
        dina.addBlockade(blockade);
        board.placePlayer(lastTile, dina);
        game.nextPlayer();
        board.placePlayer(lastTile, mark);

        // wait one round for 3 players
        for(int index = 0; index < 3; index++) {
            game.nextPlayer();
        }

        // first one reaches win
        assertEquals(game.isFinished(), true);
        assertEquals(game.getFinalWinner().getName(), "Dina");
    }


    @Test
    public void testNextAndCurrentPlayer() {
        assertEquals(game.getCurrentPlayer().getName(), "Dina");
        game.nextPlayer();
        assertEquals(game.getCurrentPlayer().getName(), "Mark");
        game.nextPlayer();
        assertEquals(game.getCurrentPlayer().getName(), "Stijn");
        game.nextPlayer();
        assertEquals(game.getCurrentPlayer().getName(), "Finn");
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
            assertEquals(tile.isStartTile(), true);
        }
    }

}

