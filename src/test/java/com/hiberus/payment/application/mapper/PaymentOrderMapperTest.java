package com.hiberus.payment.application.mapper;

import com.hiberus.payment.application.dto.PaymentOrderRequest;
import com.hiberus.payment.application.dto.PaymentOrderResponse;
import com.hiberus.payment.generated.api.model.Account;
import com.hiberus.payment.generated.api.model.Amount;
import com.hiberus.payment.infrastructure.model.PaymentOrderEntity;
import com.hiberus.payment.infrastructure.model.PaymentOrderStatusEnum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

 class PaymentOrderMapperTest {

    private PaymentOrderMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new PaymentOrderMapper();
    }

    @Test
    void toDomain_shouldMapPaymentOrderRequestToDomain() {
        // Arrange
        PaymentOrderRequest request = new PaymentOrderRequest();
        request.setExternalReference("REF123");

        request.setDebtorAccount(new Account("DEBIT-IBAN-001"));
        request.setCreditorAccount(new Account("CREDIT-IBAN-999"));

        Amount amount = new Amount();
        amount.setAmount(150.25);
        amount.setCurrency("EUR");
        request.setInstructedAmount(amount);

        request.setRemittanceInformation("Invoice 2025");
        request.setRequestedExecutionDate(LocalDate.of(2025, 1, 15));

        // Act
        PaymentOrderEntity result = mapper.toDomain(request);

        // Assert
        assertThat(result).isNotNull();
        assertThat(result.getExternalReference()).isEqualTo("REF123");
        assertThat(result.getDebtorAccount()).isEqualTo("DEBIT-IBAN-001");
        assertThat(result.getCreditorAccount()).isEqualTo("CREDIT-IBAN-999");
        assertThat(result.getAmount()).isEqualTo(BigDecimal.valueOf(150.25));
        assertThat(result.getCurrency()).isEqualTo("EUR");
        assertThat(result.getRemittanceInformation()).isEqualTo("Invoice 2025");
        assertThat(result.getRequestedExecutionDate()).isEqualTo(LocalDateTime.of(2025, 1, 15, 0, 0));
        assertThat(result.getStatus()).isEqualTo(PaymentOrderStatusEnum.PENDING);
        assertThat(result.getCreationDate()).isNotNull();
        assertThat(result.getLastUpdateDate()).isNotNull();
    }

    @Test
    void toResponse_shouldMapDomainToPaymentOrderResponse() {
        // Arrange
        LocalDateTime now = LocalDateTime.now();
        PaymentOrderEntity order = PaymentOrderEntity.builder()
                .id("PO-123")
                .externalReference("REF123")
                .debtorAccount("DEBIT-IBAN-123")
                .creditorAccount("CREDIT-IBAN-555")
                .amount(new BigDecimal("10.0"))
                .currency("USD")
                .remittanceInformation("Test Payment")
                .requestedExecutionDate(now)
                .status(PaymentOrderStatusEnum.PENDING)
                .creationDate(now.minusDays(1))
                .lastUpdateDate(now)
                .build();

        // Act
        PaymentOrderResponse response = mapper.toResponse(order);

        // Assert
        assertThat(response).isNotNull();
        assertThat(response.getId()).isEqualTo("PO-123");
        assertThat(response.getExternalReference()).isEqualTo("REF123");
        assertThat(response.getStatus()).isEqualTo("PENDING");
        assertThat(response.getDebtorAccount().getIban()).isEqualTo("DEBIT-IBAN-123");
        assertThat(response.getCreditorAccount().getIban()).isEqualTo("CREDIT-IBAN-555");
        assertThat(response.getInstructedAmount().getCurrency()).isEqualTo("USD");
        assertThat(response.getRemittanceInformation()).isEqualTo("Test Payment");
        assertThat(response.getRequestedExecutionDate()).isEqualTo(now);
        assertThat(response.getCreationDate()).isEqualTo(now.minusDays(1));
        assertThat(response.getLastUpdateDate()).isEqualTo(now);
    }


}

