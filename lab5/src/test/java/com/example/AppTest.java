package com.example;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class AppTest {

    private Bank bank;

    @Before
    public void setup() {
        bank = new Bank();
    }

    @Test
    public void shouldAnswerWithTrue() {
        assertTrue(true);
    }
}
