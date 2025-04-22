package main.member.move;

import main.Service.Outcome;

import java.util.List;

import static main.Service.Outcome.*;

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
