package com.oocl;

import com.oocl.exception.GuessNumberDuplicateNumberException;
import com.oocl.exception.GuessNumberInputSizeNotMatchException;

import java.util.List;

public interface Calculator {
    String calculateFeedback(List<Integer> answer, List<Integer> guess) throws GuessNumberInputSizeNotMatchException, GuessNumberDuplicateNumberException;
    boolean isWin(String result, int size);
}
