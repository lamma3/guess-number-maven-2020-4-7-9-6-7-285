package com.oocl.io;

public class ConsoleOutput implements Output {

    @Override
    public void send(String content) {
        System.out.println(content);
    }
}
