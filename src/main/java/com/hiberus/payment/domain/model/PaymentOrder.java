package com.hiberus.payment.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payment_order", schema = "public")
public class PaymentOrder implements Persistable<String> {

    @Id
    private String id;

    @Column("external_reference")
    private String externalReference;

    @Column("status")
    private PaymentOrderStatusEnum status;

    @Column("debtor_account")
    private String debtorAccount;

    @Column("creditor_account")
    private String creditorAccount;

    @Column("amount")
    private BigDecimal amount;

    @Column("currency")
    private String currency;

    @Column("remittance_information")
    private String remittanceInformation;

    @Column("requested_execution_date")
    private LocalDateTime requestedExecutionDate;

    @Column("creation_date")
    private LocalDateTime creationDate;

    @Column("last_update_date")
    private LocalDateTime lastUpdateDate;

    @Transient
    private boolean isNew;

    public static PaymentOrder create(String externalReference, String debtorAccount,
                                      String creditorAccount, BigDecimal amount, String currency,
                                      String remittanceInformation, LocalDateTime requestedExecutionDate) {
        LocalDateTime now = LocalDateTime.now();
        return PaymentOrder.builder()
                .externalReference(externalReference)
                .debtorAccount(debtorAccount)
                .creditorAccount(creditorAccount)
                .amount(amount)
                .currency(currency)
                .remittanceInformation(remittanceInformation)
                .requestedExecutionDate(requestedExecutionDate)
                .status(PaymentOrderStatusEnum.PENDING)
                .creationDate(now)
                .isNew(true) // ← MARCAR COMO NUEVA
                .lastUpdateDate(now)
                .build();
    }


    @Override
    public boolean isNew() {
        return isNew;
    }
}