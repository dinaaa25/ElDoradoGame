package org.utwente.game.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.beans.VetoableChangeListener;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import javax.swing.plaf.PanelUI;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.utwente.Board.Board;
import org.utwente.Board.Path;
import org.utwente.CaveCoin.CaveCoin;
import org.utwente.CaveCoin.CaveCoinClickEvent;
import org.utwente.CaveCoin.CaveCoinType;
import org.utwente.Tile.Tile;
import org.utwente.Tile.TileClickEvent;
import org.utwente.Tile.TileType;
import org.utwente.game.model.Game;
import org.utwente.game.view.GameCLI;
import org.utwente.game.view.GameView;
import org.utwente.game.view.gui.GameGui;
import org.utwente.game.view.gui.GameStart;
import org.utwente.market.model.Card;
import org.utwente.market.model.CardType;
import org.utwente.market.model.Resource;
import org.utwente.player.model.CardPile;
import org.utwente.player.model.CoinPile;
import org.utwente.player.model.PileType;
import org.utwente.player.model.Player;
import org.utwente.util.event.AddPlayersEvent;
import org.utwente.util.event.EmptyEvent;
import org.utwente.util.event.Event;
import org.utwente.util.event.PickBoardEvent;
import org.utwente.util.event.PlayCardEvent;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.*;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.utwente.game.model.*;
import org.utwente.market.controller.BuyEvent;
import org.utwente.util.event.EventManager;
import org.utwente.util.event.EventType;
import org.utwente.util.event.Event;
import static org.mockito.Mockito.*;

import ch.qos.logback.core.util.COWArrayList;
import org.junit.jupiter.api.Test;
import org.utwente.Board.Blockade.Blockade;
import org.utwente.market.model.Card;
import org.utwente.market.model.CardType;
import org.utwente.util.event.EventManager;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.utwente.Board.Board;
import org.utwente.Board.Path;
import org.utwente.Section.Section;
import org.utwente.game.view.GameView;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.beans.VetoableChangeListener;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import javax.swing.plaf.PanelUI;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.utwente.Board.Board;
import org.utwente.Board.Path;
import org.utwente.CaveCoin.CaveCoin;
import org.utwente.CaveCoin.CaveCoinClickEvent;
import org.utwente.CaveCoin.CaveCoinType;
import org.utwente.Tile.Tile;
import org.utwente.Tile.TileClickEvent;
import org.utwente.Tile.TileType;
import org.utwente.game.model.Game;
import org.utwente.game.view.GameCLI;
import org.utwente.game.view.GameView;
import org.utwente.game.view.gui.GameGui;
import org.utwente.game.view.gui.GameStart;
import org.utwente.market.model.Card;
import org.utwente.market.model.CardType;
import org.utwente.market.model.Resource;
import org.utwente.player.model.CardPile;
import org.utwente.player.model.CoinPile;
import org.utwente.player.model.PileType;
import org.utwente.player.model.Player;
import org.utwente.util.event.AddPlayersEvent;
import org.utwente.util.event.EmptyEvent;
import org.utwente.util.event.Event;
import org.utwente.util.event.PickBoardEvent;
import org.utwente.util.event.PlayCardEvent;


@Nested
class GameControllerTest {


    @Test
    void testEquals_whenOtherIsDifferent_thenReturnNotEqual() {
        // Arrange
        ArrayList<Section> sections = new ArrayList<>();
        Board board = new Board(sections, Path.HillsOfGold, true, new ArrayList<>());

        GameController gameController = new GameController(
                mock(GameView.class));
        ArrayList<Section> sections2 = new ArrayList<>();
        Board board2 = new Board(sections2, Path.HillsOfGold, true, new ArrayList<>());

        // Act and Assert
        assertNotEquals(gameController,
                new GameController( mock(GameView.class)));
    }

    /**
     * Method under test: {@link GameController#equals(Object)}
     */
    @Test
    void testEquals_whenOtherIsNull_thenReturnNotEqual() {
        // Arrange
        ArrayList<Section> sections = new ArrayList<>();
        Board board = new Board(sections, Path.HillsOfGold, true, new ArrayList<>());

        // Act and Assert
        assertNotEquals(
                new GameController(mock(GameView.class)),
                null);
    }

    /**
     * Method under test: {@link GameController#equals(Object)}
     */
    @Test
    void testEquals_whenOtherIsSame_thenReturnNotEqual() {
        // Arrange
        ArrayList<Section> sections = new ArrayList<>();
        Board board = new Board(sections, Path.HillsOfGold, true, new ArrayList<>());

        // Act and Assert
        assertNotEquals(
                new GameController(mock(GameView.class)),
                new GameController(mock(GameView.class)));
    }

    /**
     * Method under test: {@link GameController#equals(Object)}
     */
    @Test
    void testEquals_whenOtherIsWrongType_thenReturnNotEqual() {
        // Arrange
        ArrayList<Section> sections = new ArrayList<>();
        Board board = new Board(sections, Path.HillsOfGold, true, new ArrayList<>());

        // Act and Assert
        assertNotEquals(
                new GameController(mock(GameView.class)),
                "Different type to GameController");
    }
    /**
     * Method under test: {@link Player#clearFaceUpDiscardPile()}
     */
    @Test
    void testClearFaceUpDiscardPile() {
        // Arrange
        Player player = new Player("Name");

        // Act
        player.clearFaceUpDiscardPile();

        // Assert
        CardPile faceUpDiscardPile = player.getFaceUpDiscardPile();
        assertEquals(PileType.FaceUpDiscard, faceUpDiscardPile.getPileType());
        assertTrue(faceUpDiscardPile.isEmpty());
        assertSame(player, faceUpDiscardPile.getPlayer());
    }

    /**
     * Method under test: {@link Player#discardResource(Resource)}
     */
    @Test
    void testDiscardResource() {
        // Arrange
        Player player = new Player("Name");

        // Act
        player.discardResource(new Card(CardType.Kundeschafter));

        // Assert
        assertFalse(player.getFaceUpDiscardPile().isEmpty());
        assertFalse(player.getPlayPile().isEmpty());
    }

    /**
     * Method under test: {@link Player#discardResource(Resource)}
     */
    @Test
    void testDiscardResource2() {
        // Arrange
        Player player = new Player("Name");

        // Act
        player.discardResource(new CaveCoin(1, CaveCoinType.Machete));

        // Assert
        assertFalse(player.getCaveCoinPile().isEmpty());
    }

    /**
     * Method under test: {@link Player#discardResource(Resource)}
     */
    @Test
    void testDiscardResource3() {
        // Arrange
        Player player = new Player("Name");

        // Act
        player.discardResource(null);

        // Assert that nothing has changed
        assertEquals("Name", player.getName());
        assertEquals(0, player.getBlockadeCount());
        assertEquals(PileType.Discard, player.getDiscardPile().getPileType());
        assertEquals(PileType.FaceUpDiscard, player.getFaceUpDiscardPile().getPileType());
        assertEquals(PileType.OutOfGame, player.getOutOfGamePile().getPileType());
        assertEquals(PileType.Play, player.getPlayPile().getPileType());
    }

    /**
     * Method under test: {@link Player#drawPlayCards()}
     */
    @Test
    void testDrawPlayCards() {
        // Arrange
        Player player = new Player("Name");

        // Act
        player.drawPlayCards();

        // Assert that nothing has changed
        assertEquals("Name", player.getName());
        assertEquals(0, player.getBlockadeCount());
        assertEquals(PileType.Discard, player.getDiscardPile().getPileType());
        assertEquals(PileType.FaceUpDiscard, player.getFaceUpDiscardPile().getPileType());
        assertEquals(PileType.OutOfGame, player.getOutOfGamePile().getPileType());
        assertEquals(PileType.Play, player.getPlayPile().getPileType());
    }

    /**
     * Method under test: {@link Player#drawPlayCards()}
     */
    @Test
    void testDrawPlayCards2() {
        // Arrange
        Player player = new Player("Name");
        player.setPlayPile(new CardPile());

        // Act
        player.drawPlayCards();

        // Assert
        assertFalse(player.getPlayPile().isEmpty());
        assertTrue(player.getDrawPile().isEmpty());
    }

    /**
     * Method under test: {@link Player#drawPlayCards()}
     */
    @Test
    void testDrawPlayCards3() {
        // Arrange
        Player player = new Player("Name");
        player.setDrawPile(new CardPile());
        player.setPlayPile(new CardPile());

        // Act
        player.drawPlayCards();

        // Assert
        assertTrue(player.getDrawPile().isEmpty());
        assertTrue(player.getPlayPile().isEmpty());
    }

    /**
     * Method under test: {@link Player#removeCardFromGame(Card)}
     */
    @Test
    void testRemoveCardFromGame() {
        // Arrange
        Player player = new Player("Name");

        // Act
        player.removeCardFromGame(new Card(CardType.Kundeschafter));

        // Assert
        assertFalse(player.getOutOfGamePile().isEmpty());
        assertFalse(player.getPlayPile().isEmpty());
    }

    /**
     * Method under test: {@link Player#addBlockade(Blockade)}
     */
    @Test
    void testAddBlockade() {
        // Arrange
        Player player = new Player("Name");

        // Act
        player.addBlockade(new Blockade(TileType.Machete, 1, 1));

        // Assert
        assertEquals(1, player.getBlockadeCount());
    }

    /**
     * Method under test: {@link Player#getBlockadeCount()}
     */
    @Test
    void testGetBlockadeCount() {
        // Arrange, Act and Assert
        assertEquals(0, (new Player("Name")).getBlockadeCount());
    }

    /**
     * Method under test: {@link Player#getMaxBlockade()}
     */
    @Test
    void testGetMaxBlockade() {
        // Arrange, Act and Assert
        assertNull((new Player("Name")).getMaxBlockade());
    }

    /**
     * Method under test: {@link Player#discardCoin(CaveCoin)}
     */
    @Test
    void testDiscardCoin() {
        // Arrange
        Player player = new Player("Name");

        // Act
        player.discardCoin(new CaveCoin(1, CaveCoinType.Machete));

        // Assert
        assertFalse(player.getCaveCoinPile().isEmpty());
    }

    /**
     * Method under test: {@link Player#xRayEyes()}
     */
    @Test
    void testXRayEyes() {
        // Arrange
        Player player = new Player("Name");

        // Act
        player.xRayEyes();

        // Assert that nothing has changed
        assertEquals("Name", player.getName());
        assertEquals(0, player.getBlockadeCount());
        assertEquals(PileType.Discard, player.getDiscardPile().getPileType());
        assertEquals(PileType.FaceUpDiscard, player.getFaceUpDiscardPile().getPileType());
        assertEquals(PileType.OutOfGame, player.getOutOfGamePile().getPileType());
        assertEquals(PileType.Play, player.getPlayPile().getPileType());
    }

    /**
     * Method under test: {@link Player#xRayEyes()}
     */
    @Test
    void testXRayEyes2() {
        // Arrange
        Player player = new Player("Name");
        player.setDrawPile(null);
        player.setDiscardPile(null);
        player.setOutOfGameCoinsPile(null);
        player.setCaveCoinPile(null);
        player.setFaceUpDiscardPile(null);
        player.setOutOfGamePile(null);
        player.setPlayPile(null);

        // Act
        player.xRayEyes();

        // Assert that nothing has changed
        assertEquals("Name", player.getName());
        assertEquals(0, player.getBlockadeCount());
    }

    /**
     * Method under test: {@link Player#xRayEyes()}
     */
    @Test
    void testXRayEyes3() {
        // Arrange
        CoinPile outOfGameCoinsPile = new CoinPile();
        outOfGameCoinsPile.setResources(null);

        Player player = new Player("Name");
        player.setDrawPile(null);
        player.setDiscardPile(null);
        player.setOutOfGameCoinsPile(outOfGameCoinsPile);
        player.setCaveCoinPile(null);
        player.setFaceUpDiscardPile(null);
        player.setOutOfGamePile(null);
        player.setPlayPile(null);

        // Act
        player.xRayEyes();

        // Assert that nothing has changed
        assertEquals("Name", player.getName());
        assertEquals(0, player.getBlockadeCount());
    }

    /**
     * Method under test: {@link Player#xRayEyes()}
     */
    @Test
    void testXRayEyes4() {
        // Arrange
        CardPile outOfGamePile = new CardPile();
        outOfGamePile.setResources(null);

        Player player = new Player("Name");
        player.setDrawPile(null);
        player.setDiscardPile(null);
        player.setOutOfGameCoinsPile(null);
        player.setCaveCoinPile(null);
        player.setFaceUpDiscardPile(null);
        player.setOutOfGamePile(outOfGamePile);
        player.setPlayPile(null);

        // Act
        player.xRayEyes();

        // Assert that nothing has changed
        assertEquals("Name", player.getName());
        assertEquals(0, player.getBlockadeCount());
    }

    /**
     * Method under test: {@link Player#xRayEyes()}
     */
    @Test
    void testXRayEyes5() {
        // Arrange
        COWArrayList<CaveCoin> resources = mock(COWArrayList.class);

        ArrayList<CaveCoin> caveCoinList = new ArrayList<>();
        Stream<CaveCoin> streamResult = caveCoinList.stream();
        when(resources.stream()).thenReturn(streamResult);

        CoinPile outOfGameCoinsPile = new CoinPile();
        outOfGameCoinsPile.setResources(resources);

        Player player = new Player("Name");
        player.setDrawPile(null);
        player.setDiscardPile(null);
        player.setOutOfGameCoinsPile(outOfGameCoinsPile);
        player.setCaveCoinPile(null);
        player.setFaceUpDiscardPile(null);
        player.setOutOfGamePile(null);
        player.setPlayPile(null);

        // Act
        player.xRayEyes();

        // Assert that nothing has changed
        verify(resources).stream();
        assertEquals("Name", player.getName());
        assertEquals(0, player.getBlockadeCount());
    }

    /**
     * Method under test: {@link Player#equals(Object)}
     */
    @Test
    void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual() {
        // Arrange
        Player player = new Player("Finn");
        Player player2 = new Player("Finn");

        // Act and Assert
        assertEquals(player.hashCode(), player2.hashCode());
    }
    /**
     * Method under test: {@link Player#equals(Object)}
     */
    @Test
    void testEqualsAndHashCode_whenOtherIsEqual_thenReturnEqual2() {
        // Arrange
        Player player = new Player("Mark");
        player.addBlockade(mock(Blockade.class));
        Player player2 = new Player("Mark");

        // Act and Assert
        assertEquals(player, player2);
        int expectedHashCodeResult = player.hashCode();
        assertEquals(expectedHashCodeResult, player2.hashCode());
    }

    /**
     * Method under test: {@link Player#equals(Object)}
     */
    @Test
    void testEqualsAndHashCode_whenOtherIsSame_thenReturnEqual() {
        // Arrange
        Player player = new Player("Name");

        // Act and Assert
        assertEquals(player, player);
        int expectedHashCodeResult = player.hashCode();
        assertEquals(expectedHashCodeResult, player.hashCode());
    }





    /**
     * Method under test: {@link Player#Player(String)}
     */
    @Test
    void testNewPlayer() {
        // Arrange and Act
        Player actualPlayer = new Player("Dina");

        // Assert
        assertEquals("Dina", actualPlayer.getName());
        CoinPile caveCoinPile = actualPlayer.getCaveCoinPile();
        assertNull(caveCoinPile.getPileType());
        CardPile drawPile = actualPlayer.getDrawPile();
        assertNull(drawPile.getPileType());
        CoinPile outOfGameCoinsPile = actualPlayer.getOutOfGameCoinsPile();
        assertNull(outOfGameCoinsPile.getPileType());
        List<Card> resources = drawPile.getResources();
        CardPile playPile = actualPlayer.getPlayPile();
        List<Card> resources2 = playPile.getResources();
        CardPile discardPile = actualPlayer.getDiscardPile();
        CardPile faceUpDiscardPile = actualPlayer.getFaceUpDiscardPile();
        CardPile outOfGamePile = actualPlayer.getOutOfGamePile();
        assertSame(actualPlayer, caveCoinPile.getPlayer());
        assertSame(actualPlayer, discardPile.getPlayer());
        assertSame(actualPlayer, drawPile.getPlayer());
        assertSame(actualPlayer, faceUpDiscardPile.getPlayer());
        assertSame(actualPlayer, outOfGameCoinsPile.getPlayer());
        assertSame(actualPlayer, outOfGamePile.getPlayer());
        assertSame(actualPlayer, playPile.getPlayer());
        assertSame(resources2, playPile.getCards());
    }






        /**
         * Method under test: {@link GameController#onDiscardCards(Event)}
         */
        @Test
        void testOnDiscardCards() {
            // Arrange
            GameCLI gameView = mock(GameCLI.class);
            doNothing().when(gameView).redraw();
            doNothing().when(gameView).setGame(Mockito.<Game>any());
            doNothing().when(gameView).setStageStart();
            GameController gameController = new GameController(gameView);

            // Act
            gameController.onDiscardCards(new EmptyEvent());

            // Assert
            verify(gameView).redraw();
            verify(gameView).setGame(isA(Game.class));
            verify(gameView).setStageStart();
            assertTrue(gameController.getAllCurrentlySelectedCards().isEmpty());
        }

        /**
         * Method under test: {@link GameController#onBuyCardFromMarket(Event)}
         */
        @Test
        void testOnBuyCardFromMarket() {
            // Arrange
            GameCLI gameView = mock(GameCLI.class);
            doNothing().when(gameView).setGame(Mockito.<Game>any());
            doNothing().when(gameView).setStageStart();
            GameController gameController = new GameController(gameView);

            // Act
            gameController.onBuyCardFromMarket(new EmptyEvent());

            // Assert that nothing has changed
            verify(gameView).setGame(isA(Game.class));
            verify(gameView).setStageStart();
        }

        /**
         * Method under test: {@link GameController#onPlayerCardClick(Event)}
         */
        @Test
        void testOnPlayerCardClick() {
            // Arrange
            GameCLI gameView = mock(GameCLI.class);
            doNothing().when(gameView).setGame(Mockito.<Game>any());
            doNothing().when(gameView).setStageStart();
            GameController gameController = new GameController(gameView);

            // Act
            gameController.onPlayerCardClick(new EmptyEvent());

            // Assert that nothing has changed
            verify(gameView).setGame(isA(Game.class));
            verify(gameView).setStageStart();
        }

        /**
         * Method under test: {@link GameController#onPlayerCardClick(Event)}
         */
        @Test
        void testOnPlayerCardClick2() {
            // Arrange
            GameCLI gameView = mock(GameCLI.class);
            doNothing().when(gameView).redraw();
            doNothing().when(gameView).setGame(Mockito.<Game>any());
            doNothing().when(gameView).setStageStart();
            GameController gameController = new GameController(gameView);

            // Act
            gameController.onPlayerCardClick(new PlayCardEvent(new Card(CardType.Kundeschafter)));

            // Assert
            verify(gameView).redraw();
            verify(gameView).setGame(isA(Game.class));
            verify(gameView).setStageStart();
            assertEquals(1, gameController.getAllCurrentlySelectedCards().size());
        }

        /**
         * Method under test: {@link GameController#onPlayerCardClick(Event)}
         */
        @Test
        void testOnPlayerCardClick3() {
            // Arrange
            GameCLI gameView = mock(GameCLI.class);
            doNothing().when(gameView).setGame(Mockito.<Game>any());
            doNothing().when(gameView).setStageStart();
            GameController gameController = new GameController(gameView);
            PlayCardEvent event = mock(PlayCardEvent.class);
            when(event.getCard()).thenThrow(new IllegalArgumentException("foo"));

            // Act and Assert
            assertThrows(IllegalArgumentException.class, () -> gameController.onPlayerCardClick(event));
            verify(gameView).setGame(isA(Game.class));
            verify(gameView).setStageStart();
            verify(event).getCard();
        }

        /**
         * Method under test: {@link GameController#onPlayerCardClick(Event)}
         */
        @Test
        void testOnPlayerCardClick4() {
            // Arrange
            GameCLI gameView = mock(GameCLI.class);
            doThrow(new IllegalArgumentException("foo")).when(gameView).redraw();
            doNothing().when(gameView).setGame(Mockito.<Game>any());
            doNothing().when(gameView).setStageStart();
            GameController gameController = new GameController(gameView);

            // Act and Assert
            assertThrows(IllegalArgumentException.class,
                    () -> gameController.onPlayerCardClick(new PlayCardEvent(new Card(CardType.Kundeschafter))));
            verify(gameView).redraw();
            verify(gameView).setGame(isA(Game.class));
            verify(gameView).setStageStart();
        }

        /**
         * Method under test: {@link GameController#onPlayerCaveCoinClick(Event)}
         */
        @Test
        void testOnPlayerCaveCoinClick() {
            // Arrange
            GameCLI gameView = mock(GameCLI.class);
            doNothing().when(gameView).setGame(Mockito.<Game>any());
            doNothing().when(gameView).setStageStart();
            GameController gameController = new GameController(gameView);

            // Act
            gameController.onPlayerCaveCoinClick(new EmptyEvent());

            // Assert that nothing has changed
            verify(gameView).setGame(isA(Game.class));
            verify(gameView).setStageStart();
        }

        /**
         * Method under test: {@link GameController#onPlayerCaveCoinClick(Event)}
         */
        @Test
        void testOnPlayerCaveCoinClick2() {
            // Arrange
            GameCLI gameView = mock(GameCLI.class);
            doNothing().when(gameView).redraw();
            doNothing().when(gameView).setGame(Mockito.<Game>any());
            doNothing().when(gameView).setStageStart();
            GameController gameController = new GameController(gameView);

            // Act
            gameController.onPlayerCaveCoinClick(new CaveCoinClickEvent(new CaveCoin(1, CaveCoinType.Machete)));

            // Assert
            verify(gameView).redraw();
            verify(gameView).setGame(isA(Game.class));
            verify(gameView).setStageStart();
            assertTrue(gameController.getAllCurrentlySelectedCards().isEmpty());
        }

        /**
         * Method under test: {@link GameController#onPlayerCaveCoinClick(Event)}
         */
        @Test
        void testOnPlayerCaveCoinClick3() {
            // Arrange
            GameCLI gameView = mock(GameCLI.class);
            doNothing().when(gameView).setGame(Mockito.<Game>any());
            doNothing().when(gameView).setStageStart();
            GameController gameController = new GameController(gameView);
            CaveCoinClickEvent event = mock(CaveCoinClickEvent.class);
            when(event.getCaveCoin()).thenThrow(new IllegalArgumentException("foo"));

            // Act and Assert
            assertThrows(IllegalArgumentException.class, () -> gameController.onPlayerCaveCoinClick(event));
            verify(event).getCaveCoin();
            verify(gameView).setGame(isA(Game.class));
            verify(gameView).setStageStart();
        }

        /**
         * Method under test: {@link GameController#onPlayerCaveCoinClick(Event)}
         */
        @Test
        void testOnPlayerCaveCoinClick4() {
            // Arrange
            GameCLI gameView = mock(GameCLI.class);
            doThrow(new IllegalArgumentException("foo")).when(gameView).redraw();
            doNothing().when(gameView).setGame(Mockito.<Game>any());
            doNothing().when(gameView).setStageStart();
            GameController gameController = new GameController(gameView);

            // Act and Assert
            assertThrows(IllegalArgumentException.class,
                    () -> gameController.onPlayerCaveCoinClick(new CaveCoinClickEvent(new CaveCoin(1, CaveCoinType.Machete))));
            verify(gameView).redraw();
            verify(gameView).setGame(isA(Game.class));
            verify(gameView).setStageStart();
        }

        /**
         * Method under test: {@link GameController#toggleSelectedResource(Resource)}
         */
        @Test
        void testToggleSelectedResource() {
            // Arrange
            GameCLI gameView = mock(GameCLI.class);
            doNothing().when(gameView).redraw();
            doNothing().when(gameView).setGame(Mockito.<Game>any());
            doNothing().when(gameView).setStageStart();
            GameController gameController = new GameController(gameView);

            // Act
            gameController.toggleSelectedResource(new Card(CardType.Kundeschafter));

            // Assert
            verify(gameView).redraw();
            verify(gameView).setGame(isA(Game.class));
            verify(gameView).setStageStart();
            assertEquals(1, gameController.getAllCurrentlySelectedCards().size());
        }

        /**
         * Method under test: {@link GameController#getCurrentlySelectedResource()}
         */
        @Test
        void testGetCurrentlySelectedResource() {
            // Arrange, Act and Assert
            assertThrows(NoSuchElementException.class,
                    () -> (new GameController(new GameGui())).getCurrentlySelectedResource());
        }

        /**
         * Method under test: {@link GameController#getAllCurrentlySelectedCards()}
         */
        @Test
        void testGetAllCurrentlySelectedCards() {
            // Arrange, Act and Assert
            assertTrue((new GameController(new GameGui())).getAllCurrentlySelectedCards().isEmpty());
        }

        /**
         * Method under test: {@link GameController#getAllCurrentlySelectedCards()}
         */
        @Test
        void testGetAllCurrentlySelectedCards2() {
            // Arrange
            GameGui gameView = new GameGui();
            gameView.addVetoableChangeListener(mock(VetoableChangeListener.class));

            // Act and Assert
            assertTrue((new GameController(gameView)).getAllCurrentlySelectedCards().isEmpty());
        }

        /**
         * Method under test: {@link GameController#triggerSubPhase(Resource)}
         */
        @Test
        void testTriggerSubPhase() {
            // Arrange
            GameController gameController = new GameController(new GameGui());

            // Act and Assert
            assertFalse(gameController.triggerSubPhase(new Card(CardType.Kundeschafter)));
        }

        /**
         * Method under test: {@link GameController#triggerSubPhase(Resource)}
         */
        @Test
        void testTriggerSubPhase2() {
            // Arrange
            GameGui gameView = new GameGui();
            gameView.addVetoableChangeListener(mock(VetoableChangeListener.class));
            GameController gameController = new GameController(gameView);

            // Act and Assert
            assertFalse(gameController.triggerSubPhase(new Card(CardType.Kundeschafter)));
        }

        /**
         * Method under test: {@link GameController#triggerSubPhase(Resource)}
         */
        @Test
        void testTriggerSubPhase3() {
            // Arrange, Act and Assert
            assertFalse((new GameController(new GameGui())).triggerSubPhase(null));
        }

        /**
         * Method under test: {@link GameController#triggerSubPhase(Resource)}
         */
        @Test
        void testTriggerSubPhase4() {
            // Arrange
            GameController gameController = new GameController(new GameGui());

            // Act and Assert
            assertFalse(gameController.triggerSubPhase(new CaveCoin(1, CaveCoinType.Machete)));
        }



        /**
         * Method under test: {@link GameController#onMakeMove(Event)}
         */
        @Test
        void testOnMakeMove() {
            // Arrange
            GameController gameController = new GameController(new GameGui());

            // Act and Assert
            assertThrows(NoSuchElementException.class, () -> gameController.onMakeMove(new EmptyEvent()));
        }



        /**
         * Method under test: {@link GameController#removeUsedResources(Event)}
         */
        @Test
        void testRemoveUsedResources() {
            // Arrange
            GameController gameController = new GameController(new GameGui());

            // Act
            gameController.removeUsedResources(new EmptyEvent());

            // Assert
            assertTrue(gameController.getAllCurrentlySelectedCards().isEmpty());
        }

        /**
         * Method under test: {@link GameController#removeUsedResources(Event)}
         */
        @Test
        void testRemoveUsedResources2() {
            // Arrange
            GameGui gameView = new GameGui();
            gameView.addVetoableChangeListener(mock(VetoableChangeListener.class));
            GameController gameController = new GameController(gameView);

            // Act
            gameController.removeUsedResources(new EmptyEvent());

            // Assert
            assertTrue(gameController.getAllCurrentlySelectedCards().isEmpty());
        }

        /**
         * Method under test: {@link GameController#isResourceSelected(Resource)}
         */
        @Test
        void testIsResourceSelected() {
            // Arrange
            GameController gameController = new GameController(new GameGui());

            // Act and Assert
            assertFalse(gameController.isResourceSelected(new Card(CardType.Kundeschafter)));
        }

        /**
         * Method under test: {@link GameController#isResourceSelected(Resource)}
         */
        @Test
        void testIsResourceSelected2() {
            // Arrange
            GameGui gameView = new GameGui();
            gameView.addVetoableChangeListener(mock(VetoableChangeListener.class));
            GameController gameController = new GameController(gameView);

            // Act and Assert
            assertFalse(gameController.isResourceSelected(new Card(CardType.Kundeschafter)));
        }

        /**
         * Method under test: {@link GameController#onTileClick(Event)}
         */
        @Test
        void testOnTileClick() {
            // Arrange
            GameCLI gameView = mock(GameCLI.class);
            doNothing().when(gameView).setGame(Mockito.<Game>any());
            doNothing().when(gameView).setStageStart();
            GameController gameController = new GameController(gameView);

            // Act
            gameController.onTileClick(new EmptyEvent());

            // Assert that nothing has changed
            verify(gameView).setGame(isA(Game.class));
            verify(gameView).setStageStart();
        }

        /**
         * Method under test: {@link GameController#onTileClick(Event)}
         */
        @Test
        void testOnTileClick2() {
            // Arrange
            GameCLI gameView = mock(GameCLI.class);
            doNothing().when(gameView).redraw();
            doNothing().when(gameView).setGame(Mockito.<Game>any());
            doNothing().when(gameView).setStageStart();
            GameController gameController = new GameController(gameView);

            // Act
            gameController.onTileClick(new TileClickEvent(new Tile(1, 1, TileType.Machete, 1, true)));

            // Assert
            verify(gameView).redraw();
            verify(gameView).setGame(isA(Game.class));
            verify(gameView).setStageStart();
        }

        /**
         * Method under test: {@link GameController#onTileClick(Event)}
         */
        @Test
        void testOnTileClick3() {
            // Arrange
            GameCLI gameView = mock(GameCLI.class);
            doNothing().when(gameView).setGame(Mockito.<Game>any());
            doNothing().when(gameView).setStageStart();
            GameController gameController = new GameController(gameView);
            TileClickEvent event = mock(TileClickEvent.class);
            when(event.getTile()).thenThrow(new IllegalArgumentException("foo"));

            // Act and Assert
            assertThrows(IllegalArgumentException.class, () -> gameController.onTileClick(event));
            verify(event).getTile();
            verify(gameView).setGame(isA(Game.class));
            verify(gameView).setStageStart();
        }

        /**
         * Method under test: {@link GameController#onTileClick(Event)}
         */
        @Test
        void testOnTileClick4() {
            // Arrange
            GameCLI gameView = mock(GameCLI.class);
            doThrow(new IllegalArgumentException("foo")).when(gameView).redraw();
            doNothing().when(gameView).setGame(Mockito.<Game>any());
            doNothing().when(gameView).setStageStart();
            GameController gameController = new GameController(gameView);

            // Act and Assert
            assertThrows(IllegalArgumentException.class,
                    () -> gameController.onTileClick(new TileClickEvent(new Tile(1, 1, TileType.Machete, 1, true))));
            verify(gameView).redraw();
            verify(gameView).setGame(isA(Game.class));
            verify(gameView).setStageStart();
        }






        /**
         * Method under test: {@link GameController#onGameStart(Event)}
         */
        @Test
        void testOnGameStart() {
            // Arrange
            GameCLI gameView = mock(GameCLI.class);
            doNothing().when(gameView).setGame(Mockito.<Game>any());
            doNothing().when(gameView).setPlayerSetup();
            doNothing().when(gameView).setStageStart();
            GameController gameController = new GameController(gameView);

            // Act
            gameController.onGameStart(new EmptyEvent());

            // Assert that nothing has changed
            verify(gameView).setGame(isA(Game.class));
            verify(gameView).setPlayerSetup();
            verify(gameView).setStageStart();
        }

        /**
         * Method under test: {@link GameController#onPlayersAdded(Event)}
         */
        @Test
        void testOnPlayersAdded() {
            // Arrange
            GameController gameController = new GameController(new GameGui());
            ArrayList<String> playerNames = new ArrayList<>();

            // Act
            gameController.onPlayersAdded(new AddPlayersEvent(playerNames));

            // Assert
            assertEquals(playerNames, gameController.getGame().getMaxBlockadePlayers());
        }

        /**
         * Method under test: {@link GameController#onPlayersAdded(Event)}
         */
        @Test
        void testOnPlayersAdded2() {
            // Arrange
            GameController gameController = new GameController(new GameGui());

            ArrayList<String> playerNames = new ArrayList<>();
            playerNames.add("Select a Board for the Game.");

            // Act
            gameController.onPlayersAdded(new AddPlayersEvent(playerNames));

            // Assert
            Player currentPlayer = gameController.getGame().getCurrentPlayer();
            assertEquals("Select a Board for the Game.", currentPlayer.getName());
            assertNull(currentPlayer.getColor());
            CoinPile caveCoinPile = currentPlayer.getCaveCoinPile();
            assertNull(caveCoinPile.getPileType());
            CardPile drawPile = currentPlayer.getDrawPile();
            assertNull(drawPile.getPileType());
            CoinPile outOfGameCoinsPile = currentPlayer.getOutOfGameCoinsPile();
            assertNull(outOfGameCoinsPile.getPileType());
            assertEquals(0, currentPlayer.getBlockadeCount());
            CardPile discardPile = currentPlayer.getDiscardPile();
            assertEquals(PileType.Discard, discardPile.getPileType());
            CardPile faceUpDiscardPile = currentPlayer.getFaceUpDiscardPile();
            assertEquals(PileType.FaceUpDiscard, faceUpDiscardPile.getPileType());
            CardPile outOfGamePile = currentPlayer.getOutOfGamePile();
            assertEquals(PileType.OutOfGame, outOfGamePile.getPileType());
            CardPile playPile = currentPlayer.getPlayPile();
            assertEquals(PileType.Play, playPile.getPileType());
            assertFalse(caveCoinPile.isEmpty());
            assertFalse(drawPile.isEmpty());
            assertFalse(playPile.isEmpty());
            assertTrue(discardPile.isEmpty());
            assertTrue(faceUpDiscardPile.isEmpty());
            assertTrue(outOfGameCoinsPile.isEmpty());
            assertTrue(outOfGamePile.isEmpty());
            assertSame(currentPlayer, caveCoinPile.getPlayer());
            assertSame(currentPlayer, discardPile.getPlayer());
            assertSame(currentPlayer, drawPile.getPlayer());
            assertSame(currentPlayer, faceUpDiscardPile.getPlayer());
            assertSame(currentPlayer, outOfGameCoinsPile.getPlayer());
            assertSame(currentPlayer, outOfGamePile.getPlayer());
            assertSame(currentPlayer, playPile.getPlayer());
        }

        /**
         * Method under test: {@link GameController#onPlayersAdded(Event)}
         */
        @Test
        void testOnPlayersAdded3() {
            // Arrange
            GameController gameController = new GameController(new GameGui());

            ArrayList<String> playerNames = new ArrayList<>();
            playerNames.add("Finish");
            playerNames.add("Select a Board for the Game.");

            // Act
            gameController.onPlayersAdded(new AddPlayersEvent(playerNames));

            // Assert
            Game game = gameController.getGame();
            Player currentPlayer = game.getCurrentPlayer();
            assertEquals("Finish", currentPlayer.getName());
            assertNull(currentPlayer.getColor());
            CoinPile caveCoinPile = currentPlayer.getCaveCoinPile();
            assertNull(caveCoinPile.getPileType());
            CardPile drawPile = currentPlayer.getDrawPile();
            assertNull(drawPile.getPileType());
            CoinPile outOfGameCoinsPile = currentPlayer.getOutOfGameCoinsPile();
            assertNull(outOfGameCoinsPile.getPileType());
            assertEquals(0, currentPlayer.getBlockadeCount());
            CardPile discardPile = currentPlayer.getDiscardPile();
            assertEquals(PileType.Discard, discardPile.getPileType());
            CardPile faceUpDiscardPile = currentPlayer.getFaceUpDiscardPile();
            assertEquals(PileType.FaceUpDiscard, faceUpDiscardPile.getPileType());
            CardPile outOfGamePile = currentPlayer.getOutOfGamePile();
            assertEquals(PileType.OutOfGame, outOfGamePile.getPileType());
            CardPile playPile = currentPlayer.getPlayPile();
            assertEquals(PileType.Play, playPile.getPileType());
            assertFalse(caveCoinPile.isEmpty());
            assertFalse(drawPile.isEmpty());
            assertFalse(playPile.isEmpty());
            assertTrue(game.getMaxBlockadePlayers().isEmpty());
            assertTrue(discardPile.isEmpty());
            assertTrue(faceUpDiscardPile.isEmpty());
            assertTrue(outOfGameCoinsPile.isEmpty());
            assertTrue(outOfGamePile.isEmpty());
            assertSame(currentPlayer, caveCoinPile.getPlayer());
            assertSame(currentPlayer, discardPile.getPlayer());
            assertSame(currentPlayer, drawPile.getPlayer());
            assertSame(currentPlayer, faceUpDiscardPile.getPlayer());
            assertSame(currentPlayer, outOfGameCoinsPile.getPlayer());
            assertSame(currentPlayer, outOfGamePile.getPlayer());
            assertSame(currentPlayer, playPile.getPlayer());
        }

        /**
         * Method under test: {@link GameController#onPickBoard(Event)}
         */
        @Test
        void testOnPickBoard() {
            // Arrange
            GameCLI gameView = mock(GameCLI.class);
            doNothing().when(gameView).setGame(Mockito.<Game>any());
            doNothing().when(gameView).setStageStart();
            GameController gameController = new GameController(gameView);

            // Act
            gameController.onPickBoard(new EmptyEvent());

            // Assert that nothing has changed
            verify(gameView).setGame(isA(Game.class));
            verify(gameView).setStageStart();
        }

        /**
         * Method under test: {@link GameController#onPickBoard(Event)}
         */
        @Test
        void testOnPickBoard2() {
            // Arrange
            GameCLI gameView = mock(GameCLI.class);
            doNothing().when(gameView).setGameStage();
            doNothing().when(gameView).setGame(Mockito.<Game>any());
            doNothing().when(gameView).setStageStart();
            GameController gameController = new GameController(gameView);

            // Act
            gameController.onPickBoard(new PickBoardEvent(Path.HillsOfGold));

            // Assert
            verify(gameView).setGame(isA(Game.class));
            verify(gameView).setGameStage();
            verify(gameView).setStageStart();
            Game game = gameController.getGame();
            Board board = game.getBoard();
            Tile elDoradoTile = board.getElDoradoTile();
            assertNull(elDoradoTile.getBlockade());
            assertNull(elDoradoTile.getCaveCoinNeighbour());
            assertEquals(-11, elDoradoTile.getR());
            assertEquals(0, elDoradoTile.getCaveCoinCount());
            assertEquals(0, elDoradoTile.getPower());
            assertEquals(26, elDoradoTile.getQ());
            assertEquals(3, board.getLastWaitingTiles().size());
            assertEquals(4, board.getStartingTiles().size());
            assertEquals(5, board.getBlockades().size());
            assertEquals(7, board.getSections().size());
            assertEquals(Path.HillsOfGold, board.getPath());
            assertEquals(TileType.ElDorado, elDoradoTile.getTileType());
            assertFalse(board.isFlatTop());
            assertFalse(elDoradoTile.isLastWaitingTile());
            assertFalse(game.isInWaitingState());
            assertTrue(elDoradoTile.isEmpty());
        }

        /**
         * Method under test: {@link GameController#onPickBoard(Event)}
         */
        @Test
        void testOnPickBoard3() {
            // Arrange
            GameCLI gameView = mock(GameCLI.class);
            doNothing().when(gameView).setGameStage();
            doNothing().when(gameView).setGame(Mockito.<Game>any());
            doNothing().when(gameView).setStageStart();
            GameController gameController = new GameController(gameView);

            // Act
            gameController.onPickBoard(new PickBoardEvent(Path.HomeStretch));

            // Assert
            verify(gameView).setGame(isA(Game.class));
            verify(gameView).setGameStage();
            verify(gameView).setStageStart();
            Game game = gameController.getGame();
            Board board = game.getBoard();
            Tile elDoradoTile = board.getElDoradoTile();
            assertNull(elDoradoTile.getBlockade());
            assertNull(elDoradoTile.getCaveCoinNeighbour());
            assertEquals(-24, elDoradoTile.getR());
            assertEquals(0, elDoradoTile.getCaveCoinCount());
            assertEquals(0, elDoradoTile.getPower());
            assertEquals(3, board.getLastWaitingTiles().size());
            assertEquals(4, board.getStartingTiles().size());
            assertEquals(5, board.getBlockades().size());
            assertEquals(7, board.getSections().size());
            assertEquals(Path.HomeStretch, board.getPath());
            assertEquals(TileType.ElDorado, elDoradoTile.getTileType());
            assertFalse(elDoradoTile.isLastWaitingTile());
            assertFalse(game.isInWaitingState());
            assertTrue(board.isFlatTop());
            assertTrue(elDoradoTile.isEmpty());
            assertEquals(Integer.SIZE, elDoradoTile.getQ());
        }

        /**
         * Method under test: {@link GameController#onPickBoard(Event)}
         */
        @Test
        void testOnPickBoard4() {
            // Arrange
            GameCLI gameView = mock(GameCLI.class);
            doNothing().when(gameView).setGameStage();
            doNothing().when(gameView).setGame(Mockito.<Game>any());
            doNothing().when(gameView).setStageStart();
            GameController gameController = new GameController(gameView);

            // Act
            gameController.onPickBoard(new PickBoardEvent(Path.WindingPaths));

            // Assert
            verify(gameView).setGame(isA(Game.class));
            verify(gameView).setGameStage();
            verify(gameView).setStageStart();
            Game game = gameController.getGame();
            Board board = game.getBoard();
            Tile elDoradoTile = board.getElDoradoTile();
            assertNull(elDoradoTile.getBlockade());
            assertNull(elDoradoTile.getCaveCoinNeighbour());
            assertEquals(-24, elDoradoTile.getR());
            assertEquals(0, elDoradoTile.getCaveCoinCount());
            assertEquals(0, elDoradoTile.getPower());
            assertEquals(3, board.getLastWaitingTiles().size());
            assertEquals(30, elDoradoTile.getQ());
            assertEquals(4, board.getStartingTiles().size());
            assertEquals(5, board.getBlockades().size());
            assertEquals(7, board.getSections().size());
            assertEquals(Path.WindingPaths, board.getPath());
            assertEquals(TileType.ElDorado, elDoradoTile.getTileType());
            assertFalse(elDoradoTile.isLastWaitingTile());
            assertFalse(game.isInWaitingState());
            assertTrue(board.isFlatTop());
            assertTrue(elDoradoTile.isEmpty());
        }

        /**
         * Method under test: {@link GameController#onPickBoard(Event)}
         */
        @Test
        void testOnPickBoard5() {
            // Arrange
            GameCLI gameView = mock(GameCLI.class);
            doNothing().when(gameView).setGameStage();
            doNothing().when(gameView).setGame(Mockito.<Game>any());
            doNothing().when(gameView).setStageStart();
            GameController gameController = new GameController(gameView);

            // Act
            gameController.onPickBoard(new PickBoardEvent(Path.Serpentine));

            // Assert
            verify(gameView).setGame(isA(Game.class));
            verify(gameView).setGameStage();
            verify(gameView).setStageStart();
            Game game = gameController.getGame();
            Board board = game.getBoard();
            Tile elDoradoTile = board.getElDoradoTile();
            assertNull(elDoradoTile.getBlockade());
            assertNull(elDoradoTile.getCaveCoinNeighbour());
            assertEquals(-2, elDoradoTile.getR());
            assertEquals(0, elDoradoTile.getCaveCoinCount());
            assertEquals(0, elDoradoTile.getPower());
            assertEquals(20, elDoradoTile.getQ());
            assertEquals(3, board.getLastWaitingTiles().size());
            assertEquals(4, board.getStartingTiles().size());
            assertEquals(5, board.getBlockades().size());
            assertEquals(7, board.getSections().size());
            assertEquals(Path.Serpentine, board.getPath());
            assertEquals(TileType.ElDorado, elDoradoTile.getTileType());
            assertFalse(board.isFlatTop());
            assertFalse(elDoradoTile.isLastWaitingTile());
            assertFalse(game.isInWaitingState());
            assertTrue(elDoradoTile.isEmpty());
        }

        /**
         * Method under test: {@link GameController#onPickBoard(Event)}
         */
        @Test
        void testOnPickBoard6() {
            // Arrange
            GameCLI gameView = mock(GameCLI.class);
            doNothing().when(gameView).setGameStage();
            doNothing().when(gameView).setGame(Mockito.<Game>any());
            doNothing().when(gameView).setStageStart();
            GameController gameController = new GameController(gameView);
            PickBoardEvent event = mock(PickBoardEvent.class);
            when(event.getPath()).thenReturn(Path.Swamplands);

            // Act
            gameController.onPickBoard(event);

            // Assert
            verify(gameView).setGame(isA(Game.class));
            verify(gameView).setGameStage();
            verify(gameView).setStageStart();
            verify(event).getPath();
            Game game = gameController.getGame();
            Board board = game.getBoard();
            Tile elDoradoTile = board.getElDoradoTile();
            assertNull(elDoradoTile.getBlockade());
            assertNull(elDoradoTile.getCaveCoinNeighbour());
            assertEquals(0, elDoradoTile.getCaveCoinCount());
            assertEquals(0, elDoradoTile.getPower());
            assertEquals(13, elDoradoTile.getR());
            assertEquals(3, board.getLastWaitingTiles().size());
            assertEquals(3, elDoradoTile.getQ());
            assertEquals(4, board.getStartingTiles().size());
            assertEquals(5, board.getBlockades().size());
            assertEquals(8, board.getSections().size());
            assertEquals(Path.Swamplands, board.getPath());
            assertEquals(TileType.ElDorado, elDoradoTile.getTileType());
            assertFalse(elDoradoTile.isLastWaitingTile());
            assertFalse(game.isInWaitingState());
            assertTrue(board.isFlatTop());
            assertTrue(elDoradoTile.isEmpty());
        }

        /**
         * Method under test: {@link GameController#onPickBoard(Event)}
         */
        @Test
        void testOnPickBoard7() {
            // Arrange
            GameCLI gameView = mock(GameCLI.class);
            doNothing().when(gameView).setGame(Mockito.<Game>any());
            doNothing().when(gameView).setStageStart();
            GameController gameController = new GameController(gameView);
            PickBoardEvent event = mock(PickBoardEvent.class);
            when(event.getPath()).thenThrow(new IllegalArgumentException("SectionU.json"));

            // Act and Assert
            assertThrows(IllegalArgumentException.class, () -> gameController.onPickBoard(event));
            verify(gameView).setGame(isA(Game.class));
            verify(gameView).setStageStart();
            verify(event).getPath();
        }

        /**
         * Method under test: {@link GameController#onPickBoard(Event)}
         */
        @Test
        void testOnPickBoard8() {
            // Arrange
            GameCLI gameView = mock(GameCLI.class);
            doNothing().when(gameView).setGameStage();
            doNothing().when(gameView).setGame(Mockito.<Game>any());
            doNothing().when(gameView).setStageStart();
            GameController gameController = new GameController(gameView);
            PickBoardEvent event = mock(PickBoardEvent.class);
            when(event.getPath()).thenReturn(Path.WitchCauldron);

            // Act
            gameController.onPickBoard(event);

            // Assert
            verify(gameView).setGame(isA(Game.class));
            verify(gameView).setGameStage();
            verify(gameView).setStageStart();
            verify(event).getPath();
            Game game = gameController.getGame();
            Board board = game.getBoard();
            Tile elDoradoTile = board.getElDoradoTile();
            assertNull(elDoradoTile.getBlockade());
            assertNull(elDoradoTile.getCaveCoinNeighbour());
            assertEquals(0, elDoradoTile.getCaveCoinCount());
            assertEquals(0, elDoradoTile.getPower());
            assertEquals(2, elDoradoTile.getQ());
            assertEquals(3, board.getLastWaitingTiles().size());
            assertEquals(4, board.getStartingTiles().size());
            assertEquals(5, board.getBlockades().size());
            assertEquals(7, board.getSections().size());
            assertEquals(8, elDoradoTile.getR());
            assertEquals(Path.WitchCauldron, board.getPath());
            assertEquals(TileType.ElDorado, elDoradoTile.getTileType());
            assertFalse(elDoradoTile.isLastWaitingTile());
            assertFalse(game.isInWaitingState());
            assertTrue(board.isFlatTop());
            assertTrue(elDoradoTile.isEmpty());
        }

        /**
         * Method under test: {@link GameController#onPickBoard(Event)}
         */
        @Test
        void testOnPickBoard9() {
            // Arrange
            GameCLI gameView = mock(GameCLI.class);
            doNothing().when(gameView).setGameStage();
            doNothing().when(gameView).setGame(Mockito.<Game>any());
            doNothing().when(gameView).setStageStart();
            GameController gameController = new GameController(gameView);
            PickBoardEvent event = mock(PickBoardEvent.class);
            when(event.getPath()).thenReturn(Path.TestGame);

            // Act
            gameController.onPickBoard(event);

            // Assert
            verify(gameView).setGame(isA(Game.class));
            verify(gameView).setGameStage();
            verify(gameView).setStageStart();
            verify(event).getPath();
            Game game = gameController.getGame();
            Board board = game.getBoard();
            assertNull(board.getElDoradoTile());
            assertEquals(13, board.getSections().size());
            assertEquals(5, board.getBlockades().size());
            assertEquals(Path.TestGame, board.getPath());
            assertFalse(board.isFlatTop());
            assertFalse(game.isInWaitingState());
        }

        /**
         * Method under test: {@link GameController#onPickBoard(Event)}
         */
        @Test
        void testOnPickBoard10() {
            // Arrange
            GameCLI gameView = mock(GameCLI.class);
            doNothing().when(gameView).setGameStage();
            doNothing().when(gameView).setGame(Mockito.<Game>any());
            doNothing().when(gameView).setStageStart();
            GameController gameController = new GameController(gameView);
            PickBoardEvent event = mock(PickBoardEvent.class);
            when(event.getPath()).thenReturn(Path.TestGameFT);

            // Act
            gameController.onPickBoard(event);

            // Assert
            verify(gameView).setGame(isA(Game.class));
            verify(gameView).setGameStage();
            verify(gameView).setStageStart();
            verify(event).getPath();
            Game game = gameController.getGame();
            Board board = game.getBoard();
            assertNull(board.getElDoradoTile());
            assertEquals(14, board.getSections().size());
            assertEquals(5, board.getBlockades().size());
            assertEquals(Path.TestGameFT, board.getPath());
            assertFalse(game.isInWaitingState());
            assertTrue(board.isFlatTop());
        }

        /**
         * Method under test: {@link GameController#onPickBoard(Event)}
         */
        @Test
        void testOnPickBoard11() {
            // Arrange
            GameCLI gameView = mock(GameCLI.class);
            doNothing().when(gameView).setGameStage();
            doNothing().when(gameView).setGame(Mockito.<Game>any());
            doNothing().when(gameView).setStageStart();
            GameController gameController = new GameController(gameView);
            PickBoardEvent event = mock(PickBoardEvent.class);
            when(event.getPath()).thenReturn(Path.TestGameElDorado);

            // Act
            gameController.onPickBoard(event);

            // Assert
            verify(gameView).setGame(isA(Game.class));
            verify(gameView).setGameStage();
            verify(gameView).setStageStart();
            verify(event).getPath();
            Game game = gameController.getGame();
            Board board = game.getBoard();
            Tile elDoradoTile = board.getElDoradoTile();
            assertNull(elDoradoTile.getBlockade());
            assertNull(elDoradoTile.getCaveCoinNeighbour());
            assertEquals(-1, elDoradoTile.getQ());
            assertEquals(-4, elDoradoTile.getR());
            assertEquals(0, elDoradoTile.getCaveCoinCount());
            assertEquals(0, elDoradoTile.getPower());
            assertEquals(2, board.getSections().size());
            assertEquals(3, board.getLastWaitingTiles().size());
            assertEquals(4, board.getStartingTiles().size());
            assertEquals(Path.TestGameElDorado, board.getPath());
            assertEquals(TileType.ElDorado, elDoradoTile.getTileType());
            assertFalse(board.isFlatTop());
            assertFalse(elDoradoTile.isLastWaitingTile());
            assertFalse(game.isInWaitingState());
            assertTrue(board.getBlockades().isEmpty());
            assertTrue(elDoradoTile.isEmpty());
        }

        /**
         * Method under test: {@link GameController#onPickBoard(Event)}
         */
        @Test
        void testOnPickBoard12() {
            // Arrange
            GameCLI gameView = mock(GameCLI.class);
            doNothing().when(gameView).setGameStage();
            doNothing().when(gameView).setGame(Mockito.<Game>any());
            doNothing().when(gameView).setStageStart();
            GameController gameController = new GameController(gameView);
            PickBoardEvent event = mock(PickBoardEvent.class);
            when(event.getPath()).thenReturn(Path.TestGameElDoradoFT);

            // Act
            gameController.onPickBoard(event);

            // Assert
            verify(gameView).setGame(isA(Game.class));
            verify(gameView).setGameStage();
            verify(gameView).setStageStart();
            verify(event).getPath();
            Game game = gameController.getGame();
            Board board = game.getBoard();
            Tile elDoradoTile = board.getElDoradoTile();
            assertNull(elDoradoTile.getBlockade());
            assertNull(elDoradoTile.getCaveCoinNeighbour());
            assertEquals(-1, elDoradoTile.getQ());
            assertEquals(-4, elDoradoTile.getR());
            assertEquals(0, elDoradoTile.getCaveCoinCount());
            assertEquals(0, elDoradoTile.getPower());
            assertEquals(2, board.getSections().size());
            assertEquals(3, board.getLastWaitingTiles().size());
            assertEquals(4, board.getStartingTiles().size());
            assertEquals(Path.TestGameElDoradoFT, board.getPath());
            assertEquals(TileType.ElDorado, elDoradoTile.getTileType());
            assertFalse(elDoradoTile.isLastWaitingTile());
            assertFalse(game.isInWaitingState());
            assertTrue(board.getBlockades().isEmpty());
            assertTrue(board.isFlatTop());
            assertTrue(elDoradoTile.isEmpty());
        }

        /**
         * Method under test: {@link GameController#GameController(GameView)}
         */
        @Test
        void testNewGameController() {
            // Arrange, Act and Assert
            PanelUI expectedUI = ((GameGui) (new GameController(new GameGui())).getGameView()).getUI();
            assertSame(expectedUI,
                    ((GameStart) ((GameGui) (new GameController(new GameGui())).getGameView()).getComponents()[0]).getUI());
        }

        /**
         * Method under test: {@link GameController#GameController(GameView)}
         */
        @Test
        void testNewGameController2() {
            // Arrange
            GameGui gameView = new GameGui();
            gameView.addBoard();

            // Act and Assert
            PanelUI expectedUI = ((GameGui) (new GameController(gameView)).getGameView()).getUI();
            assertSame(expectedUI,
                    ((GameStart) ((GameGui) (new GameController(gameView)).getGameView()).getComponents()[0]).getUI());
        }

        /**
         * Method under test: {@link GameController#GameController(GameView)}
         */
        @Test
        void testNewGameController3() {
            // Arrange
            GameGui gameView = new GameGui();
            gameView.addVetoableChangeListener(mock(VetoableChangeListener.class));
            gameView.addBoard();

            // Act and Assert
            PanelUI expectedUI = ((GameGui) (new GameController(gameView)).getGameView()).getUI();
            assertSame(expectedUI,
                    ((GameStart) ((GameGui) (new GameController(gameView)).getGameView()).getComponents()[0]).getUI());
        }
    }