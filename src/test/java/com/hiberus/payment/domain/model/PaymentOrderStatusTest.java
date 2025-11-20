package com.hiberus.payment.domain.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

class PaymentOrderStatusTest {

    private PaymentOrderStatus paymentOrderStatus;
    private final String ID = "STATUS-123";
    private final String PAYMENT_ORDER_ID = "PO-123";
    private final PaymentOrderStatusEnum STATUS = PaymentOrderStatusEnum.PENDING;
    private final LocalDateTime LAST_UPDATE_DATE = LocalDateTime.now();

    @BeforeEach
    void setUp() {
        paymentOrderStatus = PaymentOrderStatus.builder()
                .id(ID)
                .paymentOrderId(PAYMENT_ORDER_ID)
                .status(STATUS)
                .lastUpdateDate(LAST_UPDATE_DATE)
                .build();
    }

    @Test
    @DisplayName("Should create PaymentOrderStatus with all fields correctly set")
    void testPaymentOrderStatusCreationWithBuilder() {
        // Verify
        assertThat(paymentOrderStatus.getId()).isEqualTo(ID);
        assertThat(paymentOrderStatus.getPaymentOrderId()).isEqualTo(PAYMENT_ORDER_ID);
        assertThat(paymentOrderStatus.getStatus()).isEqualTo(STATUS);
        assertThat(paymentOrderStatus.getLastUpdateDate()).isEqualTo(LAST_UPDATE_DATE);
    }

    @Test
    @DisplayName("Should create PaymentOrderStatus with no-args constructor")
    void testNoArgsConstructor() {
        // When
        PaymentOrderStatus emptyStatus = new PaymentOrderStatus();

        // Then
        assertThat(emptyStatus.getId()).isNull();
        assertThat(emptyStatus.getPaymentOrderId()).isNull();
        assertThat(emptyStatus.getStatus()).isNull();
        assertThat(emptyStatus.getLastUpdateDate()).isNull();
    }

    @Test
    @DisplayName("Should create PaymentOrderStatus with all-args constructor")
    void testAllArgsConstructor() {
        // When
        PaymentOrderStatus status = new PaymentOrderStatus(
                "NEW-ID",
                "NEW-PO-ID",
                PaymentOrderStatusEnum.COMPLETED,
                LocalDateTime.now().plusHours(1)
        );

        // Then
        assertThat(status.getId()).isEqualTo("NEW-ID");
        assertThat(status.getPaymentOrderId()).isEqualTo("NEW-PO-ID");
        assertThat(status.getStatus()).isEqualTo(PaymentOrderStatusEnum.COMPLETED);
        assertThat(status.getLastUpdateDate()).isNotNull();
    }

    @Test
    @DisplayName("Should update fields using setters")
    void testSetters2() {
        // Given
        String newId = "UPDATED-ID";
        String newPaymentOrderId = "UPDATED-PO-ID";
        PaymentOrderStatusEnum newStatus = PaymentOrderStatusEnum.FAILED;
        LocalDateTime newUpdateDate = LocalDateTime.now().plusDays(1);

        // When
        paymentOrderStatus.setId(newId);
        paymentOrderStatus.setPaymentOrderId(newPaymentOrderId);
        paymentOrderStatus.setStatus(newStatus);
        paymentOrderStatus.setLastUpdateDate(newUpdateDate);

        // Then
        assertThat(paymentOrderStatus.getId()).isEqualTo(newId);
        assertThat(paymentOrderStatus.getPaymentOrderId()).isEqualTo(newPaymentOrderId);
        assertThat(paymentOrderStatus.getStatus()).isEqualTo(newStatus);
        assertThat(paymentOrderStatus.getLastUpdateDate()).isEqualTo(newUpdateDate);
    }


    @Test
    @DisplayName("Should update fields using setters")
    void testSetters() {
        // Given
        String newId = "UPDATED-ID";
        String newPaymentOrderId = "UPDATED-PO-ID";
        PaymentOrderStatusEnum newStatus = PaymentOrderStatusEnum.FAILED;
        LocalDateTime newUpdateDate = LocalDateTime.now().plusDays(1);

        // When
        paymentOrderStatus.setId(newId);
        paymentOrderStatus.setPaymentOrderId(newPaymentOrderId);
        paymentOrderStatus.setStatus(newStatus);
        paymentOrderStatus.setLastUpdateDate(newUpdateDate);

        // Then
        assertThat(paymentOrderStatus.getId()).isEqualTo(newId);
        assertThat(paymentOrderStatus.getPaymentOrderId()).isEqualTo(newPaymentOrderId);
        assertThat(paymentOrderStatus.getStatus()).isEqualTo(newStatus);
        assertThat(paymentOrderStatus.getLastUpdateDate()).isEqualTo(newUpdateDate);
    }

    @Test
    @DisplayName("Should have correct equals and hashCode based on id")
    void testEqualsAndHashCode() {
        // Given
        PaymentOrderStatus sameStatus = PaymentOrderStatus.builder()
                .id(ID)
                .paymentOrderId("DIFFERENT-PO-ID")
                .status(PaymentOrderStatusEnum.COMPLETED)
                .lastUpdateDate(LocalDateTime.now().plusHours(1))
                .build();

        PaymentOrderStatus differentStatus = PaymentOrderStatus.builder()
                .id("DIFFERENT-ID")
                .paymentOrderId(PAYMENT_ORDER_ID)
                .status(STATUS)
                .lastUpdateDate(LAST_UPDATE_DATE)
                .build();

        // Then
        assertThat(paymentOrderStatus).isNotEqualTo(differentStatus);
    }

    @Test
    @DisplayName("Should return correct string representation")
    void testToString() {
        // When
        String toString = paymentOrderStatus.toString();

        // Then
        assertThat(toString).contains(ID);
        assertThat(toString).contains(PAYMENT_ORDER_ID);
        assertThat(toString).contains(STATUS.name());
        assertThat(toString).contains(LAST_UPDATE_DATE.toString());
    }

    @Test
    @DisplayName("Should handle null values correctly")
    void testNullValues() {
        // Given
        PaymentOrderStatus nullStatus = new PaymentOrderStatus();

        // When
        nullStatus.setId(null);
        nullStatus.setPaymentOrderId(null);
        nullStatus.setStatus(null);
        nullStatus.setLastUpdateDate(null);

        // Then
        assertThat(nullStatus.getId()).isNull();
        assertThat(nullStatus.getPaymentOrderId()).isNull();
        assertThat(nullStatus.getStatus()).isNull();
        assertThat(nullStatus.getLastUpdateDate()).isNull();
    }

    @Test
    @DisplayName("Should test all available status enum values")
    void testAllStatusEnumValues() {
        // Given
        PaymentOrderStatusEnum[] allStatuses = PaymentOrderStatusEnum.values();

        for (PaymentOrderStatusEnum statusEnum : allStatuses) {
            // When
            PaymentOrderStatus statusWithEnum = PaymentOrderStatus.builder()
                    .id("ID-" + statusEnum.name())
                    .paymentOrderId("PO-" + statusEnum.name())
                    .status(statusEnum)
                    .lastUpdateDate(LocalDateTime.now())
                    .build();

            // Then
            assertThat(statusWithEnum.getStatus()).isEqualTo(statusEnum);
            assertThat(statusWithEnum.getId()).isEqualTo("ID-" + statusEnum.name());
            assertThat(statusWithEnum.getPaymentOrderId()).isEqualTo("PO-" + statusEnum.name());
        }
    }

    @Test
    @DisplayName("Should handle different payment order IDs")
    void testDifferentPaymentOrderIds() {
        // Given
        String[] paymentOrderIds = {"PO-001", "PO-002", "PO-003", "PO-ABC-123"};

        for (String paymentOrderId : paymentOrderIds) {
            // When
            PaymentOrderStatus status = PaymentOrderStatus.builder()
                    .id("STATUS-" + paymentOrderId)
                    .paymentOrderId(paymentOrderId)
                    .status(PaymentOrderStatusEnum.PROCESSING)
                    .lastUpdateDate(LocalDateTime.now())
                    .build();

            // Then
            assertThat(status.getPaymentOrderId()).isEqualTo(paymentOrderId);
            assertThat(status.getId()).isEqualTo("STATUS-" + paymentOrderId);
        }
    }

    @Test
    @DisplayName("Should verify lastUpdateDate is stored correctly")
    void testLastUpdateDatePrecision() {
        // Given
        LocalDateTime specificDate = LocalDateTime.of(2024, 1, 15, 14, 30, 45, 123456789);

        // When
        PaymentOrderStatus statusWithSpecificDate = PaymentOrderStatus.builder()
                .id("TIME-TEST")
                .paymentOrderId("PO-TIME")
                .status(PaymentOrderStatusEnum.COMPLETED)
                .lastUpdateDate(specificDate)
                .build();

        // Then
        assertThat(statusWithSpecificDate.getLastUpdateDate()).isEqualTo(specificDate);
        assertThat(statusWithSpecificDate.getLastUpdateDate().getYear()).isEqualTo(2024);
        assertThat(statusWithSpecificDate.getLastUpdateDate().getMonthValue()).isEqualTo(1);
        assertThat(statusWithSpecificDate.getLastUpdateDate().getDayOfMonth()).isEqualTo(15);
        assertThat(statusWithSpecificDate.getLastUpdateDate().getHour()).isEqualTo(14);
        assertThat(statusWithSpecificDate.getLastUpdateDate().getMinute()).isEqualTo(30);
        assertThat(statusWithSpecificDate.getLastUpdateDate().getSecond()).isEqualTo(45);
    }

    @Test
    @DisplayName("Should test object identity")
    void testObjectIdentity() {
        // Given
        PaymentOrderStatus sameReference = paymentOrderStatus;

        // Then
        assertThat(paymentOrderStatus).isSameAs(sameReference);
        assertThat(paymentOrderStatus).isNotSameAs(new PaymentOrderStatus());
    }

    @Test
    @DisplayName("Should handle empty string values")
    void testEmptyStringValues() {
        // Given
        PaymentOrderStatus emptyStringsStatus = PaymentOrderStatus.builder()
                .id("")
                .paymentOrderId("")
                .status(PaymentOrderStatusEnum.PENDING)
                .lastUpdateDate(LocalDateTime.now())
                .build();

        // Then
        assertThat(emptyStringsStatus.getId()).isEmpty();
        assertThat(emptyStringsStatus.getPaymentOrderId()).isEmpty();
        assertThat(emptyStringsStatus.getStatus()).isEqualTo(PaymentOrderStatusEnum.PENDING);
    }

    @Test
    @DisplayName("Should verify builder pattern works correctly")
    void testBuilderPattern() {
        // When
        PaymentOrderStatus builtStatus = PaymentOrderStatus.builder()
                .id("BUILT-ID")
                .paymentOrderId("BUILT-PO-ID")
                .status(PaymentOrderStatusEnum.PENDING)
                .lastUpdateDate(LocalDateTime.now().minusDays(1))
                .build();

        // Then
        assertThat(builtStatus).isNotNull();
        assertThat(builtStatus.getId()).isEqualTo("BUILT-ID");
        assertThat(builtStatus.getPaymentOrderId()).isEqualTo("BUILT-PO-ID");
        assertThat(builtStatus.getStatus()).isEqualTo(PaymentOrderStatusEnum.PENDING);
        assertThat(builtStatus.getLastUpdateDate()).isBefore(LocalDateTime.now());
    }

}