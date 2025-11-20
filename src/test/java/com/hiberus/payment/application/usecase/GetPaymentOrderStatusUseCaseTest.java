package com.hiberus.payment.application.usecase;


import com.hiberus.payment.application.dto.PaymentOrderStatusResponse;
import com.hiberus.payment.application.mapper.PaymentOrderMapper;
import com.hiberus.payment.infrastructure.model.PaymentOrderStatusEntity;
import com.hiberus.payment.domain.service.PaymentOrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class GetPaymentOrderStatusUseCaseTest {

    @Mock
    private PaymentOrderService paymentOrderService;

    @Mock
    private PaymentOrderMapper paymentOrderMapper;

    @InjectMocks
    private GetPaymentOrderStatusUseCase useCase;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    // ----------------------------------------------------
    // Caso 1: Found → devuelve PaymentOrderStatusResponse
    // ----------------------------------------------------
    @Test
    void testExecute_WhenFound_ReturnsMappedStatusResponse() {

        // Dominio simulado
        PaymentOrderStatusEntity domainStatus = PaymentOrderStatusEntity.builder()
                .id("PO-999")
                .lastUpdateDate(LocalDateTime.now())
                .build();

        // DTO simulado
        PaymentOrderStatusResponse expectedResponse = PaymentOrderStatusResponse.builder()
                .id("PO-999")
                .lastUpdateDate(domainStatus.getLastUpdateDate())
                .build();

        when(paymentOrderService.getPaymentOrderStatus("PO-999"))
                .thenReturn(Mono.just(domainStatus));

        when(paymentOrderMapper.toStatusResponse(domainStatus))
                .thenReturn(expectedResponse);

        StepVerifier.create(useCase.execute("PO-999"))
                .expectNext(expectedResponse)
                .verifyComplete();

        verify(paymentOrderService, times(1)).getPaymentOrderStatus("PO-999");
        verify(paymentOrderMapper, times(1)).toStatusResponse(domainStatus);
    }

    // ----------------------------------------------------
    // Caso 2: Not Found → Mono.empty()
    // ----------------------------------------------------
    @Test
    void testExecute_WhenNotFound_ReturnsEmptyMono() {

        when(paymentOrderService.getPaymentOrderStatus(anyString()))
                .thenReturn(Mono.empty());

        StepVerifier.create(useCase.execute("NOT_FOUND"))
                .verifyComplete();

        verify(paymentOrderService, times(1)).getPaymentOrderStatus("NOT_FOUND");
        verify(paymentOrderMapper, never()).toStatusResponse(any());
    }
}

