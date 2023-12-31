package com.example.lab5;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Before;
import org.junit.Test;

import com.example.lab5.exceptions.AccountNotFoundException;
import com.example.lab5.exceptions.InsufficientFundsException;
import com.example.lab5.exceptions.NegativeAmountException;

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

    @Test
    void shouldThrowInsufficientFundsException() {
        long account1 = bank.createAccount("Name 1", 10);
        long account2 = bank.createAccount("Name 2", 10);

        assertThrows(InsufficientFundsException.class, () -> {
            bank.transferMoney(account1, account2, 30);
        });
    }
}
