package main.domain.player;

import main.domain.move.RpsMove;

public class User implements Player {
    private String name;
    private RpsMove move;
    private InputHandler inputHandler;

    @Override
    public void makeMove() {
    }

    @Override
    public String getname() {
        return "";
    }

    @Override
    public RpsMove getMove() {
        return null;
    }
}
