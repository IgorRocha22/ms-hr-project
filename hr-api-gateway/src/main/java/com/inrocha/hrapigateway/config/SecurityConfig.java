package com.inrocha.hrapigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    private static final String[] PUBLIC = { "/hr-oauth/oauth2/token" };

    private static final String[] OPERATOR = { "/hr-worker/**" };

    private static final String[] ADMIN = { "/hr-payroll/**", "/hr-user/**", "/actuator/**", "/hr-worker/actuator/**", "/hr-oauth/actuator/**" };

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) {
        http
            .authorizeExchange(authorize -> authorize
                .pathMatchers(PUBLIC).permitAll()
                    .pathMatchers(OPERATOR).hasAnyRole("OPERATOR", "ADMIN")
                    .pathMatchers(ADMIN).hasAnyRole("ADMIN")
                .anyExchange().authenticated()
            )
            .oauth2ResourceServer(oauth2 -> oauth2
                .jwt(Customizer.withDefaults())
            );
        return http.build();
    }
}