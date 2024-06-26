package org.utwente.game.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.utwente.CaveCoin.CaveCoin;
import org.utwente.CaveCoin.CaveCoinType;
import org.utwente.market.model.Card;
import org.utwente.market.model.CardType;
import org.utwente.player.model.Player;
import org.utwente.util.event.EventType;

class EffectPhaseTest {
    /**
     * Method under test:
     * {@link EffectPhase#createOptionalStep(EventType, int, String, String)}
     */
    @Test
    void testCreateOptionalStep() {
        // Arrange
        Card resource = new Card(CardType.Kundeschafter);
        CartographerEffectPhase cartographerEffectPhase = new CartographerEffectPhase(resource, new Player("Name"));

        // Act
        cartographerEffectPhase.createOptionalStep(EventType.StartGame, 1, "Button Text", "Step Label");

        // Assert
        EffectStep getResult = cartographerEffectPhase.getSteps().get(EventType.StartGame);
        assertEquals("Button Text", getResult.getButtonText());
        assertEquals("Step Label", getResult.getStepLabel());
        assertEquals(1, getResult.getOrder());
        assertEquals(EventType.CartographerEvent, cartographerEffectPhase.getCurrentStep());
        assertFalse(getResult.isCompleted());
        assertFalse(getResult.isMandatory());
    }

    /**
     * Method under test:
     * {@link EffectPhase#createOptionalStep(EventType, int, String, String)}
     */
    @Test
    void testCreateOptionalStep2() {
        // Arrange
        Card resource = new Card(CardType.Kundeschafter);
        CartographerEffectPhase cartographerEffectPhase = new CartographerEffectPhase(resource, new Player("Name"));

        // Act
        cartographerEffectPhase.createOptionalStep(EventType.EndGame, 1, "Button Text", "Step Label");

        // Assert
        EffectStep getResult = cartographerEffectPhase.getSteps().get(EventType.EndGame);
        assertEquals("Button Text", getResult.getButtonText());
        assertEquals("Step Label", getResult.getStepLabel());
        assertEquals(1, getResult.getOrder());
        assertEquals(EventType.CartographerEvent, cartographerEffectPhase.getCurrentStep());
        assertFalse(getResult.isCompleted());
        assertFalse(getResult.isMandatory());
    }

    /**
     * Method under test:
     * {@link EffectPhase#createOptionalStep(EventType, int, String, String)}
     */
    @Test
    void testCreateOptionalStep3() {
        // Arrange
        Card resource = new Card(CardType.Kundeschafter);
        CartographerEffectPhase cartographerEffectPhase = new CartographerEffectPhase(resource, new Player("Name"));

        // Act
        cartographerEffectPhase.createOptionalStep(EventType.PlayCards, 1, "Button Text", "Step Label");

        // Assert
        EffectStep getResult = cartographerEffectPhase.getSteps().get(EventType.PlayCards);
        assertEquals("Button Text", getResult.getButtonText());
        assertEquals("Step Label", getResult.getStepLabel());
        assertEquals(1, getResult.getOrder());
        assertEquals(EventType.CartographerEvent, cartographerEffectPhase.getCurrentStep());
        assertFalse(getResult.isCompleted());
        assertFalse(getResult.isMandatory());
    }

    /**
     * Method under test:
     * {@link EffectPhase#createOptionalStep(EventType, int, String, String)}
     */
    @Test
    void testCreateOptionalStep4() {
        // Arrange
        Card resource = new Card(CardType.Kundeschafter);
        CartographerEffectPhase cartographerEffectPhase = new CartographerEffectPhase(resource, new Player("Name"));

        // Act
        cartographerEffectPhase.createOptionalStep(EventType.BuyCards, 1, "Button Text", "Step Label");

        // Assert
        EffectStep getResult = cartographerEffectPhase.getSteps().get(EventType.BuyCards);
        assertEquals("Button Text", getResult.getButtonText());
        assertEquals("Step Label", getResult.getStepLabel());
        assertEquals(1, getResult.getOrder());
        assertEquals(EventType.CartographerEvent, cartographerEffectPhase.getCurrentStep());
        assertFalse(getResult.isCompleted());
        assertFalse(getResult.isMandatory());
    }

    /**
     * Method under test:
     * {@link EffectPhase#createOptionalStep(EventType, int, String, String)}
     */
    @Test
    void testCreateOptionalStep5() {
        // Arrange
        Card resource = new Card(CardType.Kundeschafter);
        CartographerEffectPhase cartographerEffectPhase = new CartographerEffectPhase(resource, new Player("Name"));

        // Act
        cartographerEffectPhase.createOptionalStep(EventType.DiscardCards, 1, "Button Text", "Step Label");

        // Assert
        EffectStep getResult = cartographerEffectPhase.getSteps().get(EventType.DiscardCards);
        assertEquals("Button Text", getResult.getButtonText());
        assertEquals("Step Label", getResult.getStepLabel());
        assertEquals(1, getResult.getOrder());
        assertEquals(EventType.CartographerEvent, cartographerEffectPhase.getCurrentStep());
        assertFalse(getResult.isCompleted());
        assertFalse(getResult.isMandatory());
    }

    /**
     * Method under test:
     * {@link EffectPhase#createOptionalStep(EventType, int, String, String)}
     */
    @Test
    void testCreateOptionalStep6() {
        // Arrange
        Card resource = new Card(CardType.Kundeschafter);
        CartographerEffectPhase cartographerEffectPhase = new CartographerEffectPhase(resource, new Player("Name"));

        // Act
        cartographerEffectPhase.createOptionalStep(EventType.DrawCards, 1, "Button Text", "Step Label");

        // Assert
        EffectStep getResult = cartographerEffectPhase.getSteps().get(EventType.DrawCards);
        assertEquals("Button Text", getResult.getButtonText());
        assertEquals("Step Label", getResult.getStepLabel());
        assertEquals(1, getResult.getOrder());
        assertEquals(EventType.CartographerEvent, cartographerEffectPhase.getCurrentStep());
        assertFalse(getResult.isCompleted());
        assertFalse(getResult.isMandatory());
    }

    /**
     * Method under test:
     * {@link EffectPhase#createOptionalStep(EventType, int, String, String)}
     */
    @Test
    void testCreateOptionalStep7() {
        // Arrange
        Card resource = new Card(CardType.Kundeschafter);
        CartographerEffectPhase cartographerEffectPhase = new CartographerEffectPhase(resource, new Player("Name"));

        // Act
        cartographerEffectPhase.createOptionalStep(EventType.ClickTile, 1, "Button Text", "Step Label");

        // Assert
        EffectStep getResult = cartographerEffectPhase.getSteps().get(EventType.ClickTile);
        assertEquals("Button Text", getResult.getButtonText());
        assertEquals("Step Label", getResult.getStepLabel());
        assertEquals(1, getResult.getOrder());
        assertEquals(EventType.CartographerEvent, cartographerEffectPhase.getCurrentStep());
        assertFalse(getResult.isCompleted());
        assertFalse(getResult.isMandatory());
    }

    /**
     * Method under test:
     * {@link EffectPhase#createOptionalStep(EventType, int, String, String)}
     */
    @Test
    void testCreateOptionalStep8() {
        // Arrange
        Card resource = new Card(CardType.Kundeschafter);
        CartographerEffectPhase cartographerEffectPhase = new CartographerEffectPhase(resource, new Player("Name"));

        // Act
        cartographerEffectPhase.createOptionalStep(EventType.AddPlayers, 1, "Button Text", "Step Label");

        // Assert
        EffectStep getResult = cartographerEffectPhase.getSteps().get(EventType.AddPlayers);
        assertEquals("Button Text", getResult.getButtonText());
        assertEquals("Step Label", getResult.getStepLabel());
        assertEquals(1, getResult.getOrder());
        assertEquals(EventType.CartographerEvent, cartographerEffectPhase.getCurrentStep());
        assertFalse(getResult.isCompleted());
        assertFalse(getResult.isMandatory());
    }

    /**
     * Method under test:
     * {@link EffectPhase#createOptionalStep(EventType, int, String, String)}
     */
    @Test
    void testCreateOptionalStep9() {
        // Arrange
        Card resource = new Card(CardType.Kundeschafter);
        CartographerEffectPhase cartographerEffectPhase = new CartographerEffectPhase(resource, new Player("Name"));

        // Act
        cartographerEffectPhase.createOptionalStep(EventType.PickBoard, 1, "Button Text", "Step Label");

        // Assert
        EffectStep getResult = cartographerEffectPhase.getSteps().get(EventType.PickBoard);
        assertEquals("Button Text", getResult.getButtonText());
        assertEquals("Step Label", getResult.getStepLabel());
        assertEquals(1, getResult.getOrder());
        assertEquals(EventType.CartographerEvent, cartographerEffectPhase.getCurrentStep());
        assertFalse(getResult.isCompleted());
        assertFalse(getResult.isMandatory());
    }

    /**
     * Method under test:
     * {@link EffectPhase#createOptionalStep(EventType, int, String, String)}
     */
    @Test
    void testCreateOptionalStep10() {
        // Arrange
        Card resource = new Card(CardType.Kundeschafter);
        CartographerEffectPhase cartographerEffectPhase = new CartographerEffectPhase(resource, new Player("Name"));

        // Act
        cartographerEffectPhase.createOptionalStep(EventType.NextTurn, 1, "Button Text", "Step Label");

        // Assert
        EffectStep getResult = cartographerEffectPhase.getSteps().get(EventType.NextTurn);
        assertEquals("Button Text", getResult.getButtonText());
        assertEquals("Step Label", getResult.getStepLabel());
        assertEquals(1, getResult.getOrder());
        assertEquals(EventType.CartographerEvent, cartographerEffectPhase.getCurrentStep());
        assertFalse(getResult.isCompleted());
        assertFalse(getResult.isMandatory());
    }

    /**
     * Method under test:
     * {@link EffectPhase#createOptionalStep(EventType, int, String, String)}
     */
    @Test
    void testCreateOptionalStep11() {
        // Arrange
        Card resource = new Card(CardType.Kundeschafter);
        CartographerEffectPhase cartographerEffectPhase = new CartographerEffectPhase(resource, new Player("Name"));

        // Act
        cartographerEffectPhase.createOptionalStep(EventType.NextPhase, 1, "Button Text", "Step Label");

        // Assert
        EffectStep getResult = cartographerEffectPhase.getSteps().get(EventType.NextPhase);
        assertEquals("Button Text", getResult.getButtonText());
        assertEquals("Step Label", getResult.getStepLabel());
        assertEquals(1, getResult.getOrder());
        assertEquals(EventType.CartographerEvent, cartographerEffectPhase.getCurrentStep());
        assertFalse(getResult.isCompleted());
        assertFalse(getResult.isMandatory());
    }

    /**
     * Method under test:
     * {@link EffectPhase#createMandatoryStep(EventType, int, String, String)}
     */
    @Test
    void testCreateMandatoryStep() {
        // Arrange
        Card resource = new Card(CardType.Kundeschafter);
        CartographerEffectPhase cartographerEffectPhase = new CartographerEffectPhase(resource, new Player("Name"));

        // Act
        cartographerEffectPhase.createMandatoryStep(EventType.StartGame, 1, "Button Text", "Step Label");

        // Assert
        EffectStep getResult = cartographerEffectPhase.getSteps().get(EventType.StartGame);
        assertEquals("Button Text", getResult.getButtonText());
        assertEquals("Step Label", getResult.getStepLabel());
        assertEquals(1, getResult.getOrder());
        assertEquals(EventType.CartographerEvent, cartographerEffectPhase.getCurrentStep());
        assertFalse(getResult.isCompleted());
        assertTrue(getResult.isMandatory());
    }

    /**
     * Method under test:
     * {@link EffectPhase#createMandatoryStep(EventType, int, String, String)}
     */
    @Test
    void testCreateMandatoryStep2() {
        // Arrange
        Card resource = new Card(CardType.Kundeschafter);
        CartographerEffectPhase cartographerEffectPhase = new CartographerEffectPhase(resource, new Player("Name"));

        // Act
        cartographerEffectPhase.createMandatoryStep(EventType.EndGame, 1, "Button Text", "Step Label");

        // Assert
        EffectStep getResult = cartographerEffectPhase.getSteps().get(EventType.EndGame);
        assertEquals("Button Text", getResult.getButtonText());
        assertEquals("Step Label", getResult.getStepLabel());
        assertEquals(1, getResult.getOrder());
        assertEquals(EventType.CartographerEvent, cartographerEffectPhase.getCurrentStep());
        assertFalse(getResult.isCompleted());
        assertTrue(getResult.isMandatory());
    }

    /**
     * Method under test:
     * {@link EffectPhase#createMandatoryStep(EventType, int, String, String)}
     */
    @Test
    void testCreateMandatoryStep3() {
        // Arrange
        Card resource = new Card(CardType.Kundeschafter);
        CartographerEffectPhase cartographerEffectPhase = new CartographerEffectPhase(resource, new Player("Name"));

        // Act
        cartographerEffectPhase.createMandatoryStep(EventType.PlayCards, 1, "Button Text", "Step Label");

        // Assert
        EffectStep getResult = cartographerEffectPhase.getSteps().get(EventType.PlayCards);
        assertEquals("Button Text", getResult.getButtonText());
        assertEquals("Step Label", getResult.getStepLabel());
        assertEquals(1, getResult.getOrder());
        assertEquals(EventType.CartographerEvent, cartographerEffectPhase.getCurrentStep());
        assertFalse(getResult.isCompleted());
        assertTrue(getResult.isMandatory());
    }

    /**
     * Method under test:
     * {@link EffectPhase#createMandatoryStep(EventType, int, String, String)}
     */
    @Test
    void testCreateMandatoryStep4() {
        // Arrange
        Card resource = new Card(CardType.Kundeschafter);
        CartographerEffectPhase cartographerEffectPhase = new CartographerEffectPhase(resource, new Player("Name"));

        // Act
        cartographerEffectPhase.createMandatoryStep(EventType.BuyCards, 1, "Button Text", "Step Label");

        // Assert
        EffectStep getResult = cartographerEffectPhase.getSteps().get(EventType.BuyCards);
        assertEquals("Button Text", getResult.getButtonText());
        assertEquals("Step Label", getResult.getStepLabel());
        assertEquals(1, getResult.getOrder());
        assertEquals(EventType.CartographerEvent, cartographerEffectPhase.getCurrentStep());
        assertFalse(getResult.isCompleted());
        assertTrue(getResult.isMandatory());
    }

    /**
     * Method under test:
     * {@link EffectPhase#createMandatoryStep(EventType, int, String, String)}
     */
    @Test
    void testCreateMandatoryStep5() {
        // Arrange
        Card resource = new Card(CardType.Kundeschafter);
        CartographerEffectPhase cartographerEffectPhase = new CartographerEffectPhase(resource, new Player("Name"));

        // Act
        cartographerEffectPhase.createMandatoryStep(EventType.DiscardCards, 1, "Button Text", "Step Label");

        // Assert
        EffectStep getResult = cartographerEffectPhase.getSteps().get(EventType.DiscardCards);
        assertEquals("Button Text", getResult.getButtonText());
        assertEquals("Step Label", getResult.getStepLabel());
        assertEquals(1, getResult.getOrder());
        assertEquals(EventType.CartographerEvent, cartographerEffectPhase.getCurrentStep());
        assertFalse(getResult.isCompleted());
        assertTrue(getResult.isMandatory());
    }

    /**
     * Method under test:
     * {@link EffectPhase#createMandatoryStep(EventType, int, String, String)}
     */
    @Test
    void testCreateMandatoryStep6() {
        // Arrange
        Card resource = new Card(CardType.Kundeschafter);
        CartographerEffectPhase cartographerEffectPhase = new CartographerEffectPhase(resource, new Player("Name"));

        // Act
        cartographerEffectPhase.createMandatoryStep(EventType.DrawCards, 1, "Button Text", "Step Label");

        // Assert
        EffectStep getResult = cartographerEffectPhase.getSteps().get(EventType.DrawCards);
        assertEquals("Button Text", getResult.getButtonText());
        assertEquals("Step Label", getResult.getStepLabel());
        assertEquals(1, getResult.getOrder());
        assertEquals(EventType.CartographerEvent, cartographerEffectPhase.getCurrentStep());
        assertFalse(getResult.isCompleted());
        assertTrue(getResult.isMandatory());
    }

    /**
     * Method under test:
     * {@link EffectPhase#createMandatoryStep(EventType, int, String, String)}
     */
    @Test
    void testCreateMandatoryStep7() {
        // Arrange
        Card resource = new Card(CardType.Kundeschafter);
        CartographerEffectPhase cartographerEffectPhase = new CartographerEffectPhase(resource, new Player("Name"));

        // Act
        cartographerEffectPhase.createMandatoryStep(EventType.ClickTile, 1, "Button Text", "Step Label");

        // Assert
        EffectStep getResult = cartographerEffectPhase.getSteps().get(EventType.ClickTile);
        assertEquals("Button Text", getResult.getButtonText());
        assertEquals("Step Label", getResult.getStepLabel());
        assertEquals(1, getResult.getOrder());
        assertEquals(EventType.CartographerEvent, cartographerEffectPhase.getCurrentStep());
        assertFalse(getResult.isCompleted());
        assertTrue(getResult.isMandatory());
    }

    /**
     * Method under test:
     * {@link EffectPhase#createMandatoryStep(EventType, int, String, String)}
     */
    @Test
    void testCreateMandatoryStep8() {
        // Arrange
        Card resource = new Card(CardType.Kundeschafter);
        CartographerEffectPhase cartographerEffectPhase = new CartographerEffectPhase(resource, new Player("Name"));

        // Act
        cartographerEffectPhase.createMandatoryStep(EventType.AddPlayers, 1, "Button Text", "Step Label");

        // Assert
        EffectStep getResult = cartographerEffectPhase.getSteps().get(EventType.AddPlayers);
        assertEquals("Button Text", getResult.getButtonText());
        assertEquals("Step Label", getResult.getStepLabel());
        assertEquals(1, getResult.getOrder());
        assertEquals(EventType.CartographerEvent, cartographerEffectPhase.getCurrentStep());
        assertFalse(getResult.isCompleted());
        assertTrue(getResult.isMandatory());
    }

    /**
     * Method under test:
     * {@link EffectPhase#createMandatoryStep(EventType, int, String, String)}
     */
    @Test
    void testCreateMandatoryStep9() {
        // Arrange
        Card resource = new Card(CardType.Kundeschafter);
        CartographerEffectPhase cartographerEffectPhase = new CartographerEffectPhase(resource, new Player("Name"));

        // Act
        cartographerEffectPhase.createMandatoryStep(EventType.PickBoard, 1, "Button Text", "Step Label");

        // Assert
        EffectStep getResult = cartographerEffectPhase.getSteps().get(EventType.PickBoard);
        assertEquals("Button Text", getResult.getButtonText());
        assertEquals("Step Label", getResult.getStepLabel());
        assertEquals(1, getResult.getOrder());
        assertEquals(EventType.CartographerEvent, cartographerEffectPhase.getCurrentStep());
        assertFalse(getResult.isCompleted());
        assertTrue(getResult.isMandatory());
    }

    /**
     * Method under test:
     * {@link EffectPhase#createMandatoryStep(EventType, int, String, String)}
     */
    @Test
    void testCreateMandatoryStep10() {
        // Arrange
        Card resource = new Card(CardType.Kundeschafter);
        CartographerEffectPhase cartographerEffectPhase = new CartographerEffectPhase(resource, new Player("Name"));

        // Act
        cartographerEffectPhase.createMandatoryStep(EventType.NextTurn, 1, "Button Text", "Step Label");

        // Assert
        EffectStep getResult = cartographerEffectPhase.getSteps().get(EventType.NextTurn);
        assertEquals("Button Text", getResult.getButtonText());
        assertEquals("Step Label", getResult.getStepLabel());
        assertEquals(1, getResult.getOrder());
        assertEquals(EventType.CartographerEvent, cartographerEffectPhase.getCurrentStep());
        assertFalse(getResult.isCompleted());
        assertTrue(getResult.isMandatory());
    }

    /**
     * Method under test:
     * {@link EffectPhase#createMandatoryStep(EventType, int, String, String)}
     */
    @Test
    void testCreateMandatoryStep11() {
        // Arrange
        Card resource = new Card(CardType.Kundeschafter);
        CartographerEffectPhase cartographerEffectPhase = new CartographerEffectPhase(resource, new Player("Name"));

        // Act
        cartographerEffectPhase.createMandatoryStep(EventType.NextPhase, 1, "Button Text", "Step Label");

        // Assert
        EffectStep getResult = cartographerEffectPhase.getSteps().get(EventType.NextPhase);
        assertEquals("Button Text", getResult.getButtonText());
        assertEquals("Step Label", getResult.getStepLabel());
        assertEquals(1, getResult.getOrder());
        assertEquals(EventType.CartographerEvent, cartographerEffectPhase.getCurrentStep());
        assertFalse(getResult.isCompleted());
        assertTrue(getResult.isMandatory());
    }

    /**
     * Method under test: {@link EffectPhase#completeStep(EventType)}
     */
    @Test
    void testCompleteStep() {
        // Arrange
        Card resource = new Card(CardType.Kundeschafter);

        // Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> (new CartographerEffectPhase(resource, new Player("Name"))).completeStep(EventType.StartGame));
    }

    /**
     * Method under test: {@link EffectPhase#completeStep(EventType)}
     */
    @Test
    void testCompleteStep2() {
        // Arrange
        Card resource = new Card(CardType.Kundeschafter);

        // Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> (new CartographerEffectPhase(resource, new Player("Name"))).completeStep(EventType.EndGame));
    }

    /**
     * Method under test: {@link EffectPhase#completeStep(EventType)}
     */
    @Test
    void testCompleteStep3() {
        // Arrange
        Card resource = new Card(CardType.Kundeschafter);

        // Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> (new CartographerEffectPhase(resource, new Player("Name"))).completeStep(EventType.PlayCards));
    }

    /**
     * Method under test: {@link EffectPhase#completeStep(EventType)}
     */
    @Test
    void testCompleteStep4() {
        // Arrange
        Card resource = new Card(CardType.Kundeschafter);

        // Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> (new CartographerEffectPhase(resource, new Player("Name"))).completeStep(EventType.BuyCards));
    }

    /**
     * Method under test: {@link EffectPhase#completeStep(EventType)}
     */
    @Test
    void testCompleteStep5() {
        // Arrange
        Card resource = new Card(CardType.Kundeschafter);

        // Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> (new CartographerEffectPhase(resource, new Player("Name"))).completeStep(EventType.DiscardCards));
    }

    /**
     * Method under test: {@link EffectPhase#completeStep(EventType)}
     */
    @Test
    void testCompleteStep6() {
        // Arrange
        Card resource = new Card(CardType.Kundeschafter);

        // Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> (new CartographerEffectPhase(resource, new Player("Name"))).completeStep(EventType.DrawCards));
    }

    /**
     * Method under test: {@link EffectPhase#completeStep(EventType)}
     */
    @Test
    void testCompleteStep7() {
        // Arrange
        Card resource = new Card(CardType.Kundeschafter);

        // Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> (new CartographerEffectPhase(resource, new Player("Name"))).completeStep(EventType.ClickTile));
    }

    /**
     * Method under test: {@link EffectPhase#completeStep(EventType)}
     */
    @Test
    void testCompleteStep8() {
        // Arrange
        Card resource = new Card(CardType.Kundeschafter);

        // Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> (new CartographerEffectPhase(resource, new Player("Name"))).completeStep(EventType.AddPlayers));
    }

    /**
     * Method under test: {@link EffectPhase#completeStep(EventType)}
     */
    @Test
    void testCompleteStep9() {
        // Arrange
        Card resource = new Card(CardType.Kundeschafter);

        // Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> (new CartographerEffectPhase(resource, new Player("Name"))).completeStep(EventType.PickBoard));
    }

    /**
     * Method under test: {@link EffectPhase#completeStep(EventType)}
     */
    @Test
    void testCompleteStep10() {
        // Arrange
        Card resource = new Card(CardType.Kundeschafter);

        // Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> (new CartographerEffectPhase(resource, new Player("Name"))).completeStep(EventType.NextTurn));
    }

    /**
     * Method under test: {@link EffectPhase#completeStep(EventType)}
     */
    @Test
    void testCompleteStep11() {
        // Arrange
        Card resource = new Card(CardType.Kundeschafter);

        // Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> (new CartographerEffectPhase(resource, new Player("Name"))).completeStep(EventType.NextPhase));
    }

    /**
     * Method under test: {@link EffectPhase#completeStep(EventType)}
     */
    @Test
    void testCompleteStep12() {
        // Arrange
        Card resource = new Card(CardType.Kundeschafter);

        // Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> (new CartographerEffectPhase(resource, new Player("Name"))).completeStep(EventType.MakeMove));
    }

    /**
     * Method under test: {@link EffectPhase#completeStep(EventType)}
     */
    @Test
    void testCompleteStep13() {
        // Arrange
        Card resource = new Card(CardType.Kundeschafter);

        // Act and Assert
        assertThrows(IllegalArgumentException.class,
                () -> (new CartographerEffectPhase(resource, new Player("Name"))).completeStep(EventType.ClickCaveCoin));
    }

    /**
     * Method under test: {@link EffectPhase#getCurrentStep()}
     */
    @Test
    void testGetCurrentStep() {
        // Arrange
        Card resource = new Card(CardType.Kundeschafter);

        // Act and Assert
        assertEquals(EventType.CartographerEvent,
                (new CartographerEffectPhase(resource, new Player("Name"))).getCurrentStep());
    }

    /**
     * Method under test: {@link EffectPhase#allMandatoryStepsCompleted()}
     */
    @Test
    void testAllMandatoryStepsCompleted() {
        // Arrange
        Card resource = new Card(CardType.Kundeschafter);

        // Act and Assert
        assertFalse((new CartographerEffectPhase(resource, new Player("Name"))).allMandatoryStepsCompleted());
    }

    /**
     * Method under test: {@link EffectPhase#discardEffectResource()}
     */
    @Test
    void testDiscardEffectResource() {
        // Arrange
        Card resource = new Card(CardType.Kundeschafter);
        CartographerEffectPhase cartographerEffectPhase = new CartographerEffectPhase(resource, new Player("Name"));

        // Act
        cartographerEffectPhase.discardEffectResource();

        // Assert
        Player player = cartographerEffectPhase.getPlayer();
        assertFalse(player.getFaceUpDiscardPile().isEmpty());
        assertFalse(player.getPlayPile().isEmpty());
    }

    /**
     * Method under test: {@link EffectPhase#discardEffectResource()}
     */
    @Test
    void testDiscardEffectResource2() {
        // Arrange
        CartographerEffectPhase cartographerEffectPhase = new CartographerEffectPhase(null, new Player("Name"));

        // Act
        cartographerEffectPhase.discardEffectResource();

        // Assert that nothing has changed
        assertEquals(-1, cartographerEffectPhase.getCurrentStepIndex());
        assertEquals(0, cartographerEffectPhase.getPlayer().getBlockadeCount());
        assertEquals(1, cartographerEffectPhase.getSteps().size());
        assertEquals(EffectPhaseEnum.Cartographer, cartographerEffectPhase.getEffectPhaseEnum());
    }

    /**
     * Method under test: {@link EffectPhase#discardEffectResource()}
     */
    @Test
    void testDiscardEffectResource3() {
        // Arrange
        CaveCoin resource = new CaveCoin(1, CaveCoinType.Machete);

        CartographerEffectPhase cartographerEffectPhase = new CartographerEffectPhase(resource, new Player("Name"));

        // Act
        cartographerEffectPhase.discardEffectResource();

        // Assert that nothing has changed
        assertEquals(-1, cartographerEffectPhase.getCurrentStepIndex());
        assertEquals(0, cartographerEffectPhase.getPlayer().getBlockadeCount());
        assertEquals(1, cartographerEffectPhase.getSteps().size());
        assertEquals(EffectPhaseEnum.Cartographer, cartographerEffectPhase.getEffectPhaseEnum());
        assertSame(resource, cartographerEffectPhase.getResource());
    }

    /**
     * Method under test: {@link EffectPhase#discardEffectResource()}
     */
    @Test
    void testDiscardEffectResource4() {
        // Arrange
        Card resource = new Card(CardType.Schatztruhe);
        CartographerEffectPhase cartographerEffectPhase = new CartographerEffectPhase(resource, new Player("Name"));

        // Act
        cartographerEffectPhase.discardEffectResource();

        // Assert
        Player player = cartographerEffectPhase.getPlayer();
        assertFalse(player.getOutOfGamePile().isEmpty());
        assertFalse(player.getPlayPile().isEmpty());
    }
}
