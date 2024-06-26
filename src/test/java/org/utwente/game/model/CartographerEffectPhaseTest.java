package org.utwente.game.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.utwente.market.model.Card;
import org.utwente.market.model.CardType;
import org.utwente.market.model.Resource;
import org.utwente.player.model.CardPile;
import org.utwente.player.model.Player;
import org.utwente.util.event.EventType;

class CartographerEffectPhaseTest {
    /**
     * Method under test: {@link CartographerEffectPhase#defineSteps()}
     */
    @Test
    void testDefineSteps() {
        // Arrange
        Card resource = new Card(CardType.Kundeschafter);
        CartographerEffectPhase cartographerEffectPhase = new CartographerEffectPhase(resource, new Player("Name"));

        // Act
        cartographerEffectPhase.defineSteps();

        // Assert
        Map<EventType, EffectStep> steps = cartographerEffectPhase.getSteps();
        assertEquals(1, steps.size());
        EffectStep getResult = steps.get(EventType.CartographerEvent);
        assertEquals("Draw cards", getResult.getButtonText());
        assertEquals("You can draw two cards.", getResult.getStepLabel());
        assertEquals(0, getResult.getOrder());
        assertFalse(getResult.isCompleted());
        assertTrue(getResult.isMandatory());
    }

    /**
     * Method under test:
     * {@link CartographerEffectPhase#CartographerEffectPhase(Resource, Player)}
     */
    @Test
    void testNewCartographerEffectPhase() {
        // Arrange
        Card resource = new Card(CardType.Kundeschafter);

        // Act and Assert
        CardPile playPile = (new CartographerEffectPhase(resource, new Player("Name"))).getPlayer().getPlayPile();
        List<Card> expectedCards = playPile.getResources();
        assertSame(expectedCards, playPile.getCards());
    }
}
