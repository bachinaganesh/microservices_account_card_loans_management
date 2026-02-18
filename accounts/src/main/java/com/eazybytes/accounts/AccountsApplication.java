package com.eazybytes.accounts;

import com.eazybytes.accounts.config.AccountPropertiesConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@OpenAPIDefinition(
	info = @Info(
		title = "Account Microservice",
		description = "Managing Customer and Account data",
		version = "1.0"
	)
)
@EnableConfigurationProperties(value = {AccountPropertiesConfig.class})
@SpringBootApplication
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
