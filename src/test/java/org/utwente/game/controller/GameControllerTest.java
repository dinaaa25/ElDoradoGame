package org.utwente.game.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.utwente.Board.Board;
import org.utwente.Board.Path;
import org.utwente.Section.Section;
import org.utwente.game.model.Game;
import org.utwente.game.view.GameView;

class GameControllerTest {
    /**
     * Method under test: {@link GameController#equals(Object)}
     */
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
}