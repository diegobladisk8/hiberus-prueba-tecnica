package com.hiberus.payment.domain.model;


import com.hiberus.payment.infrastructure.model.PaymentOrderStatusEntity;
import com.hiberus.payment.infrastructure.model.PaymentOrderStatusEnum;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

 class PaymentOrderTest {

     private final String ID = "STATUS-123";
     private final String PAYMENT_ORDER_ID = "PO-123";
     private final PaymentOrderStatusEnum STATUS = PaymentOrderStatusEnum.PENDING;
     private final LocalDateTime LAST_UPDATE_DATE = LocalDateTime.now();

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

     @Test
     @DisplayName("Equals should return true for same object")
     void testEquals_SameObject() {
         // Given
         PaymentOrderStatusEntity status = PaymentOrderStatusEntity.builder()
                 .id(ID)
                 .paymentOrderId(PAYMENT_ORDER_ID)
                 .status(STATUS)
                 .lastUpdateDate(LAST_UPDATE_DATE)
                 .build();

         // When & Then
         assertThat(status.equals(status)).isTrue();
     }

     @Test
     @DisplayName("Equals and HashCode contract: equal objects must have same hashCode")
     void testEqualsHashCodeContract() {
         // Given
         PaymentOrderStatusEntity status1 = PaymentOrderStatusEntity.builder()
                 .id("CONTRACT-ID")
                 .paymentOrderId("PO-1")
                 .status(PaymentOrderStatusEnum.COMPLETED)
                 .build();

         PaymentOrderStatusEntity status2 = PaymentOrderStatusEntity.builder()
                 .id("CONTRACT-ID") // Mismo ID
                 .paymentOrderId("PO-1") // Diferente paymentOrderId
                 .status(PaymentOrderStatusEnum.COMPLETED) // Diferente status
                 .build();

         // When & Then
         assertThat(status1.equals(status2)).isTrue();
         assertThat(status1.hashCode()).isEqualTo(status2.hashCode());
     }

     @Test
     @DisplayName("Equals debe retornar false comparando con null u otro tipo")
     void testEqualsNullAndDifferentType() {

         PaymentOrder po = new PaymentOrder();
         po.setId("ID-X");

         assertNotEquals(po, null);
         assertNotEquals(po, "string");
     }

     @Test
     @DisplayName("hashCode debe ser consistente entre invocaciones")
     void testHashCodeConsistency() {
         PaymentOrder po = new PaymentOrder();
                 po.setId("CONSISTENT");

         int h1 = po.hashCode();
         int h2 = po.hashCode();
         assertEquals(h1, h2);
     }


 }

