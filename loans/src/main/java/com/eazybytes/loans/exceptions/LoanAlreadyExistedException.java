package com.eazybytes.loans.exceptions;

public class LoanAlreadyExistedException extends RuntimeException{
    public LoanAlreadyExistedException(String message) {
        super(message);
    }
}
