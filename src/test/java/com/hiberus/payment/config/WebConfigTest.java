package com.hiberus.payment.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.web.reactive.function.client.WebClient;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class WebConfigTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void webClientBean_ShouldBeCreated() {
        WebClient webClient = applicationContext.getBean(WebClient.class);

        assertThat(webClient)
                .isNotNull();
    }

    @Test
    void webClientBean_ShouldBeSingleton() {
        WebClient webClient1 = applicationContext.getBean(WebClient.class);
        WebClient webClient2 = applicationContext.getBean(WebClient.class);

        assertThat(webClient1)
                .isSameAs(webClient2);
    }
}
