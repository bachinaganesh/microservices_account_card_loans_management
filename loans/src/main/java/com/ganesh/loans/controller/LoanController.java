package com.ganesh.loans.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ganesh.loans.dtos.requests.LoanRequest;
import com.ganesh.loans.dtos.responses.LoanResponse;
import com.ganesh.loans.service.ILoanService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag
(
    name = "Loans Microservice",
    description = "API'S for managing loans"
)
@RestController
@RequestMapping("/loans")
public class LoanController {

    private final ILoanService loanService;

    public LoanController(ILoanService loanService) {
        this.loanService = loanService;
    }

    @Operation(
        description = "Apply for a new loan"
    )
    @ApiResponses (
        {
            @ApiResponse(
                responseCode = "201",
                description = "Loan applied successful"
            ),
            @ApiResponse(
                responseCode = "409",
                description = "Loan application already exists for the given mobile number"
            )
        }
    )
    @PostMapping
    public ResponseEntity<LoanResponse> applyLoan(@RequestBody LoanRequest loanRequest) {
        LoanResponse loanResponse = loanService.applyLoan(loanRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(loanResponse);
    }

    @ApiResponses(
        {
            @ApiResponse(
                responseCode = "200",
                description = "Loan details retrieved successfully"
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Loan not found for the given mobile number"
            )
        }
    )
    @GetMapping
    public ResponseEntity<LoanResponse> getLoanByMobileNumber(@RequestParam String mobileNumber) {
        LoanResponse loanResponse = loanService.getLoanByMobileNumber(mobileNumber);
        return ResponseEntity.ok(loanResponse);
    }

    @ApiResponses(
        {
            @ApiResponse(
                responseCode = "200",
                description = "Loan deleted successfully"
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Loan not found for the given mobile number"
            )
        }
    )
    @DeleteMapping
    public ResponseEntity<String> deleteLoanByMobileNumber(@RequestParam String mobileNumber) {
        loanService.deleteLoanByMobileNumber(mobileNumber);
        return ResponseEntity.ok("Loan deleted successfully for mobile number: " + mobileNumber);
    }

    @ApiResponse
    @PatchMapping("/{loanId}")
    public ResponseEntity<LoanResponse> updateLoan(@PathVariable Long loanId, @RequestParam String mobileNumber) {
        LoanResponse loanResponse = loanService.updateLoan(loanId, mobileNumber);
        return ResponseEntity.ok(loanResponse);
    }

    @ApiResponses(
        {
            @ApiResponse
        }
    )
    @PostMapping("/{loanId}/pay")
    public ResponseEntity<String> makePayment(@PathVariable Long loanId, @RequestParam Double amount) {
        String response = loanService.makePayment(loanId, amount);
        return ResponseEntity.ok(response);
    }
}
