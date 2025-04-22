package main.strategy;

import main.member.move.RpsMove;

public interface Strategy {
    RpsMove selectMove();
}
