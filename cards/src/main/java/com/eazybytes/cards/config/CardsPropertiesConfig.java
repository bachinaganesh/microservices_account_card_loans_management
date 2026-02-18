package com.eazybytes.cards.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "cards")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardsPropertiesConfig {

    private String message;
    private List<String> onCallSupport;
    private Map<String, String> contactDetails;

}
