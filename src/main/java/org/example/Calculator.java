package org.example;

public class Calculator {

    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        } else {
            return convertToInt(numbers);
        }
    }

    private int convertToInt(String num) {
        return Integer.parseInt(num);
    }
}
