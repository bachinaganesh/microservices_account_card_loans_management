package com.eazybytes.cards.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardResponse {
    private Long cardId;
    private String cardNumber;
    private String cardType;
    private String mobileNumber;
    private Double totalLimit;
    private Double amountUsed;
    private Double availableAmount;
}
