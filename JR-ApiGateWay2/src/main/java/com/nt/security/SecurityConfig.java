package com.nt.security;

import java.security.Key;
import java.util.Base64;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Value("${jwt.secret-key}")
    private String SECRET_KEY_BASE64;

    // Define the JWT decoder to validate tokens using a symmetric key
    @Bean
    public ReactiveJwtDecoder jwtDecoder() {
        byte[] decodedKey = Base64.getDecoder().decode(SECRET_KEY_BASE64);
        Key key = new SecretKeySpec(decodedKey, 0, decodedKey.length, "HmacSHA256");
        return NimbusReactiveJwtDecoder.withSecretKey((SecretKey) key).build();
    }

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
            .csrf(csrf -> csrf.disable()) // Disable CSRF protection
            .authorizeExchange(exchange -> exchange
            		.pathMatchers("/auth/**").permitAll()
                .pathMatchers("/actuator/**").permitAll() // Allow access to Actuator endpoints
                .anyExchange().authenticated() // Protect all other endpoints
            )
            .oauth2ResourceServer(oauth2 -> oauth2
                .jwt(jwt -> jwt
                    .jwtDecoder(jwtDecoder()) // Use the JWT decoder defined above
                )
            );

        return http.build();
    }
}
