package com.hiberus.payment.infrastructure.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payment_order_status", schema = "public")
public class PaymentOrderStatusEntity {

    @Id
    @Column("id")
    private String id;
    @Column("payment_order_id")
    private String paymentOrderId;
    @Column("status")
    private PaymentOrderStatusEnum status;
    @Column("last_update_date")
    private LocalDateTime lastUpdateDate;


}
