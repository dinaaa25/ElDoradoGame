package org.utwente.game.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class ActionTypeTest {
    /**
     * Method under test: {@link ActionType#getActionTypeByOrder(int)}
     */
    @Test
    void testGetActionTypeByOrder() throws Exception {
        // Arrange, Act and Assert
        assertEquals(ActionType.BuyCards, ActionType.PlayCards.getActionTypeByOrder(1));
        assertThrows(Exception.class, () -> ActionType.PlayCards.getActionTypeByOrder(3));
        assertEquals(ActionType.PlayCards, ActionType.PlayCards.getActionTypeByOrder(0));
        assertEquals(ActionType.DiscardCards, ActionType.PlayCards.getActionTypeByOrder(2));
    }

    /**
     * Method under test: {@link ActionType#getValue()}
     */
    @Test
    void testGetValue() {
        // Arrange, Act and Assert
        assertEquals(0, ActionType.valueOf("PlayCards").getValue());
    }
}
