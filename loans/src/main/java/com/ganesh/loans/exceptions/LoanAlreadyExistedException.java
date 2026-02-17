package com.ganesh.loans.exceptions;

public class LoanAlreadyExistedException extends RuntimeException{
    public LoanAlreadyExistedException(String message) {
        super(message);
    }
}
