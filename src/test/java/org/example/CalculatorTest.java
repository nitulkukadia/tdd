package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest {

    Calculator calculator = new Calculator();

    @Test
    @DisplayName("Test Empty String")
    void testEmptyString() {
        assertEquals(0, calculator.add(""));
    }

    @Test
    @DisplayName("Test One Number")
    void testAddOneNumber() {
        assertEquals(1, calculator.add("1"));
    }
}
