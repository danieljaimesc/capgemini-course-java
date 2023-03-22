package com.example.ejemplos;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CalculadoraTest {

    @BeforeEach
    void setUp() throws Exception {

    }

    @Test
    void testSuma() {
        var calc = new Calculadora();
        var rslt = calc.suma(2, 2);
        assertEquals(4, rslt);
    }
}
