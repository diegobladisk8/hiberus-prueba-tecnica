package com.hiberus.payment.application.ports.input;

import com.hiberus.payment.domain.model.PaymentOrder;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Input port for payment order use cases.
 * This interface defines the operations that can be performed on payment orders.
 * Part of the hexagonal architecture ports.
 */
public interface PaymentOrderUseCase {
    
    /**
     * Creates a new payment order.
     *
     * @param paymentOrder the payment order to create
     * @return Mono containing the created payment order
     */
    Mono<PaymentOrder> createPaymentOrder(PaymentOrder paymentOrder);
    
    /**
     * Retrieves a payment order by its ID.
     *
     * @param id the payment order ID
     * @return Mono containing the payment order if found
     */
    Mono<PaymentOrder> getPaymentOrderById(UUID id);
    
    /**
     * Retrieves the status of a payment order.
     *
     * @param id the payment order ID
     * @return Mono containing the payment order if found
     */
    Mono<PaymentOrder> getPaymentOrderStatus(UUID id);
}
