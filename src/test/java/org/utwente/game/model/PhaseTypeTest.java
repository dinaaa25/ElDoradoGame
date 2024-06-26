package org.utwente.game.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class PhaseTypeTest {
    /**
     * Method under test: {@link PhaseType#next()}
     */
    @Test
    void testNext() {
        // Arrange, Act and Assert
        assertEquals(PhaseType.DISCARD_PHASE, PhaseType.BUYING_AND_PLAYING_PHASE.next());
        assertEquals(PhaseType.DRAW_PHASE, PhaseType.DISCARD_PHASE.next());
        assertEquals(PhaseType.BUYING_AND_PLAYING_PHASE, PhaseType.DRAW_PHASE.next());
    }
}
