package com.eazybytes.accounts.service;

import com.eazybytes.accounts.dto.requests.CustomerRequest;
import com.eazybytes.accounts.dto.responses.CustomerResponse;

public interface IAccountService {

    CustomerResponse createAccount(CustomerRequest customerRequest);
    CustomerResponse getAccountDetails(Long customerId);
    String deleteAccount(Long customerId);
    CustomerResponse updateAccount(CustomerRequest customerRequest, Long customerId);
}
