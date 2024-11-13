package com.introcopilot.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.introcopilot.user.security.JwtAuthenticationEntryPoint;
import com.introcopilot.user.security.JwtAuthenticationFilter;

@Configuration
public class SecurityConfig {
	private UserDetailsService userDetailsService;
	private JwtAuthenticationEntryPoint authenticationEntryPoint;
	private JwtAuthenticationFilter authenticationFilter;

	public SecurityConfig(UserDetailsService userDetailsService, JwtAuthenticationEntryPoint authenticationEntryPoint,
			JwtAuthenticationFilter authenticationFilter) {
		super();
		this.userDetailsService = userDetailsService;
		this.authenticationEntryPoint = authenticationEntryPoint;
		this.authenticationFilter = authenticationFilter;
	}

	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests((authorize) -> authorize
						.requestMatchers("/api/user/**").permitAll()
						.requestMatchers("/api/userProfile/**").permitAll()
//						.requestMatchers("/api/userProfile/validateToken").hasRole("ADMIN")
//						.requestMatchers("/api/department").permitAll()
						.anyRequest()
						.authenticated())
				.httpBasic(Customizer.withDefaults())
				.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
