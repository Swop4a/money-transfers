package com.swop4a.money.transfers.api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author swop4a
 * @since 28/04/2019 12:58
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	private static final String VERSION = "1.0.0";

	@Bean
	public Docket productApi() {
		return new Docket(DocumentationType.SWAGGER_2).select()
			.apis(RequestHandlerSelectors.basePackage("com.swop4a.money.transfers.api.controllers"))
			.build()
			.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
			.title("Transfer service")
			.description("API which provide operations for transferring money between internal user's accounts")
			.version(VERSION)
			.contact(
				new Contact("Aleksandr Pavkin",
					"https://github.com/Swop4a",
					"alexandr300896@gmail.com"))
			.build();
	}
}
