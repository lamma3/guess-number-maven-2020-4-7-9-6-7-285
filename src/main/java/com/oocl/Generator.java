package com.oocl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Generator {

    private final static int MIN_RANDOM_NUMBER = 0;
    private final static int MAX_RANDOM_NUMBER = 9;

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
