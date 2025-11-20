package com.hiberus.payment.infrastructure.adapter.controller;

import com.hiberus.payment.application.usecase.CreatePaymentOrderUseCase;
import com.hiberus.payment.application.usecase.GetPaymentOrderStatusUseCase;
import com.hiberus.payment.application.usecase.GetPaymentOrderUseCase;
import com.hiberus.payment.application.dto.PaymentOrderRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import com.hiberus.payment.application.dto.PaymentOrderResponse;
import com.hiberus.payment.application.dto.PaymentOrderStatusResponse;
import com.hiberus.payment.generated.api.model.*;
import org.mockito.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ServerWebExchange;
import reactor.test.StepVerifier;


class PaymentOrderControllerTest {

    @Mock
    private CreatePaymentOrderUseCase createPaymentOrderUseCase;

    @Mock
    private GetPaymentOrderUseCase getPaymentOrderUseCase;

    @Mock
    private GetPaymentOrderStatusUseCase getPaymentOrderStatusUseCase;

    @Mock
    private ServerWebExchange exchange;

    @InjectMocks
    private PaymentOrderController controller;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    // -----------------------------------------------------------
    // 1) Test initiatePaymentOrder()
    // -----------------------------------------------------------
    @Test
    void testInitiatePaymentOrderSuccessfully() {
        // Crear request con todos los campos obligatorios
        com.hiberus.payment.generated.api.model.PaymentOrderRequest req =
                new com.hiberus.payment.generated.api.model.PaymentOrderRequest();

        req.setExternalReference("EXT-123");
        req.setRemittanceInformation("Test payment");
        req.setRequestedExecutionDate(LocalDate.now());

        // Campos obligatorios
        req.setDebtorAccount(new com.hiberus.payment.generated.api.model.Account().iban("DEBTOR-IBAN"));
        req.setCreditorAccount(new com.hiberus.payment.generated.api.model.Account().iban("CRED-IBAN"));
        req.setInstructedAmount(new com.hiberus.payment.generated.api.model.Amount()
                .amount(50D)
                .currency("USD"));

        // Crear response - Asegurarse de usar las clases correctas
        PaymentOrderResponse response = PaymentOrderResponse.builder()
                .id("PO123")
                .externalReference("EXT-123")
                .status("PENDING")
                .debtorAccount(PaymentOrderResponse.Account.builder().iban("DEBTOR-IBAN").build())
                .creditorAccount(PaymentOrderResponse.Account.builder().iban("CRED-IBAN").build())
                .instructedAmount(PaymentOrderResponse.Amount.builder()
                        .amount(new BigDecimal("50.0"))
                        .currency("USD")
                        .build())
                .remittanceInformation("Test payment")
                .requestedExecutionDate(LocalDate.now().atStartOfDay())
                .creationDate(LocalDateTime.now())
                .lastUpdateDate(LocalDateTime.now())
                .build();

        // Mock del use case
        when(createPaymentOrderUseCase.execute(any(PaymentOrderRequest.class)))
                .thenReturn(Mono.just(response));

        // Ejecutar y verificar
        Mono<ResponseEntity<PaymentOrder>> result =
                controller.initiatePaymentOrder(Mono.just(req), exchange);

        StepVerifier.create(result)
                .expectNextMatches(res ->
                        res.getStatusCode().is2xxSuccessful()
                                && res.getBody() != null
                                && "PO123".equals(res.getBody().getId())
                                && PaymentOrder.StatusEnum.PENDING.equals(res.getBody().getStatus()))
                .verifyComplete();

        // Verificar que el use case fue llamado exactamente una vez
        verify(createPaymentOrderUseCase, times(1)).execute(any(PaymentOrderRequest.class));
    }

    // -----------------------------------------------------------
    // 2) retrievePaymentOrder()
    // -----------------------------------------------------------
    @Test
    void testRetrievePaymentOrderFound() {

        PaymentOrderResponse response = PaymentOrderResponse.builder()
                .id("PO999")
                .externalReference("EXT-999")
                .status("COMPLETED")
                .debtorAccount(PaymentOrderResponse.Account.builder().iban("AAA").build())
                .creditorAccount(PaymentOrderResponse.Account.builder().iban("BBB").build())
                .instructedAmount(PaymentOrderResponse.Amount.builder()
                        .amount(BigDecimal.TEN)
                        .currency("USD")
                        .build())
                .requestedExecutionDate(LocalDate.now().atStartOfDay())
                .creationDate(LocalDateTime.now())
                .lastUpdateDate(LocalDateTime.now())
                .build();

        when(getPaymentOrderUseCase.execute("PO999"))
                .thenReturn(Mono.just(response));

        StepVerifier.create(controller.retrievePaymentOrder("PO999", exchange))
                .expectNextMatches(res ->
                        res.getStatusCode().is2xxSuccessful()
                                && res.getBody().getId().equals("PO999"))
                .verifyComplete();

        verify(getPaymentOrderUseCase, times(1)).execute("PO999");
    }

    @Test
    void testRetrievePaymentOrderNotFound() {

        when(getPaymentOrderUseCase.execute("NOT_FOUND"))
                .thenReturn(Mono.empty());

        StepVerifier.create(controller.retrievePaymentOrder("NOT_FOUND", exchange))
                .expectNextMatches(res -> res.getStatusCode().is4xxClientError())
                .verifyComplete();
    }

    // -----------------------------------------------------------
    // 3) retrievePaymentOrderStatus()
    // -----------------------------------------------------------
    @Test
    void testRetrievePaymentOrderStatusFound() {

        PaymentOrderStatusResponse status = PaymentOrderStatusResponse.builder()
                .id("STATUS123")
                .status("PENDING")
                .lastUpdateDate(LocalDateTime.now())
                .build();

        when(getPaymentOrderStatusUseCase.execute("STATUS123"))
                .thenReturn(Mono.just(status));

        StepVerifier.create(controller.retrievePaymentOrderStatus("STATUS123", exchange))
                .expectNextMatches(res ->
                        res.getStatusCode().is2xxSuccessful()
                                && "STATUS123".equals(res.getBody().getId()))
                .verifyComplete();
    }

    @Test
    void testRetrievePaymentOrderStatusNotFound() {

        when(getPaymentOrderStatusUseCase.execute("UNKNOWN"))
                .thenReturn(Mono.empty());

        StepVerifier.create(controller.retrievePaymentOrderStatus("UNKNOWN", exchange))
                .expectNextMatches(res -> res.getStatusCode().is4xxClientError())
                .verifyComplete();
    }
}