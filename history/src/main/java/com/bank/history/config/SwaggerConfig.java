package com.bank.history.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Конфигурация swagger.
 * SwaggerConfig будет использоваться как для экспорта пользовательского интерфейса Swagger, так и для генерации спецификации OpenAPI.
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("history-app")
                        .description("Это пример службы Spring Boot RESTful с использованием OpenAPI")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Bakulin Vladimir")
                                .email("bakulin@mail.ru")));
    }
}
