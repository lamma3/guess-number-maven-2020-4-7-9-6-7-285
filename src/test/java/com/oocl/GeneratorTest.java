package com.oocl;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;

public class GeneratorTest {

    private final static int NUMBER_LIST_SIZE = 4;
    private final static int MIN_RANDOM_NUMBER = 0;
    private final static int MAX_RANDOM_NUMBER = 9;

    @Test
    public void test_generateAnswer_when_called_then_returnDistinctRandomNumberFrom0To9() {
        Generator generator = new Generator();
        List<Integer> numberList1 = generator.generateAnswer(NUMBER_LIST_SIZE);
        List<Integer> numberList2 = generator.generateAnswer(NUMBER_LIST_SIZE);

        for (Integer num: numberList1) {
            Assert.assertTrue(num >= MIN_RANDOM_NUMBER && num <= MAX_RANDOM_NUMBER);
        }
        Assert.assertEquals(NUMBER_LIST_SIZE, new HashSet<>(numberList1).size());
        Assert.assertNotEquals(numberList2, numberList1);
    }
}
