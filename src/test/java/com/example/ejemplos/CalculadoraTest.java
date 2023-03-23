package com.example.ejemplos;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CalculadoraTest {
    private Calculadora calc;

    @BeforeEach
    void setUp() throws Exception {
        calc = new Calculadora();
    }


    @Nested
    @DisplayName("Method Sum Test")
    @DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
    class Suma {
        @Nested
        class OK {
            @Test
            void test_Suma() {
                var rslt = calc.suma(2, 2);
                assertEquals(4, rslt);
            }

            @Test
            void test_Suma_Positivo_Negativo() {
                var rslt = calc.suma(3, -1);
                assertEquals(2, rslt);
            }

            @Test
            void test_Suma_Negativo_Positivo() {
                var rslt = calc.suma(-3, 3);
                assertEquals(-0, rslt);
            }

            @Test
            void test_Suma_Negativo_Negativo() {
                var rslt = calc.suma(-4, -3);
                assertEquals(-7, rslt);
            }

            @Test
            void test_Suma_Decimal() {
                var rslt = calc.suma(0.2, 0.3);
                assertEquals(0.5, rslt);
            }
        }

        @Nested
        class KO {

        }
    }

    @Nested
    class Divide {
        @Nested
        class OK {
            @Test
            void testDividirPorCero() {
                var rslt = calc.divide(1, 0.0);
                assertEquals(Double.POSITIVE_INFINITY, rslt);
            }
        }

        @Nested
        class KO {
            @Test
            void testDividirPorCero() {
                assertThrows(ArithmeticException.class, () -> calc.divide(1, 0.0));
            }
        }
    }


}
