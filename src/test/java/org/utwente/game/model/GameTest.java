package org.utwente.game.model;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.utwente.Board.Blockade.Blockade;
import org.utwente.Board.Board;
import org.utwente.Board.Path;
import org.utwente.Section.Section;
import org.utwente.Section.SectionType;
import org.utwente.Tile.Tile;
import org.utwente.Tile.TileType;
import org.utwente.market.model.Card;
import org.utwente.market.model.Market;
import org.utwente.player.model.CardPile;
import org.utwente.player.model.Player;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {



    Game game;
    Board board;
    Player dina;
    Player mark;
    Player stijn;
    Player finn;

    @BeforeEach
    public void setUp() {
        Board.BoardBuilder boardBuilder = new Board.BoardBuilder();
        board = boardBuilder.selectPath(Path.HillsOfGold).buildPath().addBlockades().build();
        dina = new Player("Dina");
        mark = new Player("Mark");
        stijn = new Player("Stijn");
        finn = new Player("Finn");
        List<Player> players = List.of(dina, mark, stijn, finn);
        game = new Game("El Dorado", "Welcome to the game!", board, players);
    }

    @Test
    public void testIsFinishedAtStart() {
        assertFalse(game.isFinished());
    }

    @Test
    public void testGameDescriptionAndBoard() {
        assertEquals("Welcome to the game!", game.getGameDescription(),
                "Game description should match the provided description.");
        assertEquals(board, game.getBoard(), "Should return the correct game board.");
    }

    @Test
    public void testPlayerAtEnd() {
        // put the first player at the end
        List<Tile> lastTiles = board.getLastWaitingTiles();
        board.placePlayer(lastTiles.get(0), dina);

        // wait one round for 3 players
        for (int index = 0; index < 4; index++) {
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
        for (int index = 0; index < 4; index++) {
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
        for (int index = 0; index < 4; index++) {
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
        for (int index = 0; index < 4; index++) {
            game.nextPlayer();
        }

        // first one reaches win
        assertTrue(game.isFinished());
        assertEquals("Dina", game.getFinalWinner().getName());
    }

    @Test
    public void testTwoPlayersAtEndWithBlockades() {
        // put two players at the end
        List<Tile> lastTiles = board.getLastWaitingTiles();
        Blockade blockade = new Blockade(TileType.Coin, 1, 1);
        dina.addBlockade(blockade);
        board.placePlayer(lastTiles.get(0), dina);
        game.nextPlayer();
        board.placePlayer(lastTiles.get(1), mark);

        // wait one round for 3 players
        for (int index = 0; index < 3; index++) {
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
    public void testGetPlayers() {
        List<Player> players = game.getPlayers();
        assertNotNull(players, "Player list should not be null.");
        assertEquals(4, players.size(), "Should have four players.");
    }

    @Test
    public void testPlayerCycling() {
        assertEquals(dina, game.getCurrentPlayer(), "Initial current player should be Dina.");
        game.nextPlayer();
        assertEquals(mark, game.getCurrentPlayer(), "Current player should cycle to Mark.");
        game.nextPlayer();
        assertEquals(stijn, game.getCurrentPlayer(), "Continue cycling through players to Stijn.");
        game.nextPlayer();
        assertEquals(finn, game.getCurrentPlayer(), "Continue cycling through players to Finn.");
        game.nextPlayer(); // Wrap around to the first player
        assertEquals(dina, game.getCurrentPlayer(), "Should wrap around to the first player, Dina.");
    }

    @Test
    public void testGetWinner() {
        assertNull(game.getFinalWinner());
    }

    @Test
    public void testPlacePlayersStart() {
        List<Tile> startTiles = game.placePlayersStart();
        assertEquals(startTiles.size(), 4);
        for (Tile tile : startTiles) {
            assertNotNull(tile);
            assertTrue(tile.isStartingTile());
        }
    }
    /**
     * Method under test: {@link Game#getCurrentPlayer()}
     */
    @Test
    void testGetCurrentPlayer() {
        // Arrange
        ArrayList<Player> players = new ArrayList<>();
        Player player = new Player("Name");
        players.add(player);
        ArrayList<Section> sections = new ArrayList<>();

        // Act and Assert
        assertSame(player, (new Game("Game Name", "Game Description",
                new Board(sections, Path.HillsOfGold, true, new ArrayList<>()), players)).getCurrentPlayer());
    }

    /**
     * Method under test: {@link Game#nextPlayer()}
     */
    @Test
    void testNextPlayer() {
        // Arrange
        Game game = new Game();
        ArrayList<Section> sections = new ArrayList<>();
        game.setBoard(new Board(sections, Path.HillsOfGold, true, new ArrayList<>()));

        // Act and Assert
        assertEquals(0, game.nextPlayer());
    }

    /**
     * Method under test: {@link Game#nextPlayer()}
     */
    @Test
    void testNextPlayer2() {
        // Arrange
        ArrayList<Section> sections = new ArrayList<>();
        sections.add(new Section(new ArrayList<>(), SectionType.A));
        Board board = new Board(sections, Path.HillsOfGold, true, new ArrayList<>());

        Game game = new Game();
        game.setBoard(board);

        // Act and Assert
        assertEquals(0, game.nextPlayer());
    }

    /**
     * Method under test: {@link Game#nextPlayer()}
     */
    @Test
    void testNextPlayer3() {
        // Arrange
        ArrayList<Section> sections = new ArrayList<>();
        sections.add(new Section(new ArrayList<>(), SectionType.A));
        sections.add(new Section(new ArrayList<>(), SectionType.A));
        Board board = new Board(sections, Path.HillsOfGold, true, new ArrayList<>());

        Game game = new Game();
        game.setBoard(board);

        // Act and Assert
        assertEquals(0, game.nextPlayer());
    }

    /**
     * Method under test: {@link Game#nextPlayer()}
     */
    @Test
    void testNextPlayer4() {
        // Arrange
        ArrayList<Tile> tiles = new ArrayList<>();
        tiles.add(new Tile(1, 1, TileType.Machete, 1, true));
        Section section = new Section(tiles, SectionType.A);

        ArrayList<Section> sections = new ArrayList<>();
        sections.add(section);
        Board board = new Board(sections, Path.HillsOfGold, true, new ArrayList<>());

        Game game = new Game();
        game.setBoard(board);

        // Act and Assert
        assertEquals(0, game.nextPlayer());
    }

    /**
     * Method under test: {@link Game#nextPlayer()}
     */
    @Test
    void testNextPlayer5() {
        // Arrange
        ArrayList<Tile> tiles = new ArrayList<>();
        tiles.add(new Tile(1, 1, TileType.Machete, 1, false));
        Section section = new Section(tiles, SectionType.A);

        ArrayList<Section> sections = new ArrayList<>();
        sections.add(section);
        Board board = new Board(sections, Path.HillsOfGold, true, new ArrayList<>());

        Game game = new Game();
        game.setBoard(board);

        // Act and Assert
        assertEquals(0, game.nextPlayer());
    }

    /**
     * Method under test: {@link Game#getMaxBlockadePlayers()}
     */
    @Test
    void testGetMaxBlockadePlayers() {
        // Arrange, Act and Assert
        assertTrue((new Game()).getMaxBlockadePlayers().isEmpty());
    }

    /**
     * Method under test: {@link Game#getMaxBlockadePlayers()}
     */
    @Test
    void testGetMaxBlockadePlayers2() {
        // Arrange
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("Name"));
        ArrayList<Section> sections = new ArrayList<>();

        // Act and Assert
        assertTrue((new Game("Game Name", "Game Description",
                new Board(sections, Path.HillsOfGold, true, new ArrayList<>()), players)).getMaxBlockadePlayers().isEmpty());
    }

    /**
     * Method under test: {@link Game#getMaxBlockadePlayers()}
     */
    @Test
    void testGetMaxBlockadePlayers3() {
        // Arrange
        Player player = new Player("Name");
        player.addBlockade(new Blockade(TileType.Machete, 1, 1));

        ArrayList<Player> players = new ArrayList<>();
        players.add(player);
        ArrayList<Section> sections = new ArrayList<>();

        // Act and Assert
        assertEquals(1, (new Game("Game Name", "Game Description",
                new Board(sections, Path.HillsOfGold, true, new ArrayList<>()), players)).getMaxBlockadePlayers().size());
    }

    /**
     * Method under test: {@link Game#getMaxBlockadePlayers()}
     */
    @Test
    void testGetMaxBlockadePlayers4() {
        // Arrange
        Player player = new Player("Name");
        player.addBlockade(new Blockade(TileType.Machete, 1, 1));
        player.addBlockade(new Blockade(TileType.Machete, 1, 1));

        ArrayList<Player> players = new ArrayList<>();
        players.add(player);
        ArrayList<Section> sections = new ArrayList<>();

        // Act and Assert
        assertEquals(1, (new Game("Game Name", "Game Description",
                new Board(sections, Path.HillsOfGold, true, new ArrayList<>()), players)).getMaxBlockadePlayers().size());
    }

    /**
     * Method under test: {@link Game#compareBlockadePower(List)}
     */
    @Test
    void testCompareBlockadePower() {
        // Arrange
        Game game = new Game();

        Player player = new Player("Name");
        player.addBlockade(new Blockade(TileType.Machete, 1, 1));

        ArrayList<Player> players = new ArrayList<>();
        players.add(player);

        // Act and Assert
        assertSame(player, game.compareBlockadePower(players));
    }

    /**
     * Method under test: {@link Game#setWinner()}
     */
    @Test
    void testSetWinner() {
        // Arrange
        Game game = new Game();
        ArrayList<Section> sections = new ArrayList<>();
        game.setBoard(new Board(sections, Path.HillsOfGold, true, new ArrayList<>()));

        // Act
        game.setWinner();

        // Assert that nothing has changed
        assertEquals(0, game.getWaitCounter());
        assertEquals(0, game.getWaitingPlayerIndex());
        assertEquals(PhaseType.BUYING_AND_PLAYING_PHASE, game.getPhase().getCurrentPhase());
        assertFalse(game.isEnteredWaitingState());
        assertFalse(game.isFinished());
        assertFalse(game.isInWaitingState());
        assertEquals(sections, game.getMaxBlockadePlayers());
    }

    /**
     * Method under test: {@link Game#setWinner()}
     */
    @Test
    void testSetWinner2() {
        // Arrange
        ArrayList<Section> sections = new ArrayList<>();
        ArrayList<Tile> tiles = new ArrayList<>();
        sections.add(new Section(tiles, SectionType.A));
        Board board = new Board(sections, Path.HillsOfGold, true, new ArrayList<>());

        Game game = new Game();
        game.setBoard(board);

        // Act
        game.setWinner();

        // Assert that nothing has changed
        assertEquals(0, game.getWaitCounter());
        assertEquals(0, game.getWaitingPlayerIndex());
        assertEquals(PhaseType.BUYING_AND_PLAYING_PHASE, game.getPhase().getCurrentPhase());
        assertFalse(game.isEnteredWaitingState());
        assertFalse(game.isFinished());
        assertFalse(game.isInWaitingState());
        assertEquals(tiles, game.getMaxBlockadePlayers());
    }

    /**
     * Method under test: {@link Game#setWinner()}
     */
    @Test
    void testSetWinner3() {
        // Arrange
        ArrayList<Section> sections = new ArrayList<>();
        ArrayList<Tile> tiles = new ArrayList<>();
        sections.add(new Section(tiles, SectionType.A));
        sections.add(new Section(new ArrayList<>(), SectionType.A));
        Board board = new Board(sections, Path.HillsOfGold, true, new ArrayList<>());

        Game game = new Game();
        game.setBoard(board);

        // Act
        game.setWinner();

        // Assert that nothing has changed
        assertEquals(0, game.getWaitCounter());
        assertEquals(0, game.getWaitingPlayerIndex());
        assertEquals(PhaseType.BUYING_AND_PLAYING_PHASE, game.getPhase().getCurrentPhase());
        assertFalse(game.isEnteredWaitingState());
        assertFalse(game.isFinished());
        assertFalse(game.isInWaitingState());
        assertEquals(tiles, game.getMaxBlockadePlayers());
    }

    /**
     * Method under test: {@link Game#setWinner()}
     */
    @Test
    void testSetWinner4() {
        // Arrange
        ArrayList<Tile> tiles = new ArrayList<>();
        tiles.add(new Tile(1, 1, TileType.Machete, 1, true));
        Section section = new Section(tiles, SectionType.A);

        ArrayList<Section> sections = new ArrayList<>();
        sections.add(section);
        ArrayList<Blockade> blockades = new ArrayList<>();
        Board board = new Board(sections, Path.HillsOfGold, true, blockades);

        Game game = new Game();
        game.setBoard(board);

        // Act
        game.setWinner();

        // Assert that nothing has changed
        assertEquals(0, game.getWaitCounter());
        assertEquals(0, game.getWaitingPlayerIndex());
        assertEquals(PhaseType.BUYING_AND_PLAYING_PHASE, game.getPhase().getCurrentPhase());
        assertFalse(game.isEnteredWaitingState());
        assertFalse(game.isFinished());
        assertFalse(game.isInWaitingState());
        assertEquals(blockades, game.getMaxBlockadePlayers());
    }

    /**
     * Method under test: {@link Game#setWinner()}
     */
    @Test
    void testSetWinner5() {
        // Arrange
        ArrayList<Tile> tiles = new ArrayList<>();
        tiles.add(new Tile(3, 3, TileType.Machete, 3, true));
        tiles.add(new Tile(1, 1, TileType.Machete, 1, true));
        Section section = new Section(tiles, SectionType.A);

        ArrayList<Section> sections = new ArrayList<>();
        sections.add(section);
        ArrayList<Blockade> blockades = new ArrayList<>();
        Board board = new Board(sections, Path.HillsOfGold, true, blockades);

        Game game = new Game();
        game.setBoard(board);

        // Act
        game.setWinner();

        // Assert that nothing has changed
        assertEquals(0, game.getWaitCounter());
        assertEquals(0, game.getWaitingPlayerIndex());
        assertEquals(PhaseType.BUYING_AND_PLAYING_PHASE, game.getPhase().getCurrentPhase());
        assertFalse(game.isEnteredWaitingState());
        assertFalse(game.isFinished());
        assertFalse(game.isInWaitingState());
        assertEquals(blockades, game.getMaxBlockadePlayers());
    }

    /**
     * Method under test: {@link Game#setWinner()}
     */
    @Test
    void testSetWinner6() {
        // Arrange
        ArrayList<Tile> tiles = new ArrayList<>();
        tiles.add(new Tile(1, 1, TileType.Machete, 1, false));
        Section section = new Section(tiles, SectionType.A);

        ArrayList<Section> sections = new ArrayList<>();
        sections.add(section);
        ArrayList<Blockade> blockades = new ArrayList<>();
        Board board = new Board(sections, Path.HillsOfGold, true, blockades);

        Game game = new Game();
        game.setBoard(board);

        // Act
        game.setWinner();

        // Assert that nothing has changed
        assertEquals(0, game.getWaitCounter());
        assertEquals(0, game.getWaitingPlayerIndex());
        assertEquals(PhaseType.BUYING_AND_PLAYING_PHASE, game.getPhase().getCurrentPhase());
        assertFalse(game.isEnteredWaitingState());
        assertFalse(game.isFinished());
        assertFalse(game.isInWaitingState());
        assertEquals(blockades, game.getMaxBlockadePlayers());
    }



    /**
     * Method under test: {@link Game#isInWaitingState()}
     */
    @Test
    void testIsInWaitingState2() {
        // Arrange
        ArrayList<Section> sections = new ArrayList<>();
        sections.add(new Section(new ArrayList<>(), SectionType.A));
        Board board = new Board(sections, Path.HillsOfGold, true, new ArrayList<>());

        Game game = new Game();
        game.setBoard(board);

        // Act and Assert
        assertFalse(game.isInWaitingState());
    }

    /**
     * Method under test: {@link Game#isInWaitingState()}
     */
    @Test
    void testIsInWaitingState3() {
        // Arrange
        ArrayList<Section> sections = new ArrayList<>();
        sections.add(new Section(new ArrayList<>(), SectionType.A));
        sections.add(new Section(new ArrayList<>(), SectionType.A));
        Board board = new Board(sections, Path.HillsOfGold, true, new ArrayList<>());

        Game game = new Game();
        game.setBoard(board);

        // Act and Assert
        assertFalse(game.isInWaitingState());
    }

    /**
     * Method under test: {@link Game#isInWaitingState()}
     */
    @Test
    void testIsInWaitingState4() {
        // Arrange
        ArrayList<Tile> tiles = new ArrayList<>();
        tiles.add(new Tile(1, 1, TileType.Machete, 1, true));
        Section section = new Section(tiles, SectionType.A);

        ArrayList<Section> sections = new ArrayList<>();
        sections.add(section);
        Board board = new Board(sections, Path.HillsOfGold, true, new ArrayList<>());

        Game game = new Game();
        game.setBoard(board);

        // Act and Assert
        assertFalse(game.isInWaitingState());
    }

    /**
     * Method under test: {@link Game#isInWaitingState()}
     */
    @Test
    void testIsInWaitingState5() {
        // Arrange
        ArrayList<Tile> tiles = new ArrayList<>();
        tiles.add(new Tile(1, 1, TileType.Machete, 1, false));
        Section section = new Section(tiles, SectionType.A);

        ArrayList<Section> sections = new ArrayList<>();
        sections.add(section);
        Board board = new Board(sections, Path.HillsOfGold, true, new ArrayList<>());

        Game game = new Game();
        game.setBoard(board);

        // Act and Assert
        assertFalse(game.isInWaitingState());
    }

    /**
     * Method under test: {@link Game#Game()}
     */
    @Test
    void testNewGame() {
        // Arrange and Act
        Game actualGame = new Game();

        // Assert
        Phase phase = actualGame.getPhase();
        assertNull(phase.getActionMessage());
        assertEquals(0, actualGame.getWaitCounter());
        assertEquals(0, actualGame.getWaitingPlayerIndex());
        Market market = actualGame.getMarket();
        assertEquals(11, market.getReserve().size());
        assertEquals(6, market.getCurrent().size());
        assertEquals(PhaseType.BUYING_AND_PLAYING_PHASE, phase.getCurrentPhase());
        assertFalse(actualGame.isEnteredWaitingState());
        assertFalse(actualGame.isFinished());
        assertFalse(phase.isMoveThoughPlayers());
        assertTrue(actualGame.getMaxBlockadePlayers().isEmpty());
        assertTrue(phase.getPlayedResources().isEmpty());
        assertTrue(phase.getSelectedResources().isEmpty());
    }

    /**
     * Method under test: {@link Game#Game(String, String, Board, List)}
     */
    @Test
    void testNewGame2() {
        // Arrange
        ArrayList<Section> sections = new ArrayList<>();
        Board board = new Board(sections, Path.HillsOfGold, true, new ArrayList<>());

        // Act
        Game actualGame = new Game("Game Name", "Game Description", board, new ArrayList<>());

        // Assert
        assertEquals("Game Description", actualGame.getGameDescription());
        assertEquals("Game Name", actualGame.getGameName());
        Phase phase = actualGame.getPhase();
        assertNull(phase.getActionMessage());
        assertEquals(0, actualGame.getWaitCounter());
        assertEquals(0, actualGame.getWaitingPlayerIndex());
        Market market = actualGame.getMarket();
        assertEquals(11, market.getReserve().size());
        assertEquals(6, market.getCurrent().size());
        assertEquals(PhaseType.BUYING_AND_PLAYING_PHASE, phase.getCurrentPhase());
        assertFalse(actualGame.isEnteredWaitingState());
        assertFalse(actualGame.isFinished());
        assertFalse(actualGame.isInWaitingState());
        assertFalse(phase.isMoveThoughPlayers());
        assertTrue(phase.getPlayedResources().isEmpty());
        assertTrue(phase.getSelectedResources().isEmpty());
        assertEquals(sections, actualGame.getMaxBlockadePlayers());
    }

    /**
     * Method under test: {@link Game#Game(String, String, Board, List)}
     */
    @Test
    void testNewGame3() {
        // Arrange
        ArrayList<Section> sections = new ArrayList<>();
        Board board = new Board(sections, Path.HillsOfGold, true, new ArrayList<>());

        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("Name"));

        // Act and Assert
        CardPile faceUpDiscardPile = (new Game("Game Name", "Game Description", board, players)).getCurrentPlayer()
                .getFaceUpDiscardPile();
        List<Card> expectedCards = faceUpDiscardPile.getResources();
        assertSame(expectedCards, faceUpDiscardPile.getCards());
    }

    /**
     * Method under test: {@link Game#Game(String, String, Board, List)}
     */
    @Test
    void testNewGame4() {
        // Arrange
        ArrayList<Section> sections = new ArrayList<>();
        Board board = new Board(sections, Path.HillsOfGold, true, new ArrayList<>());

        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("Name"));
        players.add(new Player("Name"));

        // Act and Assert
        CardPile faceUpDiscardPile = (new Game("Game Name", "Game Description", board, players)).getCurrentPlayer()
                .getFaceUpDiscardPile();
        List<Card> expectedCards = faceUpDiscardPile.getResources();
        assertSame(expectedCards, faceUpDiscardPile.getCards());
    }

    




    /**
     * Method under test: {@link Game#placePlayersStart()}
     */
    @Test
    void testPlacePlayersStart2() {
        // Arrange
        ArrayList<Section> sections = new ArrayList<>();
        sections.add(new Section(new ArrayList<>(), SectionType.A));
        Board board = new Board(sections, Path.HillsOfGold, true, new ArrayList<>());

        Game game = new Game();
        game.setBoard(board);

        // Act and Assert
        assertTrue(game.placePlayersStart().isEmpty());
    }

    /**
     * Method under test: {@link Game#placePlayersStart()}
     */
    @Test
    void testPlacePlayersStart3() {
        // Arrange
        ArrayList<Section> sections = new ArrayList<>();
        sections.add(new Section(new ArrayList<>(), SectionType.A));
        sections.add(new Section(new ArrayList<>(), SectionType.A));
        Board board = new Board(sections, Path.HillsOfGold, true, new ArrayList<>());

        Game game = new Game();
        game.setBoard(board);

        // Act and Assert
        assertTrue(game.placePlayersStart().isEmpty());
    }

    /**
     * Method under test: {@link Game#placePlayersStart()}
     */
    @Test
    void testPlacePlayersStart4() {
        // Arrange
        ArrayList<Tile> tiles = new ArrayList<>();
        tiles.add(new Tile(1, 1, TileType.Machete, 1, true));
        Section section = new Section(tiles, SectionType.A);

        ArrayList<Section> sections = new ArrayList<>();
        sections.add(section);
        Board board = new Board(sections, Path.HillsOfGold, true, new ArrayList<>());

        Game game = new Game();
        game.setBoard(board);

        // Act and Assert
        assertTrue(game.placePlayersStart().isEmpty());
    }

    /**
     * Method under test: {@link Game#placePlayersStart()}
     */
    @Test
    void testPlacePlayersStart5() {
        // Arrange
        ArrayList<Tile> tiles = new ArrayList<>();
        tiles.add(new Tile(1, 1, TileType.Start, 1, true));
        Section section = new Section(tiles, SectionType.A);

        ArrayList<Section> sections = new ArrayList<>();
        sections.add(section);
        Board board = new Board(sections, Path.HillsOfGold, true, new ArrayList<>());

        Game game = new Game();
        game.setBoard(board);

        // Act and Assert
        assertEquals(1, game.placePlayersStart().size());
    }
    /**
     * Methods under test:
     * <ul>
     *   <li>{@link Game#setFinish(boolean)}
     *   <li>{@link Game#executeAction(Action)}
     *   <li>{@link Game#getBoard()}
     *   <li>{@link Game#getFinalWinner()}
     *   <li>{@link Game#getGameDescription()}
     *   <li>{@link Game#getGameName()}
     *   <li>{@link Game#getPlayers()}
     *   <li>{@link Game#isFinished()}
     * </ul>
     */
    @Test
    void testRunGame() {
        // Arrange
        ArrayList<Tile> tiles = new ArrayList<>();
        tiles.add(new Tile(1, 1, TileType.Start, 1, true));
        Section section = new Section(tiles, SectionType.A);

        ArrayList<Section> sections = new ArrayList<>();
        sections.add(section);
        Board board = new Board(sections, Path.Swamplands, true, new ArrayList<>());
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player("Dina"));
        players.add(new Player("Stijn"));
        Game game = new Game("Game Name", "Game Description", board, players);

        //mock stdin commands
        String input = "move1\nmove2\nquit\n";
        InputStream originalIn = System.in;
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        try {
            game.runGame();
        } finally {
            //reset stdin
            System.setIn(originalIn);
        }
        assertTrue(game.isFinished());
    }

    /**
     * Method under test: {@link Game#nextPhase()}
     */
    @Test
    void testNextPhase() {
        // Arrange
        Game game = new Game();

        // Act
        game.nextPhase();

        // Assert
        assertEquals(PhaseType.DISCARD_PHASE, game.getPhase().getCurrentPhase());
    }
}
