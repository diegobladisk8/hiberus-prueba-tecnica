package org.openapitools.configuration;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.reactive.server.WebTestClient;

class HomeControllerTest {

    private final WebTestClient webTestClient = WebTestClient.bindToRouterFunction(
            new HomeController().index()
    ).build();

    @Test
    void index_shouldRedirectToSwagger() {
        webTestClient.get()
                .uri("/")
                .exchange()
                .expectStatus().is3xxRedirection()
                .expectHeader().valueEquals("Location", "swagger-ui.html");
    }
}
