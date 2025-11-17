package com.hiberus.payment.adapters.input.rest.mapper;

import com.hiberus.payment.adapters.input.rest.model.PaymentOrderRequest;
import com.hiberus.payment.adapters.input.rest.model.PaymentOrderResponse;
import com.hiberus.payment.adapters.input.rest.model.PaymentOrderStatusResponse;
import com.hiberus.payment.adapters.input.rest.model.PaymentStatus;
import com.hiberus.payment.domain.model.PaymentOrder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

/**
 * Mapper for converting between REST DTOs and domain models.
 * Part of the REST adapter in hexagonal architecture.
 */
@Component
public class PaymentOrderMapper {
    
    /**
     * Maps a PaymentOrderRequest to a domain PaymentOrder.
     */
    public PaymentOrder toDomain(PaymentOrderRequest request) {
        return PaymentOrder.create(
                request.getDebtorAccount(),
                request.getCreditorAccount(),
                request.getCreditorName(),
                BigDecimal.valueOf(request.getAmount()),
                request.getCurrency(),
                request.getDescription()
        );
    }
    
    /**
     * Maps a domain PaymentOrder to a PaymentOrderResponse.
     */
    public PaymentOrderResponse toResponse(PaymentOrder paymentOrder) {
        PaymentOrderResponse response = new PaymentOrderResponse();
        response.setId(paymentOrder.getId());
        response.setDebtorAccount(paymentOrder.getDebtorAccount());
        response.setCreditorAccount(paymentOrder.getCreditorAccount());
        response.setCreditorName(paymentOrder.getCreditorName());
        response.setAmount(paymentOrder.getAmount().doubleValue());
        response.setCurrency(paymentOrder.getCurrency());
        response.setDescription(paymentOrder.getDescription());
        response.setStatus(mapStatus(paymentOrder.getStatus()));
        response.setCreatedAt(OffsetDateTime.ofInstant(paymentOrder.getCreatedAt(), ZoneOffset.UTC));
        response.setUpdatedAt(OffsetDateTime.ofInstant(paymentOrder.getUpdatedAt(), ZoneOffset.UTC));
        return response;
    }
    
    /**
     * Maps a domain PaymentOrder to a PaymentOrderStatusResponse.
     */
    public PaymentOrderStatusResponse toStatusResponse(PaymentOrder paymentOrder) {
        PaymentOrderStatusResponse response = new PaymentOrderStatusResponse();
        response.setId(paymentOrder.getId());
        response.setStatus(mapStatus(paymentOrder.getStatus()));
        response.setUpdatedAt(OffsetDateTime.ofInstant(paymentOrder.getUpdatedAt(), ZoneOffset.UTC));
        return response;
    }
    
    /**
     * Maps domain PaymentStatus to REST PaymentStatus.
     */
    private PaymentStatus mapStatus(com.hiberus.payment.domain.model.PaymentStatus status) {
        return PaymentStatus.valueOf(status.name());
    }
}
