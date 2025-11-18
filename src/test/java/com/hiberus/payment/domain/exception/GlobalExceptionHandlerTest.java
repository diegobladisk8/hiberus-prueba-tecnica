package com.hiberus.payment.domain.exception;

import com.hiberus.payment.infrastructure.exception.GlobalExceptionHandler;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    void handlePaymentOrderNotFoundException_shouldReturnNotFoundResponse() {

        com.hiberus.payment.infrastructure.exception.PaymentOrderNotFoundException ex = new com.hiberus.payment.infrastructure.exception.PaymentOrderNotFoundException("Payment Order Not Found");
        var request = MockServerHttpRequest.get("/payment-initiation/123").build();

        Mono<ResponseEntity<Map<String, Object>>> response = handler.handlePaymentOrderNotFoundException(ex, request);

        StepVerifier.create(response)
                .assertNext(entity -> {
                    assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
                    assertThat(entity.getBody()).isNotNull();
                    assertThat(entity.getBody().get("title")).isEqualTo("Payment Order Not Found");
                    assertThat(entity.getBody().get("detail")).isEqualTo(ex.getMessage());
                    assertThat(entity.getBody().get("instance")).isEqualTo("/payment-initiation/123");
                    assertThat(entity.getBody().get("timestamp")).isInstanceOf(LocalDateTime.class);
                })
                .verifyComplete();
    }



    @Test
    void handleGenericException_shouldReturnInternalServerError() {
        Exception ex = new RuntimeException("Unexpected");
        var request = MockServerHttpRequest.get("/error").build();

        Mono<ResponseEntity<Map<String, Object>>> response = handler.handleGenericException(ex, request);

        StepVerifier.create(response)
                .assertNext(entity -> {
                    assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
                    assertThat(entity.getBody()).isNotNull();
                    assertThat(entity.getBody().get("title")).isEqualTo("Internal Server Error");
                    assertThat(entity.getBody().get("detail")).isEqualTo("An unexpected error occurred");
                    assertThat(entity.getBody().get("instance")).isEqualTo("/error");
                    assertThat(entity.getBody().get("timestamp")).isInstanceOf(LocalDateTime.class);
                })
                .verifyComplete();
    }
}
