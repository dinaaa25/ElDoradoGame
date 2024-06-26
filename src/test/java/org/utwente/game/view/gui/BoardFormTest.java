package org.utwente.game.view.gui;

import static org.junit.jupiter.api.Assertions.assertSame;

import org.junit.jupiter.api.Test;

class BoardFormTest {
    /**
     * Method under test: default or parameterless constructor of {@link BoardForm}
     */
    @Test
    void testNewBoardForm() {
        // Arrange and Act
        BoardForm actualBoardForm = new BoardForm();

        // Assert
        Object expectedTreeLock = actualBoardForm.getTreeLock();
        assertSame(expectedTreeLock, (actualBoardForm.getComponents()[2]).getTreeLock());
    }
}
