package com.oocl;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class ConsoleInputTest {

    @Test
    public void test_get_when_input_then_return_input() {
        InputStream inputStream = new ByteArrayInputStream("1 2 3 4".getBytes());
        System.setIn(inputStream);
        Input input = new ConsoleInput();
        String result = input.get();
        Assert.assertEquals("1 2 3 4", result);
    }
}
