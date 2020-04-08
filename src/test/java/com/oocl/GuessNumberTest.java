package com.oocl;

import com.oocl.exception.GuessNumberDuplicateNumberException;
import com.oocl.exception.GuessNumberInputSizeNotMatchException;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class GuessNumberTest {

    private final static int NUMBER_LIST_SIZE = 4;
    private final static int MIN_RANDOM_NUMBER = 0;
    private final static int MAX_RANDOM_NUMBER = 9;

    @Test
    public void test_calculateFeedback_when_noCorrect_then_return0A0B() throws Exception {
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

        GuessNumber guessNumber = new GuessNumber();
        String result = guessNumber.calculateFeedback(answer, guess);
        Assert.assertEquals("0A0B", result);
    }

    @Test
    public void test_calculateFeedback_when_1CorrectButWrongPosition_then_return0A1B() throws Exception {
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

        GuessNumber guessNumber = new GuessNumber();
        String result = guessNumber.calculateFeedback(answer, guess);
        Assert.assertEquals("0A1B", result);
    }

    @Test
    public void test_calculateFeedback_when_1CorrectPosition_then_return1A0B() throws Exception {
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

        GuessNumber guessNumber = new GuessNumber();
        String result = guessNumber.calculateFeedback(answer, guess);
        Assert.assertEquals("1A0B", result);
    }

    @Test(expected = GuessNumberInputSizeNotMatchException.class)
    public void test_calculateFeedback_when_inputSizeNotMatch_then_throwGuessNumberInputSizeNotMatchException() throws Exception {
        List<Integer> answer = new ArrayList<>();
        answer.add(1);

        List<Integer> guess = new ArrayList<>();
        guess.add(1);
        guess.add(2);

        GuessNumber guessNumber = new GuessNumber();
        guessNumber.calculateFeedback(answer, guess);
    }

    @Test(expected = GuessNumberDuplicateNumberException.class)
    public void test_calculateFeedback_when_answerContainsDuplicateNumber_then_throwGuessNumberDuplicateNumberException() throws Exception {
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

        GuessNumber guessNumber = new GuessNumber();
        guessNumber.calculateFeedback(answer, guess);
    }

    @Test(expected = GuessNumberDuplicateNumberException.class)
    public void test_calculateFeedback_when_guessContainsDuplicateNumber_then_throwGuessNumberDuplicateNumberException() throws Exception {
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

        GuessNumber guessNumber = new GuessNumber();
        guessNumber.calculateFeedback(answer, guess);
    }

    @Test
    public void test_generateAnswer_when_called_then_returnDistinctRandomNumberFrom0To9() {
        GuessNumber guessNumber = new GuessNumber();
        List<Integer> numberList1 = guessNumber.generateAnswer(NUMBER_LIST_SIZE);
        List<Integer> numberList2 = guessNumber.generateAnswer(NUMBER_LIST_SIZE);

        for (Integer num: numberList1) {
            Assert.assertTrue(num >= MIN_RANDOM_NUMBER && num <= MAX_RANDOM_NUMBER);
        }
        Assert.assertEquals(NUMBER_LIST_SIZE, new HashSet<>(numberList1).size());
        Assert.assertNotEquals(numberList2, numberList1);
    }
}
