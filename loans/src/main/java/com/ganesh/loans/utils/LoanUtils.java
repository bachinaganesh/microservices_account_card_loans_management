package com.ganesh.loans.utils;

public final class LoanUtils {

    public static Double getLoanAmount(String loanType) {
        // based on loan type, return the amount
        switch (loanType.toLowerCase()) {
            case "personal":
                return 50000.0;
            case "home":
                return 500000.0;
            case "educational":
                return 300000.0;
            default:
                return 100000.0;
        }
    }
}
