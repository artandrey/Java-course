package com.example;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Before;
import org.junit.Test;
import com.example.exceptions.AccountNotFoundException;
import com.example.exceptions.NegativeAmountException;

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
    public void shouldThrowExceptionIfAccountNotFound() {
        assertThrows(AccountNotFoundException.class, () -> {
            bank.findAccount(0);
        });
    }

    @Test
    public void shouldThrowInsufficientAmountEception() {
        assertThrows(NegativeAmountException.class, () -> {
            bank.createAccount("Name 1", -10);
        });
    }

    // @Test void
}
