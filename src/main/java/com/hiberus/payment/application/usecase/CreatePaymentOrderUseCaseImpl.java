package com.hiberus.payment.application.usecase;

import com.hiberus.payment.application.dto.PaymentOrderRequest;
import com.hiberus.payment.application.dto.PaymentOrderResponse;
import com.hiberus.payment.application.mapper.PaymentOrderMapper;
import com.hiberus.payment.domain.model.PaymentOrder;
import com.hiberus.payment.domain.service.PaymentOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class CreatePaymentOrderUseCaseImpl implements CreatePaymentOrderUseCase {

    private final PaymentOrderService paymentOrderService;
    private final PaymentOrderMapper paymentOrderMapper;

    public Mono<PaymentOrderResponse> execute(PaymentOrderRequest request) {
        PaymentOrder paymentOrder = paymentOrderMapper.toDomain(request);
        return paymentOrderService.createPaymentOrder(paymentOrder)
                .map(paymentOrderMapper::toResponse);
    }
}