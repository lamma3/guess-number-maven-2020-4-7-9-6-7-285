package com.oocl;

import java.util.*;

public class GuessNumberGenerator implements Generator {

    private final static int MIN_RANDOM_NUMBER = 0;
    private final static int MAX_RANDOM_NUMBER = 9;

    public List<Integer> generateAnswer(int size) {
        Set<Integer> numberSet = new HashSet<>();
        while (numberSet.size() != size) {
            int randomNumber = generateRandomNumber();
            numberSet.add(randomNumber);
        }
        return new ArrayList<>(numberSet);
    }

    private int generateRandomNumber() {
        Random random = new Random();
        return random.nextInt((MAX_RANDOM_NUMBER - MIN_RANDOM_NUMBER) + 1) + MIN_RANDOM_NUMBER;
    }
}
