package com.hiberus.payment.domain.service;


import com.hiberus.payment.domain.model.PaymentOrder;
import com.hiberus.payment.domain.model.PaymentOrderStatus;
import com.hiberus.payment.domain.model.PaymentOrderStatusEnum;
import com.hiberus.payment.domain.repository.PaymentOrderRepository;
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
    public Mono<PaymentOrder> createPaymentOrder(PaymentOrder paymentOrder) {
        PaymentOrder newPaymentOrder = paymentOrder.toBuilder()
                .id(generatePaymentOrderId())
                .creationDate(LocalDateTime.now())
                .lastUpdateDate(LocalDateTime.now())
                .status(PaymentOrderStatusEnum.PENDING)
                .build();

        return paymentOrderRepository.save(newPaymentOrder);
    }

    @Override
    public Mono<PaymentOrder> getPaymentOrderById(String id) {
        return paymentOrderRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Payment order not found")));
    }

    @Override
    public Mono<PaymentOrderStatus> getPaymentOrderStatus(String id) {
        return paymentOrderRepository.findById(id)
                .map(paymentOrder -> PaymentOrderStatus.builder()
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