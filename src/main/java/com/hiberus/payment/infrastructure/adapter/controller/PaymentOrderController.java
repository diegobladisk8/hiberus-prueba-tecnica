package com.hiberus.payment.infrastructure.adapter.controller;

import com.hiberus.payment.application.port.out.CreatePaymentOrderUseCase;
import com.hiberus.payment.application.usecase.GetPaymentOrderStatusUseCase;
import com.hiberus.payment.application.usecase.GetPaymentOrderUseCase;
import com.hiberus.payment.application.port.in.PaymentOrdersApi;
import com.hiberus.payment.generated.api.model.Account;
import com.hiberus.payment.generated.api.model.Amount;
import com.hiberus.payment.generated.api.model.PaymentOrder;
import com.hiberus.payment.generated.api.model.PaymentOrderRequest;
import com.hiberus.payment.generated.api.model.PaymentOrderStatus;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.ZoneOffset;
import java.util.Objects;

/**
 * Adaptador de controlador reactivo para exponer la API de Órdenes de Pago.
 * Implementa la interfaz PaymentOrdersApi y delega la lógica
 * a los casos de uso correspondientes en la capa de aplicación.
 *
 * NOTA: Se usan nombres de clase completos (fully qualified names) para los DTOs
 * de la capa de aplicación (ej. com.hiberus.payment.application.dto.PaymentOrderRequest)
 * para evitar conflictos de nombres con los DTOs generados por OpenAPI.
 */
@RestController
@RequiredArgsConstructor
@Slf4j
public class PaymentOrderController implements PaymentOrdersApi {

    private final CreatePaymentOrderUseCase createPaymentOrderUseCase;
    private final GetPaymentOrderUseCase getPaymentOrderUseCase;
    private final GetPaymentOrderStatusUseCase getPaymentOrderStatusUseCase;

    @Override
    public Mono<ResponseEntity<PaymentOrder>> initiatePaymentOrder(
            @Valid @RequestBody Mono<PaymentOrderRequest> paymentOrderRequest,
            ServerWebExchange exchange) {

        log.info("Received request to initiate a payment order.");

        return paymentOrderRequest
                .flatMap(this::convertToDomainRequest)
                .flatMap(createPaymentOrderUseCase::execute)
                .map(this::convertToGeneratedResponse)
                .map(response -> ResponseEntity
                        .created(URI.create("/payment-initiation/payment-orders/" + response.getId()))
                        .body(response))
                // Filter out any ResponseEntity that has a null body
                .filter(ResponseEntity::hasBody)
                .doOnSuccess(res -> log.info("Successfully created payment order with ID: {}", Objects.requireNonNull(res.getBody()).getId()))
                .doOnError(e -> log.error("Error initiating payment order", e));
    }

    @Override
    public Mono<ResponseEntity<PaymentOrder>> retrievePaymentOrder(
            String id, ServerWebExchange exchange) {

        log.info("Retrieving payment order with ID: {}", id);

        return getPaymentOrderUseCase.execute(id)
                .map(this::convertToGeneratedResponse)
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()))
                .doOnSuccess(res -> log.info("Successfully retrieved payment order with ID: {}", id))
                .doOnError(e -> log.error("Error retrieving payment order with ID: {}", id, e));
    }

    @Override
    public Mono<ResponseEntity<PaymentOrderStatus>> retrievePaymentOrderStatus(
            String id, ServerWebExchange exchange) {

        log.info("Retrieving status for payment order with ID: {}", id);

        return getPaymentOrderStatusUseCase.execute(id)
                .map(this::convertToGeneratedStatusResponse)
                .map(ResponseEntity::ok)
                .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()))
                .doOnSuccess(res -> log.info("Successfully retrieved status for payment order with ID: {}", id))
                .doOnError(e -> log.error("Error retrieving status for payment order with ID: {}", id, e));
    }

    // --- Métodos Privados de Mapeo (Adaptación) ---

    /**
     * Convierte el DTO generado por OpenAPI a nuestro DTO de aplicación para la creación.
     */
    private Mono<com.hiberus.payment.application.dto.PaymentOrderRequest> convertToDomainRequest(PaymentOrderRequest generatedRequest) {
        return Mono.just(com.hiberus.payment.application.dto.PaymentOrderRequest.builder()
                .externalReference(generatedRequest.getExternalReference())
                .debtorAccount(generatedRequest.getDebtorAccount())
                .creditorAccount(generatedRequest.getCreditorAccount())
                .instructedAmount(generatedRequest.getInstructedAmount())
                .remittanceInformation(generatedRequest.getRemittanceInformation())
                .requestedExecutionDate(generatedRequest.getRequestedExecutionDate())
                .build());
    }

    /**
     * Convierte nuestro DTO de aplicación al DTO generado por OpenAPI para la respuesta completa.
     */
    private PaymentOrder convertToGeneratedResponse(com.hiberus.payment.application.dto.PaymentOrderResponse response) {
        PaymentOrder generated = new PaymentOrder();
        generated.setId(response.getId());
        generated.setExternalReference(response.getExternalReference());
        generated.setStatus(PaymentOrder.StatusEnum.valueOf(response.getStatus()));

        Account debtorAccount = new Account();
        debtorAccount.setIban(response.getDebtorAccount().getIban());
        generated.setDebtorAccount(debtorAccount);

        Account creditorAccount = new Account();
        creditorAccount.setIban(response.getCreditorAccount().getIban());
        generated.setCreditorAccount(creditorAccount);

        Amount amount = new Amount();
        // Convertimos BigDecimal a Double para el DTO generado
        amount.setAmount(response.getInstructedAmount().getAmount());
        amount.setCurrency(response.getInstructedAmount().getCurrency());
        generated.setInstructedAmount(amount);

        generated.setRemittanceInformation(response.getRemittanceInformation());
        generated.setRequestedExecutionDate(response.getRequestedExecutionDate().toLocalDate());
        // Convertimos LocalDateTime a OffsetDateTime para el DTO generado
        generated.setCreationDate(response.getCreationDate().atOffset(ZoneOffset.UTC));
        generated.setLastUpdateDate(response.getLastUpdateDate().atOffset(ZoneOffset.UTC));

        return generated;
    }

    /**
     * Convierte nuestro DTO de estado de aplicación al DTO generado por OpenAPI.
     */
    private PaymentOrderStatus convertToGeneratedStatusResponse(com.hiberus.payment.application.dto.PaymentOrderStatusResponse response) {
        PaymentOrderStatus generated = new PaymentOrderStatus();
        generated.setId(response.getId());
        generated.setStatus(PaymentOrderStatus.StatusEnum.valueOf(response.getStatus()));
        // Convertimos LocalDateTime a OffsetDateTime para el DTO generado
        generated.setLastUpdateDate(response.getLastUpdateDate().atOffset(ZoneOffset.UTC));
        return generated;
    }
}