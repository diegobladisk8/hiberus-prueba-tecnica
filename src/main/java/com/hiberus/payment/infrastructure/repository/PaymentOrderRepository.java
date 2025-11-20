package com.hiberus.payment.infrastructure.repository;

import com.hiberus.payment.infrastructure.model.PaymentOrderEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentOrderRepository extends ReactiveCrudRepository<PaymentOrderEntity, String> {

}
