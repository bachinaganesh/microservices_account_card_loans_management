package com.eazybytes.accounts.feignclients;

import com.eazybytes.accounts.dto.responses.LoanResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("loans")
public interface LoanFeignClient {

    @GetMapping("/loans")
    public ResponseEntity<LoanResponse> getLoanByMobileNumber(@RequestParam String mobileNumber);

}
