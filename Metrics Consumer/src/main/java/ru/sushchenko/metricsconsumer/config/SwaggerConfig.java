package ru.sushchenko.metricsconsumer.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "Consumer service",
                description = "Consumer service consume metrics of producer app from Kafka and store them in PostgreSQL",
                version = "1.0.0",
                contact = @Contact(
                        name = "Sushchenko Artyom",
                        email = "artoymsushchenko@gmail.com",
                        url = "https://github.com/lordphiluren"
                )
        )
)
public class SwaggerConfig {
}
