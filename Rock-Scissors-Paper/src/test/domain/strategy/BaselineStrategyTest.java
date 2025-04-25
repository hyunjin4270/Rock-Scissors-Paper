package test.domain.strategy;

import main.domain.move.RpsMove;
import main.domain.move.Rock;
import main.domain.move.Paper;
import main.domain.move.Scissors;
import main.domain.strategy.BaselineStrategy;

import java.util.*;

public class BaselineStrategyTest {
    public static void main(String[] args) {
        testNullMoves();
        testEmptyMoves();
        testSelectMoveReturnsValid();
        testSelectMoveRandomness();
        System.out.println("모든 BaselineStrategyTest 통과");
    }

    /**
     * null moves 맵 입력 시 IllegalArgumentException 발생 여부 테스트
     */
    private static void testNullMoves() {
        try {
            new BaselineStrategy(null);
            assert false : "null 입력 시 IllegalArgumentException 발생해야 함";
        } catch (IllegalArgumentException ignored) {}
    }

    /**
     * 빈 moves 맵 입력 시 IllegalStateException 발생 여부 테스트
     */
    private static void testEmptyMoves() {
        try {
            new BaselineStrategy(Collections.emptyMap());
            assert false : "빈 map 입력 시 IllegalStateException 발생해야 함";
        } catch (IllegalStateException ignored) {}
    }

    /**
     * selectMove()가 항상 주어진 moves 값 중 하나만 반환하는지 테스트
     */
    private static void testSelectMoveReturnsValid() {
        Map<String, RpsMove> movesMap = Map.of(
                "Rock", new Rock(),
                "Paper", new Paper(),
                "Scissors", new Scissors()
        );
        BaselineStrategy strategy = new BaselineStrategy(movesMap);
        for (int i = 0; i < 10; i++) {
            RpsMove move = strategy.selectMove();
            assert movesMap.containsValue(move) : "selectMove 반환값이 moves에 포함되어야 함";
        }
    }

    /**
     * 여러 번 호출 시 모든 무브가 최소 한 번 반환될 가능성이 있는지 테스트
     */
    private static void testSelectMoveRandomness() {
        Map<String, RpsMove> movesMap = Map.of(
                "Rock", new Rock(),
                "Paper", new Paper(),
                "Scissors", new Scissors()
        );
        BaselineStrategy strategy = new BaselineStrategy(movesMap);
        Set<RpsMove> seen = new HashSet<>();
        for (int i = 0; i < 100; i++) {
            seen.add(strategy.selectMove());
        }
        assert seen.size() == movesMap.size() : "여러 번 호출 후 모든 무브가 한 번 이상 반환되어야 함";
    }
}
