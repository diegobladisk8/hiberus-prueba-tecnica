package com.hiberus.payment.domain.exception;

import com.hiberus.payment.infrastructure.exception.GlobalExceptionHandler;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.RequestPath;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.mock.http.server.reactive.MockServerHttpRequest;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.support.WebExchangeBindException;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void testHandleValidationExceptions() {

        // --- Arrange ---
        // Mock del ServerHttpRequest y RequestPath
        ServerHttpRequest request = Mockito.mock(ServerHttpRequest.class);
        RequestPath mockPath = Mockito.mock(RequestPath.class);

        Mockito.when(request.getPath()).thenReturn(mockPath);
        Mockito.when(mockPath.value()).thenReturn("/api/payment-orders");

        // Crear BindingResult con un error de validación
        BindingResult bindingResult =
                new BeanPropertyBindingResult(new Object(), "paymentOrderRequest");

        bindingResult.addError(new FieldError(
                "paymentOrderRequest",
                "externalReference",
                "must not be blank"
        ));

        WebExchangeBindException ex =
                new WebExchangeBindException(null, bindingResult);

        // --- Act ---
        Mono<ResponseEntity<Map<String, Object>>> resultMono =
                handler.handleValidationExceptions(ex, request);

        // --- Assert ---
        StepVerifier.create(resultMono)
                .assertNext(response -> {
                    assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

                    Map<String, Object> body = response.getBody();
                    assertNotNull(body);

                    assertEquals("Validation Error", body.get("title"));
                    assertEquals(400, body.get("status"));
                    assertEquals("Invalid request parameters", body.get("detail"));
                    assertEquals("/api/payment-orders", body.get("instance"));

                    assertTrue(body.containsKey("timestamp"));
                    assertTrue(body.containsKey("errors"));

                    List<FieldError> errors = (List<FieldError>) body.get("errors");
                    assertEquals(1, errors.size());

                    FieldError error = errors.get(0);
                    assertEquals("externalReference", error.getField());
                    assertEquals("must not be blank", error.getDefaultMessage());
                })
                .verifyComplete();
    }

}
