//package com.hiberus.payment.infrastructure.adapter.controller;
//
//import com.hiberus.payment.application.usecase.CreatePaymentOrderUseCase;
//import com.hiberus.payment.application.usecase.GetPaymentOrderStatusUseCase;
//import com.hiberus.payment.application.usecase.GetPaymentOrderUseCase;
//import com.hiberus.payment.application.dto.PaymentOrderRequest;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.validation.ValidationAutoConfiguration;
//import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.context.annotation.Import;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.reactive.server.WebTestClient;
//import reactor.core.publisher.Mono;
//
//import java.math.BigDecimal;
//import java.time.LocalDate;
//import java.time.LocalDateTime;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//@WebFluxTest(controllers = PaymentOrderController.class)
//@Import(ValidationAutoConfiguration.class) // 👈 Esto arregla la validación
//class PaymentOrderControllerTest {
//
//    @MockBean // 👈 Usar @MockBean en lugar de @Mock para Spring context
//    private CreatePaymentOrderUseCase createPaymentOrderUseCase;
//
//    @MockBean
//    private GetPaymentOrderUseCase getPaymentOrderUseCase;
//
//    @MockBean
//    private GetPaymentOrderStatusUseCase getPaymentOrderStatusUseCase;
//
//    @Autowired
//    private WebTestClient webTestClient;
// // 👈 Para serialización/deserialización
//
//    private PaymentOrderRequest validRequest;
//    private com.hiberus.payment.application.dto.PaymentOrderResponse mockResponse;
//    private com.hiberus.payment.application.dto.PaymentOrderStatusResponse mockStatusResponse;
//
//    @BeforeEach
//    void setUp() {
//        // Configurar request válido con TODOS los campos requeridos
//        validRequest = new PaymentOrderRequest();
//        validRequest.setExternalReference("REF123");
//        validRequest.setDebtorAccount(new PaymentOrderRequest.Account("ES1234567890123456789012"));
//        validRequest.setCreditorAccount(new PaymentOrderRequest.Account("ES9876543210987654321098"));
//        validRequest.setInstructedAmount(new PaymentOrderRequest.Amount(100.0, "EUR"));
//        validRequest.setRemittanceInformation("Test payment");
//        validRequest.setRequestedExecutionDate(LocalDate.now().plusDays(1));
//
//        // Configurar response mock
//        mockResponse = com.hiberus.payment.application.dto.PaymentOrderResponse.builder()
//                .id("order-123")
//                .externalReference("REF123")
//                .status("PENDING")
//                .debtorAccount(com.hiberus.payment.application.dto.PaymentOrderResponse.Account.builder()
//                        .iban("ES1234567890123456789012")
//                        .build())
//                .creditorAccount(com.hiberus.payment.application.dto.PaymentOrderResponse.Account.builder()
//                        .iban("ES9876543210987654321098")
//                        .build())
//                .instructedAmount(com.hiberus.payment.application.dto.PaymentOrderResponse.Amount.builder()
//                        .amount(new BigDecimal("100.0"))
//                        .currency("EUR")
//                        .build())
//                .remittanceInformation("Test payment")
//                .requestedExecutionDate(LocalDateTime.now().plusDays(1))
//                .creationDate(LocalDateTime.now())
//                .lastUpdateDate(LocalDateTime.now())
//                .build();
//
//        // Configurar status response mock
//        mockStatusResponse = com.hiberus.payment.application.dto.PaymentOrderStatusResponse.builder()
//                .id("order-123")
//                .status("COMPLETED")
//                .lastUpdateDate(LocalDateTime.now())
//                .build();
//    }
//
//    @Test
//    void initiatePaymentOrder_WithValidRequest_ShouldReturnCreated() {
//        // Given
//        when(createPaymentOrderUseCase.execute(any()))
//                .thenReturn(Mono.just(mockResponse));
//
//        // When & Then
//        webTestClient.post()
//                .uri("/payment-initiation/payment-orders")
//                .contentType(MediaType.APPLICATION_JSON)
//                .bodyValue(validRequest)
//                .exchange()
//                .expectStatus().isCreated()
//                .expectHeader().exists("Location")
//                .expectBody()
//                .jsonPath("$.id").isEqualTo("order-123")
//                .jsonPath("$.externalReference").isEqualTo("REF123")
//                .jsonPath("$.status").isEqualTo("PENDING");
//
//        verify(createPaymentOrderUseCase).execute(any());
//    }
//
//    @Test
//    void initiatePaymentOrder_WithUseCaseError_ShouldReturnError() {
//        // Given
//        when(createPaymentOrderUseCase.execute(any()))
//                .thenReturn(Mono.error(new RuntimeException("Use case error")));
//
//        // When & Then
//        webTestClient.post()
//                .uri("/payment-initiation/payment-orders")
//                .contentType(MediaType.APPLICATION_JSON)
//                .bodyValue(validRequest)
//                .exchange()
//                .expectStatus().is5xxServerError();
//
//        verify(createPaymentOrderUseCase).execute(any());
//    }
//
//    @Test
//    void retrievePaymentOrder_WithExistingId_ShouldReturnOk() {
//        // Given
//        String orderId = "order-123";
//        when(getPaymentOrderUseCase.execute(orderId))
//                .thenReturn(Mono.just(mockResponse));
//
//        // When & Then
//        webTestClient.get()
//                .uri("/payment-initiation/payment-orders/{id}", orderId)
//                .exchange()
//                .expectStatus().isOk()
//                .expectBody()
//                .jsonPath("$.id").isEqualTo("order-123")
//                .jsonPath("$.status").isEqualTo("PENDING");
//
//        verify(getPaymentOrderUseCase).execute(orderId);
//    }
//
//    @Test
//    void retrievePaymentOrder_WithNonExistingId_ShouldReturnNotFound() {
//        // Given
//        String orderId = "non-existing";
//        when(getPaymentOrderUseCase.execute(orderId))
//                .thenReturn(Mono.empty());
//
//        // When & Then
//        webTestClient.get()
//                .uri("/payment-initiation/payment-orders/{id}", orderId)
//                .exchange()
//                .expectStatus().isNotFound();
//
//        verify(getPaymentOrderUseCase).execute(orderId);
//    }
//
//    @Test
//    void retrievePaymentOrderStatus_WithExistingId_ShouldReturnOk() {
//        // Given
//        String orderId = "order-123";
//        when(getPaymentOrderStatusUseCase.execute(orderId))
//                .thenReturn(Mono.just(mockStatusResponse));
//
//        // When & Then
//        webTestClient.get()
//                .uri("/payment-initiation/payment-orders/{id}/status", orderId)
//                .exchange()
//                .expectStatus().isOk()
//                .expectBody()
//                .jsonPath("$.id").isEqualTo("order-123")
//                .jsonPath("$.status").isEqualTo("COMPLETED");
//
//        verify(getPaymentOrderStatusUseCase).execute(orderId);
//    }
//
//    @Test
//    void retrievePaymentOrderStatus_WithNonExistingId_ShouldReturnNotFound() {
//        // Given
//        String orderId = "non-existing";
//        when(getPaymentOrderStatusUseCase.execute(orderId))
//                .thenReturn(Mono.empty());
//
//        // When & Then
//        webTestClient.get()
//                .uri("/payment-initiation/payment-orders/{id}/status", orderId)
//                .exchange()
//                .expectStatus().isNotFound();
//
//        verify(getPaymentOrderStatusUseCase).execute(orderId);
//    }
//
//    @Test
//    void initiatePaymentOrder_WithNullExternalReference_ShouldReturnBadRequest() {
//        // Given
//        PaymentOrderRequest invalidRequest = new PaymentOrderRequest();
//        invalidRequest.setDebtorAccount(new PaymentOrderRequest.Account("ES123456789"));
//        invalidRequest.setCreditorAccount(new PaymentOrderRequest.Account("ES987654321"));
//        invalidRequest.setInstructedAmount(new PaymentOrderRequest.Amount(100.0, "EUR"));
//        // externalReference es null → debería fallar la validación
//
//        // When & Then
//        webTestClient.post()
//                .uri("/payment-initiation/payment-orders")
//                .contentType(MediaType.APPLICATION_JSON)
//                .bodyValue(invalidRequest)
//                .exchange()
//                .expectStatus().isBadRequest();
//
//        verifyNoInteractions(createPaymentOrderUseCase);
//    }
//
//    @Test
//    void initiatePaymentOrder_WithNullDebtorAccount_ShouldReturnBadRequest() {
//        // Given
//        PaymentOrderRequest invalidRequest = new PaymentOrderRequest();
//        invalidRequest.setExternalReference("REF123");
//        invalidRequest.setCreditorAccount(new PaymentOrderRequest.Account("ES987654321"));
//        invalidRequest.setInstructedAmount(new PaymentOrderRequest.Amount(100.0, "EUR"));
//        // debtorAccount es null → debería fallar la validación
//
//        // When & Then
//        webTestClient.post()
//                .uri("/payment-initiation/payment-orders")
//                .contentType(MediaType.APPLICATION_JSON)
//                .bodyValue(invalidRequest)
//                .exchange()
//                .expectStatus().isBadRequest();
//
//        verifyNoInteractions(createPaymentOrderUseCase);
//    }
//
//    @Test
//    void initiatePaymentOrder_WithNegativeAmount_ShouldReturnBadRequest() {
//        // Given
//        PaymentOrderRequest invalidRequest = new PaymentOrderRequest();
//        invalidRequest.setExternalReference("REF123");
//        invalidRequest.setDebtorAccount(new PaymentOrderRequest.Account("ES123456789"));
//        invalidRequest.setCreditorAccount(new PaymentOrderRequest.Account("ES987654321"));
//        invalidRequest.setInstructedAmount(new PaymentOrderRequest.Amount(-100.0, "EUR")); // 👈 Amount negativo
//
//        // When & Then
//        webTestClient.post()
//                .uri("/payment-initiation/payment-orders")
//                .contentType(MediaType.APPLICATION_JSON)
//                .bodyValue(invalidRequest)
//                .exchange()
//                .expectStatus().isBadRequest();
//
//        verifyNoInteractions(createPaymentOrderUseCase);
//    }
//
//    @Test
//    void initiatePaymentOrder_WithEmptyIban_ShouldReturnBadRequest() {
//        // Given
//        PaymentOrderRequest invalidRequest = new PaymentOrderRequest();
//        invalidRequest.setExternalReference("REF123");
//        invalidRequest.setDebtorAccount(new PaymentOrderRequest.Account("")); // 👈 IBAN vacío
//        invalidRequest.setCreditorAccount(new PaymentOrderRequest.Account("ES987654321"));
//        invalidRequest.setInstructedAmount(new PaymentOrderRequest.Amount(100.0, "EUR"));
//
//        // When & Then
//        webTestClient.post()
//                .uri("/payment-initiation/payment-orders")
//                .contentType(MediaType.APPLICATION_JSON)
//                .bodyValue(invalidRequest)
//                .exchange()
//                .expectStatus().isBadRequest();
//
//        verifyNoInteractions(createPaymentOrderUseCase);
//    }
//}