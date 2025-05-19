package com.tradingai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors() // Enable CORS
                .and()
                .authorizeRequests()
                .antMatchers("/api/public/**").permitAll() // Public endpoints
                .anyRequest().authenticated() // All other endpoints require authentication
                .and()
                .oauth2Login() // Enable OAuth2 login
                .defaultSuccessUrl("/api/private/welcome", true) // Redirect after successful login
                .and()
                .oauth2ResourceServer()
                .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter())); // Add JWT converter
        return http.build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        // Customize converter if needed (e.g., setJwtGrantedAuthoritiesConverter)
        return new JwtAuthenticationConverter();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().antMatchers("/api/public/**");
    }
}
