package main.controller;

import main.domain.move.RpsMove;

public interface InputHandler {
    RpsMove readMove();
    void readMenu();
    void execute();
    String start();

}
