package com.introcopilot.user.security;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import com.introcopilot.user.token.TokenRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private UserDetailsService userDetailsService;
    private JwtHelper jwtHelper;
    private TokenRepository tokenRepository;

    public JwtAuthenticationFilter(UserDetailsService userDetailsService, JwtHelper jwtHelper,
            TokenRepository tokenRepository) {
        super();
        this.userDetailsService = userDetailsService;
        this.jwtHelper = jwtHelper;
        this.tokenRepository = tokenRepository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // Skip token validation for login and registration endpoints
        String requestUri = request.getRequestURI();
        if (requestUri.equals("/api/user/login") || requestUri.equals("/api/user/register")) {
            // Continue to the next filter in the chain (skip token validation)
            filterChain.doFilter(request, response);
            return;
        }

        // Extract token from the request
        String token = getTokenFromRequest(request);

        // If token is present, validate it
        if (token != null) {
            // Extract username from the token
            String username = jwtHelper.getUsernameFromToken(token);

            // Load user details from the database or user service
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            // Validate token by checking its expiration and revocation status
            Boolean isTokenValid = tokenRepository.findByToken(token).map(t -> !t.isRevoked() && !t.isExpired()).orElse(false);

            // If the token is valid, authenticate the user
            if (StringUtils.hasText(token) && jwtHelper.validateToken(token, userDetails) && isTokenValid) {
                // Create authentication token and set it in SecurityContextHolder
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            } else {
                // Token is invalid, respond with Unauthorized status
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("Invalid or expired token");
                return;  // Prevent further processing
            }
        } else {
            // If token is not provided, respond with Unauthorized status
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Authorization header is missing or invalid");
            return;  // Prevent further processing
        }

        // If everything is fine, continue the filter chain
        filterChain.doFilter(request, response);
    }

    // Extract token from the Authorization header
    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // Remove "Bearer " prefix
        }
        return null; // No token provided
    }
}
