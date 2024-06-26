package org.utwente.game.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.utwente.market.model.Card;
import org.utwente.market.model.CardType;
import org.utwente.market.model.Resource;
import org.utwente.player.model.Player;

import java.util.ArrayList;
import java.util.List;

class DiscardActionTest {
    /**
     * Methods under test:
     * <ul>
     *   <li>{@link DiscardAction#DiscardAction(Player, List<Resource>)}
     *   <li>{@link DiscardAction#execute()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        Player player = new Player("Name");
        List<Resource> resources = new ArrayList<>();
        resources.add(new Card(CardType.Kundeschafter));
        // Act
        DiscardAction actualDiscardAction = new DiscardAction(player, resources);
        actualDiscardAction.execute();

        // Assert that nothing has changed
        Player player2 = actualDiscardAction.player;
        assertEquals("Name", player2.getName());
        assertEquals(0, player2.getBlockadeCount());
    }

    /**
     * Method under test: {@link DiscardAction#validate()}
     */
    @Test
    void testValidate() {
        // Arrange
        Player player = new Player("Name");
        List<Resource> resources = new ArrayList<>();
        resources.add(new Card(CardType.Kundeschafter));

        // Act and Assert
        Assertions.assertTrue(new DiscardAction(player, resources).validate());
    }
}
