package com.hiberus.payment.generated.api.model;

import com.hiberus.payment.generated.api.PaymentOrdersApi;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.server.MockServerWebExchange;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

class PaymentOrdersApiTest {

    private final PaymentOrdersApi api = new PaymentOrdersApi() {
    };

    @Test
    void initiatePaymentOrder_shouldReturnNotImplemented() {
        ServerWebExchange exchange = MockServerWebExchange.from(
                org.springframework.mock.http.server.reactive.MockServerHttpRequest.post("/payment-initiation/payment-orders")
        );

        Mono<ResponseEntity<PaymentOrder>> result = api.initiatePaymentOrder(Mono.empty(), exchange);

        StepVerifier.create(result)
                .verifyComplete();

        assert exchange.getResponse().getStatusCode() == HttpStatus.NOT_IMPLEMENTED;
    }

    @Test
    void retrievePaymentOrder_shouldReturnNotImplemented() {
        ServerWebExchange exchange = MockServerWebExchange.from(
                org.springframework.mock.http.server.reactive.MockServerHttpRequest.get("/payment-initiation/payment-orders/123")
        );

        Mono<ResponseEntity<PaymentOrder>> result = api.retrievePaymentOrder("123", exchange);

        StepVerifier.create(result)
                .verifyComplete();

        assert exchange.getResponse().getStatusCode() == HttpStatus.NOT_IMPLEMENTED;
    }

    @Test
    void retrievePaymentOrderStatus_shouldReturnNotImplemented() {
        ServerWebExchange exchange = MockServerWebExchange.from(
                org.springframework.mock.http.server.reactive.MockServerHttpRequest.get("/payment-initiation/payment-orders/123/status")
        );

        Mono<ResponseEntity<PaymentOrderStatus>> result = api.retrievePaymentOrderStatus("123", exchange);

        StepVerifier.create(result)
                .verifyComplete();

        assert exchange.getResponse().getStatusCode() == HttpStatus.NOT_IMPLEMENTED;
    }
}
