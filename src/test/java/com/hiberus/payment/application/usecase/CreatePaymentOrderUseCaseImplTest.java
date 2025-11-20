package com.hiberus.payment.application.usecase;

import com.hiberus.payment.application.dto.PaymentOrderRequest;
import com.hiberus.payment.application.dto.PaymentOrderResponse;
import com.hiberus.payment.application.mapper.PaymentOrderMapper;
import com.hiberus.payment.generated.api.model.Amount;
import com.hiberus.payment.infrastructure.model.PaymentOrderEntity;
import com.hiberus.payment.domain.service.PaymentOrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

class CreatePaymentOrderUseCaseImplTest {

    @Mock
    private PaymentOrderService paymentOrderService;

    @Mock
    private PaymentOrderMapper paymentOrderMapper;

    @InjectMocks
    private CreatePaymentOrderUseCaseImpl useCase; // <-- Clase concreta

    @BeforeEach
    void init() {
        MockitoAnnotations.openMocks(this);
    }

    // -------------------------------------------------------------
    // Caso 1: Creación exitosa
    // -------------------------------------------------------------
    @Test
    void testExecute_WhenSuccess_ReturnsPaymentOrderResponse() {

        // Request de entrada
        PaymentOrderRequest request = PaymentOrderRequest.builder()
                .externalReference("EXT-100")
                .remittanceInformation("Test remittance")
                .instructedAmount(new Amount(BigDecimal.valueOf(99).doubleValue(),
                        "USD"))
                .build();

        // Objeto dominio mapeado desde el request
        PaymentOrderEntity domain = PaymentOrderEntity.builder()
                .id("P-001")
                .externalReference("EXT-100")
                .creationDate(LocalDateTime.now())
                .lastUpdateDate(LocalDateTime.now())
                .build();

        // Respuesta después de la creación
        PaymentOrderResponse expectedResponse = PaymentOrderResponse.builder()
                .id("P-001")
                .externalReference("EXT-100")
                .status("CREATED")
                .creationDate(domain.getCreationDate())
                .lastUpdateDate(domain.getLastUpdateDate())
                .build();

        // Mock: mapper.toDomain()
        when(paymentOrderMapper.toDomain(request))
                .thenReturn(domain);

        // Mock: service.createPaymentOrder()
        when(paymentOrderService.createPaymentOrder(domain))
                .thenReturn(Mono.just(domain));

        // Mock: mapper.toResponse()
        when(paymentOrderMapper.toResponse(domain))
                .thenReturn(expectedResponse);

        // Ejecutar
        StepVerifier.create(useCase.execute(request))
                .expectNext(expectedResponse)
                .verifyComplete();

        // Verify
        verify(paymentOrderMapper, times(1)).toDomain(request);
        verify(paymentOrderService, times(1)).createPaymentOrder(domain);
        verify(paymentOrderMapper, times(1)).toResponse(domain);
    }

    // -------------------------------------------------------------
    // Caso 2: Service retorna Mono.empty() → resultado vacío
    // -------------------------------------------------------------
    @Test
    void testExecute_WhenServiceReturnsEmpty_ReturnsEmptyMono() {

        PaymentOrderRequest request = PaymentOrderRequest.builder()
                .externalReference("EXT-500")
                .build();

        PaymentOrderEntity domain = PaymentOrderEntity.builder()
                .id("TEMP")
                .externalReference("EXT-500")
                .build();

        when(paymentOrderMapper.toDomain(request))
                .thenReturn(domain);

        when(paymentOrderService.createPaymentOrder(domain))
                .thenReturn(Mono.empty());

        StepVerifier.create(useCase.execute(request))
                .verifyComplete();

        verify(paymentOrderMapper, times(1)).toDomain(request);
        verify(paymentOrderService, times(1)).createPaymentOrder(domain);
        verify(paymentOrderMapper, never()).toResponse(any());
    }
}
