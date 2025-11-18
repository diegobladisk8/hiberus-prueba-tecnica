package com.hiberus.payment.domain.repository;

import com.hiberus.payment.domain.model.PaymentOrder;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentOrderRepository extends ReactiveCrudRepository<PaymentOrder, String> {

}
