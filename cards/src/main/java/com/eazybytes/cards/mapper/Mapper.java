package com.eazybytes.cards.mapper;

import com.eazybytes.cards.dto.requests.CardRequest;
import com.eazybytes.cards.dto.responses.CardResponse;
import com.eazybytes.cards.model.Card;

public class Mapper {

    public static Card mapToCard(CardRequest cardRequest, Card card) {
        card.setCardType(cardRequest.getCardType());
        card.setMobileNumber(cardRequest.getMobileNumber());
        return card;
    }

    public static CardResponse mapToCardResponse(Card card, CardResponse cardResponse) {
        cardResponse.setCardId(card.getCardId());
        cardResponse.setCardNumber(card.getCardNumber());
        cardResponse.setCardType(card.getCardType());
        cardResponse.setMobileNumber(card.getMobileNumber());
        cardResponse.setTotalLimit(card.getTotalLimit());
        cardResponse.setAmountUsed(card.getAmountUsed());
        cardResponse.setAvailableAmount(card.getAvailableAmount());
        return cardResponse;
    }
}
