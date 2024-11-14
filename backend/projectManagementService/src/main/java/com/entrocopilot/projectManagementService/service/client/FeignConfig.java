package com.entrocopilot.projectManagementService.service.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {
    
    @Bean
    public FeignAuthorizationInterceptor feignAuthorizationInterceptor() {
        return new FeignAuthorizationInterceptor();
    }
}