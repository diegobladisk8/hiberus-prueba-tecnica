package com.hiberus.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

/**
 * Main application class for the Payment Orders Service.
 * Implements hexagonal architecture with reactive programming.
 */
@SpringBootApplication
@EnableR2dbcRepositories
public class PaymentOrdersApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(PaymentOrdersApplication.class, args);
    }
}
