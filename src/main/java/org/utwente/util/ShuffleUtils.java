
package org.utwente.util;

import java.util.Collections;
import java.util.List;
import java.util.Random;

public class ShuffleUtils {
    private static final String RANDOM_SEED_ENV = "ELDORADO_RANDOM_SEED";
    private static final Random DEFAULT_RANDOM = new Random();

    public static void shuffle(List<?> list) {
        // requires `Dotenv dotenv = Dotenv.load();` in main method looks for .env file
        // in project root
        String seedStr = System.getenv(RANDOM_SEED_ENV);
        if (seedStr != null) {
            try {
                long seed = Long.parseLong(seedStr);
                Collections.shuffle(list, new Random(seed));
            } catch (NumberFormatException e) {
                // Fallback to default randomness if the seed is not a valid long
                Collections.shuffle(list, DEFAULT_RANDOM);
            }
        } else {
            Collections.shuffle(list, DEFAULT_RANDOM);
        }
    }
}
