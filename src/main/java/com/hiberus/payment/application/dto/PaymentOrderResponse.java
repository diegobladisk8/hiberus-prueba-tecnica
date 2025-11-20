package com.hiberus.payment.application.dto;

import com.hiberus.payment.generated.api.model.Account;
import com.hiberus.payment.generated.api.model.Amount;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SuppressFBWarnings({"EI_EXPOSE_REP", "EI_EXPOSE_REP2"})
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


}