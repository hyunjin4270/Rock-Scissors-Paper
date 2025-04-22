package main.domain.player;

import main.domain.move.RpsMove;
import main.domain.strategy.Strategy;

public class Computer implements Player {
    private final Strategy strategy;
    private final String name;
    private RpsMove move;

    public Computer(Strategy strategy, int number) {
        this.strategy = strategy;
        this.name = "Computer" + number;
    }

    @Override
    public void makeMove() {
        move = strategy.selectMove();
    }

    @Override
    public String getname() {
        return name;
    }

    @Override
    public RpsMove getMove() {
        return move;
    }
}
