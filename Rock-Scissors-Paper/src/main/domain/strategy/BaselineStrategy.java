package main.domain.strategy;

import main.domain.move.RpsMove;

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
