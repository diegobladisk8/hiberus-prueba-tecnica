//package com.hiberus.payment.adapters.input.rest.controller;
//
//import com.hiberus.payment.adapters.input.rest.PaymentOrderRequest;
//import com.hiberus.payment.adapters.input.rest.PaymentOrderResponse;
//import com.hiberus.payment.adapters.input.rest..PaymentOrderStatusResponse;
//import com.hiberus.payment.adapters.input.rest.model.PaymentStatus;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.reactive.server.WebTestClient;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
///**
// * Integration test for Payment Order REST API endpoints.
// */
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class PaymentOrderControllerIntegrationTest {
//
//    @Autowired
//    private WebTestClient webTestClient;
//
//    @Test
//    void shouldCreatePaymentOrder() {
//        PaymentOrderRequest request = new PaymentOrderRequest();
//        request.setDebtorAccount("ES7921000813610123456789");
//        request.setCreditorAccount("ES1234567890123456789012");
//        request.setCreditorName("John Doe");
//        request.setAmount(100.50);
//        request.setCurrency("EUR");
//        request.setDescription("Test payment");
//
//        webTestClient.post()
//                .uri("/payment-initiation/payment-orders")
//                .contentType(MediaType.APPLICATION_JSON)
//                .bodyValue(request)
//                .exchange()
//                .expectStatus().isCreated()
//                .expectBody(PaymentOrderResponse.class)
//                .value(response -> {
//                    assertThat(response.getId()).isNotNull();
//                    assertThat(response.getDebtorAccount()).isEqualTo("ES7921000813610123456789");
//                    assertThat(response.getCreditorAccount()).isEqualTo("ES1234567890123456789012");
//                    assertThat(response.getCreditorName()).isEqualTo("John Doe");
//                    assertThat(response.getAmount()).isEqualTo(100.50);
//                    assertThat(response.getCurrency()).isEqualTo("EUR");
//                    assertThat(response.getDescription()).isEqualTo("Test payment");
//                    assertThat(response.getStatus()).isEqualTo(PaymentStatus.PENDING);
//                    assertThat(response.getCreatedAt()).isNotNull();
//                    assertThat(response.getUpdatedAt()).isNotNull();
//                });
//    }
//
//    @Test
//    void shouldGetPaymentOrderById() {
//        // Create a payment order first
//        PaymentOrderRequest request = new PaymentOrderRequest();
//        request.setDebtorAccount("ES7921000813610123456789");
//        request.setCreditorAccount("ES1234567890123456789012");
//        request.setCreditorName("Jane Doe");
//        request.setAmount(250.75);
//        request.setCurrency("EUR");
//        request.setDescription("Invoice payment");
//
//        PaymentOrderResponse createdOrder = webTestClient.post()
//                .uri("/payment-initiation/payment-orders")
//                .contentType(MediaType.APPLICATION_JSON)
//                .bodyValue(request)
//                .exchange()
//                .expectStatus().isCreated()
//                .expectBody(PaymentOrderResponse.class)
//                .returnResult()
//                .getResponseBody();
//
//        assertThat(createdOrder).isNotNull();
//
//        // Now retrieve it
//        webTestClient.get()
//                .uri("/payment-initiation/payment-orders/{id}", createdOrder.getId())
//                .exchange()
//                .expectStatus().isOk()
//                .expectBody(PaymentOrderResponse.class)
//                .value(response -> {
//                    assertThat(response.getId()).isEqualTo(createdOrder.getId());
//                    assertThat(response.getDebtorAccount()).isEqualTo("ES7921000813610123456789");
//                    assertThat(response.getCreditorAccount()).isEqualTo("ES1234567890123456789012");
//                    assertThat(response.getCreditorName()).isEqualTo("Jane Doe");
//                    assertThat(response.getAmount()).isEqualTo(250.75);
//                    assertThat(response.getCurrency()).isEqualTo("EUR");
//                    assertThat(response.getDescription()).isEqualTo("Invoice payment");
//                    assertThat(response.getStatus()).isEqualTo(PaymentStatus.PENDING);
//                });
//    }
//
//    @Test
//    void shouldGetPaymentOrderStatus() {
//        // Create a payment order first
//        PaymentOrderRequest request = new PaymentOrderRequest();
//        request.setDebtorAccount("ES7921000813610123456789");
//        request.setCreditorAccount("ES1234567890123456789012");
//        request.setAmount(500.00);
//        request.setCurrency("EUR");
//
//        PaymentOrderResponse createdOrder = webTestClient.post()
//                .uri("/payment-initiation/payment-orders")
//                .contentType(MediaType.APPLICATION_JSON)
//                .bodyValue(request)
//                .exchange()
//                .expectStatus().isCreated()
//                .expectBody(PaymentOrderResponse.class)
//                .returnResult()
//                .getResponseBody();
//
//        assertThat(createdOrder).isNotNull();
//
//        // Now get the status
//        webTestClient.get()
//                .uri("/payment-initiation/payment-orders/{id}/status", createdOrder.getId())
//                .exchange()
//                .expectStatus().isOk()
//                .expectBody(PaymentOrderStatusResponse.class)
//                .value(response -> {
//                    assertThat(response.getId()).isEqualTo(createdOrder.getId());
//                    assertThat(response.getStatus()).isEqualTo(PaymentStatus.PENDING);
//                    assertThat(response.getUpdatedAt()).isNotNull();
//                });
//    }
//
//    @Test
//    void shouldReturn404WhenPaymentOrderNotFound() {
//        webTestClient.get()
//                .uri("/payment-initiation/payment-orders/{id}", "550e8400-e29b-41d4-a716-446655440000")
//                .exchange()
//                .expectStatus().isNotFound();
//    }
//
//    @Test
//    void shouldReturn404WhenPaymentOrderStatusNotFound() {
//        webTestClient.get()
//                .uri("/payment-initiation/payment-orders/{id}/status", "550e8400-e29b-41d4-a716-446655440000")
//                .exchange()
//                .expectStatus().isNotFound();
//    }
//}
