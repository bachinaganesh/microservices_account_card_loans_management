package com.eazybytes.cards.exceptions;

public class CardAlreadyExistedException extends RuntimeException{

    public CardAlreadyExistedException(String message) {
        super(message);
    }

}
