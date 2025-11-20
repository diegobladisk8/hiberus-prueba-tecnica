package com.hiberus.payment.infrastructure.adapter.controller;

import com.hiberus.payment.application.port.out.CreatePaymentOrderUseCase;
import com.hiberus.payment.application.usecase.GetPaymentOrderStatusUseCase;
import com.hiberus.payment.application.usecase.GetPaymentOrderUseCase;
import com.hiberus.payment.application.dto.PaymentOrderRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
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

        // Crear response
        PaymentOrderResponse response = PaymentOrderResponse.builder()
                .id("PO123")
                .externalReference("EXT-123")
                .status("PENDING")
                .debtorAccount(new Account("DEBTOR-IBAN"))
                .creditorAccount(new Account("CRED-IBAN"))
                .instructedAmount(new Amount(50D, "USD"))
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
                .assertNext(res -> {
                    assertNotNull(res);             // Evita el posible null
                    assertNotNull(res.getBody());   // SpotBugs deja de alertar

                    assertTrue(res.getStatusCode().is2xxSuccessful());
                    assertEquals("PO123", res.getBody().getId());
                    assertEquals(PaymentOrder.StatusEnum.PENDING, res.getBody().getStatus());
                })
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
                .debtorAccount(new Account("DEBTOR-IBAN"))
                .creditorAccount(new Account("CRED-IBAN"))
                .instructedAmount(new Amount(50D, "USD"))
                .requestedExecutionDate(LocalDate.now().atStartOfDay())
                .creationDate(LocalDateTime.now())
                .lastUpdateDate(LocalDateTime.now())
                .build();

        when(getPaymentOrderUseCase.execute("PO999"))
                .thenReturn(Mono.just(response));

        StepVerifier.create(controller.retrievePaymentOrder("PO999", exchange))
                .assertNext(res -> {
                    assertNotNull(res);              // SpotBugs queda tranquilo
                    assertNotNull(res.getBody());    // <--- esto evita el warning
                    assertTrue(res.getStatusCode().is2xxSuccessful());
                    assertEquals("PO999", res.getBody().getId());
                })
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
                .assertNext(res -> {
                    assertThat(res.getStatusCode().is2xxSuccessful()).isTrue();
                    assertThat(res.getBody()).isNotNull();
                    assertThat(Objects.requireNonNull(res.getBody()).getId()).isEqualTo("STATUS123");
                })
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