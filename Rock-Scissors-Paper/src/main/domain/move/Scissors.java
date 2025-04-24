package main.domain.move;

import main.domain.Outcome;

import java.util.List;

import static main.domain.Outcome.*;
import static main.domain.Outcome.WIN;

public class Scissors implements RpsMove {
    private final String name = "scissors";
    @Override
    public String getName() {
        return name;
    }

    @Override
    public Outcome compare(RpsMove other) {
        if (other instanceof Paper) return WIN;
        if (other instanceof  Scissors) return DRAW;
        if (other instanceof Rock) return LOSE;
        return UNDECIDED;
    }


    @Override
    public List<String> asEmoji() {
        return null;
    }
}
