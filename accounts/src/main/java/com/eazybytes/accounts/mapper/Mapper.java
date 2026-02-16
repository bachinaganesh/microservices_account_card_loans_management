package com.eazybytes.accounts.mapper;

import java.time.LocalDateTime;

import com.eazybytes.accounts.dto.requests.AccountRequest;
import com.eazybytes.accounts.dto.requests.CustomerRequest;
import com.eazybytes.accounts.dto.responses.AccountResponse;
import com.eazybytes.accounts.dto.responses.CustomerResponse;
import com.eazybytes.accounts.entities.Account;
import com.eazybytes.accounts.entities.Customer;

public class Mapper {

    public static Customer mapToCustomer(CustomerRequest customerRequest, Customer customer) {
        customer.setName(customerRequest.getName());
        customer.setMobileNumber(customerRequest.getMobileNumber());
        customer.setGender(customerRequest.getGender());
        customer.setMail(customerRequest.getMail());
        customer.setUpdatedAt(LocalDateTime.now());
        customer.setCreatedBy(customerRequest.getName());
        return customer;
    }

    public static Account mapToAccount(AccountRequest accountRequest, Account account) {
        account.setAccountType(accountRequest.getType());
        account.setBranch(accountRequest.getBranch());
        account.setBalance(accountRequest.getBalance());
        return account;
    }

    public static AccountResponse mapToAccountResponse(Account account, AccountResponse accountResponse) {
        accountResponse.setAccountId(account.getAccountId());
        accountResponse.setBalance(account.getBalance());
        accountResponse.setBranch(account.getBranch());
        accountResponse.setBalance(account.getBalance());
        accountResponse.setType(account.getAccountType());
        return accountResponse;
    }

    public static CustomerResponse mapToCustomerResponse(Customer customer, CustomerResponse customerResponse) {
        customerResponse.setEmail(customer.getMail());
        customerResponse.setGender(customer.getGender());
        customerResponse.setName(customer.getName());
        customerResponse.setMobileNumber(customer.getMobileNumber());
        customerResponse.setId(customer.getId());
        return customerResponse;
    }
}
