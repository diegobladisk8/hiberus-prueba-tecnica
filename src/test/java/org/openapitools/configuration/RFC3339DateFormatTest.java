package org.openapitools.configuration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openapitools.RFC3339DateFormat;

import java.text.ParsePosition;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class RFC3339DateFormatTest {

    private final RFC3339DateFormat dateFormat = new RFC3339DateFormat();



    @Test
    @DisplayName("parse(): debería retornar null para una fecha inválida")
    void testParseInvalidRFC3339() {
        String input = "fecha-invalida";
        ParsePosition pos = new ParsePosition(0);

        Date result = dateFormat.parse(input, pos);

        assertNull(result, "Una fecha no válida debe retornar null");
    }

//    @Test
//    @DisplayName("clone(): debería retornar la misma instancia")
//    void testCloneReturnsSameInstance() {
//        Object cloned = dateFormat.clone();
//
//        assertSame(dateFormat, cloned,
//                "Según la implementación, clone() retorna this");
//    }
}

