package com.eazybytes.accounts.controller;

import com.eazybytes.accounts.dto.responses.CustomerDetailsResponse;
import com.eazybytes.accounts.service.ICustomerDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/customerdetails")
public class CustomerDetailsController {

    private final ICustomerDetailsService customerDetailsService;

    public CustomerDetailsController(ICustomerDetailsService customerDetailsService) {
        this.customerDetailsService = customerDetailsService;
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerDetailsResponse> getCustomerDetails(@PathVariable Long customerId) {
        CustomerDetailsResponse customerDetailsResponse = customerDetailsService.getCustomerDetails(customerId);
        return ResponseEntity.ok(customerDetailsResponse);
    }
}
