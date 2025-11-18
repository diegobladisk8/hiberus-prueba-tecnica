package com.hiberus.payment.infrastructure.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(PaymentOrderNotFoundException.class)
    public Mono<ResponseEntity<Map<String, Object>>> handlePaymentOrderNotFoundException(
            PaymentOrderNotFoundException ex, ServerHttpRequest request) {

        Map<String, Object> body = new HashMap<>();
        body.put("title", "Payment Order Not Found");
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("detail", ex.getMessage());
        body.put("instance", request.getPath().value());
        body.put("timestamp", LocalDateTime.now());

        return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND).body(body));
    }

    @ExceptionHandler(WebExchangeBindException.class)
    public Mono<ResponseEntity<Map<String, Object>>> handleValidationExceptions(
            WebExchangeBindException ex, ServerHttpRequest request) {

        Map<String, Object> body = new HashMap<>();
        body.put("title", "Validation Error");
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("detail", "Invalid request parameters");
        body.put("instance", request.getPath().value());
        body.put("timestamp", LocalDateTime.now());
        body.put("errors", ex.getBindingResult().getFieldErrors());

        return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body));
    }

    @ExceptionHandler(Exception.class)
    public Mono<ResponseEntity<Map<String, Object>>> handleGenericException(
            Exception ex, ServerHttpRequest request) {

        log.error("Unexpected error occurred", ex);

        Map<String, Object> body = new HashMap<>();
        body.put("title", "Internal Server Error");
        body.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
        body.put("detail", "An unexpected error occurred");
        body.put("instance", request.getPath().value());
        body.put("timestamp", LocalDateTime.now());

        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body));
    }
}
