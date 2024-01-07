package com.example.lab4_2;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CosonantsUtilTest {
    @Test
    public void testLastLetter() {
        assertEquals(ConsonantsUtil.getPreviousConsonant('z'), 'y');
    }

    @Test

    public void testFirstLetter() {
        assertEquals(ConsonantsUtil.getPreviousConsonant('b'), 'z');
    }

    @Test

    public void testMiddleLetter() {
        assertEquals(ConsonantsUtil.getPreviousConsonant('v'), 't');
    }
}
