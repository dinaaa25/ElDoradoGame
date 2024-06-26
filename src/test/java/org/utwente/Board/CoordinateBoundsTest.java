package org.utwente.Board;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class CoordinateBoundsTest {
    /**
     * Method under test: {@link CoordinateBounds#toString()}
     */
    @Test
    void testToString() {
        // Arrange, Act and Assert
        assertEquals("CoordinateBounds{minQ=1, maxQ=3, minR=1, maxR=3}", (new CoordinateBounds(1, 3, 1, 3)).toString());
    }
}
