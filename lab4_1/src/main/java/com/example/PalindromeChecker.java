package com.example;

import java.util.stream.IntStream;

public class PalindromeChecker {
    public static boolean checkIsPalindrome(String input) {
        if (input == null) {
            return false;
        }
        return IntStream.range(0, input.length() / 2)
                .noneMatch(i -> input.charAt(i) != input.charAt(input.length() - 1 - i));
    }
}
