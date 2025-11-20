package com.hiberus.payment.domain.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.URI;

import static org.junit.jupiter.api.Assertions.*;

class ErrorTest {

    @Test
    @DisplayName("Debe crear un Error usando el constructor con parámetros obligatorios")
    void testConstructorWithRequiredParams() {
        URI type = URI.create("https://example.com/errors/payment-order-not-found");
        String title = "Payment Order Not Found";
        Integer status = 404;

        Error error = new Error(type, title, status);

        assertEquals(type, error.getType());
        assertEquals(title, error.getTitle());
        assertEquals(status, error.getStatus());
        assertNull(error.getDetail());
        assertNull(error.getInstance());
    }

    @Test
    @DisplayName("Debe asignar valores usando setters y obtenerlos correctamente")
    void testGettersAndSetters() {
        Error error = new Error();

        URI type = URI.create("https://example.com/errors/invalid-request");
        String title = "Invalid Request";
        Integer status = 400;
        String detail = "The provided request payload is invalid";
        URI instance = URI.create("/payments/12345");

        error.setType(type);
        error.setTitle(title);
        error.setStatus(status);
        error.setDetail(detail);
        error.setInstance(instance);

        assertEquals(type, error.getType());
        assertEquals(title, error.getTitle());
        assertEquals(status, error.getStatus());
        assertEquals(detail, error.getDetail());
        assertEquals(instance, error.getInstance());
    }

    @Test
    @DisplayName("Debe soportar el uso fluido del builder")
    void testBuilderPatternMethods() {
        Error error = new Error()
                .type(URI.create("https://example.com/errors/server"))
                .title("Server Error")
                .status(500)
                .detail("Unexpected server error")
                .instance(URI.create("/process/xyz"));

        assertEquals("Server Error", error.getTitle());
        assertEquals(500, error.getStatus());
        assertEquals("Unexpected server error", error.getDetail());
        assertEquals("/process/xyz", error.getInstance().toString());
    }

    @Test
    @DisplayName("equals y hashCode deben funcionar correctamente")
    void testEqualsAndHashCode() {
        URI type = URI.create("https://example.com/errors/abc");
        String title = "Error ABC";
        Integer status = 409;

       Error error1 = new Error(type, title, status);
       Error error2 = new Error(type, title, status);

        assertEquals(error1, error2);
        assertEquals(error1.hashCode(), error2.hashCode());
    }

    @Test
    @DisplayName("toString debe contener los campos principales")
    void testToString() {
        Error error = new Error(
                URI.create("https://example.com/errors/test"),
                "Test Error",
                418
        ).detail("I'm a teapot");

        String str = error.toString();

        assertTrue(str.contains("Test Error"));
        assertTrue(str.contains("418"));
        assertTrue(str.contains("teapot"));
    }
}
