package com.entrocopilot.employeeManagementService.service.client;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.stereotype.Component;
import org.springframework.http.HttpHeaders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Component
public class FeignAuthorizationInterceptor implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
        // Fetch token from incoming HTTP request header (for example, from the API Gateway)
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            String token = attributes.getRequest().getHeader(HttpHeaders.AUTHORIZATION);
            if (token != null && token.startsWith("Bearer ")) {
                requestTemplate.header(HttpHeaders.AUTHORIZATION, token);
            }
        }
    }
}
