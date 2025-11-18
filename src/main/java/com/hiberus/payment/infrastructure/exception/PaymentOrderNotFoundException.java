package com.hiberus.payment.infrastructure.exception;

public class PaymentOrderNotFoundException extends RuntimeException {

    public PaymentOrderNotFoundException(String message) {
        super(message);
    }
}
