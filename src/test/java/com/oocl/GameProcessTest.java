package com.oocl;

import com.oocl.calculator.GuessNumberCalculator;
import com.oocl.exception.GuessNumberDuplicateNumberException;
import com.oocl.exception.GuessNumberInputSizeNotMatchException;
import com.oocl.generator.GuessNumberGenerator;
import com.oocl.io.ConsoleOutput;
import com.oocl.io.Input;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

public class GameProcessTest {

    private final InputStream originalIn = System.in;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setIn(originalIn);
        System.setOut(originalOut);
    }

    private void input(String str) {
        InputStream inputStream = new ByteArrayInputStream(str.getBytes());
        System.setIn(inputStream);
    }

    @Test
    public void test_play_when_win_then_throwGuessNumberGameOverException() throws Exception {
        GuessNumberCalculator calculator = Mockito.mock(GuessNumberCalculator.class);
        Mockito.when(calculator.calculateFeedback(Mockito.anyListOf(Integer.class), Mockito.anyListOf(Integer.class)))
                .thenReturn("4A0B");
        Mockito.when(calculator.isWin(Mockito.anyString(), Mockito.anyInt()))
                .thenReturn(true);

        Input input = Mockito.mock(Input.class);
        Mockito.when(input.get())
                .thenReturn("1 2 3 4");

        GameProcess gameProcess = new GameProcess(calculator, new GuessNumberGenerator(), input, new ConsoleOutput());
        gameProcess.play();
        Assert.assertEquals("4A0B" + System.lineSeparator() + "Game Over" + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void test_play_when_lose_then_throwGuessNumberGameOverException() throws Exception {
        GuessNumberCalculator calculator = Mockito.mock(GuessNumberCalculator.class);
        Mockito.when(calculator.calculateFeedback(Mockito.anyListOf(Integer.class), Mockito.anyListOf(Integer.class)))
                .thenReturn("4A0B");
        Mockito.when(calculator.isWin(Mockito.anyString(), Mockito.anyInt()))
                .thenReturn(false);

        Input input = Mockito.mock(Input.class);
        Mockito.when(input.get())
                .thenReturn("1 2 3 4");

        GameProcess gameProcess = new GameProcess(calculator, new GuessNumberGenerator(), input, new ConsoleOutput());
        gameProcess.play();
        String expected = "4A0B" + System.lineSeparator() +
                "4A0B" + System.lineSeparator() +
                "4A0B" + System.lineSeparator() +
                "4A0B" + System.lineSeparator() +
                "4A0B" + System.lineSeparator() +
                "4A0B" + System.lineSeparator() +
                "Game Over" + System.lineSeparator();
        Assert.assertEquals(expected, outContent.toString());
    }

    @Test
    public void test_play_when_input_then_returnResult() throws Exception {
        GuessNumberCalculator calculator = Mockito.mock(GuessNumberCalculator.class);
        Mockito.when(calculator.calculateFeedback(Mockito.anyListOf(Integer.class), Mockito.anyListOf(Integer.class)))
                .thenReturn("4A0B");
        Mockito.when(calculator.isWin(Mockito.anyString(), Mockito.anyInt()))
                .thenReturn(true);

        Input input = Mockito.mock(Input.class);
        Mockito.when(input.get())
                .thenReturn("1 2 3 4");

        GameProcess gameProcess = new GameProcess(calculator, new GuessNumberGenerator(), input, new ConsoleOutput());
        gameProcess.play();
        Assert.assertEquals("4A0B" + System.lineSeparator() + "Game Over" + System.lineSeparator(), outContent.toString());
    }

    @Test
    public void test_play_when_GetGuessNumberDuplicateNumberException_then_printError() throws Exception {
        GuessNumberCalculator calculator = Mockito.mock(GuessNumberCalculator.class);
        Mockito.when(calculator.calculateFeedback(Mockito.anyListOf(Integer.class), Mockito.anyListOf(Integer.class)))
                .thenThrow(new GuessNumberDuplicateNumberException())
                .thenReturn("4A0B");
        Mockito.when(calculator.isWin(Mockito.anyString(), Mockito.anyInt()))
                .thenReturn(true);

        Input input = Mockito.mock(Input.class);
        Mockito.when(input.get())
                .thenReturn("1 2 3 4");

        GameProcess gameProcess = new GameProcess(calculator, new GuessNumberGenerator(), input, new ConsoleOutput());
        gameProcess.play();

        String expected = "Wrong Input, Input again" + System.lineSeparator() +
                "4A0B" + System.lineSeparator() +
                "Game Over" + System.lineSeparator();
        Assert.assertEquals(expected, outContent.toString());
    }

    @Test
    public void test_play_when_GetGuessNumberInputSizeNotMatchException_then_printError() throws Exception {
        GuessNumberCalculator calculator = Mockito.mock(GuessNumberCalculator.class);
        Mockito.when(calculator.calculateFeedback(Mockito.anyListOf(Integer.class), Mockito.anyListOf(Integer.class)))
                .thenThrow(new GuessNumberInputSizeNotMatchException())
                .thenReturn("4A0B");
        Mockito.when(calculator.isWin(Mockito.anyString(), Mockito.anyInt()))
                .thenReturn(true);

        Input input = Mockito.mock(Input.class);
        Mockito.when(input.get())
                .thenReturn("1 2 3");

        GameProcess gameProcess = new GameProcess(calculator, new GuessNumberGenerator(), input, new ConsoleOutput());
        gameProcess.play();
        String expected = "Wrong Input, Input again" + System.lineSeparator() +
                "4A0B" + System.lineSeparator() +
                "Game Over" + System.lineSeparator();
        Assert.assertEquals(expected, outContent.toString());
    }
}