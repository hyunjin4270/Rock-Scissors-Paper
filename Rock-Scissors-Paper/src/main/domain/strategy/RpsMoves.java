package main.domain.strategy;

import main.domain.move.Paper;
import main.domain.move.Rock;
import main.domain.move.RpsMove;
import main.domain.move.Scissors;

import java.util.List;

public class RpsMoves {
    private static final List<RpsMove> DEFAULT_MOVES = List.of(
        new Rock(), new Scissors(), new Paper()
    );

    public static List<RpsMove> getDefaultMoves() {
        return DEFAULT_MOVES;
    }
}
