package com.bank.antifraud.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Documentation")
                        .version("1.0.0")
                        .description("Documentation for the API")
                        .contact(new Contact()
                                .name("Fedor")
                                .email("terbyt35@gmail.com")
                                .url("https://gitlab.com/mysmurov/p_ss_bank_4")));
    }
}
