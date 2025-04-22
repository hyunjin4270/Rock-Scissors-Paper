package main.strategy;

import main.member.move.Paper;
import main.member.move.Rock;
import main.member.move.RpsMove;
import main.member.move.Scissors;

import java.util.List;
import java.util.Random;

public class BaselineStrategy implements Strategy {
    private List<RpsMove> moves;
    private Random random = new Random();


    public BaselineStrategy(List<RpsMove> moves) {
        this.moves = moves;
    }

    public BaselineStrategy() {
        this.moves = RpsMoves.getDefaultMoves();
    }

    @Override
    public RpsMove selectMove() {
        return moves.get(random.nextInt(moves.size()));
    }
}
