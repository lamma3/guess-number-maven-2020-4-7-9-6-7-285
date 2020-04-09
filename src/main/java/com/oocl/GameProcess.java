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

    private final static int NUMBER_LIST_SIZE = 4;
    private final static int MAX_ATTEMPT = 6;
    private final static String RETRY_MESSAGE = "Wrong Input, Input again";
    private final static String END_MESSAGE = "Game Over";

    private Input input;
    private Output output;
    private Calculator calculator;
    private List<Integer> answer;

    public GameProcess(Calculator calculator, Generator generator, Input input, Output output) {
        this.input = input;
        this.output = output;
        this.calculator = calculator;
        this.answer = generator.generateAnswer(NUMBER_LIST_SIZE);
    }

    public void play() {
        boolean gameover = false;
        int attempt = 0;
        while (!gameover) {
            String guess = input.get();
            List<Integer> guessNumberList = parseGuess(guess);
            try {
                String result = calculator.calculateFeedback(answer, guessNumberList);
                output.send(result);
                if (calculator.isWin(result, NUMBER_LIST_SIZE)) {
                    gameover = true;
                }
                if (++attempt >= MAX_ATTEMPT) {
                    gameover = true;
                }
            } catch (GuessNumberDuplicateNumberException | GuessNumberInputSizeNotMatchException e) {
                output.send(RETRY_MESSAGE);
            }
        }

        output.send(END_MESSAGE);
    }

    private List<Integer> parseGuess(String guess) {
        String[] guessSplitBySpace = guess.split(" ");
        List<Integer> numberList = new ArrayList<>();
        for (String numString: guessSplitBySpace) {
            int num = Integer.parseInt(numString);
            numberList.add(num);
        }
        return numberList;
    }
}
