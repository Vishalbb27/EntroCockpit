package com.entrocopilot.gatewayserver.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class SpringSecurityConfig {
	@Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(); // Creates a RestTemplate instance
    }

}
