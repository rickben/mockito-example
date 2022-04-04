package utils;

import java.util.UUID;

import org.jeasy.random.EasyRandom;

public class Utils {

    private static final EasyRandom EASY_RANDOM = new EasyRandom();

    public static EasyRandom easyRandom() {
        return EASY_RANDOM;
    }

    public static <T> T random(final Class<T> type) {
        return easyRandom().nextObject(type);
    }

    public static String randomString() {
        return random(String.class);
    }

    public static String createIdentifier() {
        return UUID.randomUUID().toString();
    }
}
