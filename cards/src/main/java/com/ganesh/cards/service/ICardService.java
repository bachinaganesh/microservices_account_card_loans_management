package com.ganesh.cards.service;

import com.ganesh.cards.dto.requests.CardRequest;
import com.ganesh.cards.dto.responses.CardResponse;

public interface ICardService {

    public CardResponse applyCard(CardRequest cardRequest);
    public CardResponse getCardDetails(String mobileNumber);
    public String deleteCard(String mobileNumber);
    public CardResponse updateCard(Long cardId, String mobileNumber);
}
