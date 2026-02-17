package com.ganesh.cards.utils;

public class CardUtils {

    public static Double getCardLimit(String cardType) {
        switch (cardType.toUpperCase()) {
            case "CREDIT":
                return 50000.0;
            case "DEBIT":
                return 25000.0;
            default:
                return 10000.0;
        }
    }

    public static String generateCardNumber() {
        StringBuilder cardNumber = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            int digit = (int) (Math.random() * 10);
            cardNumber.append(digit);
        }
        return cardNumber.toString();
    }
}
