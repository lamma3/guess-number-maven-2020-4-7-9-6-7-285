package com.oocl.calculator;

import java.util.List;

public interface Calculator {
    String calculateFeedback(List<Integer> answer, List<Integer> guess);
    boolean isWin(String result, int size);
}
