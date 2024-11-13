package com.entrocopilot.gatewayserver.config;

import org.springframework.stereotype.Component;
import org.springframework.http.server.reactive.ServerHttpRequest;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Predicate;

@Component
public class RouteValidator {
    
    // Using a Set for fast lookups
    private static final Set<String> openApiEndpoints = new HashSet<>(Set.of(
            "/api/user/register",
            "/api/user/login",
            "/eureka"));

    // Using a Predicate to check if the request is secured
    public Predicate<ServerHttpRequest> isSecured = request -> {
        String path = request.getURI().getPath();
        
        // Match the path exactly or apply your custom matching logic
        return openApiEndpoints.stream().noneMatch(path::equals);
    };
}
