package com.eazybytes.accounts.service;

import com.eazybytes.accounts.dto.responses.CustomerDetailsResponse;
import org.springframework.web.bind.annotation.RequestParam;

public interface ICustomerDetailsService {

    public CustomerDetailsResponse getCustomerDetails(@RequestParam Long customerId);
}
