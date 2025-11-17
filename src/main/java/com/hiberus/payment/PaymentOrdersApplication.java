package com.hiberus.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main application class for the Payment Orders Service.
 * Implements hexagonal architecture with reactive programming.
 */
@SpringBootApplication
public class PaymentOrdersApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(PaymentOrdersApplication.class, args);
    }
}
