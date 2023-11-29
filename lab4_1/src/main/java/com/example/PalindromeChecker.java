package com.example;

public class PalindromeChecker {
    public static boolean checkIsPalindrome(String input) {
        if (input == null) {
            return true;
        }

        String cleanedPhrase = input.trim().replaceAll("\\s", " ").toLowerCase();

        for (int i = 0; i < cleanedPhrase.length() / 2; i++) {
            if (cleanedPhrase.charAt(i) != cleanedPhrase.charAt(cleanedPhrase.length() - 1 - i)) {
                return false;
            }
        }

        return true;
    }
}
