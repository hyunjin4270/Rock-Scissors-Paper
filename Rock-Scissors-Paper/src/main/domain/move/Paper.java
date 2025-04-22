package main.domain.move;

import main.Outcome;

import java.util.List;

import static main.Outcome.*;

public class Paper implements RpsMove {

    @Override
    public String getName() {
        return "paper";
    }

    @Override
    public Outcome compare(RpsMove other) {
        if (other instanceof Rock) return WIN;
        if (other instanceof  Paper) return DRAW;
        if (other instanceof Scissors) return LOSE;
        return UNDECIDED;
    }


    @Override
    public List<String> asEmoji() {
        return null;
    }
}
