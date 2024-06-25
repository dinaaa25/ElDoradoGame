package org.utwente.util;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import io.github.cdimascio.dotenv.Dotenv;

public class ShuffleUtils {
    private static final Dotenv dotenv;
    private static final String RANDOM_SEED_ENV = "ELDORADO_RANDOM_SEED";
    private static final Random DEFAULT_RANDOM = new Random();

    static {
        // if the .env file is not found, dotenv will be null
        Dotenv tempDotenv;
        try {
            tempDotenv = Dotenv.configure().load();
        } catch (Exception e) {
            tempDotenv = null;
            System.out.println("Dotenv file not found, proceeding without environment variables.");
        }
        dotenv = tempDotenv;
    }

    public static void shuffle(List<?> list) {
        // optionally looks for `Dotenv dotenv = Dotenv.load();` in main method looks
        // for .env file
        // in project root, if not defined, it will use the default randomness
        String seedStr = (dotenv != null) ? dotenv.get(RANDOM_SEED_ENV) : null;
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
