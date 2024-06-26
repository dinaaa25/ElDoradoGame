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

class DrawActionTest {
    /**
     * Method under test: {@link DrawAction#execute()}
     */
    @Test
    void testExecute() {
        // Arrange
        Player player = new Player("Name");
        DrawAction drawAction = new DrawAction(player, new Card(CardType.Kundeschafter));

        // Act
        drawAction.execute();

        // Assert
        Player player2 = drawAction.player;
        assertFalse(player2.getDrawPile().isEmpty());
        assertFalse(player2.getPlayPile().isEmpty());
    }

    /**
     * Method under test: {@link DrawAction#validate()}
     */
    @Test
    void testValidate() {
        // Arrange
        Player player = new Player("Name");

        // Act
        ValidationResult actualValidateResult = (new DrawAction(player, new Card(CardType.Kundeschafter))).validate();

        // Assert
        assertEquals("Card not allowed for draw action.", actualValidateResult.getMessage());
        assertFalse(actualValidateResult.getStatus());
    }

    /**
     * Method under test: {@link DrawAction#validate()}
     */
    @Test
    void testValidate2() {
        // Arrange
        Player player = new Player("Name");

        // Act
        ValidationResult actualValidateResult = (new DrawAction(player, new CaveCoin(1, CaveCoinType.Machete))).validate();

        // Assert
        assertEquals("Coin not allowed for draw action.", actualValidateResult.getMessage());
        assertFalse(actualValidateResult.getStatus());
    }

    /**
     * Method under test: {@link DrawAction#validate()}
     */
    @Test
    void testValidate3() {
        // Arrange
        Player player = new Player("Name");

        // Act
        ValidationResult actualValidateResult = (new DrawAction(player, new CaveCoin(1, CaveCoinType.Draw))).validate();

        // Assert
        assertEquals("", actualValidateResult.getMessage());
        assertTrue(actualValidateResult.getStatus());
    }

    /**
     * Method under test: {@link DrawAction#validate()}
     */
    @Test
    void testValidate4() {
        // Arrange
        Player player = new Player("Name");

        // Act
        ValidationResult actualValidateResult = (new DrawAction(player, new Card(CardType.Reisende))).validate();

        // Assert
        assertEquals("", actualValidateResult.getMessage());
        assertTrue(actualValidateResult.getStatus());
    }


    /**
     * Method under test: {@link DrawAction#DrawAction(Player, Resource)}
     */
    @Test
    void testNewDrawAction() {
        // Arrange
        Player player = new Player("Name");

        // Act and Assert
        CardPile playPile = (new DrawAction(player, new Card(CardType.Kundeschafter))).player.getPlayPile();
        List<Card> expectedCards = playPile.getResources();
        assertSame(expectedCards, playPile.getCards());
    }
}
