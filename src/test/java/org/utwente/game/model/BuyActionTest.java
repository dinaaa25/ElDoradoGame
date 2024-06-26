package org.utwente.game.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.utwente.market.model.Card;
import org.utwente.market.model.CardType;
import org.utwente.market.model.Resource;
import org.utwente.player.model.Player;

import org.junit.jupiter.api.Test;
import org.utwente.game.model.*;
import org.utwente.market.model.*;
import org.utwente.player.model.*;
import org.utwente.util.ValidationResult;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;


import static org.mockito.Mockito.when;

class BuyActionTest {
    /**
     * Methods under test:
     * <ul>
     *   <li>{@link BuyAction#BuyAction(Player, List<Resource>, CardType, Market)}
     *   <li>{@link BuyAction#execute()}
     * </ul>
     */
    @Test
    void testGettersAndSetters() {
        // Arrange
        Player player = new Player("Name");
        List<Resource> resources = new ArrayList<>();
        resources.add(new Card(CardType.Kundeschafter));
        Market market = mock(Market.class);

        // Act
        BuyAction actualBuyAction = new BuyAction(player, resources, CardType.Kundeschafter, market);
        actualBuyAction.execute();

        // Assert that nothing has changed
        Player player2 = actualBuyAction. getPlayer();
        assertEquals("Name", player2.getName());
        assertEquals(0, player2.getBlockadeCount());
    }

    /**
     * Method under test: {@link BuyAction#validate()}
     */

    @Test
    void testValidate() {
        // Arrange
        Player player = new Player("Name");
        List<Resource> resources = new ArrayList<>();
        resources.add(new Card(CardType.Kundeschafter));
        Market market = mock(Market.class);

        // Mocking the return value of canBuy to avoid NullPointerException
        ValidationResult validationResult = new ValidationResult(true, "Success");
        when(market.canBuy(any())).thenReturn(validationResult);

        // Act and Assert
        assertTrue(new BuyAction(player, resources, CardType.Kundeschafter, market).validate());
    }
}

