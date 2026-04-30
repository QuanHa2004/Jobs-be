package com.example.API.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class RapidApiConfig {
    @Value("${rapidapi.key}")
    private String apiKey;

    @Value("${rapidapi.host}")
    private String apiHost;

    @Value("${rapidapi.base-url}")
    private String baseUrl;

    @Bean
    public WebClient rapidWebClient() {
        return WebClient.builder()
                .baseUrl(baseUrl)
                .defaultHeader("x-rapidapi-key", apiKey)
                .defaultHeader("x-rapidapi-host", apiHost)
                .defaultHeader("Content-Type", "application/json")
                .build();
    }
}
