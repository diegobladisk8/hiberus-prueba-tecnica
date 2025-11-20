package com.hiberus.payment.domain.utils;

import com.hiberus.payment.domain.model.PaymentOrderStatusEnum;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;

@ReadingConverter
public class PaymentOrderStatusReadConverter implements Converter<String, PaymentOrderStatusEnum> {
    @Override
    public PaymentOrderStatusEnum convert(String source) {
        return PaymentOrderStatusEnum.valueOf(source);
    }
}



