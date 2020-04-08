package com.oocl;

public class App {

    public static void main(String[] args) throws Exception {
        GameProcess gameProcess = new GameProcess();
        gameProcess.play(new Calculator(), new Generator());
    }
}
