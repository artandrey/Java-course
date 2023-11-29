package com.example;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PalindromeCheckerTest {

    @Test
    public void testIsPalindromeWithPalindrome() {
        assertTrue(PalindromeChecker.checkIsPalindrome("radar"));
    }

    @Test
    public void testIsPalindromeWithNonPalindrome() {
        assertFalse(PalindromeChecker.checkIsPalindrome("hello"));
    }

    @Test
    public void testIsPalindromeWithMixedCasePalindrome() {
        assertTrue(PalindromeChecker.checkIsPalindrome("Level"));
    }

    @Test
    public void testIsPalindromeWithPhrasePalindrome() {
        assertFalse(PalindromeChecker.checkIsPalindrome("A man a plan a canal Panama"));
    }

    @Test
    public void testIsPalindromeWithEmptyString() {
        assertTrue(PalindromeChecker.checkIsPalindrome(""));
    }

    @Test
    public void testIsPalindromeWithSingleCharacter() {
        assertTrue(PalindromeChecker.checkIsPalindrome("a"));
    }

    @Test
    public void testIsPalindromeWithWhitespace() {
        assertTrue(PalindromeChecker.checkIsPalindrome("Able was I ere I saw Elba"));
    }
}