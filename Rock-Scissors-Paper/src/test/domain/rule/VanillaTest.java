// 파일 경로: test/domain/rule/VanillaTest.java
package test.domain.rule;

import main.domain.GameResult;
import main.domain.Outcome;
import main.domain.move.Paper;
import main.domain.move.Rock;
import main.domain.move.Scissors;
import main.domain.move.RpsMove;
import main.domain.rule.Vanilla;
import main.domain.player.Player;
import main.domain.player.User;

import java.util.*;

public class VanillaTest {
    public static void main(String[] args) {
        testNullPlayers();
        testEmptyPlayers();
        testPlayersNullKey();
        testPlayersNullValue();
        testDrawAllSameMove();
        testDrawMultipleSameMove();
        testDrawAllThreeMoves();
        testTwoPlayersWinLose();
        testMultiplePlayersWinLose();
        System.out.println("✔︎ 모든 VanillaTest 통과");
    }

    /**
     * players가 null일 때 IllegalArgumentException 발생 테스트
     */
    private static void testNullPlayers() {
        try {
            new Vanilla().decide(null);
            assert false : "players가 null일 때 IllegalArgumentException이 발생해야 함";
        } catch (IllegalArgumentException e) {
            assert "players is null".equals(e.getMessage()) : "메시지가 'players is null'이어야 함";
        }
    }

    /**
     * players가 비어있을 때 IllegalStateException 발생 테스트
     */
    private static void testEmptyPlayers() {
        try {
            new Vanilla().decide(Collections.emptyMap());
            assert false : "players가 비어있을 때 IllegalStateException이 발생해야 함";
        } catch (IllegalStateException e) {
            assert "players is empty".equals(e.getMessage()) : "메시지가 'players is empty'이어야 함";
        }
    }

    /**
     * players에 null key가 있을 때 IllegalStateException 발생 테스트
     */
    private static void testPlayersNullKey() {
        Map<Player, RpsMove> players = new HashMap<>();
        players.put(null, new Rock());
        try {
            new Vanilla().decide(players);
            assert false : "players에 null key 있을 때 IllegalStateException이 발생해야 함";
        } catch (IllegalStateException e) {
            assert "players can`t get null key".equals(e.getMessage())
                    : "메시지가 'players can`t get null key'이어야 함";
        }
    }

    /**
     * players에 null value가 있을 때 IllegalStateException 발생 테스트
     */
    private static void testPlayersNullValue() {
        Map<Player, RpsMove> players = new HashMap<>();
        players.put(new User("A"), null);
        try {
            new Vanilla().decide(players);
            assert false : "players에 null value 있을 때 IllegalStateException이 발생해야 함";
        } catch (IllegalStateException e) {
            assert "players can`t get null value".equals(e.getMessage())
                    : "메시지가 'players can`t get null value'이어야 함";
        }
    }

    /**
     * 두 명이 동일 무브(Rock) 선택 시 draw 결과 테스트
     */
    private static void testDrawAllSameMove() {
        Player a = new User("A"), b = new User("B");
        Map<Player, RpsMove> players = new LinkedHashMap<>();
        players.put(a, new Rock());
        players.put(b, new Rock());

        GameResult result = new Vanilla().decide(players);
        Map<Player, Outcome> pr = result.getPlayerResult();

        assert pr.size() == 2 : "동일 무브 draw 시 map 크기";
        assert pr.get(a) == Outcome.DRAW : "A는 DRAW";
        assert pr.get(b) == Outcome.DRAW : "B는 DRAW";
    }

    /**
     * 세 명이 동일 무브(Scissors) 선택 시 draw 결과 테스트
     */
    private static void testDrawMultipleSameMove() {
        Player a = new User("A"), b = new User("B"), c = new User("C");
        Map<Player, RpsMove> players = new LinkedHashMap<>();
        players.put(a, new Scissors());
        players.put(b, new Scissors());
        players.put(c, new Scissors());

        GameResult result = new Vanilla().decide(players);
        Map<Player, Outcome> pr = result.getPlayerResult();

        assert pr.size() == 3 : "여러 명 동일 무브 draw 시 map 크기";
        assert pr.get(a) == Outcome.DRAW : "A는 DRAW";
        assert pr.get(b) == Outcome.DRAW : "B는 DRAW";
        assert pr.get(c) == Outcome.DRAW : "C는 DRAW";
    }

    /**
     * 세 가지 무브(Rock, Paper, Scissors)가 모두 등장할 때 draw 결과 테스트
     */
    private static void testDrawAllThreeMoves() {
        Player a = new User("A"), b = new User("B"), c = new User("C");
        Map<Player, RpsMove> players = new LinkedHashMap<>();
        players.put(a, new Rock());
        players.put(b, new Paper());
        players.put(c, new Scissors());

        GameResult result = new Vanilla().decide(players);
        Map<Player, Outcome> pr = result.getPlayerResult();

        assert pr.size() == 3 : "3가지 무브 draw 시 map 크기";
        for (Player u : List.of(a, b, c)) {
            assert pr.get(u) == Outcome.DRAW : u.getName() + "는 DRAW";
        }
    }

    /**
     * 두 명 플레이어 승패 결과 테스트:
     * - Rock vs Scissors → A 승, B 패
     * - Paper vs Scissors → B 승, A 패
     */
    private static void testTwoPlayersWinLose() {
        Player a = new User("A"), b = new User("B");

        // Rock vs Scissors
        Map<Player, RpsMove> p1 = Map.of(a, new Rock(), b, new Scissors());
        GameResult r1 = new Vanilla().decide(p1);
        Map<Player, Outcome> pr1 = r1.getPlayerResult();
        assert pr1.get(a) == Outcome.WIN : "Rock(A)는 WIN";
        assert pr1.get(b) == Outcome.LOSE : "Scissors(B)는 LOSE";

        // Paper vs Scissors
        Map<Player, RpsMove> p2 = Map.of(a, new Paper(), b, new Scissors());
        GameResult r2 = new Vanilla().decide(p2);
        Map<Player, Outcome> pr2 = r2.getPlayerResult();
        assert pr2.get(a) == Outcome.LOSE : "Paper(A)는 LOSE";
        assert pr2.get(b) == Outcome.WIN  : "Scissors(B)는 WIN";
    }

    /**
     * 여러 명 플레이어 승패 그룹화 테스트:
     * Rock 2명 vs Scissors 2명 → Rock 승자 2명, Scissors 패자 2명
     */
    private static void testMultiplePlayersWinLose() {
        Player r1 = new User("R1"), r2 = new User("R2");
        Player s1 = new User("S1"), s2 = new User("S2");
        Map<Player, RpsMove> players = new LinkedHashMap<>();
        players.put(r1, new Rock());
        players.put(r2, new Rock());
        players.put(s1, new Scissors());
        players.put(s2, new Scissors());

        GameResult result = new Vanilla().decide(players);
        Map<Player, Outcome> pr = result.getPlayerResult();

        assert pr.get(r1) == Outcome.WIN  : "R1은 WIN";
        assert pr.get(r2) == Outcome.WIN  : "R2은 WIN";
        assert pr.get(s1) == Outcome.LOSE : "S1은 LOSE";
        assert pr.get(s2) == Outcome.LOSE : "S2은 LOSE";
    }
}
