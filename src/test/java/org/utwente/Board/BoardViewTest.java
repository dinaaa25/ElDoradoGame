package org.utwente.Board;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.utwente.game.view.GameConfig.PADDING;

import org.utwente.Section.Section;
import org.utwente.Tile.TileImageLoader;
import org.utwente.Tile.TileType;
import org.utwente.game.view.GameConfig;
import org.utwente.Tile.Tile;
import org.utwente.secondboard.SecondBoardLoader;

class BoardViewTest {
    /**
     * Method under test: {@link BoardView#calculatePreferredSize(Board)}
     */
    @Test
    void testCalculatePreferredSize() {
        // Arrange
        List<Section> sections = new ArrayList<>();
        Board board = new Board(sections, Path.HillsOfGold, true, new ArrayList<>());
        BoardView boardView = new BoardView(board);
        // Act
        Dimension preferredSize = boardView.calculatePreferredSize(board);

        // Assert
        assertNotNull(preferredSize);
        assertEquals(GameConfig.DEFAULT_BOARD_SIZE_X, preferredSize.width);
        assertEquals(GameConfig.DEFAULT_BOARD_SIZE_Y, preferredSize.height);
    }

    /**
     * Method under test: {@link BoardView#calculatePreferredSize(Board)}
     */
    @Test
    void testCalculatePreferredSizeWithSections() {
        // Arrange

        ArrayList<Section> sections = new ArrayList<>();
        Board board = new Board(sections, Path.HillsOfGold, true, new ArrayList<>());

        // Create BoardView with the required Board parameter
        BoardView boardView = new BoardView(board);

        Section section = mock(Section.class);
        Tile tile = mock(Tile.class);
        when(tile.getQ()).thenReturn(0);
        when(tile.getR()).thenReturn(0);
        List<Tile> tiles = new ArrayList<>();
        tiles.add(tile);
        when(section.getTiles()).thenReturn(tiles);
        sections.add(section);

        // Act
        Dimension preferredSize = boardView.calculatePreferredSize(board);

        // Assert
        assertNotNull(preferredSize);
        assertTrue(preferredSize.width > 0);
        assertTrue(preferredSize.height > 0);
    }

    /**
     * Method under test: {@link BoardView#calculateOffsets(Board)}
     */
    @Test
    void testCalculateOffsets() {
        // Arrange

        List<Section> sections = new ArrayList<>();
        Board board = new Board(sections, Path.HillsOfGold, true, new ArrayList<>());

        BoardView boardView = new BoardView(board);

        // Act
        Point offsets = boardView.calculateOffsets(board);

        // Assert
        assertNotNull(offsets);
        assertEquals(PADDING, offsets.x);
        assertEquals(PADDING, offsets.y);
    }

    /**
     * Method under test: {@link BoardView#calculateOffsets(Board)}
     */
    @Test
    void testCalculateOffsetsWithSections() {
        // Arrange
        Section section = mock(Section.class);
        Tile tile = mock(Tile.class);
        when(tile.getQ()).thenReturn(0);
        when(tile.getR()).thenReturn(0);
        List<Tile> tiles = new ArrayList<>();
        tiles.add(tile);
        when(section.getTiles()).thenReturn(tiles);
        List<Section> sections = new ArrayList<>();
        sections.add(section);
        Board board = new Board(sections, Path.HillsOfGold, true, new ArrayList<>());

        BoardView boardView = new BoardView(board);
        // Act
        Point offsets = boardView.calculateOffsets(board);

        // Assert
        assertNotNull(offsets);
        assertTrue(offsets.x >= 0);
        assertTrue(offsets.y >= 0);
    }

    /**
     * Method under test:
     * {@link BoardView#drawBoard(Board)}
     */
    @Test
    void testDrawBoard() {
        SecondBoardLoader secondBoardLoader = new SecondBoardLoader();
        List<Board> boards = new ArrayList<>();
        ArrayList<Section> sections = new ArrayList<>();
        boards.add(new Board(sections, Path.HillsOfGold, true, new ArrayList<>()));
        boards.add(secondBoardLoader.getConvertedBoard());

        for (Board board : boards) {
            // Arrange
            BoardView boardView = new BoardView(board);
            TileImageLoader tileImageLoader = new TileImageLoader();

            // Act
            boardView.drawBoard(board);

            // Assert that nothing has changed
            assertNotNull(tileImageLoader.getImage(TileType.Machete, 1));
        }
    }

    /**
     * Method under test:
     * {@link BoardView#drawBoard(Board)}
     */
    @Test
    void testDrawBoard2() {
        SecondBoardLoader secondBoardLoader = new SecondBoardLoader();
        List<Board> boards = new ArrayList<>();
        ArrayList<Section> sections = new ArrayList<>();
        boards.add(new Board(sections, Path.HillsOfGold, true, new ArrayList<>()));
        boards.add(secondBoardLoader.getConvertedBoard());

        for (Board board : boards) {
            // Arrange
            BoardView boardView = new BoardView(board);

            TileImageLoader tileImageLoader = new TileImageLoader();

            // Act
            boardView.drawBoard(board);

            // Assert
            assertNotNull(tileImageLoader.getImage(TileType.Machete, 1));
        }
    }
}
