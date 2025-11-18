//package com.hiberus.payment.domain.repository;
//
//import com.hiberus.payment.domain.model.PaymentOrder;
//import com.hiberus.payment.domain.model.PaymentOrderStatusEnum;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.r2dbc.core.DatabaseClient;
//import org.springframework.test.context.ActiveProfiles;
//import reactor.test.StepVerifier;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//@SpringBootTest
//@ActiveProfiles("test")
//class PaymentOrderRepositoryTest {
//
//    @Autowired
//    private PaymentOrderRepository repository;
//
//    @Autowired
//    private DatabaseClient databaseClient;
//
//    @BeforeEach
//    void setup() {
//        databaseClient.sql("""
//            CREATE TABLE IF NOT EXISTS payment_order (
//                id VARCHAR(50) PRIMARY KEY,
//                external_reference VARCHAR(255),
//                status VARCHAR(50),
//                debtor_iban VARCHAR(50),
//                creditor_iban VARCHAR(50),
//                amount DECIMAL(19,2),
//                currency VARCHAR(10),
//                remittance_information VARCHAR(255),
//                requested_execution_date TIMESTAMP,
//                creation_date TIMESTAMP,
//                last_update_date TIMESTAMP
//            );
//            """).fetch().rowsUpdated().block();
//    }
//
//    @Test
//    void findByExternalReference_ShouldReturnRecord() {
//        PaymentOrder po = PaymentOrder.builder()
//                .id("ID-123")
//                .externalReference("EXT-001")
//                .status(PaymentOrderStatusEnum.PENDING)
//                .requestedExecutionDate(LocalDateTime.now())
//                .creationDate(LocalDateTime.now())
//                .lastUpdateDate(LocalDateTime.now())
//                .build();
//
//        StepVerifier.create(repository.save(po))
//                .expectNextMatches(saved -> saved.getId().equals("ID-123"))
//                .verifyComplete();
//
//        StepVerifier.create(repository.findByExternalReference("EXT-001"))
//                .expectNextMatches(result -> result.getExternalReference().equals("EXT-001"))
//                .verifyComplete();
//    }
//
//    @Test
//    void findByStatus_ShouldReturnMultipleRecords() {
//        List<PaymentOrder> orders = List.of(
//                buildOrder("1", PaymentOrderStatusEnum.PENDING),
//                buildOrder("2", PaymentOrderStatusEnum.PENDING),
//                buildOrder("3", PaymentOrderStatusEnum.COMPLETED)
//        );
//
//        StepVerifier.create(repository.saveAll(orders))
//                .expectNextCount(3)
//                .verifyComplete();
//
//        StepVerifier.create(repository.findByStatus(PaymentOrderStatusEnum.PENDING))
//                .expectNextCount(2)
//                .verifyComplete();
//    }
//
//    @Test
//    void findByRequestedExecutionDateBefore_ShouldReturnRecord() {
//        LocalDateTime now = LocalDateTime.now();
//
//        PaymentOrder order = buildOrder("44", PaymentOrderStatusEnum.PENDING);
//        order.setRequestedExecutionDate(now.minusDays(1));
//
//        StepVerifier.create(repository.save(order))
//                .expectNextCount(1)
//                .verifyComplete();
//
//        StepVerifier.create(repository.findByRequestedExecutionDateBefore(now))
//                .expectNextMatches(result -> result.getId().equals("44"))
//                .verifyComplete();
//    }
//
//    private PaymentOrder buildOrder(String id, PaymentOrderStatusEnum status) {
//        return PaymentOrder.builder()
//                .id(id)
//                .externalReference("EXT-" + id)
//                .status(status)
//                .requestedExecutionDate(LocalDateTime.now())
//                .creationDate(LocalDateTime.now())
//                .lastUpdateDate(LocalDateTime.now())
//                .build();
//    }
//}
