package main.member;

import main.Service.Outcome;

import java.util.List;

import static main.Service.Outcome.*;

public class Rock implements RpsMove{
    @Override
    public Outcome compete(RpsMove move) {
        if (move == null) return ERROR;
        if (move instanceof Paper) return LOSE;
        if (move instanceof Rock) return DRAW;
        return WIN;
    }

    @Override
    public List<String> asEmoji() {
        return null;
    }
}
