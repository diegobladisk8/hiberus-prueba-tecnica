package com.hiberus.payment.adapters.input.rest.controller;

import com.hiberus.payment.adapters.input.rest.api.PaymentOrdersApi;
import com.hiberus.payment.adapters.input.rest.mapper.PaymentOrderMapper;
import com.hiberus.payment.adapters.input.rest.model.PaymentOrderRequest;
import com.hiberus.payment.adapters.input.rest.model.PaymentOrderResponse;
import com.hiberus.payment.adapters.input.rest.model.PaymentOrderStatusResponse;
import com.hiberus.payment.application.ports.input.PaymentOrderUseCase;
import com.hiberus.payment.domain.model.PaymentOrder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;

/**
 * REST controller implementing the PaymentOrders API.
 * This is the input adapter in hexagonal architecture.
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class PaymentOrderController implements PaymentOrdersApi {
    
    private final PaymentOrderUseCase paymentOrderUseCase;
    private final PaymentOrderMapper mapper;
    
    @Override
    public Mono<ResponseEntity<PaymentOrderResponse>> createPaymentOrder(
            Mono<PaymentOrderRequest> paymentOrderRequest,
            ServerWebExchange exchange) {
        
        log.info("Received request to create payment order");
        
        return paymentOrderRequest
                .map(mapper::toDomain)
                .flatMap(paymentOrderUseCase::createPaymentOrder)
                .map(mapper::toResponse)
                .map(response -> ResponseEntity.status(HttpStatus.CREATED).body(response))
                .doOnSuccess(response -> log.info("Payment order created successfully"))
                .doOnError(error -> log.error("Error creating payment order", error));
    }
    
    @Override
    public Mono<ResponseEntity<PaymentOrderResponse>> getPaymentOrderById(
            UUID id,
            ServerWebExchange exchange) {
        
        log.info("Received request to get payment order by id: {}", id);
        
        return paymentOrderUseCase.getPaymentOrderById(id)
                .map(mapper::toResponse)
                .map(ResponseEntity::ok)
                .doOnSuccess(response -> log.info("Payment order retrieved successfully"))
                .doOnError(error -> log.error("Error retrieving payment order", error));
    }
    
    @Override
    public Mono<ResponseEntity<PaymentOrderStatusResponse>> getPaymentOrderStatus(
            UUID id,
            ServerWebExchange exchange) {
        
        log.info("Received request to get payment order status for id: {}", id);
        
        return paymentOrderUseCase.getPaymentOrderStatus(id)
                .map(mapper::toStatusResponse)
                .map(ResponseEntity::ok)
                .doOnSuccess(response -> log.info("Payment order status retrieved successfully"))
                .doOnError(error -> log.error("Error retrieving payment order status", error));
    }
}
