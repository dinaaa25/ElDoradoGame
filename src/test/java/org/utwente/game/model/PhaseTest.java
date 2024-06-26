package org.utwente.game.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class PhaseTest {
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
        assertTrue(actualPhase.getPlayedCards().isEmpty());
    }

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
        assertEquals(PhaseType.DISCARD_PHASE, phase.getCurrentPhase());
    }
}
