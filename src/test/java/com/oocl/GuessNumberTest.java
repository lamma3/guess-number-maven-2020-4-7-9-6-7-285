package com.oocl;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GuessNumberTest {

    @Test
    public void test_calculateFeedback_when_noCorrect_then_return0A0B() {
        List<Integer> answer = new ArrayList<>();
        answer.add(0);
        answer.add(0);
        answer.add(0);
        answer.add(0);

        List<Integer> guess = new ArrayList<>();
        guess.add(1);
        guess.add(1);
        guess.add(1);
        guess.add(1);

        GuessNumber guessNumber = new GuessNumber();
        String result = guessNumber.calculateFeedback(answer, guess);
        Assert.assertEquals("0A0B", result);
    }

    @Test
    public void test_calculateFeedback_when_1CorrectButWrongPosition_then_return0A1B() {
        List<Integer> answer = new ArrayList<>();
        answer.add(0);
        answer.add(0);
        answer.add(0);
        answer.add(2);

        List<Integer> guess = new ArrayList<>();
        guess.add(1);
        guess.add(1);
        guess.add(1);
        guess.add(0);

        GuessNumber guessNumber = new GuessNumber();
        String result = guessNumber.calculateFeedback(answer, guess);
        Assert.assertEquals("0A1B", result);
    }
}
