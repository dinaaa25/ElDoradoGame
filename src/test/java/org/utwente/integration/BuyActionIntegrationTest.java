package org.utwente.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.utwente.Board.Board;
import org.utwente.Board.Path;
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
import org.utwente.market.controller.BuyEvent;
import org.utwente.util.event.EventManager;
import org.utwente.util.event.EventType;
import org.utwente.game.model.Configuration;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BuyActionIntegrationTest {

    private GameController gameController;
    private Game game;
    private GameView gameView;
    private Player player;
    private EventManager eventManager;

    @BeforeEach
    void setUp() throws Exception {
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
        player = new Player("TestPlayer");

        // Set the board in the game
        game.setBoard(board);

        // Add player to the game
        game.setPlayers(List.of(player));
        game.setCurrentPlayer(0);

        // Reset EventManager singleton
        resetEventManager();

        // Initialize EventManager
        eventManager = EventManager.getInstance();

        // Ensure the game controller is subscribed to the BuyCards event
        eventManager.subscribe(gameController::onBuyCardFromMarket, EventType.BuyCards);

        // Reset configuration to not use free market
        Configuration.getInstance().freeMarket = false;
    }

    @Test
    void testBuyCard() {
        // Setup resources for player
        Card resourceCard1 = new Card(CardType.Journalistin);
        Card resourceCard2 = new Card(CardType.Millionarin);
        Card resourceCard3 = new Card(CardType.Fotografin);

        // Add resources to the game's phase
        game.getPhase().getSelectedResources().push(resourceCard1);
        game.getPhase().getSelectedResources().push(resourceCard2);
        game.getPhase().getSelectedResources().push(resourceCard3);

        // Ensure the current phase is correct
        game.getPhase().setCurrentPhase(PhaseType.BUYING_AND_PLAYING_PHASE);
        // Components: Game, PhaseType
        assertEquals(PhaseType.BUYING_AND_PLAYING_PHASE, game.getPhase().getCurrentPhase());

        // Trigger the BuyCards event using a BuyEvent
        eventManager.notifying(EventType.BuyCards, new BuyEvent(CardType.Fotografin));

        // Wait for a short period to ensure the event processing completes
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Validate the action message
        // Components: Game, Phase, ValidationResult
        ValidationResult actionMessage = game.getPhase().getActionMessage();
        assertNotNull(actionMessage, "Action message should not be null");
        // Components: Game, Phase, ValidationResult
        assertTrue(actionMessage.getStatus(), actionMessage.getMessage());

        // Verify the card was bought and added to the player's face-up discard pile
        // Components: Player, Card, Market
        assertFalse(player.getFaceUpDiscardPile().isEmpty());
        // Components: Player, Card, Market
        assertEquals(CardType.Fotografin, player.getFaceUpDiscardPile().getCards().get(0).getCardType());

        // Verify the market was updated
        // Components: Market
        assertEquals(50, game.getMarket().getRemainingCardAmount());

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
