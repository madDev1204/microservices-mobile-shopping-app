package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {
    //created a bean of type WebClient called webClient
    @Bean
    public WebClient webClient(){
        return WebClient.builder().build();
    }
}
