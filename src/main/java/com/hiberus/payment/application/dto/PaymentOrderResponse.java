package com.hiberus.payment.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentOrderResponse {

    private String id;
    private String externalReference;
    private String status;
    private Account debtorAccount;
    private Account creditorAccount;
    private Amount instructedAmount;
    private String remittanceInformation;
    private LocalDateTime requestedExecutionDate;
    private LocalDateTime creationDate;
    private LocalDateTime lastUpdateDate;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Account {
        private String iban;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Amount {
        private BigDecimal amount;
        private String currency;
    }
}