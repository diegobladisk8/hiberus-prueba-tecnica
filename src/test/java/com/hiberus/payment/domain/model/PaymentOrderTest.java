package com.hiberus.payment.domain.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import static org.junit.jupiter.api.Assertions.*;


class PaymentOrderTest {

    private PaymentOrder paymentOrder;
    private final String ID = "PO123";
    private final String EXTERNAL_REFERENCE = "EXT-123";
    private final String DEBTOR_ACCOUNT = "DEBTOR-IBAN";
    private final String CREDITOR_ACCOUNT = "CREDITOR-IBAN";
    private final BigDecimal AMOUNT = new BigDecimal("100.50");
    private final String CURRENCY = "USD";
    private final String REMITTANCE_INFO = "Test payment";
    private final LocalDateTime EXECUTION_DATE = LocalDateTime.now().plusDays(1);

    @BeforeEach
    void setUp() {
        paymentOrder = PaymentOrder.builder()
                .id(ID)
                .externalReference(EXTERNAL_REFERENCE)
                .status(PaymentOrderStatusEnum.PENDING)
                .debtorAccount(DEBTOR_ACCOUNT)
                .creditorAccount(CREDITOR_ACCOUNT)
                .amount(AMOUNT)
                .currency(CURRENCY)
                .remittanceInformation(REMITTANCE_INFO)
                .requestedExecutionDate(EXECUTION_DATE)
                .creationDate(LocalDateTime.now())
                .lastUpdateDate(LocalDateTime.now())
                .isNew(true)
                .build();
    }

    @Test
    @DisplayName("Should create payment order with all fields correctly set")
    void testPaymentOrderCreationWithBuilder() {
        // Verify
        assertThat(paymentOrder.getId()).isEqualTo(ID);
        assertThat(paymentOrder.getExternalReference()).isEqualTo(EXTERNAL_REFERENCE);
        assertThat(paymentOrder.getStatus()).isEqualTo(PaymentOrderStatusEnum.PENDING);
        assertThat(paymentOrder.getDebtorAccount()).isEqualTo(DEBTOR_ACCOUNT);
        assertThat(paymentOrder.getCreditorAccount()).isEqualTo(CREDITOR_ACCOUNT);
        assertThat(paymentOrder.getAmount()).isEqualByComparingTo(AMOUNT);
        assertThat(paymentOrder.getCurrency()).isEqualTo(CURRENCY);
        assertThat(paymentOrder.getRemittanceInformation()).isEqualTo(REMITTANCE_INFO);
        assertThat(paymentOrder.getRequestedExecutionDate()).isEqualTo(EXECUTION_DATE);
        assertThat(paymentOrder.isNew()).isTrue();
        assertThat(paymentOrder.getCreationDate()).isNotNull();
        assertThat(paymentOrder.getLastUpdateDate()).isNotNull();
    }

    @Test
    @DisplayName("Should create payment order using static factory method")
    void testCreatePaymentOrderWithStaticMethod() {
        // When
        PaymentOrder newPaymentOrder = PaymentOrder.create(
                EXTERNAL_REFERENCE,
                DEBTOR_ACCOUNT,
                CREDITOR_ACCOUNT,
                AMOUNT,
                CURRENCY,
                REMITTANCE_INFO,
                EXECUTION_DATE
        );

        // Then
        assertThat(newPaymentOrder.getId()).isNull();
        assertThat(newPaymentOrder.getExternalReference()).isEqualTo(EXTERNAL_REFERENCE);
        assertThat(newPaymentOrder.getDebtorAccount()).isEqualTo(DEBTOR_ACCOUNT);
        assertThat(newPaymentOrder.getCreditorAccount()).isEqualTo(CREDITOR_ACCOUNT);
        assertThat(newPaymentOrder.getAmount()).isEqualByComparingTo(AMOUNT);
        assertThat(newPaymentOrder.getCurrency()).isEqualTo(CURRENCY);
        assertThat(newPaymentOrder.getRemittanceInformation()).isEqualTo(REMITTANCE_INFO);
        assertThat(newPaymentOrder.getRequestedExecutionDate()).isEqualTo(EXECUTION_DATE);
        assertThat(newPaymentOrder.getStatus()).isEqualTo(PaymentOrderStatusEnum.PENDING);
        assertThat(newPaymentOrder.isNew()).isTrue();
        assertThat(newPaymentOrder.getCreationDate()).isNotNull();
        assertThat(newPaymentOrder.getLastUpdateDate()).isNotNull();
        assertThat(newPaymentOrder.getCreationDate()).isEqualTo(newPaymentOrder.getLastUpdateDate());
    }

    @Test
    @DisplayName("Should mark as not new when isNew is false")
    void testIsNewMethod() {
        // Given
        paymentOrder.setNew(false);

        // Then
        assertThat(paymentOrder.isNew()).isFalse();
    }

    @Test
    @DisplayName("Should update payment order using toBuilder")
    void testUpdateWithToBuilder() {
        // Given
        PaymentOrderStatusEnum newStatus = PaymentOrderStatusEnum.COMPLETED;
        BigDecimal newAmount = new BigDecimal("200.75");
        LocalDateTime newUpdateDate = LocalDateTime.now().plusHours(1);

        // When
        PaymentOrder updatedPaymentOrder = paymentOrder.toBuilder()
                .status(newStatus)
                .amount(newAmount)
                .lastUpdateDate(newUpdateDate)
                .isNew(false)
                .build();

        // Then
        assertThat(updatedPaymentOrder.getId()).isEqualTo(ID);
        assertThat(updatedPaymentOrder.getStatus()).isEqualTo(newStatus);
        assertThat(updatedPaymentOrder.getAmount()).isEqualByComparingTo(newAmount);
        assertThat(updatedPaymentOrder.getLastUpdateDate()).isEqualTo(newUpdateDate);
        assertThat(updatedPaymentOrder.isNew()).isFalse();

        // Verify original object unchanged
        assertThat(paymentOrder.getStatus()).isEqualTo(PaymentOrderStatusEnum.PENDING);
        assertThat(paymentOrder.getAmount()).isEqualByComparingTo(AMOUNT);
    }

    @Test
    @DisplayName("Should have correct equals and hashCode based on id")
    void testEqualsAndHashCode() {
        // Given
        PaymentOrder samePaymentOrder = PaymentOrder.builder()
                .id(ID)  // Mismo ID
                .externalReference("DIFFERENT-REF")  // Diferente referencia
                .status(PaymentOrderStatusEnum.COMPLETED)  // Diferente estado
                .debtorAccount("DIFFERENT-DEBTOR")  // Diferente cuenta deudor
                .creditorAccount("DIFFERENT-CREDITOR")  // Diferente cuenta acreedor
                .amount(new BigDecimal("999.99"))  // Diferente monto
                .currency("EUR")  // Diferente moneda
                .remittanceInformation("Different info")  // Diferente información
                .requestedExecutionDate(LocalDateTime.now().plusYears(1))  // Diferente fecha
                .creationDate(LocalDateTime.now().plusHours(5))  // Diferente fecha creación
                .lastUpdateDate(LocalDateTime.now().plusHours(10))  // Diferente fecha actualización
                .isNew(false)  // Diferente estado isNew
                .build();

        PaymentOrder differentPaymentOrder = PaymentOrder.builder()
                .id("DIFFERENT-ID")  // Diferente ID
                .externalReference(EXTERNAL_REFERENCE)  // Misma referencia
                .status(PaymentOrderStatusEnum.PENDING)  // Mismo estado
                .debtorAccount(DEBTOR_ACCOUNT)  // Misma cuenta deudor
                .creditorAccount(CREDITOR_ACCOUNT)  // Misma cuenta acreedor
                .amount(AMOUNT)  // Mismo monto
                .currency(CURRENCY)  // Misma moneda
                .remittanceInformation(REMITTANCE_INFO)  // Misma información
                .requestedExecutionDate(EXECUTION_DATE)  // Misma fecha ejecución
                .creationDate(LocalDateTime.now())  // Misma fecha creación
                .lastUpdateDate(LocalDateTime.now())  // Misma fecha actualización
                .isNew(true)  // Mismo estado isNew
                .build();

        // Then - Verificar equals
        assertThat(paymentOrder).isNotEqualTo(differentPaymentOrder);

        // Verificar que objetos diferentes tienen diferentes hash codes (no siempre garantizado pero probable)
        assertThat(paymentOrder.hashCode())
                .as("Hash codes should be different for objects with different IDs")
                .isNotEqualTo(differentPaymentOrder.hashCode());
    }

    @Test
    @DisplayName("Should return correct string representation")
    void testToString() {
        // When
        String toString = paymentOrder.toString();

        // Then
        assertThat(toString).contains(ID);
        assertThat(toString).contains(EXTERNAL_REFERENCE);
        assertThat(toString).contains(PaymentOrderStatusEnum.PENDING.name());
        assertThat(toString).contains(DEBTOR_ACCOUNT);
        assertThat(toString).contains(CREDITOR_ACCOUNT);
    }

    @Test
    @DisplayName("Should handle null values correctly")
    void testNullValues() {
        // Given
        PaymentOrder nullPaymentOrder = PaymentOrder.builder()
                .id(null)
                .externalReference(null)
                .status(null)
                .debtorAccount(null)
                .creditorAccount(null)
                .amount(null)
                .currency(null)
                .remittanceInformation(null)
                .requestedExecutionDate(null)
                .creationDate(null)
                .lastUpdateDate(null)
                .isNew(true)
                .build();

        // Then
        assertThat(nullPaymentOrder.getId()).isNull();
        assertThat(nullPaymentOrder.getExternalReference()).isNull();
        assertThat(nullPaymentOrder.getStatus()).isNull();
        assertThat(nullPaymentOrder.getDebtorAccount()).isNull();
        assertThat(nullPaymentOrder.getCreditorAccount()).isNull();
        assertThat(nullPaymentOrder.getAmount()).isNull();
        assertThat(nullPaymentOrder.getCurrency()).isNull();
        assertThat(nullPaymentOrder.getRemittanceInformation()).isNull();
        assertThat(nullPaymentOrder.getRequestedExecutionDate()).isNull();
        assertThat(nullPaymentOrder.getCreationDate()).isNull();
        assertThat(nullPaymentOrder.getLastUpdateDate()).isNull();
        assertThat(nullPaymentOrder.isNew()).isTrue();
    }

    @Test
    @DisplayName("Should create payment order with zero amount")
    void testCreateWithZeroAmount() {
        // When
        PaymentOrder zeroAmountOrder = PaymentOrder.create(
                EXTERNAL_REFERENCE,
                DEBTOR_ACCOUNT,
                CREDITOR_ACCOUNT,
                BigDecimal.ZERO,
                CURRENCY,
                REMITTANCE_INFO,
                EXECUTION_DATE
        );

        // Then
        assertThat(zeroAmountOrder.getAmount()).isEqualByComparingTo(BigDecimal.ZERO);
    }

    @Test
    @DisplayName("Should handle different statuses correctly")
    void testDifferentStatuses() {
        // Given
        PaymentOrderStatusEnum[] statuses = PaymentOrderStatusEnum.values();

        for (PaymentOrderStatusEnum status : statuses) {
            // When
            PaymentOrder statusOrder = paymentOrder.toBuilder()
                    .status(status)
                    .build();

            // Then
            assertThat(statusOrder.getStatus()).isEqualTo(status);
        }
    }

    @Test
    @DisplayName("Should set creation and update date to same value when created")
    void testCreationAndUpdateDateOnCreation() {
        // When
        PaymentOrder newOrder = PaymentOrder.create(
                "NEW-REF",
                "DEBTOR1",
                "CREDITOR1",
                new BigDecimal("50.00"),
                "EUR",
                "New payment",
                LocalDateTime.now().plusDays(2)
        );

        // Then
        assertThat(newOrder.getCreationDate()).isNotNull();
        assertThat(newOrder.getLastUpdateDate()).isNotNull();
        assertThat(newOrder.getCreationDate()).isEqualTo(newOrder.getLastUpdateDate());
    }


    @Test
    @DisplayName("Equals debe retornar false comparando con null u otro tipo")
    void testEqualsNullAndDifferentType() {

        com.hiberus.payment.generated.api.model.PaymentOrder po = new com.hiberus.payment.generated.api.model.PaymentOrder();
        po.setId("ID-X");

        assertNotEquals(po, null);
        assertNotEquals(po, "string");
    }

    @Test
    @DisplayName("hashCode debe ser consistente entre invocaciones")
    void testHashCodeConsistency() {
        com.hiberus.payment.generated.api.model.PaymentOrder po = new com.hiberus.payment.generated.api.model.PaymentOrder();
        po.setId("CONSISTENT");

        int h1 = po.hashCode();
        int h2 = po.hashCode();
        assertEquals(h1, h2);
    }

    @Test
    @DisplayName("Debería permitir settear y obtener todos los campos correctamente")
    void testSettersAndGetters() {
        PaymentOrder po = new PaymentOrder();

        LocalDateTime now = LocalDateTime.now();
        LocalDateTime execDate = LocalDateTime.now().plusDays(1);

        po.setId("ID-001");
        po.setExternalReference("EXT-001");
        po.setStatus(PaymentOrderStatusEnum.PENDING);
        po.setDebtorAccount("ACC-DEBTOR");
        po.setCreditorAccount("ACC-CREDITOR");
        po.setAmount(BigDecimal.valueOf(100.50));
        po.setCurrency("USD");
        po.setRemittanceInformation("Pago de factura");
        po.setRequestedExecutionDate(execDate);
        po.setCreationDate(now);
        po.setLastUpdateDate(now);
        po.setNew(false);

        assertAll(
                () -> assertEquals("ID-001", po.getId()),
                () -> assertEquals("EXT-001", po.getExternalReference()),
                () -> assertEquals(PaymentOrderStatusEnum.PENDING, po.getStatus()),
                () -> assertEquals("ACC-DEBTOR", po.getDebtorAccount()),
                () -> assertEquals("ACC-CREDITOR", po.getCreditorAccount()),
                () -> assertEquals(BigDecimal.valueOf(100.50), po.getAmount()),
                () -> assertEquals("USD", po.getCurrency()),
                () -> assertEquals("Pago de factura", po.getRemittanceInformation()),
                () -> assertEquals(execDate, po.getRequestedExecutionDate()),
                () -> assertEquals(now, po.getCreationDate()),
                () -> assertEquals(now, po.getLastUpdateDate()),
                () -> assertFalse(po.isNew())
        );
    }

    @Test
    void testEquals_AllFields() {
        LocalDateTime now = LocalDateTime.now();
        PaymentOrder po1 = PaymentOrder.builder()
                .id("1")
                .externalReference("EXT")
                .status(PaymentOrderStatusEnum.PENDING)
                .debtorAccount("A")
                .creditorAccount("B")
                .amount(BigDecimal.TEN)
                .currency("USD")
                .remittanceInformation("remit")
                .requestedExecutionDate(now)
                .creationDate(now)
                .lastUpdateDate(now)
                .isNew(false)
                .build();

        PaymentOrder po2 = PaymentOrder.builder()
                .id("1")
                .externalReference("EXT")
                .status(PaymentOrderStatusEnum.PENDING)
                .debtorAccount("A")
                .creditorAccount("B")
                .amount(BigDecimal.TEN)
                .currency("USD")
                .remittanceInformation("remit")
                .requestedExecutionDate(now)
                .creationDate(now)
                .lastUpdateDate(now)
                .isNew(false)
                .build();

        assertEquals(po1, po2);
        assertEquals(po1.hashCode(), po2.hashCode());
    }

    @Test
    void testEquals_Different() {
        PaymentOrder po1 = PaymentOrder.builder().id("1").build();
        PaymentOrder po2 = PaymentOrder.builder().id("2").build();
        assertNotEquals(po1, po2);
    }

}
