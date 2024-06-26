package org.utwente.game.model;

import org.junit.jupiter.api.Test;
import org.utwente.market.model.Card;
import org.utwente.market.model.CardType;
import org.utwente.market.model.Resource;

import static org.junit.jupiter.api.Assertions.*;

class PhaseTest {
    /**
     * Method under test: {@link Phase#next()}
     */
    @Test
    void testNext() {
        // Arrange
        Phase phase = new Phase();

        // Act
        phase.next();

        // Assert
        assertNull(phase.getSelectedTile());
        assertEquals(PhaseType.DISCARD_PHASE, phase.getCurrentPhase());
        assertTrue(phase.getSelectedResources().isEmpty());
    }

    /**
     * Method under test: {@link Phase#addPlayedResource(Resource)}
     */
    @Test
    void testAddPlayedResource() {
        // Arrange
        Phase phase = new Phase();

        // Act
        phase.addPlayedResource(new Card(CardType.Kundeschafter));

        // Assert
        assertEquals(1, phase.getPlayedResources().size());
    }

    /**
     * Method under test: default or parameterless constructor of {@link Phase}
     */
    @Test
    void testNewPhase() {
        // Arrange and Act
        Phase actualPhase = new Phase();

        // Assert
        assertEquals(PhaseType.BUYING_AND_PLAYING_PHASE, actualPhase.getCurrentPhase());
        assertFalse(actualPhase.isMoveThoughPlayers());
        assertTrue(actualPhase.getPlayedResources().isEmpty());
        assertTrue(actualPhase.getSelectedResources().isEmpty());
    }

    /**
     * Method under test: {@link Phase#toString()}
     */
    @Test
    void testToString() {
        // Arrange, Act and Assert
        assertEquals(
                "Phase{currentPhase=BUYING_AND_PLAYING_PHASE, playedResources=[], selectedResources=[], moveThoughPlayers"
                        + "=false, selectedTile=null}",
                (new Phase()).toString());
    }
}
