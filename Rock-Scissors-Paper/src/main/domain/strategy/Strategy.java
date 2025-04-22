package main.domain.strategy;

import main.domain.move.RpsMove;

public interface Strategy {
    RpsMove selectMove();
}
