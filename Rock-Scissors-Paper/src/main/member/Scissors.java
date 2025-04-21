package main.member;

import main.Service.Outcome;

import java.util.List;

import static main.Service.Outcome.*;
import static main.Service.Outcome.WIN;

public class Scissors implements RpsMove{
    @Override
    public Outcome compete(RpsMove move) {
        if (move == null) return ERROR;
        if (move instanceof Rock) return LOSE;
        if (move instanceof Scissors) return DRAW;
        return WIN;
    }

    @Override
    public List<String> asEmoji() {
        return null;
    }
}
