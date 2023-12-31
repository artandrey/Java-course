package com.example.lab5;

import java.util.ArrayList;
import java.util.List;

import com.example.lab5.exceptions.AccountNotFoundException;
import com.example.lab5.exceptions.InsufficientFundsException;
import com.example.lab5.exceptions.NegativeAmountException;

public class Bank {
    private final List<BankAccount> accounts = new ArrayList<>();

    public long createAccount(String accountName, double initialDeposit) throws NegativeAmountException {
        if (initialDeposit <= 0) {
            throw new NegativeAmountException(initialDeposit);
        }
        BankAccount newAccount = new BankAccount(AccountNumberGenerator.generateNumber(), accountName, initialDeposit);
        accounts.add(newAccount);
        return newAccount.getAccountNumber();
    }

    public BankAccount findAccount(long accountNumber) throws AccountNotFoundException {
        return accounts.stream().filter(account -> account.getAccountNumber() == accountNumber).findAny().orElseThrow(() -> new AccountNotFoundException(accountNumber));
    }

    void transferMoney(long fromAccountNumber, long toAccountNumber, double amount) throws AccountNotFoundException, NegativeAmountException, InsufficientFundsException {
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
