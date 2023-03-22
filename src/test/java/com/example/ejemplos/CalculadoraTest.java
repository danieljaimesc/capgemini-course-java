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

    @Test
    void testSumaPositivoNegativo() {
        var calc = new Calculadora();
        var rslt = calc.suma(3, -1);
        assertEquals(2, rslt);
    }

    @Test
    void testSumaNegativoPositivo() {
        var calc = new Calculadora();
        var rslt = calc.suma(-3, 3);
        assertEquals(-0, rslt);
    }

    @Test
    void testSumaNegativoNegativo() {
        var calc = new Calculadora();
        var rslt = calc.suma(-4, -3);
        assertEquals(-7, rslt);
    }

    @Test
    void testSumaDecimal() {
        var calc = new Calculadora();
        var rslt = calc.suma(0.2, 0.3);
        assertEquals(0.5, rslt);
    }
}
