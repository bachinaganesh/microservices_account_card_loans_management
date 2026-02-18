package com.eazybytes.loans;

import com.eazybytes.loans.config.LoansPropertiesConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@OpenAPIDefinition(
	info = @Info(
		title = "Loans API",
		version = "1.0",
		description = "API for managing loans"
	)
)
@EnableConfigurationProperties(value = {LoansPropertiesConfig.class})
@SpringBootApplication
public class LoansApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
	}

}
