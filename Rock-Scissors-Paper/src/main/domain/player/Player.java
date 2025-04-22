package main.domain.player;

import main.domain.move.RpsMove;

public interface Player {
    void makeMove();
    String getname();
    RpsMove getMove();
}
