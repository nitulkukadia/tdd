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

    @Test
    @DisplayName("Test Two Numbers")
    void testAddMultipleNumbers() {
        assertEquals(6, calculator.add("1,5"));
    }

    @Test
    @DisplayName("Test Multiple Numbers")
    void testAddAnyAmountOfNumbers() {
        assertEquals(55, calculator.add(String.join(",", "1,2,3,4,5,6,7,8,9,10")));
    }

    @Test
    @DisplayName("Test New Line As Delimiter")
    void testNewLine() {
        assertEquals(10, calculator.add("1\n2,3\n4"));
    }

    @Test
    @DisplayName("Test Custom Delimiter")
    void testOtherDelimiter() {
        assertEquals(3, calculator.add("//;\n1;2"));
    }

}
