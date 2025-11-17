package com.hiberus.payment.application.ports.output;

import com.hiberus.payment.domain.model.PaymentOrder;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * Output port for payment order persistence.
 * This interface defines the operations for persisting payment orders.
 * Part of the hexagonal architecture ports.
 */
public interface PaymentOrderRepository {
    
    /**
     * Saves a payment order.
     *
     * @param paymentOrder the payment order to save
     * @return Mono containing the saved payment order
     */
    Mono<PaymentOrder> save(PaymentOrder paymentOrder);
    
    /**
     * Finds a payment order by its ID.
     *
     * @param id the payment order ID
     * @return Mono containing the payment order if found, empty otherwise
     */
    Mono<PaymentOrder> findById(UUID id);
}
