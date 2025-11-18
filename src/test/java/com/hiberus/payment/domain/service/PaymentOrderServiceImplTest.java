package com.hiberus.payment.domain.service;



import com.hiberus.payment.domain.model.PaymentOrder;
import com.hiberus.payment.domain.repository.PaymentOrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

class PaymentOrderServiceImplTest {

    @Mock
    private PaymentOrderRepository paymentOrderRepository;

    @InjectMocks
    private PaymentOrderServiceImpl service;

    private PaymentOrder paymentOrder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        paymentOrder = PaymentOrder.builder()
                .id(null)
                .externalReference("REF-123")
                .debtorAccount("DEBTOR-IBAN-001")
                .creditorAccount("CREDITOR-IBAN-999")
                .amount(BigDecimal.TEN)
                .currency("USD")
                .remittanceInformation("Test payment")
                .requestedExecutionDate(LocalDateTime.now())
                .creationDate(null)
                .lastUpdateDate(null)
                .status(null)
                .build();
    }



    @Test
    void getPaymentOrderById_shouldReturnOrderWhenExists() {
        when(paymentOrderRepository.findById("PO-123")).thenReturn(Mono.just(paymentOrder));

        StepVerifier.create(service.getPaymentOrderById("PO-123"))
                .assertNext(result -> assertThat(result).isEqualTo(paymentOrder))
                .verifyComplete();
    }

    @Test
    void getPaymentOrderById_shouldReturnErrorWhenNotFound() {
        when(paymentOrderRepository.findById("PO-404")).thenReturn(Mono.empty());

        StepVerifier.create(service.getPaymentOrderById("PO-404"))
                .expectErrorMatches(ex -> ex instanceof RuntimeException
                        && ex.getMessage().equals("Payment order not found"))
                .verify();
    }


    @Test
    void getPaymentOrderStatus_shouldReturnErrorWhenNotFound() {
        when(paymentOrderRepository.findById("PO-987")).thenReturn(Mono.empty());

        StepVerifier.create(service.getPaymentOrderStatus("PO-987"))
                .expectErrorMatches(ex -> ex instanceof RuntimeException
                        && ex.getMessage().equals("Payment order not found"))
                .verify();
    }
}
