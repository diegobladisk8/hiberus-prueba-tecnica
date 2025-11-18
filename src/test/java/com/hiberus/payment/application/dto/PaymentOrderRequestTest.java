package com.hiberus.payment.application.dto;


import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.Set;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

 class PaymentOrderRequestTest {

    private static Validator validator;

    @BeforeAll
    static void setupValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    private PaymentOrderRequest.Account validAccount() {
        return PaymentOrderRequest.Account.builder()
                .iban("EC1212345678900000000001")
                .build();
    }

    private PaymentOrderRequest.Amount validAmount() {
        return PaymentOrderRequest.Amount.builder()
                .amount(150.75)
                .currency("USD")
                .build();
    }

    @Test
    @DisplayName("Debe construir correctamente un PaymentOrderRequest válido")
    void testValidPaymentOrderRequest() {
        PaymentOrderRequest request = PaymentOrderRequest.builder()
                .externalReference("EXT-12345")
                .debtorAccount(validAccount())
                .creditorAccount(validAccount())
                .instructedAmount(validAmount())
                .remittanceInformation("Payment for services")
                .requestedExecutionDate(LocalDate.of(2025, 1, 20))
                .build();

        Set<ConstraintViolation<PaymentOrderRequest>> violations = validator.validate(request);

        assertTrue(violations.isEmpty(), "No deben existir violaciones de validación");
        assertEquals("EXT-12345", request.getExternalReference());
        assertEquals("USD", request.getInstructedAmount().getCurrency());
    }

    @Test
    @DisplayName("Debe fallar cuando faltan campos obligatorios")
    void testMissingRequiredFields() {
        PaymentOrderRequest request = new PaymentOrderRequest(); // vacío

        Set<ConstraintViolation<PaymentOrderRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty(), "Debe tener violaciones por campos requeridos");

        assertTrue(
                violations.stream().anyMatch(v -> v.getMessage().contains("External reference is required"))
        );
        assertTrue(
                violations.stream().anyMatch(v -> v.getMessage().contains("Debtor account is required"))
        );
        assertTrue(
                violations.stream().anyMatch(v -> v.getMessage().contains("Creditor account is required"))
        );
        assertTrue(
                violations.stream().anyMatch(v -> v.getMessage().contains("Instructed amount is required"))
        );
    }

    @Test
    @DisplayName("Debe fallar cuando la cuenta no contiene IBAN")
    void testInvalidIban() {
        PaymentOrderRequest request = PaymentOrderRequest.builder()
                .externalReference("EXT-12345")
                .debtorAccount(new PaymentOrderRequest.Account("")) // inválido
                .creditorAccount(validAccount())
                .instructedAmount(validAmount())
                .build();

        Set<ConstraintViolation<PaymentOrderRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertTrue(
                violations.stream().anyMatch(v -> v.getMessage().contains("IBAN is required"))
        );
    }

    @Test
    @DisplayName("Debe fallar cuando Amount es negativo o nulo")
    void testInvalidAmount() {
        PaymentOrderRequest.Amount invalidAmt = new PaymentOrderRequest.Amount(null, "USD"); // amount null

        PaymentOrderRequest request = PaymentOrderRequest.builder()
                .externalReference("EXT-12345")
                .debtorAccount(validAccount())
                .creditorAccount(validAccount())
                .instructedAmount(invalidAmt)
                .build();

        Set<ConstraintViolation<PaymentOrderRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertTrue(
                violations.stream().anyMatch(v -> v.getMessage().contains("Amount is required"))
        );
    }

    @Test
    @DisplayName("Debe fallar cuando currency está vacío")
    void testInvalidCurrency() {
        PaymentOrderRequest.Amount invalidAmt = new PaymentOrderRequest.Amount(100.0, "");

        PaymentOrderRequest request = PaymentOrderRequest.builder()
                .externalReference("EXT-12345")
                .debtorAccount(validAccount())
                .creditorAccount(validAccount())
                .instructedAmount(invalidAmt)
                .build();

        Set<ConstraintViolation<PaymentOrderRequest>> violations = validator.validate(request);

        assertFalse(violations.isEmpty());
        assertTrue(
                violations.stream().anyMatch(v -> v.getMessage().contains("Currency is required"))
        );
    }
}
