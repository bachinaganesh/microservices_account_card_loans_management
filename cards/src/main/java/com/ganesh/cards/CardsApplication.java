package com.ganesh.cards;

import com.ganesh.cards.config.CardsPropertiesConfig;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@OpenAPIDefinition(
		info = @Info(
				title = "Cards Microservice",
				description = "Rest API end points on card microservices",
				version = "1.0"
		)
)
@EnableConfigurationProperties(value = {CardsPropertiesConfig.class})
@SpringBootApplication
public class CardsApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardsApplication.class, args);
	}

}
