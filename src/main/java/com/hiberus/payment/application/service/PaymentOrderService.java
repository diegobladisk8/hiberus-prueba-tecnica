package com.hiberus.payment.application.service;

import com.hiberus.payment.application.ports.input.PaymentOrderUseCase;
import com.hiberus.payment.application.ports.output.PaymentOrderRepository;
import com.hiberus.payment.domain.exception.PaymentOrderNotFoundException;
import com.hiberus.payment.domain.model.PaymentOrder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Application service implementing payment order use cases.
 * This is the application layer in hexagonal architecture.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class PaymentOrderService implements PaymentOrderUseCase {
    
    private final PaymentOrderRepository paymentOrderRepository;
    
    @Override
    public Mono<PaymentOrder> createPaymentOrder(PaymentOrder paymentOrder) {
        log.info("Creating payment order for debtor account: {}", paymentOrder.getDebtorAccount());
        return paymentOrderRepository.save(paymentOrder)
                .doOnSuccess(saved -> log.info("Payment order created with id: {}", saved.getId()))
                .doOnError(error -> log.error("Error creating payment order", error));
    }
    
    @Override
    public Mono<PaymentOrder> getPaymentOrderById(UUID id) {
        log.info("Retrieving payment order with id: {}", id);
        return paymentOrderRepository.findById(id)
                .switchIfEmpty(Mono.error(new PaymentOrderNotFoundException(id)))
                .doOnSuccess(order -> log.info("Payment order retrieved: {}", id))
                .doOnError(error -> log.error("Error retrieving payment order with id: {}", id, error));
    }
    
    @Override
    public Mono<PaymentOrder> getPaymentOrderStatus(UUID id) {
        log.info("Retrieving payment order status for id: {}", id);
        return getPaymentOrderById(id)
                .doOnSuccess(order -> log.info("Payment order status retrieved: {} - {}", id, order.getStatus()));
    }
}
