package com.hiberus.payment.application.usecase;

import com.hiberus.payment.application.dto.PaymentOrderRequest;
import com.hiberus.payment.application.dto.PaymentOrderResponse;
import reactor.core.publisher.Mono;

public interface CreatePaymentOrderUseCase {
    Mono<PaymentOrderResponse> execute(PaymentOrderRequest request);

}
