package com.hiberus.payment.domain.service;


import com.hiberus.payment.infrastructure.model.PaymentOrderEntity;
import com.hiberus.payment.infrastructure.model.PaymentOrderStatusEntity;
import com.hiberus.payment.infrastructure.model.PaymentOrderStatusEnum;
import com.hiberus.payment.infrastructure.repository.PaymentOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentOrderServiceImpl implements PaymentOrderService {

    private final PaymentOrderRepository paymentOrderRepository;

    @Override
    public Mono<PaymentOrderEntity> createPaymentOrder(PaymentOrderEntity paymentOrder) {
        PaymentOrderEntity newPaymentOrder = paymentOrder.toBuilder()
                .id(generatePaymentOrderId())
                .creationDate(LocalDateTime.now())
                .lastUpdateDate(LocalDateTime.now())
                .status(PaymentOrderStatusEnum.PENDING)
                .build();

        return paymentOrderRepository.save(newPaymentOrder);
    }


    @Override
    public Mono<PaymentOrderEntity> getPaymentOrderById(String id) {
        return paymentOrderRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Payment order not found")));
    }

    @Override
    public Mono<PaymentOrderStatusEntity> getPaymentOrderStatus(String id) {
        return paymentOrderRepository.findById(id)
                .map(paymentOrder -> PaymentOrderStatusEntity.builder()
                        .id(id)
                        .paymentOrderId(id)
                        .status(paymentOrder.getStatus())
                        .lastUpdateDate(paymentOrder.getLastUpdateDate())
                        .build())
                .switchIfEmpty(Mono.error(new RuntimeException("Payment order not found")));
    }


    private String generatePaymentOrderId() {
        return "PO-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}