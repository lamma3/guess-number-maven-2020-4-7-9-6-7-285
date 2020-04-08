package com.oocl;

import com.oocl.exception.GuessNumberDuplicateNumberException;
import com.oocl.exception.GuessNumberGameOverException;
import com.oocl.exception.GuessNumberInputSizeNotMatchException;
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

    @Test(expected = GuessNumberGameOverException.class)
    public void test_play_when_win_then_throwGuessNumberGameOverException() throws Exception {
        Calculator calculator = Mockito.mock(Calculator.class);
        Mockito.when(calculator.isWin(Mockito.anyString(), Mockito.anyInt()))
                .thenReturn(true);

        input("1 2 3 4" + System.lineSeparator());

        GameProcess gameProcess = new GameProcess();
        gameProcess.play(calculator, new Generator());
    }

    @Test(expected = GuessNumberGameOverException.class)
    public void test_play_when_lose_then_throwGuessNumberGameOverException() throws Exception {
        Calculator calculator = Mockito.mock(Calculator.class);
        Mockito.when(calculator.isWin(Mockito.anyString(), Mockito.anyInt()))
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(false)
                .thenReturn(false);

        input("1 2 3 4" + System.lineSeparator() +
                "1 2 3 4" + System.lineSeparator() +
                "1 2 3 4" + System.lineSeparator() +
                "1 2 3 4" + System.lineSeparator() +
                "1 2 3 4" + System.lineSeparator() +
                "1 2 3 4" + System.lineSeparator());

        GameProcess gameProcess = new GameProcess();
        gameProcess.play(calculator, new Generator());
    }

    @Test
    public void test_play_when_input_then_returnResult() throws Exception {
        Calculator calculator = Mockito.mock(Calculator.class);
        Mockito.when(calculator.calculateFeedback(Mockito.anyListOf(Integer.class), Mockito.anyListOf(Integer.class)))
                .thenReturn("4A0B");
        Mockito.when(calculator.isWin(Mockito.anyString(), Mockito.anyInt()))
                .thenReturn(true);

        input("1 2 3 4" + System.lineSeparator());

        try {
            GameProcess gameProcess = new GameProcess();
            gameProcess.play(calculator, new Generator());
        } catch (GuessNumberGameOverException e) {
            Assert.assertEquals("4A0B" + System.lineSeparator() ,outContent.toString());
        }
    }

    @Test
    public void test_play_when_GetGuessNumberDuplicateNumberException_then_printError() throws Exception {
        Calculator calculator = Mockito.mock(Calculator.class);
        Mockito.when(calculator.calculateFeedback(Mockito.anyListOf(Integer.class), Mockito.anyListOf(Integer.class)))
                .thenThrow(new GuessNumberDuplicateNumberException());
        Mockito.when(calculator.isWin(Mockito.anyString(), Mockito.anyInt()))
                .thenReturn(true);

        input("1 1 3 4" + System.lineSeparator());

        try {
            GameProcess gameProcess = new GameProcess();
            gameProcess.play(calculator, new Generator());
        } catch (GuessNumberGameOverException e) {
            Assert.assertEquals("Wrong Input, Input again" + System.lineSeparator() ,outContent.toString());
        }
    }

    @Test
    public void test_play_when_GetGuessNumberInputSizeNotMatchException_then_printError() throws Exception {
        Calculator calculator = Mockito.mock(Calculator.class);
        Mockito.when(calculator.calculateFeedback(Mockito.anyListOf(Integer.class), Mockito.anyListOf(Integer.class)))
                .thenThrow(new GuessNumberInputSizeNotMatchException());
        Mockito.when(calculator.isWin(Mockito.anyString(), Mockito.anyInt()))
                .thenReturn(true);

        input("1 1 3" + System.lineSeparator());

        try {
            GameProcess gameProcess = new GameProcess();
            gameProcess.play(calculator, new Generator());
        } catch (GuessNumberGameOverException e) {
            Assert.assertEquals("Wrong Input, Input again" + System.lineSeparator() ,outContent.toString());
        }
    }
}