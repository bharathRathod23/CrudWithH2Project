package com.bvr.h2project;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@Slf4j
public class H2projectApplication {

	public static void main(String[] args) {

		SpringApplication.run(H2projectApplication.class, args);
	}


//	@Bean
//	public OpenAPI customOpenAPI() {
//		log.info("custom OpenAPI Started...");
//		return new OpenAPI()
//				.components(new Components())
//				.info(new Info().title("Notification Engine NotificationProducer Application API Doc").description(
//						"This is a Spring Boot RESTful service using springdoc-openapi and OpenAPI 3."));
//	}

}
