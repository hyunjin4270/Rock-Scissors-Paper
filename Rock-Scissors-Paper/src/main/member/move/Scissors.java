package main.member.move;

import main.Service.Outcome;

import java.util.List;

import static main.Service.Outcome.*;
import static main.Service.Outcome.WIN;

public class Scissors implements RpsMove {

    @Override
    public String getName() {
        return "scissors";
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
