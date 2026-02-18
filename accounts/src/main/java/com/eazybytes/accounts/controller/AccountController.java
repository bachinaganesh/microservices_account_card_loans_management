package com.eazybytes.accounts.controller;

import com.eazybytes.accounts.config.AccountPropertiesConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eazybytes.accounts.dto.requests.CustomerRequest;
import com.eazybytes.accounts.dto.responses.CustomerResponse;
import com.eazybytes.accounts.service.IAccountService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(
    name = "Account Controller Rest API",
    description = "Managing CRUD end points related to Account Data"
)
@RestController
@Validated
@RequestMapping("/accounts")
public class AccountController {

    private final IAccountService accountService;
    private final AccountPropertiesConfig accountPropertiesConfig;
    @Value("${build.version}")
    private String buildVersion;

    public AccountController(IAccountService accountService, AccountPropertiesConfig accountPropertiesConfig) {
        this.accountService = accountService;
        this.accountPropertiesConfig = accountPropertiesConfig;
    }

    @Operation(
        description = "Create user account"
    )
    @ApiResponses(
        {
            @ApiResponse(
                responseCode = "201",
                description = "Account created successfully"
            ),
            @ApiResponse(
                responseCode = "409",
                description = "Account already existed"
            )
        }
    )
    @PostMapping
    public ResponseEntity<CustomerResponse> createAccount(@Valid @RequestBody CustomerRequest customerRequest) {
        CustomerResponse customerResponse = this.accountService.createAccount(customerRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(customerResponse);
    }

    @ApiResponses(
        {
            @ApiResponse(
                responseCode = "200",
                description = "Fetch the account details based on customer id"
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Customer not found based on provided customer id"
            )
        }
    )
    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerResponse> getAccountDetails(@PathVariable Long customerId) {
        CustomerResponse customerResponse = this.accountService.getAccountDetails(customerId);
        return ResponseEntity.ok().body(customerResponse);
    }

    @ApiResponses(
        {
            @ApiResponse(
                responseCode = "200",
                description = "Account deleted successfully"
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Account not found based on customer id"
            )
        }
    )
    @DeleteMapping("/{customerId}")
    public ResponseEntity<String> deleteAccount(@PathVariable Long customerId) {
        String message = this.accountService.deleteAccount(customerId);
        return ResponseEntity.ok().body(message);
    }

    @ApiResponses(
        {
            @ApiResponse(
                responseCode = "200",
                description = "Account updated successfully"
            ),
            @ApiResponse(
                responseCode = "404",
                description = "Account not found based on customer id"
            )
        }
    )
    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerResponse> updateAccount(@Valid @RequestBody CustomerRequest customerRequest, @PathVariable Long customerId) {
        CustomerResponse customerResponse = this.accountService.updateAccount(customerRequest, customerId);
        return ResponseEntity.ok().body(customerResponse);
    }

    @GetMapping("/build-version")
    public ResponseEntity<String> getBuildVersion() {
        return ResponseEntity.ok(buildVersion);
    }

    @GetMapping("/contact-details")
    public ResponseEntity<AccountPropertiesConfig>  getAccountPropertiesConfig() {
        return ResponseEntity.ok(accountPropertiesConfig);
    }
}
