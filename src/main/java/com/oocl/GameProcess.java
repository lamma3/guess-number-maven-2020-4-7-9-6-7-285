package com.oocl;

import com.oocl.exception.GuessNumberDuplicateNumberException;
import com.oocl.exception.GuessNumberInputSizeNotMatchException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameProcess {

    private final static int NUMBER_LIST_SIZE = 4;
    private final static int MAX_ATTEMPT = 6;
    private Scanner scanner = new Scanner(System.in);
    private final static String RETRY_MESSAGE = "Wrong Input, Input again";
    private final static String END_MESSAGE = "Game Over";

    public void play(Calculator calculator, Generator generator) {
        List<Integer> answer = generator.generateAnswer(NUMBER_LIST_SIZE);

        boolean gameover = false;
        int attempt = 0;
        while (!gameover && scanner.hasNextLine()) {
            String guess = scanner.nextLine();
            List<Integer> guessNumberList = parseGuess(guess);
            try {
                String result = calculator.calculateFeedback(answer, guessNumberList);
                System.out.println(result);
                if (calculator.isWin(result, NUMBER_LIST_SIZE)) {
                    gameover = true;
                }
                if (++attempt >= MAX_ATTEMPT) {
                    gameover = true;
                }
            } catch (GuessNumberDuplicateNumberException | GuessNumberInputSizeNotMatchException e) {
                System.out.println(RETRY_MESSAGE);
            }
        }

        System.out.println(END_MESSAGE);
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
