package com.hiberus.payment.domain.service;

import com.hiberus.payment.domain.model.PaymentOrder;
import com.hiberus.payment.domain.model.PaymentOrderStatus;
import reactor.core.publisher.Mono;


public interface PaymentOrderService {

    Mono<PaymentOrder> createPaymentOrder(PaymentOrder paymentOrder);

    Mono<PaymentOrder> getPaymentOrderById(String id);

    Mono<PaymentOrderStatus> getPaymentOrderStatus(String id);

}