package com.hiberus.payment.application.usecase;

import com.hiberus.payment.application.dto.PaymentOrderResponse;
import com.hiberus.payment.application.mapper.PaymentOrderMapper;
import com.hiberus.payment.domain.service.PaymentOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class GetPaymentOrderUseCase {

    private final PaymentOrderService paymentOrderService;
    private final PaymentOrderMapper paymentOrderMapper;

    public Mono<PaymentOrderResponse> execute(String id) {
        return paymentOrderService.getPaymentOrderById(id)
                .map(paymentOrderMapper::toResponse);
    }
}