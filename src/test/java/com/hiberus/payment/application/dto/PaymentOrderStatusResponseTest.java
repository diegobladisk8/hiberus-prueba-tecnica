package com.hiberus.payment.application.dto;


import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;

public class PaymentOrderStatusResponseTest {

    @Test
    void testBuilderAndGetters() {
        LocalDateTime now = LocalDateTime.now();

        PaymentOrderStatusResponse response = PaymentOrderStatusResponse.builder()
                .id("PO-1001")
                .status("PENDING")
                .lastUpdateDate(now)
                .build();

        assertEquals("PO-1001", response.getId());
        assertEquals("PENDING", response.getStatus());
        assertEquals(now, response.getLastUpdateDate());
    }

    @Test
    void testSetters() {
        PaymentOrderStatusResponse response = new PaymentOrderStatusResponse();
        LocalDateTime date = LocalDateTime.now();

        response.setId("PO-2002");
        response.setStatus("COMPLETED");
        response.setLastUpdateDate(date);

        assertEquals("PO-2002", response.getId());
        assertEquals("COMPLETED", response.getStatus());
        assertEquals(date, response.getLastUpdateDate());
    }

    @Test
    void testEqualsAndHashcode() {
        LocalDateTime date = LocalDateTime.now();

        PaymentOrderStatusResponse r1 = PaymentOrderStatusResponse.builder()
                .id("X1")
                .status("PROCESSING")
                .lastUpdateDate(date)
                .build();

        PaymentOrderStatusResponse r2 = PaymentOrderStatusResponse.builder()
                .id("X1")
                .status("PROCESSING")
                .lastUpdateDate(date)
                .build();

        assertEquals(r1, r2);
        assertEquals(r1.hashCode(), r2.hashCode());
    }

    @Test
    void testToStringNotNull() {
        PaymentOrderStatusResponse r = new PaymentOrderStatusResponse();
        assertNotNull(r.toString());
    }
}

