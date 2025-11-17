package com.hiberus.payment.domain.exception;

import java.util.UUID;

/**
 * Exception thrown when a payment order is not found.
 */
public class PaymentOrderNotFoundException extends RuntimeException {
    
    public PaymentOrderNotFoundException(UUID id) {
        super("Payment order not found with id: " + id);
    }
}
