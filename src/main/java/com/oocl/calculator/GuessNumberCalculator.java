package com.oocl.calculator;

import com.oocl.exception.GuessNumberDuplicateNumberException;
import com.oocl.exception.GuessNumberInputSizeNotMatchException;

import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;

public class GuessNumberCalculator implements Calculator {

    public String calculateFeedback(List<Integer> answer, List<Integer> guess) throws GuessNumberInputSizeNotMatchException, GuessNumberDuplicateNumberException {
        if (answer.size() != guess.size()) {
            throw new GuessNumberInputSizeNotMatchException();
        }
        if (containsDuplicateNumber(answer) || containsDuplicateNumber(guess)) {
            throw new GuessNumberDuplicateNumberException();
        }
        int numOfCorrectNumber = getNumberOfCorrectNumber(answer, guess);
        int numOfCorrectPosition = getNumberOfCorrectPosition(answer, guess);
        return formatResult(numOfCorrectPosition, numOfCorrectNumber - numOfCorrectPosition);
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

    private String formatResult(int numOfCorrectPosition, int numOfWrongPosition) {
        return String.format("%dA%dB", numOfCorrectPosition, numOfWrongPosition);
    }

    public boolean isWin(String result, int size) {
        return formatResult(size, 0).equals(result);
    }
}
