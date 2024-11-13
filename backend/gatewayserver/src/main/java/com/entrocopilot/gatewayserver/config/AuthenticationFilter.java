package com.entrocopilot.gatewayserver.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.entrocopilot.gatewayserver.service.client.UserFeignClient;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {
	
	
	private RouteValidator validator;
	
	
	private RestTemplate restTemplate;
	
	public AuthenticationFilter(RouteValidator validator, RestTemplate restTemplate) {
        super(Config.class);
        this.validator = validator;
        this.restTemplate = restTemplate;
    }

	public static class Config{
		
	}

	@Override
	public GatewayFilter apply(Config config) {
	    return ((exchange, chain) -> {
	        // Check if the request needs authentication
	        if (validator.isSecured.test(exchange.getRequest())) {
	            // Check if the authorization header is present
	            if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
	                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
	                return exchange.getResponse().setComplete();  // End the request here with 401
	            }

	            // Extract the token from the authorization header
	            String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
	            if (authHeader != null && authHeader.startsWith("Bearer ")) {
	                String token = authHeader.substring(7);  // Remove "Bearer " prefix
	                
	                // Prepare the Authorization header for the REST request
	                HttpHeaders headers = new HttpHeaders();
	                headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + token);  // Add the token to the Authorization header

	                // Prepare the request entity with the headers
	                HttpEntity<String> entity = new HttpEntity<>(headers);

	                try {
	                    // Call the backend service to validate the token
	                    ResponseEntity<Boolean> response = restTemplate.exchange(
	                        "http://localhost:8081/api/user/userProfile/validateToken", 
	                        HttpMethod.GET, 
	                        entity, 
	                        Boolean.class
	                    );
	                    
	                    // If the token is not valid, return 401 Unauthorized
	                    if (response.getBody() == null || !response.getBody()) {
	                        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
	                        return exchange.getResponse().setComplete();  // End the request here with 401
	                    }
	                    
	                } catch (Exception e) {
	                    // Handle any exceptions that may occur during the validation request
	                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
	                    exchange.getResponse().getHeaders().add("Error", "Invalid or expired token");
	                    return exchange.getResponse().setComplete();  // End the request here with 401
	                }
	            } else {
	                // Invalid token format
	                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
	                return exchange.getResponse().setComplete();  // End the request here with 401
	            }
	        }

	        // Continue to the next filter in the chain
	        return chain.filter(exchange);
	    });
	}

}
