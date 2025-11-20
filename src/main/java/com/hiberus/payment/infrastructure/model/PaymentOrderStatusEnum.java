package com.hiberus.payment.infrastructure.model;

/**
 * Enum representing the status of a payment order.
 * Part of the domain layer.
 */
public enum PaymentOrderStatusEnum {
    PENDING,
    PROCESSING,
    COMPLETED,
    FAILED,
    CANCELLED
}
