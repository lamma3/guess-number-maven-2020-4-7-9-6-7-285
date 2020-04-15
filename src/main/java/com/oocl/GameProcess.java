package com.oocl;

import com.oocl.calculator.Calculator;
import com.oocl.exception.GuessNumberDuplicateNumberException;
import com.oocl.exception.GuessNumberInputSizeNotMatchException;
import com.oocl.generator.Generator;
import com.oocl.io.Input;
import com.oocl.io.Output;

import java.util.ArrayList;
import java.util.List;

public class GameProcess {

    private final static int ANSWER_SIZE = 4;
    private final static int MAX_ATTEMPT = 6;
    private final static String RETRY_MESSAGE = "Wrong Input, Input again";
    private final static String END_MESSAGE = "Game Over";
    private final static String SPACE_DELIMITER = " ";

    private Input input;
    private Output output;
    private Calculator calculator;
    private List<Integer> answer;
    private int attempt;

    public GameProcess(Calculator calculator, Generator generator, Input input, Output output) {
        this.input = input;
        this.output = output;
        this.calculator = calculator;
        this.answer = generator.generateAnswer(ANSWER_SIZE);
        this.attempt = 0;
    }

    public void play() {
        boolean gameover = false;
        while (!gameover) {
            String guess = input.get();
            List<Integer> guessNumberList = parseGuess(guess);
            try {
                String result = calculator.calculateFeedback(answer, guessNumberList);
                output.send(result);
                gameover = isGameOver(result);
            } catch (GuessNumberDuplicateNumberException | GuessNumberInputSizeNotMatchException e) {
                output.send(RETRY_MESSAGE);
            }
        }

        output.send(END_MESSAGE);
    }

    private List<Integer> parseGuess(String guess) {
        String[] guessSplitBySpace = guess.split(SPACE_DELIMITER);
        List<Integer> numbers = new ArrayList<>();
        for (String numberString: guessSplitBySpace) {
            int number = Integer.parseInt(numberString);
            numbers.add(number);
        }
        return numbers;
    }

    private boolean isGameOver(String result) {
        if (calculator.isWin(result, answer.size())) {
            return true;
        }
        return ++attempt >= MAX_ATTEMPT;
    }
}
