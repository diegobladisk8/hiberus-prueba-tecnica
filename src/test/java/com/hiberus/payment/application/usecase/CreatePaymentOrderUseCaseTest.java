package com.hiberus.payment.application.usecase;


import com.hiberus.payment.application.dto.PaymentOrderRequest;
import com.hiberus.payment.application.dto.PaymentOrderResponse;
import com.hiberus.payment.application.mapper.PaymentOrderMapper;
import com.hiberus.payment.domain.model.PaymentOrder;
import com.hiberus.payment.domain.model.PaymentOrderStatusEnum;
import com.hiberus.payment.domain.service.PaymentOrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreatePaymentOrderUseCaseTest {

    @Mock
    private PaymentOrderService paymentOrderService;

    @Mock
    private PaymentOrderMapper paymentOrderMapper;

    @InjectMocks
    private CreatePaymentOrderUseCase createPaymentOrderUseCase;

    private PaymentOrderRequest paymentOrderRequest;
    private PaymentOrder paymentOrder;
    private PaymentOrderResponse paymentOrderResponse;

    @BeforeEach
    void setUp() {
        paymentOrderRequest = PaymentOrderRequest.builder()
                .externalReference("EXT-1")
                .debtorAccount(PaymentOrderRequest.Account.builder()
                        .iban("EC12DEBTOR")
                        .build())
                .creditorAccount(PaymentOrderRequest.Account.builder()
                        .iban("EC98CREDITOR")
                        .build())
                .instructedAmount(PaymentOrderRequest.Amount.builder()
                        .amount(150.75)
                        .currency("USD")
                        .build())
                .remittanceInformation("Factura 001-123")
                .requestedExecutionDate(LocalDate.of(2025, 10, 31))
                .build();

        paymentOrder = PaymentOrder.builder()
                .id("PO-12345678")
                .externalReference("EXT-1")
                .status(PaymentOrderStatusEnum.PENDING)
                .debtorAccount("EC12DEBTOR")
                .creditorAccount("EC98CREDITOR")
                .amount(new BigDecimal("150.75"))
                .currency("USD")
                .remittanceInformation("Factura 001-123")
                .requestedExecutionDate(LocalDate.of(2025, 10, 31).atStartOfDay())
                .creationDate(LocalDateTime.now())
                .lastUpdateDate(LocalDateTime.now())
                .build();

        paymentOrderResponse = PaymentOrderResponse.builder()
                .id("PO-12345678")
                .externalReference("EXT-1")
                .status("PENDING")
                .debtorAccount(PaymentOrderResponse.Account.builder()
                        .iban("EC12DEBTOR")
                        .build())
                .creditorAccount(PaymentOrderResponse.Account.builder()
                        .iban("EC98CREDITOR")
                        .build())
                .instructedAmount(PaymentOrderResponse.Amount.builder()
                        .amount(new BigDecimal("150.75"))
                        .currency("USD")
                        .build())
                .remittanceInformation("Factura 001-123")
                .requestedExecutionDate(LocalDate.of(2025, 10, 31).atStartOfDay())
                .creationDate(LocalDateTime.now())
                .lastUpdateDate(LocalDateTime.now())
                .build();
    }

    @Test
    @DisplayName("Should create a payment order successfully")
    void execute() {
        // Given
        when(paymentOrderMapper.toDomain(any(PaymentOrderRequest.class))).thenReturn(paymentOrder);
        when(paymentOrderService.createPaymentOrder(any(PaymentOrder.class))).thenReturn(Mono.just(paymentOrder));
        when(paymentOrderMapper.toResponse(any(PaymentOrder.class))).thenReturn(paymentOrderResponse);

        // When
        Mono<PaymentOrderResponse> result = createPaymentOrderUseCase.execute(paymentOrderRequest);

        // Then
        StepVerifier.create(result)
                .expectNextMatches(response ->
                        response.getId().equals("PO-12345678") &&
                                response.getExternalReference().equals("EXT-1") &&
                                response.getStatus().equals("PENDING"))
                .verifyComplete();
    }
}
