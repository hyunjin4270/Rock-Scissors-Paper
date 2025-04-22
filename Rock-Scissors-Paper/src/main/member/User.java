package main.member;

import main.member.move.RpsMove;

public class User {
    private String name = "Player";
    private RpsMove move;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RpsMove getMove() {
        return move;
    }

    public void setMove(RpsMove move) {
        this.move = move;
    }
}
