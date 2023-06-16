package com.ragalzi.controlprocessservice.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    RestTemplate restTemplate() {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        // Imposta un timeout per le richieste HTTP (opzionale)
        requestFactory.setConnectTimeout(5000);
        requestFactory.setReadTimeout(5000);

        return new RestTemplate(requestFactory);
    }
}
