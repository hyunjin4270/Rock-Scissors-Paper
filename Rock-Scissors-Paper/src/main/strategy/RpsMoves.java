package main.strategy;

import main.member.move.Paper;
import main.member.move.Rock;
import main.member.move.RpsMove;
import main.member.move.Scissors;

import java.util.List;

public class RpsMoves {
    private static final List<RpsMove> DEFAULT_MOVES = List.of(
        new Rock(), new Scissors(), new Paper()
    );

    public static List<RpsMove> getDefaultMoves() {
        return DEFAULT_MOVES;
    }
}
