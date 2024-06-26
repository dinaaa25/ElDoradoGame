package org.utwente.Board.Blockade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;

import java.awt.Graphics2D;

import org.junit.jupiter.api.Test;
import org.utwente.Tile.TileType;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;


class BlockadeControllerTest {
    /**
     * Method under test:
     * {@link BlockadeController#BlockadeController(Blockade, BlockadeView)}
     */
    @Test
    void testNewBlockadeController() {
        // Arrange
        Blockade blockade = new Blockade(TileType.Machete, 1, 1);

        BlockadeView blockadeView = new BlockadeView();

        // Act
        BlockadeController actualBlockadeController = new BlockadeController(blockade, blockadeView);

        // Assert
        Blockade blockade2 = actualBlockadeController.getBlockade();
        assertNull(blockade2.getSection1());
        assertNull(blockade2.getSection2());
        assertEquals(1, blockade2.getPoints());
        assertEquals(1, blockade2.getPower());
        assertEquals(TileType.Machete, blockade2.getTileType());
        assertFalse(blockade2.isRemoved());
        assertSame(blockade, blockade2);
        assertSame(blockadeView, actualBlockadeController.getBlockadeView());
    }

   /**
   * Test to verify that updateView correctly calls draw on the BlockadeView with appropriate arguments.
   */
    @Test
    void testUpdateView() {
        // Arrange
        Blockade blockade = new Blockade(TileType.Machete, 1, 1);
        BlockadeView blockadeView = mock(BlockadeView.class); // Mocking BlockadeView
        Graphics2D g2d = mock(Graphics2D.class); // Mocking Graphics2D
        BlockadeController blockadeController = new BlockadeController(blockade, blockadeView);
        int yOffset = 10;

        // Act
        blockadeController.updateView(g2d, yOffset);

        // Assert
        verify(blockadeView).draw(g2d, blockade, yOffset); // Verify that draw was called with correct parameters
    }
}
