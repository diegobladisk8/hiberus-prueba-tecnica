package com.hiberus.payment.application.mapper;


import com.hiberus.payment.application.dto.PaymentOrderRequest;
import com.hiberus.payment.application.dto.PaymentOrderResponse;
import com.hiberus.payment.application.dto.PaymentOrderStatusResponse;
import com.hiberus.payment.domain.model.PaymentOrder;
import com.hiberus.payment.domain.model.PaymentOrderStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PaymentOrderMapper {

    public PaymentOrder toDomain(PaymentOrderRequest request) {
        return PaymentOrder.create(
                request.getExternalReference(),
                request.getDebtorAccount().getIban(),
                request.getCreditorAccount().getIban(),
                java.math.BigDecimal.valueOf(request.getInstructedAmount().getAmount()),
                request.getInstructedAmount().getCurrency(),
                request.getRemittanceInformation(),
                request.getRequestedExecutionDate() != null ?
                        request.getRequestedExecutionDate().atStartOfDay() :
                        LocalDateTime.now()
        );
    }

    public PaymentOrderResponse toResponse(PaymentOrder paymentOrder) {
        return PaymentOrderResponse.builder()
                .id(paymentOrder.getId())
                .externalReference(paymentOrder.getExternalReference())
                .status(paymentOrder.getStatus().name())
                .debtorAccount(PaymentOrderResponse.Account.builder()
                        .iban(paymentOrder.getDebtorAccount())
                        .build())
                .creditorAccount(PaymentOrderResponse.Account.builder()
                        .iban(paymentOrder.getCreditorAccount())
                        .build())
                .instructedAmount(PaymentOrderResponse.Amount.builder()
                        .amount(paymentOrder.getAmount())
                        .currency(paymentOrder.getCurrency())
                        .build())
                .remittanceInformation(paymentOrder.getRemittanceInformation())
                .requestedExecutionDate(paymentOrder.getRequestedExecutionDate())
                .creationDate(paymentOrder.getCreationDate())
                .lastUpdateDate(paymentOrder.getLastUpdateDate())
                .build();
    }

    public PaymentOrderStatusResponse toStatusResponse(PaymentOrderStatus status) {
        return PaymentOrderStatusResponse.builder()
                .id(status.getId())
                .status(status.getStatus().name())
                .lastUpdateDate(status.getLastUpdateDate())
                .build();
    }
}
