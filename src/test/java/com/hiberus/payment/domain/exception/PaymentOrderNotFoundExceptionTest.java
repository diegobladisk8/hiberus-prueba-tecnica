package com.hiberus.payment.domain.exception;

import org.junit.jupiter.api.Test;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;

class PaymentOrderNotFoundExceptionTest {

    @Test
    void shouldGenerateCorrectMessage() {
        UUID id = UUID.randomUUID();
        PaymentOrderNotFoundException exception = new PaymentOrderNotFoundException(id);

        assertThat(exception.getMessage())
                .isEqualTo("Payment order not found with id: " + id);
    }
}