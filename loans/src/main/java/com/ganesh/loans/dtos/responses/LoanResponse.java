package com.ganesh.loans.dtos.responses;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoanResponse {
    
    private Long loanId;
    private String loanType;
    private Double totalAmount;
    private Double amountPaid;
    private Double outstandingAmount;
    private String loanStatus;
    private String mobileNumber;

}
