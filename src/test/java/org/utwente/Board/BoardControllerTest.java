package org.utwente.Board;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.utwente.Board.Blockade.Blockade;
import org.utwente.Section.Section;
import org.utwente.Section.SectionType;
import org.utwente.Tile.Tile;
import org.utwente.Tile.TileImageLoader;
import org.utwente.Tile.TileType;

import static org.junit.jupiter.api.Assertions.*;


class BoardControllerTest {
    /**
     * Method under test: {@link BoardController#BoardController(Board, BoardView)}
     */
    @Test
    void testNewBoardController() {
        // Arrange
        ArrayList<Section> sections = new ArrayList<>();
        ArrayList<Blockade> blockades = new ArrayList<>();
        Board board = new Board(sections, Path.HillsOfGold, true, blockades);

        BoardView boardView = new BoardView(board);

        // Act
        BoardController actualBoardController = new BoardController(board, boardView);

        // Assert
        Board board2 = actualBoardController.getBoard();
        assertNull(board2.getElDoradoTile());
        assertEquals(Path.HillsOfGold, board2.getPath());
        List<Blockade> blockades2 = board2.getBlockades();
        assertTrue(blockades2.isEmpty());
        List<Section> sections2 = board2.getSections();
        assertTrue(sections2.isEmpty());
        List<Tile> startingTiles = board2.getStartingTiles();
        assertTrue(startingTiles.isEmpty());
        assertTrue(board2.isFlatTop());
        assertSame(blockades, blockades2);
        assertSame(sections, sections2);
        assertSame(board, board2);
        assertSame(boardView, actualBoardController.getBoardView());
        assertSame(startingTiles, board2.getLastWaitingTiles());
    }

    /**
     * Method under test: {@link BoardController#updateView()}
     */
     @Test
        void testUpdateView() {
            // Arrange
            ArrayList<Section> sections = new ArrayList<>();
            Board board = new Board(sections, Path.HillsOfGold, true, new ArrayList<>());

            // Create BoardView with the required Board parameter
            BoardView boardView = new BoardView(board);

            BoardController boardController = new BoardController(board, boardView);
            TileImageLoader tileImageLoader = new TileImageLoader();

            // Initialize Graphics2D object
            BufferedImage bufferedImage = new BufferedImage(100, 100, BufferedImage.TYPE_4BYTE_ABGR);
            Graphics2D g = bufferedImage.createGraphics();

            int x = 0;
            int y = 0;
            boolean someBoolean = true;

            // Act
            boardController.updateView();

            // Assert
            // Check that the tile image is not null, meaning it has been loaded
            BufferedImage loadedImage = tileImageLoader.getImage(TileType.Machete, 1);
            assertNotNull(loadedImage, "The tile image should be loaded after update.");
            assertSame(BufferedImage.TYPE_4BYTE_ABGR, loadedImage.getType());
        }

        
    /**
     * Method under test: {@link BoardController#updateView()}
     */
    @Test
    void testUpdateViewWithSections() {
        // Arrange
        ArrayList<Section> sections = new ArrayList<>();
        sections.add(new Section(new ArrayList<>(), SectionType.A));
        Board board = new Board(sections, Path.HillsOfGold, true, new ArrayList<>());

        BoardView boardView = new BoardView(board);
        BoardController boardController = new BoardController(board, boardView);
        TileImageLoader tileImageLoader = new TileImageLoader();

        // Initialize Graphics2D object
        BufferedImage bufferedImage = new BufferedImage(100, 100, BufferedImage.TYPE_4BYTE_ABGR);
        Graphics2D g = bufferedImage.createGraphics();

        int x = 0;
        int y = 0;
        boolean someBoolean = true;

        // Act
        boardController.updateView();

        // Assert
        // Check that the tile image is not null, meaning it has been loaded
        BufferedImage loadedImage = tileImageLoader.getImage(TileType.Machete, 1);
        assertNotNull(loadedImage, "The tile image should be loaded after update.");
        assertSame(BufferedImage.TYPE_4BYTE_ABGR , loadedImage.getType());
    }
}