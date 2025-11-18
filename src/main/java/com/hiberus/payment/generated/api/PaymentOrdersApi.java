package com.hiberus.payment.generated.api;

import com.hiberus.payment.generated.api.model.PaymentOrder;
import com.hiberus.payment.generated.api.model.PaymentOrderRequest;
import com.hiberus.payment.generated.api.model.PaymentOrderStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;


public interface PaymentOrdersApi {

    /**
     * POST /payment-initiation/payment-orders : Initiate a payment order
     *
     * @param paymentOrderRequest Payment order to be created (required)
     * @param exchange ServerWebExchange
     * @return {@link Mono}<{@link ResponseEntity}<{@link PaymentOrder}>>
     */
    @PostMapping(
            value = "/payment-initiation/payment-orders",
            produces = { "application/json" },
            consumes = { "application/json" }
    )
    default Mono<ResponseEntity<PaymentOrder>> initiatePaymentOrder(
            @Valid @RequestBody Mono<PaymentOrderRequest> paymentOrderRequest,
            ServerWebExchange exchange) {
        // Implementación por defecto que devuelve "No Implementado"
        exchange.getResponse().setStatusCode(org.springframework.http.HttpStatus.NOT_IMPLEMENTED);
        return exchange.getResponse().setComplete().then(Mono.empty());
    }

    /**
     * GET /payment-initiation/payment-orders/{id} : Retrieve a payment order
     *
     * @param id ID de la orden de pago a recuperar (required)
     * @param exchange ServerWebExchange
     * @return {@link Mono}<{@link ResponseEntity}<{@link PaymentOrder}>>
     */
    @GetMapping(
            value = "/payment-initiation/payment-orders/{id}",
            produces = { "application/json" }
    )
    default Mono<ResponseEntity<PaymentOrder>> retrievePaymentOrder(
            @NotNull @PathVariable("id") String id,
            ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(org.springframework.http.HttpStatus.NOT_IMPLEMENTED);
        return exchange.getResponse().setComplete().then(Mono.empty());
    }

    /**
     * GET /payment-initiation/payment-orders/{id}/status : Retrieve status of a payment order
     *
     * @param id ID de la orden de pago a consultar (required)
     * @param exchange ServerWebExchange
     * @return {@link Mono}<{@link ResponseEntity}<{@link PaymentOrderStatus}>>
     */
    @GetMapping(
            value = "/payment-initiation/payment-orders/{id}/status",
            produces = { "application/json" }
    )
    default Mono<ResponseEntity<PaymentOrderStatus>> retrievePaymentOrderStatus(
            @NotNull @PathVariable("id") String id,
            ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(org.springframework.http.HttpStatus.NOT_IMPLEMENTED);
        return exchange.getResponse().setComplete().then(Mono.empty());
    }
}