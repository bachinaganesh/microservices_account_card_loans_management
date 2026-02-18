package com.eazybytes.loans.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.eazybytes.loans.dtos.requests.LoanRequest;
import com.eazybytes.loans.dtos.responses.LoanResponse;
import com.eazybytes.loans.exceptions.LoanAlreadyExistedException;
import com.eazybytes.loans.exceptions.LoanNotFoundException;
import com.eazybytes.loans.mapper.Mapper;
import com.eazybytes.loans.model.Loan;
import com.eazybytes.loans.repository.LoanRepository;
import com.eazybytes.loans.service.ILoanService;
import com.eazybytes.loans.utils.LoanUtils;

@Service
public class LoanServiceImpl implements ILoanService{

    private final LoanRepository loanRepository;

    public LoanServiceImpl(LoanRepository loanRepository) {
        this.loanRepository = loanRepository;
    }


    @Override
    public LoanResponse applyLoan(LoanRequest loanRequest) {
        String mobileNumber = loanRequest.getMobileNumber();

        Optional<Loan> optionalLoan = loanRepository.findByMobileNumber(mobileNumber);
        if(optionalLoan.isPresent()) {
            throw new LoanAlreadyExistedException("Loan already exists for the given mobile number: " + mobileNumber);
        }

        Loan loan = Mapper.mapToLoan(loanRequest, new Loan());
        Double totalAmount = LoanUtils.getLoanAmount(loan.getLoanType());
        loan.setTotalAmount(totalAmount);
        loan.setOutstandingAmount(totalAmount);
        loan.setLoanStatus("ACTIVE");
        loan.setAmountPaid(0.0);

        Loan createdLoan = loanRepository.save(loan);

        LoanResponse loanResponse = Mapper.mapToLoanResponse(createdLoan, new LoanResponse());
        return loanResponse;
    }

    @Override
    public LoanResponse getLoanByMobileNumber(String mobileNumber) {
        Loan loan = loanRepository.findByMobileNumber(mobileNumber).orElseThrow(()-> new LoanNotFoundException("Loan not found with an mobile number : "+mobileNumber));
        
        LoanResponse loanResponse = Mapper.mapToLoanResponse(loan, new LoanResponse());
        return loanResponse;
    }


    @Override
    public void deleteLoanByMobileNumber(String mobileNumber) {
        loanRepository.findByMobileNumber(mobileNumber).orElseThrow(()-> new LoanNotFoundException("Loan not found with an mobile number : "+mobileNumber));
        
        loanRepository.deleteByMobileNumber(mobileNumber);
    }


    @Override
    public LoanResponse updateLoan(Long loanId, String mobileNumber) {
        Loan loan = loanRepository.findById(loanId).orElseThrow(()-> new LoanNotFoundException("Loan not found with an id : "+loanId));

        loan.setMobileNumber(mobileNumber);
        Loan updatedLoan = loanRepository.save(loan);

        LoanResponse loanResponse = Mapper.mapToLoanResponse(updatedLoan, new LoanResponse());
        return loanResponse;

    }


    @Override
    public String makePayment(Long loanId, Double amount) {
        Loan loan = loanRepository.findById(loanId).orElseThrow(()-> new LoanNotFoundException("Loan not found with an id : "+loanId));

        if(amount <=0 || amount > loan.getOutstandingAmount()) {
            throw new IllegalArgumentException("Invalid payment amount. Amount should be greater than 0 and less than or equal to the outstanding amount.");
        }

        loan.setAmountPaid(loan.getAmountPaid() + amount);
        loan.setOutstandingAmount(loan.getOutstandingAmount() - amount);
        loanRepository.save(loan);
        return "Payment made successfully for loan ID: " + loanId;
    }

}
