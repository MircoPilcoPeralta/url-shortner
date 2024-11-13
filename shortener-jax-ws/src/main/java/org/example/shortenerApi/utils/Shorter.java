package org.example.shortenerApi.utils;

import java.security.SecureRandom;

public class Shorter {
    private static final String ALPHABETIC_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int SIZE = 16;
    private static final SecureRandom RANDOM = new SecureRandom();

    public static String generate() {
        StringBuilder shortLink = new StringBuilder();
        for (int i = 0; i < SIZE; i++) {
            final int randomPosition = RANDOM.nextInt(ALPHABETIC_CHARACTERS.length());
            shortLink.append(ALPHABETIC_CHARACTERS.charAt(randomPosition));
        }
        return shortLink.toString();
    }

}
