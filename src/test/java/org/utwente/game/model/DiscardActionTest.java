package org.utwente.game.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.utwente.CaveCoin.CaveCoin;
import org.utwente.CaveCoin.CaveCoinType;
import org.utwente.market.model.Card;
import org.utwente.market.model.CardType;
import org.utwente.market.model.Resource;
import org.utwente.player.model.CardPile;
import org.utwente.player.model.Player;
import org.utwente.util.ValidationResult;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DiscardActionTest {
    /**
     * Methods under test:
     * <ul>
     *   <li>{@link DiscardAction#DiscardAction(Player, List<Resource>)}
     *   <li>{@link DiscardAction#execute()}
     * </ul>
     */
    /**
     * Method under test: {@link DiscardAction#validate()}
     */
    @Test
    void testValidate() {
        // Arrange
        Player player = new Player("Name");
        Card resource = new Card(CardType.Kundeschafter);

        // Act
        ValidationResult actualValidateResult = (new DiscardAction(player, resource, new Phase())).validate();

        // Assert
        assertEquals("", actualValidateResult.getMessage());
        assertTrue(actualValidateResult.getStatus());
    }

    /**
     * Method under test: {@link DiscardAction#validate()}
     */
    @Test
    void testValidate2() {
        // Arrange
        Player player = new Player("Name");
        CaveCoin resource = new CaveCoin(1, CaveCoinType.Machete);

        // Act
        ValidationResult actualValidateResult = (new DiscardAction(player, resource, new Phase())).validate();

        // Assert
        assertEquals("No card to discard.", actualValidateResult.getMessage());
        assertFalse(actualValidateResult.getStatus());
    }

    /**
     * Method under test: {@link DiscardAction#discard()}
     */
    @Test
    void testDiscard() {
        // Arrange
        Player player = new Player("Name");
        Card resource = new Card(CardType.Kundeschafter);
        DiscardAction discardAction = new DiscardAction(player, resource, new Phase());

        // Act
        discardAction.discard();

        // Assert
        Player player2 = discardAction.player;
        assertFalse(player2.getFaceUpDiscardPile().isEmpty());
        assertFalse(player2.getPlayPile().isEmpty());
    }

    /**
     * Method under test:
     * {@link DiscardAction#DiscardAction(Player, Resource, Phase)}
     */
    @Test
    void testNewDiscardAction() {
        // Arrange
        Player player = new Player("Name");
        Card resource = new Card(CardType.Kundeschafter);

        // Act and Assert
        CardPile playPile = (new DiscardAction(player, resource, new Phase())).player.getPlayPile();
        List<Card> expectedCards = playPile.getResources();
        assertSame(expectedCards, playPile.getCards());
    }
}
