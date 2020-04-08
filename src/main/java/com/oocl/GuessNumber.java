package com.oocl;

import java.util.List;

public class GuessNumber {

    public String calculateFeedback(List<Integer> answer, List<Integer> guess) {
        int numOfCorrect = getNumberOfCorrectNumber(answer, guess);
        return formatResult(numOfCorrect);
    }

    private int getNumberOfCorrectNumber(List<Integer> answer, List<Integer> guess) {
        return (int) guess.stream()
                .filter(answer::contains)
                .count();
    }

    private String formatResult(int numOfCorrect) {
        return String.format("0A%dB", numOfCorrect);
    }
}
