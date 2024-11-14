package org.example;

public class Calculator {

    private static final String DELIMITER_REGX = "[\n,]";

    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        } else {
            String[] numList = splitNumbers(numbers);
            return sum(numList);
        }
    }

    private int convertToInt(String num) {
        return Integer.parseInt(num);
    }
    private String[] splitNumbers(String numbers) {
        return numbers.split(DELIMITER_REGX);
    }
    private int sum(String[] numbers) {
        int sum = 0;
        for (String number : numbers) {
            sum += convertToInt(number);
        }
        return sum;
    }
}
