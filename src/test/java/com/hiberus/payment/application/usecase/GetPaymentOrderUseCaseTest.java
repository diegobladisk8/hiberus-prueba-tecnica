package com.hiberus.payment.application.usecase;


import com.hiberus.payment.application.dto.PaymentOrderResponse;
import com.hiberus.payment.application.dto.PaymentOrderStatusResponse;
import com.hiberus.payment.application.mapper.PaymentOrderMapper;
import com.hiberus.payment.domain.model.PaymentOrder;
import com.hiberus.payment.domain.model.PaymentOrderStatus;
import com.hiberus.payment.domain.model.PaymentOrderStatusEnum;
import com.hiberus.payment.domain.service.PaymentOrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;
class GetPaymentOrderUseCaseTest {

    @Mock
    private PaymentOrderService paymentOrderService;

    @Mock
    private PaymentOrderMapper paymentOrderMapper;

    @InjectMocks
    private GetPaymentOrderUseCase useCase;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    // -----------------------------------------------------
    // Caso 1: Encontrado
    // -----------------------------------------------------
    @Test
    void testExecute_WhenFound_ReturnsMappedResponse() {

        // Domain entity simulada
        PaymentOrder domain = PaymentOrder.builder()
                .id("PO-123")
                .externalReference("EXT-999")
                .creationDate(LocalDateTime.now())
                .lastUpdateDate(LocalDateTime.now())
                .build();

        // DTO respuesta simulada
        PaymentOrderResponse mappedResponse = PaymentOrderResponse.builder()
                .id("PO-123")
                .externalReference("EXT-999")
                .creationDate(domain.getCreationDate())
                .lastUpdateDate(domain.getLastUpdateDate())
                .build();

        when(paymentOrderService.getPaymentOrderById("PO-123"))
                .thenReturn(Mono.just(domain));

        when(paymentOrderMapper.toResponse(domain))
                .thenReturn(mappedResponse);

        StepVerifier.create(useCase.execute("PO-123"))
                .expectNext(mappedResponse)
                .verifyComplete();

        verify(paymentOrderService, times(1)).getPaymentOrderById("PO-123");
        verify(paymentOrderMapper, times(1)).toResponse(domain);
    }

    // -----------------------------------------------------
    // Caso 2: No encontrado
    // -----------------------------------------------------
    @Test
    void testExecute_WhenNotFound_ReturnsEmptyMono() {

        when(paymentOrderService.getPaymentOrderById(anyString()))
                .thenReturn(Mono.empty());

        StepVerifier.create(useCase.execute("NOT_FOUND"))
                .verifyComplete();

        verify(paymentOrderService, times(1)).getPaymentOrderById("NOT_FOUND");
        verify(paymentOrderMapper, never()).toResponse(any());
    }
}