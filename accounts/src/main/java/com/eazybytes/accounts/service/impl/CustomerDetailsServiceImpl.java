package com.eazybytes.accounts.service.impl;

import com.eazybytes.accounts.dto.responses.AccountResponse;
import com.eazybytes.accounts.dto.responses.CardResponse;
import com.eazybytes.accounts.dto.responses.CustomerDetailsResponse;
import com.eazybytes.accounts.dto.responses.LoanResponse;
import com.eazybytes.accounts.entities.Account;
import com.eazybytes.accounts.entities.Customer;
import com.eazybytes.accounts.exceptions.CustomerNotFoundException;
import com.eazybytes.accounts.feignclients.CardFeignClient;
import com.eazybytes.accounts.feignclients.LoanFeignClient;
import com.eazybytes.accounts.mapper.Mapper;
import com.eazybytes.accounts.repository.AccountRepository;
import com.eazybytes.accounts.repository.CustomerRepository;
import com.eazybytes.accounts.service.ICustomerDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



@Service
public class CustomerDetailsServiceImpl implements ICustomerDetailsService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final LoanFeignClient loanFeignClient;
    private final CardFeignClient  cardFeignClient;

    public CustomerDetailsServiceImpl(AccountRepository accountRepository, CustomerRepository customerRepository, LoanFeignClient loanFeignClient, CardFeignClient cardFeignClient) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
        this.loanFeignClient = loanFeignClient;
        this.cardFeignClient = cardFeignClient;
    }

    @Override
    public CustomerDetailsResponse getCustomerDetails(Long customerId) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(()-> new CustomerNotFoundException("Customer not found with an id: "+customerId));
        Account account = accountRepository.findByCustomerId(customer.getId()).orElseThrow(()-> new CustomerNotFoundException("Account not found with an id: "+customerId));

        AccountResponse accountResponse = Mapper.mapToAccountResponse(account, new AccountResponse());

        CustomerDetailsResponse customerDetailsResponse = Mapper.mapToCustomerDetailsResponse(customer, new CustomerDetailsResponse());
        customerDetailsResponse.setAccountResponse(accountResponse);

        ResponseEntity<LoanResponse> loanResponseResponseEntity = loanFeignClient.getLoanByMobileNumber(customer.getMobileNumber());
        LoanResponse loanResponse = loanResponseResponseEntity.getBody();

        ResponseEntity<CardResponse> cardResponseResponseEntity = cardFeignClient.getCardDetails(customer.getMobileNumber());
        CardResponse cardResponse = cardResponseResponseEntity.getBody();

        customerDetailsResponse.setCard(cardResponse);
        customerDetailsResponse.setLaon(loanResponse);

        return customerDetailsResponse;

    }

}
