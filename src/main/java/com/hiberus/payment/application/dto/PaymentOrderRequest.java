package com.hiberus.payment.application.dto;

import com.hiberus.payment.generated.api.model.Account;
import com.hiberus.payment.generated.api.model.Amount;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SuppressFBWarnings({"EI_EXPOSE_REP", "EI_EXPOSE_REP2"})
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

}
