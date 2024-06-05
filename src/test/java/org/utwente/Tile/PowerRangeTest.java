package org.utwente.Tile;

import org.junit.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PowerRangeTest {
    @Test
    public void testPowerRange() {
        PowerRange powerRange = new PowerRange(0, 3);
        List<Integer> range = powerRange.getRange();
        assertEquals(List.of(0, 1, 2, 3), range);
    }

    @Test
    public void testPowerRangeSingleValue() {
        PowerRange powerRange = new PowerRange(3, 3);
        List<Integer> range = powerRange.getRange();
        assertEquals(List.of(3), range);
    }
}
