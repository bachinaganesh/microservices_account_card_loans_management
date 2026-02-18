package com.eazybytes.accounts.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountPropertiesConfig {
    private String message;
    private List<String> onCallSupport;
    private Map<String, String> contactDetails;
}
