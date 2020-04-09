package com.oocl;

import com.oocl.io.ConsoleOutput;
import com.oocl.io.Output;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class ConsoleOutputTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void test_send_when_receiveContent_then_printToConsole() {
        Output output = new ConsoleOutput();
        output.send("1A1B");

        Assert.assertEquals("1A1B" + System.lineSeparator(), outContent.toString());
    }
}