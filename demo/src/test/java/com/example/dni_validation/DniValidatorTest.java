package com.example.dni_validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DniValidatorTest {

    @BeforeEach
    void setUp() {

    }

    @ParameterizedTest(name = "Caso: {0}")
    @CsvSource(value = {"62902182B", "58972195B", "73685401W", "69112466L", "69112466L"})
    void validDNI(String dni) {
        assertTrue(DniValidator.validate(dni));
    }

    @ParameterizedTest(name = "Caso: {0}")
    @CsvSource(value = {"00000000Z", "12345678X", "69112466F", "00000000T", "99999999E"})
    @NullSource
    @EmptySource
    void invalidDNI(String dni) {
        assertFalse(DniValidator.validate(dni));
    }


}