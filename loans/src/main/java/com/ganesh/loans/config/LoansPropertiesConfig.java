package com.ganesh.loans.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "loans")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoansPropertiesConfig {

    private String message;
    private List<String> onCallSupport;
    private Map<String, String> contactDetails;
}
