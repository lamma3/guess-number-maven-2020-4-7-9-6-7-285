package com.oocl;

import com.oocl.exception.GuessNumberDuplicateNumberException;
import com.oocl.exception.GuessNumberGameOverException;
import com.oocl.exception.GuessNumberInputSizeNotMatchException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {

    private final static int NUMBER_LIST_SIZE = 4;
    private Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

    public void play(Calculator calculator, Generator generator) throws GuessNumberGameOverException, GuessNumberDuplicateNumberException, GuessNumberInputSizeNotMatchException {
        boolean gameover = false;
        List<Integer> answer = generator.generateAnswer(NUMBER_LIST_SIZE);
        while (!gameover) {
            String guess = scanner.nextLine();
            List<Integer> guessNumberList = parseGuess(guess);
            String result = calculator.calculateFeedback(answer, guessNumberList);
            if (calculator.isWin(result, NUMBER_LIST_SIZE)) {
                gameover = true;
            }
        }

        throw new GuessNumberGameOverException();
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
