package com.hiberus.payment.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

/**
 * Domain entity representing a payment order.
 * This is part of the domain layer in hexagonal architecture.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentOrder {
    
    private UUID id;
    private String debtorAccount;
    private String creditorAccount;
    private String creditorName;
    private BigDecimal amount;
    private String currency;
    private String description;
    private PaymentStatus status;
    private Instant createdAt;
    private Instant updatedAt;
    
    /**
     * Creates a new payment order with initial status.
     */
    public static PaymentOrder create(
            String debtorAccount,
            String creditorAccount,
            String creditorName,
            BigDecimal amount,
            String currency,
            String description) {
        
        Instant now = Instant.now();
        return PaymentOrder.builder()
                .id(UUID.randomUUID())
                .debtorAccount(debtorAccount)
                .creditorAccount(creditorAccount)
                .creditorName(creditorName)
                .amount(amount)
                .currency(currency)
                .description(description)
                .status(PaymentStatus.PENDING)
                .createdAt(now)
                .updatedAt(now)
                .build();
    }
    
    /**
     * Updates the status of the payment order.
     */
    public void updateStatus(PaymentStatus newStatus) {
        this.status = newStatus;
        this.updatedAt = Instant.now();
    }
}
