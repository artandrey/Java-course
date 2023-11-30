package com.example;

import java.util.ArrayList;
import java.util.List;

import com.example.exceptions.InsufficientFundsException;
import com.example.exceptions.NegativeAmountException;

public class Bank {
    private final List<BankAccount> accounts = new ArrayList<>();

    public long createAccount(String accountName, double initialDeposit) {
        BankAccount newAccount = new BankAccount(AccountNumberGenerator.generateNumber(), accountName, initialDeposit);
        accounts.add(newAccount);
        return newAccount.getAccountNumber();
    }

    public BankAccount findAccount(int accountNumber) {
        return accounts.stream().filter(account -> account.getAccountNumber() == accountNumber).findAny().orElseThrow(() -> new com.example.exceptions.AccountNotFoundException(accountNumber));
    }

    void transferMoney(int fromAccountNumber, int toAccountNumber, double amount) {
        BankAccount senderAccount = findAccount(fromAccountNumber);
        BankAccount recepientAccount = findAccount(toAccountNumber);
        if (amount <= 0) {
            throw new NegativeAmountException(amount);
        }
        if (amount > senderAccount.getBalance()) {
            throw new InsufficientFundsException(senderAccount.getBalance(), amount);
        }
        senderAccount.withdraw(amount);
        recepientAccount.deposit(amount);
    }
}
