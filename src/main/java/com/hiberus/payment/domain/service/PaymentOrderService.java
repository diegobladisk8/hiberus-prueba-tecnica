package com.hiberus.payment.domain.service;

import com.hiberus.payment.infrastructure.model.PaymentOrderEntity;
import com.hiberus.payment.infrastructure.model.PaymentOrderStatusEntity;
import reactor.core.publisher.Mono;


public interface PaymentOrderService {

    Mono<PaymentOrderEntity> createPaymentOrder(PaymentOrderEntity paymentOrder);

    Mono<PaymentOrderEntity> getPaymentOrderById(String id);

    Mono<PaymentOrderStatusEntity> getPaymentOrderStatus(String id);

}