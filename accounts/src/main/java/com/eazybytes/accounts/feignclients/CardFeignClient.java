package com.eazybytes.accounts.feignclients;

import com.eazybytes.accounts.dto.responses.CardResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("cards")
public interface CardFeignClient {

    @GetMapping("/cards")
    public ResponseEntity<CardResponse> getCardDetails(@RequestParam String mobileNumber);
}
