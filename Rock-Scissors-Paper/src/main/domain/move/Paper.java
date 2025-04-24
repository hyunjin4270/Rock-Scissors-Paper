package main.domain.move;

import main.domain.Outcome;

import java.util.List;

import static main.domain.Outcome.*;

public class Paper implements RpsMove {
    private final String name = "paper";

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Outcome compare(RpsMove other) {
        if (other instanceof Scissors) return WIN;
        if (other instanceof  Rock) return DRAW;
        if (other instanceof Paper) return LOSE;
        return UNDECIDED;
    }


    @Override
    public List<String> asEmoji() {
        return null;
    }
}
