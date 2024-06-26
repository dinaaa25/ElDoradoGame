package org.utwente.game.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.utwente.CaveCoin.CaveCoin;
import org.utwente.CaveCoin.CaveCoinType;
import org.utwente.market.model.Card;
import org.utwente.market.model.CardType;
import org.utwente.market.model.Resource;
import org.utwente.player.model.CardPile;
import org.utwente.player.model.Player;
import org.utwente.util.ValidationResult;

class RemoveActionTest {
    /**
     * Method under test: {@link RemoveAction#execute()}
     */
    @Test
    void testExecute() {
        // Arrange
        Player player = new Player("Name");
        RemoveAction removeAction = new RemoveAction(player, new Card(CardType.Kundeschafter));

        // Act
        removeAction.execute();

        // Assert
        Player player2 = removeAction.player;
        assertFalse(player2.getOutOfGamePile().isEmpty());
        assertFalse(player2.getPlayPile().isEmpty());
    }

    /**
     * Method under test: {@link RemoveAction#validate()}
     */
    @Test
    void testValidate() {
        // Arrange
        Player player = new Player("Name");

        // Act
        ValidationResult actualValidateResult = (new RemoveAction(player, new Card(CardType.Kundeschafter))).validate();

        // Assert
        assertEquals("you can remove any card from the game.", actualValidateResult.getMessage());
        assertTrue(actualValidateResult.getStatus());
    }

    /**
     * Method under test: {@link RemoveAction#validate()}
     */
    @Test
    void testValidate2() {
        // Arrange
        Player player = new Player("Name");

        // Act
        ValidationResult actualValidateResult = (new RemoveAction(player, new CaveCoin(1, CaveCoinType.Machete)))
                .validate();

        // Assert
        assertEquals("cannot remove coins this way.", actualValidateResult.getMessage());
        assertFalse(actualValidateResult.getStatus());
    }


    /**
     * Method under test: {@link RemoveAction#RemoveAction(Player, Resource)}
     */
    @Test
    void testNewRemoveAction() {
        // Arrange
        Player player = new Player("Name");

        // Act and Assert
        CardPile playPile = (new RemoveAction(player, new Card(CardType.Kundeschafter))).player.getPlayPile();
        List<Card> expectedCards = playPile.getResources();
        assertSame(expectedCards, playPile.getCards());
    }
}
