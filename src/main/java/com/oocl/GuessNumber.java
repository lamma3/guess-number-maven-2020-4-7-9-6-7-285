package com.oocl;

import java.util.List;
import java.util.stream.IntStream;

public class GuessNumber {

    public String calculateFeedback(List<Integer> answer, List<Integer> guess) {
        int numOfCorrectNumber = getNumberOfCorrectNumber(answer, guess);
        int numOfCorrectPosition = getNumberOfCorrectPosition(answer, guess);
        return formatResult(numOfCorrectPosition, numOfCorrectNumber - numOfCorrectPosition);
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
}
