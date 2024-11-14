package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

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

    @Test
    @DisplayName("Test Negative Number With Default Delimiter")
    void testNegativeNumberWithDefaultDelimiter() {
        try {
            calculator.add("-1,2");
            fail("Should fail if one number is negative");
        } catch (IllegalArgumentException e) {
            assertEquals("negative numbers not allowed: -1", e.getMessage());
        }
        try {
            calculator.add("1,-2,3,-5");
            fail("Should fail if one multiple numbers are negative");
        } catch (IllegalArgumentException e) {
            assertEquals("negative numbers not allowed: -2,-5", e.getMessage());
        }
    }

    @Test
    @DisplayName("Test Negative Number With Custom Delimiter")
    void testNegativeNumberWithCustomDelimiter() {
        try {
            calculator.add("//;\n-1;2");
            fail("Should fail if one number is negative with custom delimiter");
        } catch (IllegalArgumentException e) {
            assertEquals("negative numbers not allowed: -1", e.getMessage());
        }
        try {
            calculator.add("//;\n1;-2;3;-5");
            fail("Should fail if one multiple numbers are negative with custom delimiter");
        } catch (IllegalArgumentException e) {
            assertEquals("negative numbers not allowed: -2,-5", e.getMessage());
        }
    }

}
