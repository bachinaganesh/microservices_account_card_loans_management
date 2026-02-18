package com.eazybytes.cards.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.eazybytes.cards.dto.requests.CardRequest;
import com.eazybytes.cards.dto.responses.CardResponse;
import com.eazybytes.cards.exceptions.CardAlreadyExistedException;
import com.eazybytes.cards.exceptions.CardNotFoundException;
import com.eazybytes.cards.mapper.Mapper;
import com.eazybytes.cards.model.Card;
import com.eazybytes.cards.repository.CardRepository;
import com.eazybytes.cards.service.ICardService;
import com.eazybytes.cards.utils.CardUtils;

@Service
public class CardServiceImpl implements ICardService {

    private final CardRepository cardRepository;

    public CardServiceImpl(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }


    @Override
    public CardResponse applyCard(CardRequest cardRequest) {
        Optional<Card> optionalCard = cardRepository.findByMobileNumber(cardRequest.getMobileNumber());
        if(optionalCard.isPresent()) {
            throw new CardAlreadyExistedException("Card already existed for the given mobile number : "+cardRequest.getMobileNumber());
        }

        Double cardLimit = CardUtils.getCardLimit(cardRequest.getCardType());
        Card card = new Card();
        card = Mapper.mapToCard(cardRequest, card);
        card.setTotalLimit(cardLimit);
        card.setAvailableAmount(cardLimit);
        card.setAmountUsed(0.0);
        card.setCardNumber(CardUtils.generateCardNumber());

        Card savedCard = cardRepository.save(card);
        CardResponse cardResponse = new CardResponse();
        cardResponse = Mapper.mapToCardResponse(savedCard, cardResponse);
        return cardResponse;
    }

    @Override
    public CardResponse getCardDetails(String mobileNumber) {
        Optional<Card> optionalCard = cardRepository.findByMobileNumber(mobileNumber);
        if(optionalCard.isEmpty()) {
            throw new CardNotFoundException("Card not found for the given mobile number : "+mobileNumber);
        }

        Card card = optionalCard.get();
        CardResponse cardResponse = new CardResponse();
        cardResponse = Mapper.mapToCardResponse(card, cardResponse);
        return cardResponse;
    }

    @Override
    public String deleteCard(String mobileNumber) {
        this.cardRepository.findByMobileNumber(mobileNumber).orElseThrow(()-> new CardNotFoundException("Card not found for the given mobile number : "+mobileNumber));
        this.cardRepository.deleteByMobileNumber(mobileNumber);
        return "Card deleted successfully";
    }

    @Override
    public CardResponse updateCard(Long cardId, String mobileNumber) {
        Card card = this.cardRepository.findById(cardId).orElseThrow(() -> new CardNotFoundException("Card not found with an id : "+cardId));
        card.setMobileNumber(mobileNumber);

        Card updatedCard = this.cardRepository.save(card);
        return Mapper.mapToCardResponse(updatedCard, new CardResponse());
    }

}
