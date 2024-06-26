package org.utwente.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.utwente.Board.Board;
import org.utwente.Board.Path;
import org.utwente.Board.Blockade.Blockade;
import org.utwente.Section.Section;
import org.utwente.Section.SectionType;
import org.utwente.Tile.Tile;
import org.utwente.Tile.TileType;
import org.utwente.game.controller.GameController;
import org.utwente.game.model.Game;
import org.utwente.game.model.PhaseType;
import org.utwente.game.view.GameView;
import org.utwente.market.model.Card;
import org.utwente.market.model.CardType;
import org.utwente.player.model.Player;
import org.utwente.util.ValidationResult;
import org.utwente.util.event.EmptyEvent;
import org.utwente.util.event.EventManager;
import org.utwente.util.event.EventType;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MoveActionIntegrationTest {

    private GameController gameController;
    private Game game;
    private GameView gameView;
    private Player player;
    private Tile fromTile;
    private Tile toTile;
    private Card resource;
    private EventManager eventManager;

    @BeforeEach
    void setUp() throws Exception {
        // Reset EventManager singleton
        resetEventManager();

        // Mock GameView
        gameView = mock(GameView.class);

        // Initialize GameController
        gameController = new GameController(gameView);

        // Access the Game instance
        game = gameController.getGame();

        // Setup a board with sections and tiles
        List<Tile> tiles = new ArrayList<>();
        tiles.add(new Tile(1, 1, TileType.Machete, 1, new ArrayList<>(), false));
        tiles.add(new Tile(1, 2, TileType.Machete, 2, new ArrayList<>(), false));
        Section section = new Section(tiles, SectionType.A);

        List<Section> sections = new ArrayList<>();
        sections.add(section);

        Board board = new Board(sections, Path.HillsOfGold, true, new ArrayList<>());

        // Setup a player
        player = new Player("Finn");

        // Setup tiles
        fromTile = tiles.get(0);
        toTile = tiles.get(1);

        // Add tiles to board
        fromTile.setBoard(board);
        toTile.setBoard(board);

        // Add player to the fromTile
        fromTile.placePlayer(player);

        // Set the board in the game
        game.setBoard(board);

        // Add player to the game
        game.setPlayers(List.of(player));
        game.setCurrentPlayer(0); // Ensure the current player index is valid

        // Setup a resource card
        resource = new Card(CardType.Entdecker);
        resource.setPower(3); // Ensure the card has sufficient power for the move

        // Select the resource and the target tile in the game phase
        game.getPhase().getSelectedResources().push(resource);
        game.getPhase().setSelectedTile(toTile);

        // Initialize EventManager
        eventManager = EventManager.getInstance();

        // Ensure the game controller is subscribed to the MakeMove event
        eventManager.subscribe(gameController::onMakeMove, EventType.MakeMove);
    }

    @Test
    void testMakeMove() {
        // Ensure the current phase is correct
        // Components: Game, PhaseType
        assertEquals(PhaseType.BUYING_AND_PLAYING_PHASE, game.getPhase().getCurrentPhase());

        // Trigger the MakeMove event
        // Components: EventManager, EventType, GameController, Game
        eventManager.notifying(EventType.MakeMove, new EmptyEvent());

        // Validate the action message
        // Components: Game, Phase, ValidationResult
        ValidationResult actionMessage = game.getPhase().getActionMessage();
        assertFalse(actionMessage.getStatus(), actionMessage.getMessage());

        // Verify the player has moved to the destination tile
        // Components: Player, Tile, Game, Board
        assertTrue(toTile.getPlayers().contains(player));
        assertFalse(fromTile.getPlayers().contains(player));

        // Verify the source tile is empty
        // Components: Tile, Player
        assertTrue(fromTile.isEmpty());

        // Verify the game view was redrawn
        // Components: GameView, Game
        verify(gameView, atLeastOnce()).redraw();
    }

    @Test
    void testMakeMoveWithBlockade() {
        // Setup a blockade on the fromTile
        // Components: Tile, Blockade
        fromTile.setBlockade(new Blockade(TileType.Machete, 1, 1));

        // Ensure the current phase is correct
        // Components: Game, PhaseType
        assertEquals(PhaseType.BUYING_AND_PLAYING_PHASE, game.getPhase().getCurrentPhase());

        // Trigger the MakeMove event
        // Components: EventManager, EventType, GameController, Game
        eventManager.notifying(EventType.MakeMove, new EmptyEvent());

        // Validate the action message
        // Components: Game, Phase, ValidationResult
        ValidationResult actionMessage = game.getPhase().getActionMessage();
        assertTrue(actionMessage.getStatus(), actionMessage.getMessage());

        // Verify the player has moved to the destination tile
        // Components: Player, Tile, Game, Board
        assertTrue(toTile.getPlayers().contains(player));
        assertFalse(fromTile.getPlayers().contains(player));

        // Verify the source tile is empty and blockade is handled
        // Components: Tile, Player, Blockade
        assertTrue(fromTile.isEmpty());
        assertFalse(fromTile.isBlockadeTile());

        // Verify the game view was redrawn
        // Components: GameView, Game
        verify(gameView, atLeastOnce()).redraw();
    }

    private void resetEventManager() throws Exception {
        Field instance = EventManager.class.getDeclaredField("instance");
        instance.setAccessible(true);
        instance.set(null, null);
    }
}
