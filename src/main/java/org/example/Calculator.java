package org.example;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {

    private static final String COMMA = ",";
    private static final String DEFAULT_DELIMITER_REGEX = "[\n,]";
    // This pattern is not working

    // private static final Pattern pattern =  Pattern.compile("^//( \\[ .* \\] ) \\n (.*)$");

    private static final Pattern pattern2 = Pattern.compile("^//\\[(.*)\\]\n(.*)$");
    private static final Pattern pattern1 = Pattern.compile("^//(.)\\n(.*)$");
    private static final int MAX_NUMBER = 1001;

    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        } else {
            String delimiterRegex;
            String numbersPart;
            Matcher matcher2 = pattern2.matcher(numbers);
            Matcher matcher1 = pattern1.matcher(numbers);
            if (matcher2.matches()) {
                delimiterRegex = matcher2.group(1); // Capturing the delimiter Regex
                numbersPart = matcher2.group(2); // Capturing the numbers part
            }else if (matcher1.matches()) {
                delimiterRegex = matcher1.group(1); // Capturing the delimiter Regex
                numbersPart = matcher1.group(2); // Capturing the numbers part
            } else {
                delimiterRegex = DEFAULT_DELIMITER_REGEX;
                numbersPart = numbers;
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
        boolean isNegativeNumberFound = false;
        StringBuilder negativeNumbers = new StringBuilder();
        for (String number : numbers) {
            int tempNumber = convertToInt(number);
            if(isNegative(tempNumber)){
                if(isNegativeNumberFound){
                    negativeNumbers.append(COMMA).append(number);
                }else{
                    negativeNumbers.append(number);
                    isNegativeNumberFound = true;
                }
            }else if(tempNumber < MAX_NUMBER){
                sum += tempNumber;
            }
        }
        if (isNegativeNumberFound) {
            throw new IllegalArgumentException("negative numbers not allowed: " + negativeNumbers);
        }
        return sum;
    }

    private boolean isNegative(int number) {
        return number < 0;
    }
}
