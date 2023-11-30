package com.example.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class InsufficientFundsException extends RuntimeException {
    private final double balance;
    private final double withdrawAmount;
}
