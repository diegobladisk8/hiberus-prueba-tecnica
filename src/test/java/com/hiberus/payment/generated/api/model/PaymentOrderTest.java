package com.hiberus.payment.generated.api.model;


import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.OffsetDateTime;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

 class PaymentOrderTest {

    @Test
    @DisplayName("Debería crear PaymentOrder con constructor obligatorio")
    void testRequiredConstructor() {
        Account debtor = Mockito.mock(Account.class);
        Account creditor = Mockito.mock(Account.class);
        Amount amount = Mockito.mock(Amount.class);

        PaymentOrder paymentOrder = new PaymentOrder(
                "PO-0001",
                "EXT-123",
                PaymentOrder.StatusEnum.PENDING,
                debtor,
                creditor,
                amount
        );

        assertEquals("PO-0001", paymentOrder.getId());
        assertEquals("EXT-123", paymentOrder.getExternalReference());
        assertEquals(PaymentOrder.StatusEnum.PENDING, paymentOrder.getStatus());
        assertEquals(debtor, paymentOrder.getDebtorAccount());
        assertEquals(creditor, paymentOrder.getCreditorAccount());
        assertEquals(amount, paymentOrder.getInstructedAmount());
    }

    @Test
    @DisplayName("Debería permitir settear los valores correctamente")
    void testSettersAndGetters() {
        PaymentOrder paymentOrder = new PaymentOrder();

        Account debtor = Mockito.mock(Account.class);
        Account creditor = Mockito.mock(Account.class);
        Amount amount = Mockito.mock(Amount.class);
        LocalDate executionDate = LocalDate.now();
        OffsetDateTime now = OffsetDateTime.now();

        paymentOrder.setId("PO-100");
        paymentOrder.setExternalReference("EXT-999");
        paymentOrder.setStatus(PaymentOrder.StatusEnum.COMPLETED);
        paymentOrder.setDebtorAccount(debtor);
        paymentOrder.setCreditorAccount(creditor);
        paymentOrder.setInstructedAmount(amount);
        paymentOrder.setRemittanceInformation("Factura 001-123");
        paymentOrder.setRequestedExecutionDate(executionDate);
        paymentOrder.setCreationDate(now);
        paymentOrder.setLastUpdateDate(now);

        assertAll(
                () -> assertEquals("PO-100", paymentOrder.getId()),
                () -> assertEquals("EXT-999", paymentOrder.getExternalReference()),
                () -> assertEquals(PaymentOrder.StatusEnum.COMPLETED, paymentOrder.getStatus()),
                () -> assertEquals(debtor, paymentOrder.getDebtorAccount()),
                () -> assertEquals(creditor, paymentOrder.getCreditorAccount()),
                () -> assertEquals(amount, paymentOrder.getInstructedAmount()),
                () -> assertEquals("Factura 001-123", paymentOrder.getRemittanceInformation()),
                () -> assertEquals(executionDate, paymentOrder.getRequestedExecutionDate()),
                () -> assertEquals(now, paymentOrder.getCreationDate()),
                () -> assertEquals(now, paymentOrder.getLastUpdateDate())
        );
    }

    @Test
    @DisplayName("StatusEnum.fromValue debe devolver el Enum correcto")
    void testStatusEnumFromValue() {
        assertEquals(PaymentOrder.StatusEnum.REJECTED, PaymentOrder.StatusEnum.fromValue("REJECTED"));
    }

    @Test
    @DisplayName("StatusEnum.fromValue debe lanzar excepción si el valor no existe")
    void testStatusEnumInvalidValue() {
        assertThrows(IllegalArgumentException.class, () -> PaymentOrder.StatusEnum.fromValue("INVALID"));
    }

    @Test
    @DisplayName("equals y hashCode deben funcionar correctamente")
    void testEqualsAndHashCode() {
        Account debtor = Mockito.mock(Account.class);
        Account creditor = Mockito.mock(Account.class);
        Amount amount = Mockito.mock(Amount.class);

        PaymentOrder p1 = new PaymentOrder("PO-10", "EXT-A", PaymentOrder.StatusEnum.PENDING, debtor, creditor, amount);
        PaymentOrder p2 = new PaymentOrder("PO-10", "EXT-A", PaymentOrder.StatusEnum.PENDING, debtor, creditor, amount);

        assertEquals(p1, p2);
        assertEquals(p1.hashCode(), p2.hashCode());
    }

    @Test
    @DisplayName("toString debe contener campos importantes")
    void testToString() {
        PaymentOrder paymentOrder = new PaymentOrder();
        paymentOrder.setId("PO-123");

        String result = paymentOrder.toString();

        assertTrue(result.contains("PO-123"));
        assertTrue(result.contains("class PaymentOrder"));
    }
}

