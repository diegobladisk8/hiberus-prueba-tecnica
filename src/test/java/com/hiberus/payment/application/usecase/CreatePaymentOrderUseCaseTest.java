package com.hiberus.payment.application.usecase;


import com.hiberus.payment.application.dto.PaymentOrderRequest;
import com.hiberus.payment.application.dto.PaymentOrderResponse;
import com.hiberus.payment.application.mapper.PaymentOrderMapper;
import com.hiberus.payment.domain.model.PaymentOrder;
import com.hiberus.payment.domain.service.PaymentOrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
class CreatePaymentOrderUseCaseTest {

    @Mock
    private PaymentOrderService paymentOrderService;

    @Mock
    private PaymentOrderMapper paymentOrderMapper;

    private CreatePaymentOrderUseCase createPaymentOrderUseCase;

    @BeforeEach
    void setup() {
        createPaymentOrderUseCase =
                new CreatePaymentOrderUseCaseImpl(paymentOrderService, paymentOrderMapper);
    }

    @Test
    void shouldCreatePaymentOrderSuccessfully() {
        // Given
        PaymentOrderRequest request = PaymentOrderRequest.builder()
                .externalReference("ABC-123")
                .build();

        PaymentOrder domain = PaymentOrder.builder()
                .id("ID-TEST")
                .externalReference("ABC-123")
                .build();

        PaymentOrderResponse expectedResponse = PaymentOrderResponse.builder()
                .id("ID-TEST")
                .externalReference("ABC-123")
                .build();

        Mockito.when(paymentOrderMapper.toDomain(request)).thenReturn(domain);
        Mockito.when(paymentOrderService.createPaymentOrder(domain)).thenReturn(Mono.just(domain));
        Mockito.when(paymentOrderMapper.toResponse(domain)).thenReturn(expectedResponse);

        // When & Then
        StepVerifier.create(createPaymentOrderUseCase.execute(request))
                .expectNextMatches(r ->
                        r.getId().equals("ID-TEST")
                                && r.getExternalReference().equals("ABC-123")
                )
                .verifyComplete();

        Mockito.verify(paymentOrderMapper).toDomain(request);
        Mockito.verify(paymentOrderService).createPaymentOrder(domain);
        Mockito.verify(paymentOrderMapper).toResponse(domain);
    }
}
