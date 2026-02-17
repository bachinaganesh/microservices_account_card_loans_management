package com.ganesh.loans.mapper;

import com.ganesh.loans.dtos.requests.LoanRequest;
import com.ganesh.loans.dtos.responses.LoanResponse;
import com.ganesh.loans.model.Loan;

public class Mapper {

    public static Loan mapToLoan(LoanRequest loanRequest, Loan loan) {
        loan.setLoanType(loanRequest.getLoanType());
        loan.setMobileNumber(loanRequest.getMobileNumber());
        return loan;
    }

    public static LoanResponse mapToLoanResponse(Loan loan, LoanResponse loanResponse) {
        loanResponse.setLoanId(loan.getLoanId());
        loanResponse.setLoanType(loan.getLoanType());
        loanResponse.setMobileNumber(loan.getMobileNumber());
        loanResponse.setAmountPaid(loan.getAmountPaid());
        loanResponse.setOutstandingAmount(loan.getOutstandingAmount());
        loanResponse.setLoanStatus(loan.getLoanStatus());
        loanResponse.setTotalAmount(loan.getTotalAmount());
        return loanResponse;
    }
}
