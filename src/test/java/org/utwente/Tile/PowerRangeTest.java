package org.utwente.Tile;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

class PowerRangeTest {
    /**
     * Method under test: {@link PowerRange#getRange()}
     */
    @Test
    void testGetRange() {
        // Arrange and Act
        List<Integer> actualRange = (new PowerRange(1)).getRange();

        // Assert
        assertEquals(1, actualRange.size());
        assertEquals(1, actualRange.get(0).intValue());
    }

    /**
     * Method under test: {@link PowerRange#PowerRange(int)}
     */
    @Test
    void testNewPowerRange() {
        // Arrange and Act
        PowerRange actualPowerRange = new PowerRange(1);

        // Assert
        List<Integer> range = actualPowerRange.getRange();
        assertEquals(1, range.size());
        assertEquals(1, range.get(0).intValue());
        assertEquals(1, actualPowerRange.getMaxPower());
        assertEquals(1, actualPowerRange.getMinPower());
    }

    /**
     * Method under test: {@link PowerRange#PowerRange(int, int)}
     */
    @Test
    void testNewPowerRange2() {
        // Arrange and Act
        PowerRange actualPowerRange = new PowerRange(1, 3);

        // Assert
        List<Integer> range = actualPowerRange.getRange();
        assertEquals(3, range.size());
        assertEquals(1, range.get(0).intValue());
        assertEquals(1, actualPowerRange.getMinPower());
        assertEquals(2, range.get(1).intValue());
        assertEquals(3, range.get(2).intValue());
        assertEquals(3, actualPowerRange.getMaxPower());
    }

    @org.junit.Test
    public void testPowerRange3() {
        PowerRange powerRange = new PowerRange(0, 3);
        List<Integer> range = powerRange.getRange();
        assertEquals(List.of(0, 1, 2, 3), range);
    }

    @org.junit.Test
    public void testPowerRangeSingleValue() {
        PowerRange powerRange = new PowerRange(3, 3);
        List<Integer> range = powerRange.getRange();
        assertEquals(List.of(3), range);
    }
}

