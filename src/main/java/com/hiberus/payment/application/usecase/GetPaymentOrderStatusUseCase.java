package com.hiberus.payment.application.usecase;

import com.hiberus.payment.application.dto.PaymentOrderStatusResponse;
import com.hiberus.payment.application.mapper.PaymentOrderMapper;
import com.hiberus.payment.domain.service.PaymentOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class GetPaymentOrderStatusUseCase {

    private final PaymentOrderService paymentOrderService;
    private final PaymentOrderMapper paymentOrderMapper;

    public Mono<PaymentOrderStatusResponse> execute(String id) {
        return paymentOrderService.getPaymentOrderStatus(id)
                .map(paymentOrderMapper::toStatusResponse);
    }
}
