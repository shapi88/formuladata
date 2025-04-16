package com.sport.formuladata.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {
    @Bean
    public RestClient openF1RestClient() {
        return RestClient.builder()
                .baseUrl("https://api.openf1.org/v1")
                .build();
    }
}
