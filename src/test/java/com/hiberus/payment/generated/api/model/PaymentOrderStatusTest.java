package com.hiberus.payment.generated.api.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.OffsetDateTime;

class PaymentOrderStatusTest {

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


    @Test
    @DisplayName("toString() debería contener los campos principales")
    void testToStringContainsFields() {
        PaymentOrderRequest request = new PaymentOrderRequest();
        request.setExternalReference("EXT-999");

        String result = request.toString();

        assertTrue(result.contains("PaymentOrderRequest"));
        assertTrue(result.contains("externalReference: EXT-999"));
    }

    @Test
    @DisplayName("toIndentedString() debería indentar correctamente las líneas internas (probado vía toString)")
    void testToIndentedStringIndentation() {
        PaymentOrderRequest request = new PaymentOrderRequest();
        request.setExternalReference("EXT-123");

        String result = request.toString();

        // El toIndentedString agrega 4 espacios a las líneas internas
        assertTrue(result.contains("    externalReference: EXT-123"));
    }

    @Test
    @DisplayName("equals() debería retornar false para objetos diferentes")
    void testEqualsFalse() {
        PaymentOrderRequest r1 = new PaymentOrderRequest();
        r1.setExternalReference("A");

        PaymentOrderRequest r2 = new PaymentOrderRequest();
        r2.setExternalReference("B");

        assertNotEquals(r1, r2);
    }

    @Test
    @DisplayName("equals() debería retornar true para objetos iguales")
    void testEqualsTrue() {
        Account debtor = new Account();
        Account creditor = new Account();
        Amount amount = new Amount();
        LocalDate date = LocalDate.of(2025, 10, 30);

        PaymentOrderRequest r1 = new PaymentOrderRequest("EXT-1", debtor, creditor, amount);
        r1.setRemittanceInformation("ABC");
        r1.setRequestedExecutionDate(date);

        PaymentOrderRequest r2 = new PaymentOrderRequest("EXT-1", debtor, creditor, amount);
        r2.setRemittanceInformation("ABC");
        r2.setRequestedExecutionDate(date);

        assertEquals(r1, r2);
        assertEquals(r1.hashCode(), r2.hashCode());
    }

    @Test
    @DisplayName("hashCode() debería ser consistente con equals()")
    void testHashCode() {
        Account debtor = new Account();
        Account creditor = new Account();
        Amount amount = new Amount();
        LocalDate date = LocalDate.of(2026, 5, 15);

        PaymentOrderRequest p1 = new PaymentOrderRequest("EXT-55", debtor, creditor, amount);
        p1.setRemittanceInformation("ABC");
        p1.setRequestedExecutionDate(date);

        PaymentOrderRequest p2 = new PaymentOrderRequest("EXT-55", debtor, creditor, amount);
        p2.setRemittanceInformation("ABC");
        p2.setRequestedExecutionDate(date);

        PaymentOrderRequest p3 = new PaymentOrderRequest("OTHER", debtor, creditor, amount);

        // Mismos valores → equals true → mismo hashCode
        assertEquals(p1, p2);
        assertEquals(p1.hashCode(), p2.hashCode());

        // Valores distintos → equals false → hashCode generalmente distinto
        assertNotEquals(p1, p3);
        assertNotEquals(p1.hashCode(), p3.hashCode());
    }
}

