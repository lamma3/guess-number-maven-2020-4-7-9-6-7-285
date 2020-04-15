package com.oocl.calculator;

import com.oocl.exception.GuessNumberDuplicateNumberException;
import com.oocl.exception.GuessNumberInputSizeNotMatchException;

import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;

public class GuessNumberCalculator implements Calculator {

    private static final String RESULT_FORMAT = "%dA%dB";

    public String calculateFeedback(List<Integer> answer, List<Integer> guess) {
        if (answer.size() != guess.size()) {
            throw new GuessNumberInputSizeNotMatchException();
        }
        if (containsDuplicateNumber(answer) || containsDuplicateNumber(guess)) {
            throw new GuessNumberDuplicateNumberException();
        }
        int numberOfCorrectNumber = getNumberOfCorrectNumber(answer, guess);
        int numberOfCorrectPosition = getNumberOfCorrectPosition(answer, guess);
        return formatResult(numberOfCorrectPosition, numberOfCorrectNumber - numberOfCorrectPosition);
    }

    private boolean containsDuplicateNumber(List<Integer> numberList) {
        return (new HashSet<>(numberList).size()) != numberList.size();
    }

    private int getNumberOfCorrectNumber(List<Integer> answer, List<Integer> guess) {
        return (int) guess.stream()
                .filter(answer::contains)
                .count();
    }

    private int getNumberOfCorrectPosition(List<Integer> answer, List<Integer> guess) {
        return (int) IntStream.range(0, guess.size())
                .filter(index -> guess.get(index).equals(answer.get(index)))
                .count();
    }

    private String formatResult(int numberOfCorrectPosition, int numberOfWrongPosition) {
        return String.format(RESULT_FORMAT, numberOfCorrectPosition, numberOfWrongPosition);
    }

    public boolean isWin(String result, int size) {
        return formatResult(size, 0).equals(result);
    }
}
