package com.hiberus.payment.domain.service;



import com.hiberus.payment.domain.model.PaymentOrder;
import com.hiberus.payment.domain.model.PaymentOrderStatusEnum;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PaymentOrderServiceImplTest {

    @Mock
    private PaymentOrderRepository paymentOrderRepository;

    @InjectMocks
    private PaymentOrderServiceImpl service;

    private PaymentOrder paymentOrder;



    @Test
    void createPaymentOrder_shouldGenerateIdAndSave() {
        PaymentOrder paymentOrder = PaymentOrder.builder()
                .externalReference("EXT-123")
                .debtorAccount("ACC-1")
                .creditorAccount("ACC-2")
                .amount(java.math.BigDecimal.TEN)
                .currency("USD")
                .remittanceInformation("Test")
                .requestedExecutionDate(LocalDateTime.now())
                .build();

        when(paymentOrderRepository.save(any(PaymentOrder.class)))
                .thenAnswer(invocation -> {
                    PaymentOrder saved = invocation.getArgument(0);
                    return Mono.just(saved);
                });

        Mono<PaymentOrder> result = service.createPaymentOrder(paymentOrder);

        StepVerifier.create(result)
                .assertNext(saved -> {
                    verify(paymentOrderRepository, times(1)).save(any(PaymentOrder.class));
                    assert saved.getId() != null;
                    assert saved.getStatus() == PaymentOrderStatusEnum.PENDING;
                    assert saved.getCreationDate() != null;
                    assert saved.getLastUpdateDate() != null;
                })
                .verifyComplete();
    }

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
