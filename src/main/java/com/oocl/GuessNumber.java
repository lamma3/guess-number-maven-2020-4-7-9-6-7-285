package com.oocl;

import com.oocl.exception.GuessNumberDuplicateNumberException;
import com.oocl.exception.GuessNumberInputSizeNotMatchException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

public class GuessNumber {

    private final static int MIN_RANDOM_NUMBER = 0;
    private final static int MAX_RANDOM_NUMBER = 9;

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

    public List<Integer> generateAnswer(int size) {
        List<Integer> numberList = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            Integer randomNumber = null;
            boolean isDistinct = false;
            while (!isDistinct) {
                randomNumber = generateRandomNumber();
                isDistinct = !numberList.contains(randomNumber);
            }
            numberList.add(randomNumber);
        }
        return numberList;
    }

    private int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt((MAX_RANDOM_NUMBER - MIN_RANDOM_NUMBER) + 1) + MIN_RANDOM_NUMBER;
    }
}
