package com.hiberus.payment.infrastructure.adapter.model;

import com.hiberus.payment.domain.model.PaymentOrderStatus;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PaymentOrderStatusEntityTest {

    @Test
    void testRequiredConstructor() {
        OffsetDateTime date = OffsetDateTime.now();
        PaymentOrderStatus status = new PaymentOrderStatus("PO-01", PaymentOrderStatus.StatusEnum.PENDING, date);

        assertEquals("PO-01", status.getId());
        assertEquals(PaymentOrderStatus.StatusEnum.PENDING, status.getStatus());
        assertEquals(date, status.getLastUpdateDate());
    }

    @Test
    void testSetterGetter() {
        PaymentOrderStatus status = new PaymentOrderStatus();
        OffsetDateTime date = OffsetDateTime.now();

        status.setId("PO-02");
        status.setStatus(PaymentOrderStatus.StatusEnum.COMPLETED);
        status.setLastUpdateDate(date);

        assertEquals("PO-02", status.getId());
        assertEquals(PaymentOrderStatus.StatusEnum.COMPLETED, status.getStatus());
        assertEquals(date, status.getLastUpdateDate());
    }

    @Test
    void testEqualsAndHashCode() {
        OffsetDateTime date = OffsetDateTime.now();
        PaymentOrderStatus s1 = new PaymentOrderStatus("PO-03", PaymentOrderStatus.StatusEnum.REJECTED, date);
        PaymentOrderStatus s2 = new PaymentOrderStatus("PO-03", PaymentOrderStatus.StatusEnum.REJECTED, date);
        PaymentOrderStatus s3 = new PaymentOrderStatus("PO-04", PaymentOrderStatus.StatusEnum.REJECTED, date);

        assertEquals(s1, s2);
        assertEquals(s1.hashCode(), s2.hashCode());

        assertNotEquals(s1, s3);
        assertNotEquals(s1.hashCode(), s3.hashCode());
    }


    @Test
    void testEnumFromValue() {
        assertEquals(PaymentOrderStatus.StatusEnum.PENDING, PaymentOrderStatus.StatusEnum.fromValue("PENDING"));
        assertEquals(PaymentOrderStatus.StatusEnum.COMPLETED, PaymentOrderStatus.StatusEnum.fromValue("COMPLETED"));
    }

    @Test
    void testEnumFromValueInvalid() {
        assertThrows(IllegalArgumentException.class, () -> PaymentOrderStatus.StatusEnum.fromValue("INVALID_VALUE"));
    }
}

