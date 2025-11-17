package com.hiberus.payment.domain.model;

/**
 * Enum representing the status of a payment order.
 * Part of the domain layer.
 */
public enum PaymentStatus {
    PENDING,
    PROCESSING,
    COMPLETED,
    FAILED,
    CANCELLED
}
