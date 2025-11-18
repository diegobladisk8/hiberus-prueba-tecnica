package com.hiberus.payment.application.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentOrderStatusResponse {

    private String id;
    private String status;
    private LocalDateTime lastUpdateDate;
}
