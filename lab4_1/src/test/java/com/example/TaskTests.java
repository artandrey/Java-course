    package com.example;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TaskTests {

    @Test
    public void isPalindrome() {
        assertTrue(PalindromeChecker.checkIsPalindrome(null));
        assertTrue(PalindromeChecker.checkIsPalindrome("radar"));
        assertFalse(PalindromeChecker.checkIsPalindrome("banana"));
        assertTrue(PalindromeChecker.checkIsPalindrome("hannah"));
        assertTrue(PalindromeChecker.checkIsPalindrome("pup"));
        assertTrue(PalindromeChecker.checkIsPalindrome("nan"));
        assertFalse(PalindromeChecker.checkIsPalindrome("lollipop"));
        assertTrue(PalindromeChecker.checkIsPalindrome("eye"));
        assertTrue(PalindromeChecker.checkIsPalindrome("6543456"));
        assertTrue(PalindromeChecker.checkIsPalindrome("step on no pets"));
        assertFalse(PalindromeChecker.checkIsPalindrome("A man a plan a canal Panama"));
    }

}
