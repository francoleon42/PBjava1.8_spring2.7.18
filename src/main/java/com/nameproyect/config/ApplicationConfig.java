package com.nameproyect.config;

import com.nameproyect.repository.IUsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.client.RestTemplate;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    private final IUsuarioRepository iUsuarioRepository;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
