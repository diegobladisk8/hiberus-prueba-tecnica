package com.hiberus.payment.generated.api.model;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AccountTest {

    @Test
    @DisplayName("Debe crear Account usando el constructor obligatorio")
    void testRequiredConstructor() {
        Account account = new Account("EC123456789");

        assertEquals("EC123456789", account.getIban());
    }

    @Test
    @DisplayName("Debe permitir settear y obtener el IBAN correctamente")
    void testSetterGetter() {
        Account account = new Account();
        account.setIban("EC000111222");

        assertEquals("EC000111222", account.getIban());
    }

    @Test
    @DisplayName("Debe realizar equals y hashCode correctamente")
    void testEqualsAndHashCode() {
        Account a1 = new Account("ECIBAN01");
        Account a2 = new Account("ECIBAN01");
        Account a3 = new Account("ECIBAN99");

        assertEquals(a1, a2);
        assertEquals(a1.hashCode(), a2.hashCode());

        assertNotEquals(a1, a3);
        assertNotEquals(a1.hashCode(), a3.hashCode());
    }

    @Test
    @DisplayName("toString debe contener campos importantes")
    void testToString() {
        Account account = new Account("EC555");
        String text = account.toString();

        assertTrue(text.contains("Account"));
        assertTrue(text.contains("EC555"));
    }
}

