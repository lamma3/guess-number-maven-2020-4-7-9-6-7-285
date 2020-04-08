package com.oocl;

import com.oocl.exception.GuessNumberGameOverException;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class AppTest {

    private void input(String str) {
        InputStream inputStream = new ByteArrayInputStream(str.getBytes());
        System.setIn(inputStream);
    }

    @Test(expected = GuessNumberGameOverException.class)
    public void test_play_when_win_then_throwGuessNumberGameOverException() throws Exception {
        Calculator calculator = Mockito.mock(Calculator.class);
        Generator generator = Mockito.mock(Generator.class);
        Mockito.when(calculator.isWin(Mockito.anyString(), Mockito.anyInt()))
                .thenReturn(true);

        App app = new App();
        input("1 2 3 4");
        app.play(calculator, generator);
    }
}