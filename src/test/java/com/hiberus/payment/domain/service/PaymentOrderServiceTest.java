package com.hiberus.payment.domain.service;

import com.hiberus.payment.infrastructure.model.PaymentOrderEntity;
import com.hiberus.payment.infrastructure.model.PaymentOrderStatusEntity;
import com.hiberus.payment.infrastructure.model.PaymentOrderStatusEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

class PaymentOrderServiceTest {

    private PaymentOrderService service;

    @BeforeEach
    void setUp() {
        service = Mockito.mock(PaymentOrderService.class);
    }

    @Test
    void createPaymentOrder_shouldReturnCreatedOrder() {
        PaymentOrderEntity order = PaymentOrderEntity.builder()
                .id("123")
                .build();

        when(service.createPaymentOrder(order)).thenReturn(Mono.just(order));

        StepVerifier.create(service.createPaymentOrder(order))
                .assertNext(result ->
                        assertThat(result.getId()).isEqualTo("123"))
                .verifyComplete();
    }

    @Test
    void getPaymentOrderById_shouldReturnOrder() {
        PaymentOrderEntity order = PaymentOrderEntity.builder()
                .id("ABC")
                .build();

        when(service.getPaymentOrderById("ABC")).thenReturn(Mono.just(order));

        StepVerifier.create(service.getPaymentOrderById("ABC"))
                .assertNext(result ->
                        assertThat(result.getId()).isEqualTo("ABC"))
                .verifyComplete();
    }

    @Test
    void getPaymentOrderStatus_shouldReturnStatus() {
        PaymentOrderStatusEntity status = new PaymentOrderStatusEntity();

        status.setPaymentOrderId("XYZ");
        status.setStatus(PaymentOrderStatusEnum.PENDING);
                status.setLastUpdateDate(java.time.LocalDateTime.now());
        when(service.getPaymentOrderStatus("XYZ")).thenReturn(Mono.just(status));

        StepVerifier.create(service.getPaymentOrderStatus("XYZ"))
                .assertNext(result ->
                        assertThat(result.getStatus()).isEqualTo(PaymentOrderStatusEnum.PENDING))
                .verifyComplete();
    }
}
