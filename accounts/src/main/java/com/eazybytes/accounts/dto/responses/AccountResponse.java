package com.eazybytes.accounts.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponse {

    private Long accountId;
    private String type;
    private String branch;
    private Double balance;
}
