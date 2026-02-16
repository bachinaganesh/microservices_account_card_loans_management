package com.eazybytes.accounts.service.impl;

import java.util.Optional;
import java.util.Random;

import org.springframework.stereotype.Service;

import com.eazybytes.accounts.dto.requests.AccountRequest;
import com.eazybytes.accounts.dto.requests.CustomerRequest;
import com.eazybytes.accounts.dto.responses.AccountResponse;
import com.eazybytes.accounts.dto.responses.CustomerResponse;
import com.eazybytes.accounts.entities.Account;
import com.eazybytes.accounts.entities.Customer;
import com.eazybytes.accounts.exceptions.CustomerAlreadyExistException;
import com.eazybytes.accounts.exceptions.CustomerNotFoundException;
import com.eazybytes.accounts.mapper.Mapper;
import com.eazybytes.accounts.repository.AccountRepository;
import com.eazybytes.accounts.repository.CustomerRepository;
import com.eazybytes.accounts.service.IAccountService;

@Service
public class AccountServiceImpl implements IAccountService{

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    public AccountServiceImpl(AccountRepository accountRepository, CustomerRepository customerRepository) {
        this.accountRepository = accountRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerResponse createAccount(CustomerRequest customerRequest) {
        Optional<Customer> optionalCustomer = this.customerRepository.findByMobileNumber(customerRequest.getMobileNumber());

        if(optionalCustomer.isPresent()) {
            throw new CustomerAlreadyExistException("Customer already exist with mobile number: "+customerRequest.getMobileNumber());
        }

        Customer customer = Mapper.mapToCustomer(customerRequest, new Customer());
        Customer createdCustomer = this.customerRepository.save(customer);

        Account account = Mapper.mapToAccount(customerRequest.getAccountRequest(), new Account());
        account.setAccountId(1000000L+ new Random().nextLong(90000000L));
        account.setCustomerId(createdCustomer.getId());

        Account createdAccount = this.accountRepository.save(account);

        AccountResponse accountResponse = Mapper.mapToAccountResponse(createdAccount, new AccountResponse());

        CustomerResponse customerResponse = Mapper.mapToCustomerResponse(createdCustomer, new CustomerResponse());
        customerResponse.setAccountResponse(accountResponse);

        return customerResponse;
    }

    @Override
    public CustomerResponse getAccountDetails(Long customerId) {
        Customer customer = this.customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException("Customer not found with an id: "+customerId));

        Account account = this.accountRepository.findByCustomerId(customerId).get();

        AccountResponse accountResponse = Mapper.mapToAccountResponse(account, new AccountResponse());

        CustomerResponse customerResponse = Mapper.mapToCustomerResponse(customer, new CustomerResponse());
        customerResponse.setAccountResponse(accountResponse);

        return customerResponse;
    }

    @Override
    public String deleteAccount(Long customerId) {
        this.customerRepository.findById(customerId).orElseThrow(()-> new CustomerNotFoundException("Customer not found with an id: "+customerId));
        
        this.customerRepository.deleteById(customerId);
        this.accountRepository.deleteByCustomerId(customerId);

        return "Customer deleted with an id: "+customerId;
    }

    @Override
    public CustomerResponse updateAccount(CustomerRequest customerRequest, Long customerId) {
        Customer customer = this.customerRepository.findById(customerId).orElseThrow(()-> new CustomerNotFoundException("Customer not found with an id: "+customerId));

        Customer updatedCustomer = Mapper.mapToCustomer(customerRequest, customer);
        updatedCustomer.setId(customerId);
        this.customerRepository.save(updatedCustomer);

        AccountRequest accountRequest = customerRequest.getAccountRequest();

        Account account = this.accountRepository.findByCustomerId(customerId).get();

        account = Mapper.mapToAccount(accountRequest, account);
        this.accountRepository.save(account);
        
        AccountResponse accountResponse = Mapper.mapToAccountResponse(account, new AccountResponse());
        CustomerResponse customerResponse = Mapper.mapToCustomerResponse(updatedCustomer, new CustomerResponse());
        customerResponse.setAccountResponse(accountResponse);

        return customerResponse;
    }

}
