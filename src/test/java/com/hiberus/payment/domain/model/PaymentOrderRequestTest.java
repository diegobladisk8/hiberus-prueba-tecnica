package com.hiberus.payment.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PaymentOrderRequestTest {

    @Test
    @DisplayName("Constructor requerido debería inicializar los campos obligatorios")
    void testRequiredConstructor() {
        Account debtor = new Account();
        Account creditor = new Account();
        Amount amount = new Amount();

        PaymentOrderRequest request = new PaymentOrderRequest(
                "EXT-123",
                debtor,
                creditor,
                amount
        );

        assertEquals("EXT-123", request.getExternalReference());
        assertEquals(debtor, request.getDebtorAccount());
        assertEquals(creditor, request.getCreditorAccount());
        assertEquals(amount, request.getInstructedAmount());
        assertNull(request.getRemittanceInformation());
        assertNull(request.getRequestedExecutionDate());
    }

    @Test
    @DisplayName("Setter y Getter deberían asignar y retornar los valores correctamente")
    void testSettersAndGetters() {
        PaymentOrderRequest request = new PaymentOrderRequest();

        Account debtor = new Account();
        Account creditor = new Account();
        Amount amount = new Amount();
        LocalDate date = LocalDate.of(2025, 1, 20);

        request.setExternalReference("REF-1");
        request.setDebtorAccount(debtor);
        request.setCreditorAccount(creditor);
        request.setInstructedAmount(amount);
        request.setRemittanceInformation("Factura 001-123");
        request.setRequestedExecutionDate(date);

        assertEquals("REF-1", request.getExternalReference());
        assertEquals(debtor, request.getDebtorAccount());
        assertEquals(creditor, request.getCreditorAccount());
        assertEquals(amount, request.getInstructedAmount());
        assertEquals("Factura 001-123", request.getRemittanceInformation());
        assertEquals(date, request.getRequestedExecutionDate());
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
    @DisplayName("equals() debería retornar false para objetos diferentes")
    void testEqualsFalse() {
        PaymentOrderRequest r1 = new PaymentOrderRequest();
        r1.setExternalReference("A");

        PaymentOrderRequest r2 = new PaymentOrderRequest();
        r2.setExternalReference("B");

        assertNotEquals(r1, r2);
    }

    @Test
    @DisplayName("toString() debería contener los campos principales")
    void testToString() {
        PaymentOrderRequest request = new PaymentOrderRequest();
        request.setExternalReference("EXT-999");

        String result = request.toString();

        assertTrue(result.contains("EXT-999"));
        assertTrue(result.contains("PaymentOrderRequest"));
    }
}
