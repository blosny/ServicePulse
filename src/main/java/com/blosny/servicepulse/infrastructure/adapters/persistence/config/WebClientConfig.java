package com.blosny.servicepulse.infrastructure.adapters.persistence.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    
    @Bean
    public WebClient webClient() {
        // Dışarıdan builder beklemiyoruz, kendimiz o an oluşturuyoruz.
        // Bu hata riskini sıfıra indirir.
        return WebClient.builder().build();
    }
}