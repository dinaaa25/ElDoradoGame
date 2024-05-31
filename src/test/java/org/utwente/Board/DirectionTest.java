package org.utwente.Board;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DirectionTest {

    @Test
    void testEnumGeneration() {
        assertEquals(0, DirectionType.FlatTopDirection.NORTH.getDq());
    }

    @Test
    void testEnumGeneration2() {
        List<DirectionType.Direction> flatTopDirections = List.of(DirectionType.FLAT_TOP.getDirections());
        assertFalse(flatTopDirections.contains(DirectionType.PointyTopDirection.EAST));
        assertTrue(flatTopDirections.contains(DirectionType.FlatTopDirection.NORTH));
    }
}
