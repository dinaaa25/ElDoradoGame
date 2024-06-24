package org.utwente.util;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class ShuffleUtilsTest {

    @Test
    public void testShuffleWithSeed() {
        // Set the environment variable for the test
        System.setProperty("ELDORADO_RANDOM_SEED", "12345");

        List<Integer> originalList = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> listToShuffle = Arrays.asList(1, 2, 3, 4, 5);

        // Perform the shuffle
        ShuffleUtils.shuffle(listToShuffle);

        // Check that the list has been shuffled
        assertNotEquals(originalList, listToShuffle);

        // Reset the list and perform another shuffle
        listToShuffle = Arrays.asList(1, 2, 3, 4, 5);
        ShuffleUtils.shuffle(listToShuffle);

        // Check that the shuffle produces the same result with the same seed
        assertEquals(Arrays.asList(3, 5, 4, 2, 1), listToShuffle);
    }

    @Test
    public void testShuffleWithoutSeed() {
        // Clear the environment variable for this test
        System.clearProperty("ELDORADO_RANDOM_SEED");

        List<Integer> originalList = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> listToShuffle = Arrays.asList(1, 2, 3, 4, 5);

        // Perform the shuffle
        ShuffleUtils.shuffle(listToShuffle);

        // Check that the list has been shuffled
        assertNotEquals(originalList, listToShuffle);
    }
}
