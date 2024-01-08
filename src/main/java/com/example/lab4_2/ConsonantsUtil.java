package com.example.lab4_2;

public class ConsonantsUtil {
    private static final String CONSONANTS = "bcdfghjklmnpqrstvwxyz";

    public static char getPreviousConsonant(char input) {
        int index = CONSONANTS.indexOf(input);

        if (index == 0) {
            return CONSONANTS.charAt(CONSONANTS.length() - 1);
        }

        return CONSONANTS.charAt(index - 1);
    }

}
