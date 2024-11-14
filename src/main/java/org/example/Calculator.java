package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {

    private static final String DEFAULT_DELIMITER_REGEX = "[\n,]";
    private static final Pattern pattern = Pattern.compile("^//(.)\\n(.*)$");
    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        } else {
            String delimiterRegex = DEFAULT_DELIMITER_REGEX;
            String numbersPart = numbers;
            Matcher matcher = pattern.matcher(numbers);
            if (matcher.matches()) {
                delimiterRegex = matcher.group(1); // Capturing the delimiter Regex
                numbersPart = matcher.group(2); // Capturing the numbers part
            }
            String[] numList = splitNumbers(numbersPart, delimiterRegex);
            return sum(numList);
        }
    }

    private int convertToInt(String num) {
        return Integer.parseInt(num);
    }
    private String[] splitNumbers(String numbers, String delimiterRegex) {
        return numbers.split(delimiterRegex);
    }
    private int sum(String[] numbers) {
        int sum = 0;
        for (String number : numbers) {
            sum += convertToInt(number);
        }
        return sum;
    }
}
