package com.example;

public class PalindromeChecker {
    public static boolean checkIsPalindrome(String input) {
        String formattedInput = input.replaceAll("\\s", "").toLowerCase();
        return formattedInput.equals(new StringBuilder(formattedInput).reverse().toString());
    }
}
