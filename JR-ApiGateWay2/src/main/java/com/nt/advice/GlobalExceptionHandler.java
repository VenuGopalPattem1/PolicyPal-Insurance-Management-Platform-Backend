package com.nt.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.oauth2.server.resource.InvalidBearerTokenException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.reactive.function.server.ServerResponse;

import reactor.core.publisher.Mono;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidBearerTokenException.class)
    public Mono<ServerResponse> handleInvalidBearerTokenException(InvalidBearerTokenException ex) {
        return ServerResponse
                .status(HttpStatus.UNAUTHORIZED)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("{\"error\":\"Invalid token: " + ex.getMessage() + "\"}");
    }

    @ExceptionHandler(BadCredentialsException.class)
    public Mono<ServerResponse> handleBadCredentialsException(BadCredentialsException ex) {
        return ServerResponse
                .status(HttpStatus.UNAUTHORIZED)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("{\"error\":\"Bad credentials: " + ex.getMessage() + "\"}");
    }

    @ExceptionHandler(AuthenticationCredentialsNotFoundException.class)
    public Mono<ServerResponse> handleAuthenticationCredentialsNotFoundException(AuthenticationCredentialsNotFoundException ex) {
        return ServerResponse
                .status(HttpStatus.UNAUTHORIZED)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue("{\"error\":\"Credentials not found: " + ex.getMessage() + "\"}");
    }

    // Add more exception handlers as needed
}

