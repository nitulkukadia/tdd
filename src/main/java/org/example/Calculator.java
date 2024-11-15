package org.example;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {

    private static final String COMMA = ",";
    private static final String DEFAULT_DELIMITER_REGEX = "[\n,]";
    private static final Pattern pattern2 = Pattern.compile("^//\\[(.*)\\]\n(.*)$");
    private static final Pattern pattern1 = Pattern.compile("^//(.)\\n(.*)$");
    private static final int MAX_NUMBER = 1001;

    public int add(String numbers) {
        if (numbers.isEmpty()) {
            return 0;
        } else {
            String[] numList = getNumList(numbers);
            return sum(numList);
        }
    }

    private String[] getNumList(String numbers) {
        ExtractDelimiterAndNumbers result = getExtractDelimiterAndNumbers(numbers);
        if(result != null){
            return splitNumbers(result.numbersPart(), result.delimiterRegex());
        }
        return splitNumbers(numbers, DEFAULT_DELIMITER_REGEX);
    }

    private ExtractDelimiterAndNumbers getExtractDelimiterAndNumbers(String numbers) {
        ExtractDelimiterAndNumbers result;
        if ((result = parseNumbers(numbers, pattern1)) != null || (result = parseNumbers(numbers, pattern2)) != null) {
            return result;
        }
        return null;
    }

    private record ExtractDelimiterAndNumbers(String delimiterRegex, String numbersPart) {
    }

    private ExtractDelimiterAndNumbers parseNumbers(String numbers, Pattern pattern){
        Matcher matcher = pattern.matcher(numbers);
        if (matcher.matches()) {
            return new ExtractDelimiterAndNumbers( matcher.group(1), matcher.group(2));
        }
        return null;
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
