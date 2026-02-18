package com.eazybytes.loans.service;

import com.eazybytes.loans.dtos.requests.LoanRequest;
import com.eazybytes.loans.dtos.responses.LoanResponse;

public interface ILoanService {

    public LoanResponse applyLoan(LoanRequest loanRequest);
    public LoanResponse getLoanByMobileNumber(String mobileNumber);

    public void deleteLoanByMobileNumber(String mobileNumber);

    public LoanResponse updateLoan(Long loanId, String mobileNumber);

    public String makePayment(Long loanId, Double amount);
}
