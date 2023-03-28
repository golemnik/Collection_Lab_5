package com.golem.app.fileSystem;

import java.util.Scanner;

public class ConsoleInputer implements Input {
    Scanner scanner;
    public ConsoleInputer() {
        scanner = new Scanner(System.in);
    }
    @Override
    public String input() {
        return scanner.nextLine();
    }
}
