package main.member.player;

import main.member.move.RpsMove;

public interface Player {
    void makeMove();
    String getname();
    RpsMove getMove();
}
