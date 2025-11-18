package org.openapitools.configuration;


import com.fasterxml.jackson.databind.Module;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class OpenApiGeneratorApplicationTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void contextLoads() {
        // Valida que el contexto inicie correctamente
        assertThat(applicationContext).isNotNull();
    }

    @Test
    void jsonNullableModuleBeanIsRegistered() {
        // Verifica que el bean esté presente en el contexto con el nombre definido
        Module module = applicationContext.getBean("org.openapitools.OpenApiGeneratorApplication.jsonNullableModule", Module.class);
        assertThat(module).isNotNull();
    }
}
