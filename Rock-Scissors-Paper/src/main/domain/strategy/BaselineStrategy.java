package main.domain.strategy;

import main.domain.move.RpsMove;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class BaselineStrategy implements Strategy {
    private final List<RpsMove> moves;
    private final Random random = new Random();

    public BaselineStrategy(Map<String, RpsMove> moves) {

    }

    @Override
    public RpsMove selectMove() {

        return moves.get(random.nextInt(moves.size()));
    }


}
