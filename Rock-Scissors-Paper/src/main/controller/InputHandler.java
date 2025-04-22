package main.controller;

import java.util.Scanner;

public class InputHandler {
    private Scanner scanner = new Scanner(System.in);
    public String readInput() {
        return scanner.nextLine();
    }
}
