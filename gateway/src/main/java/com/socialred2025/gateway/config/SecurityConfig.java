package com.socialred2025.gateway.config;

import com.socialred2025.gateway.filter.JwtTokenValidatorFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    private final JwtTokenValidatorFilter jwtTokenValidatorFilter;

    public SecurityConfig(JwtTokenValidatorFilter jwtTokenValidatorFilter) {
        this.jwtTokenValidatorFilter = jwtTokenValidatorFilter;
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(authorize -> authorize
                        .pathMatchers("/auth/**").permitAll()
                        .pathMatchers("/users/**").hasAuthority("LIKE_POSTS")
                        .anyExchange().authenticated())
                .addFilterAt(jwtTokenValidatorFilter, SecurityWebFiltersOrder.AUTHENTICATION)
                .build();
    }
}
