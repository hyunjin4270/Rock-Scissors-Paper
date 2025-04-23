package main.domain.player;

import main.domain.move.RpsMove;

import java.util.Map;

public class User implements Player {
    private String name;
    private RpsMove move;

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
