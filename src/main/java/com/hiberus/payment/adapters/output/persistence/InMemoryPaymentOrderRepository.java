package com.hiberus.payment.adapters.output.persistence;

import com.hiberus.payment.application.ports.output.PaymentOrderRepository;
import com.hiberus.payment.domain.model.PaymentOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * In-memory implementation of PaymentOrderRepository.
 * This is the output adapter for persistence in hexagonal architecture.
 * In production, this would be replaced with a database implementation.
 */
@Repository
@Slf4j
public class InMemoryPaymentOrderRepository implements PaymentOrderRepository {
    
    private final ConcurrentHashMap<UUID, PaymentOrder> storage = new ConcurrentHashMap<>();
    
    @Override
    public Mono<PaymentOrder> save(PaymentOrder paymentOrder) {
        log.debug("Saving payment order with id: {}", paymentOrder.getId());
        return Mono.fromCallable(() -> {
            storage.put(paymentOrder.getId(), paymentOrder);
            return paymentOrder;
        });
    }
    
    @Override
    public Mono<PaymentOrder> findById(UUID id) {
        log.debug("Finding payment order with id: {}", id);
        return Mono.fromCallable(() -> storage.get(id));
    }
}
