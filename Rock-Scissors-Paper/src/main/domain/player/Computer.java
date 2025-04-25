package main.domain.player;

import main.domain.move.RpsMove;
import main.domain.strategy.Strategy;

public class Computer implements Player {
    private final String name;

    public Computer(int number) {
        this.name = "Computer" + number;
    }
    public Computer() {
        this.name = "Computer";
    }

    public Computer(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

}
