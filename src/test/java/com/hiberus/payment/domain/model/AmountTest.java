package com.hiberus.payment.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
class AmountTest {

    @Test
    void testRequiredConstructor() {
        Amount amount = new Amount(150.75, "USD");

        assertEquals(150.75, amount.getAmount());
        assertEquals("USD", amount.getCurrency());
    }

    @Test
    void testSetterGetter() {
        Amount amount = new Amount();

        amount.setAmount(99.50);
        amount.setCurrency("EUR");

        assertEquals(99.50, amount.getAmount());
        assertEquals("EUR", amount.getCurrency());
    }

    @Test
    void testEqualsAndHashCode() {
        Amount a1 = new Amount(200.0, "USD");
        Amount a2 = new Amount(200.0, "USD");
        Amount a3 = new Amount(300.0, "USD");

        assertEquals(a1, a2);
        assertEquals(a1.hashCode(), a2.hashCode());

        assertNotEquals(a1, a3);
        assertNotEquals(a1.hashCode(), a3.hashCode());
    }

    @Test
    void testToString() {
        Amount amount = new Amount(75.10, "CLP");
        String text = amount.toString();

        assertTrue(text.contains("75.1"));
        assertTrue(text.contains("CLP"));
        assertTrue(text.contains("Amount"));
    }
}
