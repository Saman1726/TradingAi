package com.tradingai.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.Collection;

public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors() // <-- This enables CORS with your WebMvcConfigurer
                .and()
                .authorizeRequests()
                .antMatchers("/api/public/**", "/error").permitAll() // <-- add "/error"
                .anyRequest().authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt(jwt -> jwt.jwtAuthenticationConverter(jwtAuthenticationConverter()));
        return http.build();
    }

    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter converter = new JwtAuthenticationConverter();
        // Example: Use "roles" claim from JWT as authorities
        converter.setJwtGrantedAuthoritiesConverter(jwt -> {
            Collection<GrantedAuthority> authorities = new ArrayList<>();
            Object roles = jwt.getClaims().get("roles");
            if (roles instanceof Collection<?>) {
                ((Collection<?>) roles).forEach(role ->
                    authorities.add(new SimpleGrantedAuthority("ROLE_" + role))
                );
            }
            return authorities;
        });
        return converter;
    }
}
