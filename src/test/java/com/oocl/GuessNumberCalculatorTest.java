package com.oocl;

import com.oocl.calculator.GuessNumberCalculator;
import com.oocl.exception.GuessNumberDuplicateNumberException;
import com.oocl.exception.GuessNumberInputSizeNotMatchException;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class GuessNumberCalculatorTest {

    private final static int NUMBER_LIST_SIZE = 4;
    private final static String WIN_RESULT = "4A0B";

    @Test
    public void test_calculateFeedback_when_noCorrect_then_return0A0B() {
        List<Integer> answer = new ArrayList<>();
        answer.add(1);
        answer.add(2);
        answer.add(3);
        answer.add(4);

        List<Integer> guess = new ArrayList<>();
        guess.add(5);
        guess.add(6);
        guess.add(7);
        guess.add(8);

        GuessNumberCalculator calculator = new GuessNumberCalculator();
        String result = calculator.calculateFeedback(answer, guess);
        Assert.assertEquals("0A0B", result);
    }

    @Test
    public void test_calculateFeedback_when_1CorrectButWrongPosition_then_return0A1B() {
        List<Integer> answer = new ArrayList<>();
        answer.add(1);
        answer.add(2);
        answer.add(3);
        answer.add(4);

        List<Integer> guess = new ArrayList<>();
        guess.add(5);
        guess.add(6);
        guess.add(7);
        guess.add(1);

        GuessNumberCalculator calculator = new GuessNumberCalculator();
        String result = calculator.calculateFeedback(answer, guess);
        Assert.assertEquals("0A1B", result);
    }

    @Test
    public void test_calculateFeedback_when_1CorrectPosition_then_return1A0B() {
        List<Integer> answer = new ArrayList<>();
        answer.add(1);
        answer.add(2);
        answer.add(3);
        answer.add(4);

        List<Integer> guess = new ArrayList<>();
        guess.add(1);
        guess.add(6);
        guess.add(7);
        guess.add(8);

        GuessNumberCalculator calculator = new GuessNumberCalculator();
        String result = calculator.calculateFeedback(answer, guess);
        Assert.assertEquals("1A0B", result);
    }

    @Test(expected = GuessNumberInputSizeNotMatchException.class)
    public void test_calculateFeedback_when_inputSizeNotMatch_then_throwGuessNumberInputSizeNotMatchException() {
        List<Integer> answer = new ArrayList<>();
        answer.add(1);

        List<Integer> guess = new ArrayList<>();
        guess.add(1);
        guess.add(2);

        GuessNumberCalculator calculator = new GuessNumberCalculator();
        calculator.calculateFeedback(answer, guess);
    }

    @Test(expected = GuessNumberDuplicateNumberException.class)
    public void test_calculateFeedback_when_answerContainsDuplicateNumber_then_throwGuessNumberDuplicateNumberException() {
        List<Integer> answer = new ArrayList<>();
        answer.add(1);
        answer.add(1);
        answer.add(1);
        answer.add(1);

        List<Integer> guess = new ArrayList<>();
        guess.add(5);
        guess.add(6);
        guess.add(7);
        guess.add(8);

        GuessNumberCalculator calculator = new GuessNumberCalculator();
        calculator.calculateFeedback(answer, guess);
    }

    @Test(expected = GuessNumberDuplicateNumberException.class)
    public void test_calculateFeedback_when_guessContainsDuplicateNumber_then_throwGuessNumberDuplicateNumberException() {
        List<Integer> answer = new ArrayList<>();
        answer.add(1);
        answer.add(2);
        answer.add(3);
        answer.add(4);

        List<Integer> guess = new ArrayList<>();
        guess.add(5);
        guess.add(5);
        guess.add(5);
        guess.add(5);

        GuessNumberCalculator calculator = new GuessNumberCalculator();
        calculator.calculateFeedback(answer, guess);
    }

    @Test
    public void test_isWin_when_win_then_returnTrue() {
        GuessNumberCalculator calculator = new GuessNumberCalculator();
        boolean result = calculator.isWin(WIN_RESULT, NUMBER_LIST_SIZE);
        Assert.assertTrue(result);
    }

    @Test
    public void test_isWin_when_lose_then_returnTrue() {
        GuessNumberCalculator calculator = new GuessNumberCalculator();
        boolean result = calculator.isWin("0A0B", NUMBER_LIST_SIZE);
        Assert.assertFalse(result);
    }
}
