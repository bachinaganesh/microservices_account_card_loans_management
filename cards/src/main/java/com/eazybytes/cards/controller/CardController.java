package com.eazybytes.cards.controller;

import com.eazybytes.cards.config.CardsPropertiesConfig;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.eazybytes.cards.dto.requests.CardRequest;
import com.eazybytes.cards.dto.responses.CardResponse;
import com.eazybytes.cards.service.ICardService;

@Tag(
        description = "REST end points of card ms",
        name = "Card Controller Microservice"
)
@RestController
@RequestMapping("/cards")
public class CardController {

    private final ICardService cardService;
    private final CardsPropertiesConfig cardsPropertiesConfig;
    @Value("${build.version}")
    private String buildVersion;

    public CardController(ICardService cardService, CardsPropertiesConfig cardsPropertiesConfig) {
        this.cardService = cardService;
        this.cardsPropertiesConfig = cardsPropertiesConfig;
    }

    @Operation(
            description = "Apply a new card"
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully applied the card"
                    ),
                    @ApiResponse(
                            responseCode = "409",
                            description = "Card already existed with the given mobile number"
                    )
            }
    )
    @PostMapping
    public ResponseEntity<CardResponse> applyCard(@RequestBody CardRequest cardRequest) {
        CardResponse cardResponse = cardService.applyCard(cardRequest);
        return ResponseEntity.ok(cardResponse);
    }

    @Operation(
            description = "Get the card details based on the provided mobile number"
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully get the card details"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Card not found with given mobile number"
                    )
            }
    )
    @GetMapping
    public ResponseEntity<CardResponse> getCardDetails(@RequestParam String mobileNumber) {
        CardResponse cardResponse = cardService.getCardDetails(mobileNumber);
        return ResponseEntity.ok(cardResponse);
    }

    @Operation(
            description = "Delete card based on provided mobile number"
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully deleted the card"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Card not found with given mobile number"
                    )
            }
    )
    @DeleteMapping
    public ResponseEntity<String> deleteCard(@RequestParam String mobileNumber) {
        String response = this.cardService.deleteCard(mobileNumber);
        return ResponseEntity.ok(response);
    }

    @Operation(
            description = "Update the card mobile number"
    )
    @ApiResponses(
            {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successfully updated the card"
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Card not found with the given card id"
                    )
            }
    )
    @PatchMapping("/{cardId}")
    public ResponseEntity<CardResponse> updateCard(@PathVariable Long cardId, @RequestParam String mobileNumber) {
        CardResponse cardResponse = this.cardService.updateCard(cardId, mobileNumber);
        return ResponseEntity.ok(cardResponse);
    }

    @GetMapping("/build-version")
    public ResponseEntity<String> getBuildVersion() {
        return ResponseEntity.ok(buildVersion);
    }

    @GetMapping("/contact-details")
    public ResponseEntity<CardsPropertiesConfig> getContactDetails() {
        return ResponseEntity.ok(cardsPropertiesConfig);
    }
}
