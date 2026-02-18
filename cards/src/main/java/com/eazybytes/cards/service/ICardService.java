package com.eazybytes.cards.service;

import com.eazybytes.cards.dto.requests.CardRequest;
import com.eazybytes.cards.dto.responses.CardResponse;

public interface ICardService {

    public CardResponse applyCard(CardRequest cardRequest);
    public CardResponse getCardDetails(String mobileNumber);
    public String deleteCard(String mobileNumber);
    public CardResponse updateCard(Long cardId, String mobileNumber);
}
