package com.hiberus.payment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class H2ConsoleWebFluxConfiguration {

    @Bean
    public RouterFunction<ServerResponse> h2ConsoleRouter() {
        return RouterFunctions
                .resources("/h2-console/**",
                        new ClassPathResource("org/h2/server/web/"));
    }
}