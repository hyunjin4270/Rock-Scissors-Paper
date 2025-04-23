package main.controller;

import main.domain.move.RpsMove;
import main.repository.MoveRepository;
import main.repository.PlayerRepository;

import java.util.Scanner;

public class ConsoleInputHandler implements InputHandler {
    Scanner scanner = new Scanner(System.in);

    @Override
    public RpsMove readMove() {
        return null;
    }

    @Override
    public String start() {
        return null;
    }


}
