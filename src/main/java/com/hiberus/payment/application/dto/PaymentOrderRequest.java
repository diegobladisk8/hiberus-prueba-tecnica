package com.hiberus.payment.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentOrderRequest {

    @NotBlank(message = "External reference is required")
    private String externalReference;

    @Valid
    @NotNull(message = "Debtor account is required")
    private Account debtorAccount;

    @Valid
    @NotNull(message = "Creditor account is required")
    private Account creditorAccount;

    @Valid
    @NotNull(message = "Instructed amount is required")
    private Amount instructedAmount;

    private String remittanceInformation;

    private LocalDate requestedExecutionDate;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Account {

        @NotBlank(message = "IBAN is required")
        private String iban;
    }

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Amount {

        @NotNull(message = "Amount is required")
        @Positive(message = "Amount must be positive")
        private Double amount;

        @NotBlank(message = "Currency is required")
        private String currency;
    }
}
