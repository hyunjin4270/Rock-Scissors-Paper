package main.member;

import main.Service.Outcome;

import java.util.List;

import static main.Service.Outcome.*;

public class Paper implements RpsMove{
    @Override
    public Outcome compete(RpsMove move) {
        if (move == null) return ERROR;
        if (move instanceof Scissors) return LOSE;
        if (move instanceof Paper) return DRAW;
        return WIN;
    }

    @Override
    public List<String> asEmoji() {
        return null;
    }
}
