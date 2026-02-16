package com.eazybytes.accounts.exceptions;

public class CustomerAlreadyExistException extends RuntimeException{

    public CustomerAlreadyExistException(String message) {
        super(message);
    }
}
