package com.hiberus.payment.domain;

import com.hiberus.payment.domain.model.PaymentOrderStatusEnum;
import com.hiberus.payment.domain.utils.PaymentOrderStatusReadConverter;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentOrderStatusReadConverterTest {

    private final PaymentOrderStatusReadConverter converter = new PaymentOrderStatusReadConverter();

    @Test
    void convert_shouldReturnEnumValue() {
        PaymentOrderStatusEnum result = converter.convert("PENDING");
        assertThat(result).isEqualTo(PaymentOrderStatusEnum.PENDING);
    }
}
