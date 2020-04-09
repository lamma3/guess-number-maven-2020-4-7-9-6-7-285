package com.oocl.io;

import java.util.Scanner;

public class ConsoleInput implements Input {

    private Scanner scanner = new Scanner(System.in);;

    @Override
    public String get() {
        return scanner.nextLine();
    }
}
