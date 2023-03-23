package com.example.ejemplos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonaTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void testCreate() {
        var p = Persona.builder().id(1).nombre("Daniel").apellidos("Jaimes").build();
        assertNotNull(p);
        assertTrue(p instanceof Persona, "No es instancia de persona");

        assertAll("Validar propiedades",
                () -> assertEquals(p.toString(), "Persona(id=1, nombre=Daniel, apellidos=Jaimes)"),
                () -> assertEquals(1, p.getId()),
                () -> assertEquals("Daniel", p.getNombre()),
                () -> assertEquals("Jaimes", p.getApellidos())
        );
    }
}