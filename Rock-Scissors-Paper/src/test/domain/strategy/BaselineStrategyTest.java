// 파일 경로: test/domain/strategy/BaselineStrategyTest.java
package test.domain.strategy;

import main.domain.move.Paper;
import main.domain.move.Rock;
import main.domain.move.RpsMove;
import main.domain.move.Scissors;
import main.domain.strategy.BaselineStrategy;

import java.lang.reflect.Field;
import java.util.*;

public class BaselineStrategyTest {
    public static void main(String[] args) throws Exception {
        testNullMovesMap();
        testEmptyMovesMap();
        testMovesNullKey();
        testMovesNullValue();
        testMovesListCopiedInOrder();
        testSelectMoveReturnsValidMove();
        System.out.println("✔︎ 모든 BaselineStrategyTest 통과");
    }

    /**
     * null을 파라미터로 넘기면 IllegalArgumentException이 발생해야 한다.
     */
    private static void testNullMovesMap() {
        try {
            new BaselineStrategy(null);
            assert false : "null 입력 시 IllegalArgumentException이 발생해야 함";
        } catch (IllegalArgumentException e) {
            assert "moves is null".equals(e.getMessage())
                    : "메시지가 'moves is null'이어야 함";
        }
    }

    /**
     * 비어있는 맵을 넘기면 IllegalStateException이 발생해야 한다.
     */
    private static void testEmptyMovesMap() {
        try {
            new BaselineStrategy(Collections.emptyMap());
            assert false : "빈 맵 입력 시 IllegalStateException이 발생해야 함";
        } catch (IllegalStateException e) {
            assert "moves is empty".equals(e.getMessage())
                    : "메시지가 'moves is empty'이어야 함";
        }
    }

    /**
     * 키가 null인 엔트리가 있으면 IllegalStateException이 발생해야 한다.
     */
    private static void testMovesNullKey() {
        Map<String, RpsMove> m = new HashMap<>();
        m.put(null, new Rock());
        try {
            new BaselineStrategy(m);
            assert false : "null 키 있을 때 IllegalStateException이 발생해야 함";
        } catch (IllegalStateException e) {
            assert "players can't get null key".equals(e.getMessage())
                    : "메시지가 'players can't get null key'이어야 함";
        }
    }

    /**
     * 값이 null인 엔트리가 있으면 IllegalStateException이 발생해야 한다.
     */
    private static void testMovesNullValue() {
        Map<String, RpsMove> m = new HashMap<>();
        m.put("Rock", null);
        try {
            new BaselineStrategy(m);
            assert false : "null 값 있을 때 IllegalStateException이 발생해야 함";
        } catch (IllegalStateException e) {
            assert "players can't get null value".equals(e.getMessage())
                    : "메시지가 'players can't get null value'이어야 함";
        }
    }

    /**
     * 생성자에서 입력 맵의 values()가 순서대로 복사되는지 확인한다.
     */
    private static void testMovesListCopiedInOrder() throws Exception {
        RpsMove rock     = new Rock();
        RpsMove paper    = new Paper();
        RpsMove scissors = new Scissors();
        Map<String, RpsMove> m = new LinkedHashMap<>();
        m.put("R", rock);
        m.put("P", paper);
        m.put("S", scissors);

        BaselineStrategy strat = new BaselineStrategy(m);

        // 리플렉션으로 private 필드 moves를 꺼내와 검증
        Field f = BaselineStrategy.class.getDeclaredField("moves");
        f.setAccessible(true);
        @SuppressWarnings("unchecked")
        List<RpsMove> list = (List<RpsMove>) f.get(strat);

        assert list.size() == 3 : "moves 리스트 크기";
        assert list.get(0) == rock     : "첫 번째 요소는 rock";
        assert list.get(1) == paper    : "두 번째 요소는 paper";
        assert list.get(2) == scissors : "세 번째 요소는 scissors";
    }

    /**
     * selectMove()가 항상 입력된 moves 중 하나를 반환하는지 확인한다.
     */
    private static void testSelectMoveReturnsValidMove() {
        RpsMove rock     = new Rock();
        RpsMove paper    = new Paper();
        RpsMove scissors = new Scissors();
        Map<String, RpsMove> m = Map.of(
                "R", rock,
                "P", paper,
                "S", scissors
        );
        BaselineStrategy strat = new BaselineStrategy(m);

        for (int i = 0; i < 50; i++) {
            RpsMove picked = strat.selectMove();
            assert picked == rock || picked == paper || picked == scissors
                    : "selectMove 결과는 원본 moves 중 하나여야 함";
        }
    }
}
