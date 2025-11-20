//package com.hiberus.payment.application.dto;
//
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//
//import com.hiberus.payment.generated.api.model.Account;
//import org.junit.jupiter.api.Test;
//
// class PaymentOrderResponseTest {
//
//    private Account validAccount() {
//        return Account.builder()
//                .iban("EC1212345678900000000001")
//                .build();
//    }
//
//    private PaymentOrderResponse.Amount validAmount() {
//        return PaymentOrderResponse.Amount.builder()
//                .amount(new BigDecimal("150.50"))
//                .currency("USD")
//                .build();
//    }
//
//    @Test
//    void testBuilderAndGetters() {
//        LocalDateTime now = LocalDateTime.now();
//
//        PaymentOrderResponse response = PaymentOrderResponse.builder()
//                .id("PO-0001")
//                .externalReference("EXT-777")
//                .status("PENDING")
//                .remittanceInformation("Service payment")
//                .requestedExecutionDate(now.minusDays(1))
//                .creationDate(now.minusDays(2))
//                .lastUpdateDate(now)
//                .build();
//
//        assertEquals("PO-0001", response.getId());
//        assertEquals("EXT-777", response.getExternalReference());
//        assertEquals("PENDING", response.getStatus());
//        assertNotNull(response.getDebtorAccount());
//        assertNotNull(response.getCreditorAccount());
//        assertEquals("EC1212345678900000000001", response.getDebtorAccount().getIban());
//        assertNotNull(response.getInstructedAmount());
//        assertEquals("USD", response.getInstructedAmount().getCurrency());
//        assertEquals("Service payment", response.getRemittanceInformation());
//        assertNotNull(response.getRequestedExecutionDate());
//        assertNotNull(response.getCreationDate());
//        assertNotNull(response.getLastUpdateDate());
//    }
//
//    @Test
//    void testSetters() {
//        PaymentOrderResponse response = new PaymentOrderResponse();
//
//        PaymentOrderResponse.Account account = new PaymentOrderResponse.Account();
//        account.setIban("EC9999999999999999999999");
//
//        PaymentOrderResponse.Amount amount = new PaymentOrderResponse.Amount();
//        amount.setAmount(new BigDecimal("10.00"));
//        amount.setCurrency("EUR");
//
//        LocalDateTime date = LocalDateTime.now();
//
//        response.setId("PO-1234");
//        response.setExternalReference("EXT-999");
//        response.setStatus("COMPLETED");
//        response.setDebtorAccount(account);
//        response.setCreditorAccount(account);
//        response.setInstructedAmount(amount);
//        response.setRemittanceInformation("Invoice payment");
//        response.setRequestedExecutionDate(date);
//        response.setCreationDate(date);
//        response.setLastUpdateDate(date);
//
//        assertEquals("PO-1234", response.getId());
//        assertEquals("EXT-999", response.getExternalReference());
//        assertEquals("COMPLETED", response.getStatus());
//        assertEquals("EC9999999999999999999999", response.getDebtorAccount().getIban());
//        assertEquals(new BigDecimal("10.00"), response.getInstructedAmount().getAmount());
//        assertEquals("EUR", response.getInstructedAmount().getCurrency());
//        assertEquals("Invoice payment", response.getRemittanceInformation());
//        assertEquals(date, response.getRequestedExecutionDate());
//        assertEquals(date, response.getCreationDate());
//        assertEquals(date, response.getLastUpdateDate());
//    }
//
//    @Test
//    void testEqualsAndHashcode() {
//        Account account = new Account(
//                "EC000");
//
//        Amount amount = new Amount(5D,  "USD");
//
//        PaymentOrderResponse r1 = PaymentOrderResponse.builder()
//                .id("X1")
//                .externalReference("E1")
//                .status("PROCESSING")
//                .debtorAccount(account)
//                .creditorAccount(account)
//                .instructedAmount(amount)
//                .build();
//
//        PaymentOrderResponse r2 = PaymentOrderResponse.builder()
//                .id("X1")
//                .externalReference("E1")
//                .status("PROCESSING")
//                .debtorAccount(account)
//                .creditorAccount(account)
//                .instructedAmount(amount)
//                .build();
//
//        assertEquals(r1, r2);
//        assertEquals(r1.hashCode(), r2.hashCode());
//    }
//
//    @Test
//    void testToStringNotNull() {
//        PaymentOrderResponse r = new PaymentOrderResponse();
//        assertNotNull(r.toString());
//    }
//}
//
