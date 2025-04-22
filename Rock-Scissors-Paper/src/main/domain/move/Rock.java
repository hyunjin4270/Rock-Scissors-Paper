package main.domain.move;

import main.Outcome;

import java.util.List;

import static main.Outcome.*;

public class Rock implements RpsMove {
    @Override
    public String getName() {
        return "rock";
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
