package main.domain.strategy;

import main.domain.move.RpsMove;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class BaselineStrategy implements Strategy {
    private final List<RpsMove> moves;
    private final Random random = new Random();

    public BaselineStrategy(Map<String, RpsMove> moves) {
        if (moves == null) throw new IllegalArgumentException("moves is null");
        if (moves.isEmpty()) throw new IllegalStateException("moves is empty");
        this.moves = new ArrayList<>(moves.values());
    }

    /**
     * 일정한 확률로 무브를 무작위 반환
     * @return 무작위 무브
     */
    @Override
    public RpsMove selectMove() {
        return moves.get(random.nextInt(moves.size()));
    }


}
