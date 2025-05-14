package com.tradingai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.web.SecurityFilterChain;

public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/api/public/**").permitAll() // Public endpoints
                .anyRequest().authenticated() // All other endpoints require authentication
                .and()
                .oauth2Login() // Enable OAuth2 login
                .defaultSuccessUrl("/api/private/welcome", true) // Redirect after successful login
                .and()
                .oauth2ResourceServer()
                .jwt(); // Enable JWT validation for resource server
        return http.build();
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring().antMatchers("/api/public/**");
    }
}
