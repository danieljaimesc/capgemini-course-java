package com.example.ejemplos;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class PersonaTest {

    @BeforeEach
    void setUp() {
    }

    @Nested
    @DisplayName("Method Constructor Test")
    public class Constructor {

        @Nested
        public class OK {
            @Test
            void testCreate() {
                var p = Persona.builder().id(1).nombre("Daniel").apellidos("Jaimes").build();
                assertNotNull(p);
                assertTrue(p instanceof Persona, "No es instancia de persona");

                assertAll("Validar propiedades", () -> assertEquals(1, p.getId()), () -> assertEquals("Daniel",
                        p.getNombre()), () -> assertEquals("Jaimes", p.getApellidos()));
            }

            @RepeatedTest(value = 5, name = "{displayName} {currentRepetition} / {totalRepetitions}")
            void repeatedTest(RepetitionInfo repetitionInfo) {
                var p = Persona.builder().id(repetitionInfo.getCurrentRepetition()).nombre("Daniel" + (repetitionInfo.getCurrentRepetition() % 3 == 0 ? "" : repetitionInfo.getCurrentRepetition())).apellidos("Jaimes").build();

                assertNotNull(p);
                assertTrue(p instanceof Persona, "No es instancia de persona");
                assertAll("Validar propiedades", () -> assertEquals(repetitionInfo.getCurrentRepetition(), p.getId(),
                        "id"), () -> assertEquals("Daniel" + repetitionInfo.getCurrentRepetition(), p.getNombre(),
                        "getNombre"), () -> assertEquals("Jaimes", p.getApellidos(), "getApellidos"));
            }
            //TODO - Personalized Test

        }

        @Nested
        public class KO {

        }
    }
}