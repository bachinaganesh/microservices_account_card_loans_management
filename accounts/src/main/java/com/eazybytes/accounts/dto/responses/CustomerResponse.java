package com.eazybytes.accounts.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponse {

    private Long id;
    private String name;
    private String email;
    private String gender;
    private String mobileNumber;
    private AccountResponse accountResponse;
}
