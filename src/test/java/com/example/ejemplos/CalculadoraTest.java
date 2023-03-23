package com.example.ejemplos;

import com.example.core.test.Smoke;
import com.example.core.test.SpaceCamelCase;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class CalculadoraTest {
    private Calculadora calc;

    @BeforeEach
    void setUp() {
        calc = new Calculadora();
    }


    @Nested
    @DisplayName("Method Sum Test")
    @DisplayNameGeneration(SpaceCamelCase.class)
    class Suma {
        @Nested
        class OK {
            @Smoke
            @Test
            void testSuma() {
                var rslt = calc.suma(2, 2);
                assertEquals(4, rslt);
            }

            @Test
            void testSumaPositivoNegativo() {
                var rslt = calc.suma(3, -1);
                assertEquals(2, rslt);
            }

            @Test
            void testSumaNegativoPositivo() {
                var rslt = calc.suma(-3, 3);
                assertEquals(-0, rslt);
            }

            @Test
            void testSumaNegativoNegativo() {
                var rslt = calc.suma(-4, -3);
                assertEquals(-7, rslt);
            }

            @Test
            void testSumaDecimal() {
                var rslt = calc.suma(0.2, 0.3);
                assertEquals(0.5, rslt);
            }


            @Test
            void testSumasMultiple() {
                assertEquals(2, calc.suma(1, 1));
                assertEquals(0, calc.suma(-1, 1));
                assertEquals(0, calc.suma(1, -1));
                assertEquals(-2, calc.suma(-1, -1));
                assertEquals(0, calc.suma(0, 0));
            }

            @ParameterizedTest(name = "{1} + {2} = {3}")
            @CsvSource(value = {"1, 2, 3", "0.2, 0.2, 0.4", "0, 0, 0", "-1, 1, 0", "-1, -2, -3"})
            void testSumaOk(double op1, double op2, double rslt) {
                assertEquals(rslt, calc.suma(op1, op2));
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
