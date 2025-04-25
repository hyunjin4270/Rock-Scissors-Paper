// 파일 경로: test/domain/GameResultTest.java
package test.domain;

import main.domain.GameResult;
import main.domain.Outcome;
import main.domain.player.Player;
import main.domain.player.User;

import java.util.*;

public class GameResultTest {
    public static void main(String[] args) {
        testDrawNullParticipants();
        testDrawEmptyParticipants();
        testDrawSingleParticipant();
        testDrawDuplicateParticipants();
        testDrawCorrectOutcome();
        testDrawIndependenceFromOriginalList();
        testResultNullWinner();
        testResultNullLoser();
        testResultEmptyWinner();
        testResultEmptyLoser();
        testResultCorrectOutcome();
        testResultMultipleWinnersAndLosers();
        testResultIndependenceFromOriginalLists();
        testResultOverlappingParticipants();
        testImmutability();
        System.out.println("✔︎ 모든 GameResultTest 통과");
    }

    /**
     * draw() 호출 시 participants가 null이면 IllegalArgumentException 발생 테스트
     */
    private static void testDrawNullParticipants() {
        try {
            GameResult.draw(null);
            assert false : "draw(null) 시 IllegalArgumentException 발생해야 함";
        } catch (IllegalArgumentException e) {
            assert "participants is null".equals(e.getMessage());
        }
    }

    /**
     * draw() 호출 시 participants가 비어있으면 IllegalStateException 발생 테스트
     */
    private static void testDrawEmptyParticipants() {
        try {
            GameResult.draw(Collections.emptyList());
            assert false : "draw(empty) 시 IllegalStateException 발생해야 함";
        } catch (IllegalStateException e) {
            assert "participants is empty".equals(e.getMessage());
        }
    }

    /**
     * 단일 참가자 리스트로 draw 호출 시 정상 결과 테스트
     */
    private static void testDrawSingleParticipant() {
        Player u = new User("Solo");
        GameResult result = GameResult.draw(List.of(u));
        Map<Player, Outcome> pr = result.getPlayerResult();

        assert pr.size() == 1 : "단일 참가자 draw 시 map 크기 1";
        assert pr.get(u) == Outcome.DRAW : "Solo는 DRAW";
    }

    /**
     * 중복된 참가자 리스트로 draw 호출 시 예외 처리 필요 (현재는 실패하도록 테스트)
     */
    private static void testDrawDuplicateParticipants() {
        Player u = new User("Dup");
        List<Player> list = new ArrayList<>();
        list.add(u);
        list.add(u);
        try {
            GameResult.draw(list);
            assert false : "중복된 참가자 리스트 허용은 잘못된 동작";
        } catch (IllegalArgumentException | IllegalStateException e) {
            // 의도된 예외
        }
    }

    /**
     * draw()가 두 명 이상 정상적인 DRAW 결과를 반환하는지 테스트
     */
    private static void testDrawCorrectOutcome() {
        Player a = new User("A");
        Player b = new User("B");
        GameResult result = GameResult.draw(List.of(a, b));
        Map<Player, Outcome> pr = result.getPlayerResult();

        assert pr.size() == 2 : "draw 시 map 크기 2";
        assert pr.get(a) == Outcome.DRAW : "A는 DRAW";
        assert pr.get(b) == Outcome.DRAW : "B는 DRAW";
    }

    /**
     * 원본 participants 리스트 변경이 결과에 영향 없는지 테스트
     */
    private static void testDrawIndependenceFromOriginalList() {
        Player a = new User("A");
        Player b = new User("B");
        List<Player> list = new ArrayList<>(List.of(a, b));
        GameResult result = GameResult.draw(list);

        list.clear();  // 원본 리스트 변경
        Map<Player, Outcome> pr = result.getPlayerResult();
        assert pr.size() == 2 : "원본 리스트 변경 후에도 map 크기 유지";
    }

    /**
     * result() 호출 시 winner가 null이면 IllegalArgumentException 발생 테스트
     */
    private static void testResultNullWinner() {
        try {
            GameResult.result(null, List.of(new User("X")));
            assert false : "result(null, nonEmpty) 시 IllegalArgumentException 발생해야 함";
        } catch (IllegalArgumentException e) {
            assert "winner is null".equals(e.getMessage());
        }
    }

    /**
     * result() 호출 시 loser가 null이면 IllegalArgumentException 발생 테스트
     */
    private static void testResultNullLoser() {
        try {
            GameResult.result(List.of(new User("X")), null);
            assert false : "result(nonEmpty, null) 시 IllegalArgumentException 발생해야 함";
        } catch (IllegalArgumentException e) {
            assert "loser is null".equals(e.getMessage());
        }
    }

    /**
     * result() 호출 시 winner가 비어있으면 IllegalStateException 발생 테스트
     */
    private static void testResultEmptyWinner() {
        try {
            GameResult.result(Collections.emptyList(), List.of(new User("X")));
            assert false : "result(empty, nonEmpty) 시 IllegalStateException 발생해야 함";
        } catch (IllegalStateException e) {
            assert "winner is empty".equals(e.getMessage());
        }
    }

    /**
     * result() 호출 시 loser가 비어있으면 IllegalStateException 발생 테스트
     */
    private static void testResultEmptyLoser() {
        try {
            GameResult.result(List.of(new User("X")), Collections.emptyList());
            assert false : "result(nonEmpty, empty) 시 IllegalStateException 발생해야 함";
        } catch (IllegalStateException e) {
            assert "loser is empty".equals(e.getMessage());
        }
    }

    /**
     * result()가 단일 승자/패자에 대해 올바른 WIN/LOSE 결과를 반환하는지 테스트
     */
    private static void testResultCorrectOutcome() {
        Player w = new User("Winner");
        Player l = new User("Loser");
        GameResult result = GameResult.result(List.of(w), List.of(l));
        Map<Player, Outcome> pr = result.getPlayerResult();

        assert pr.size() == 2 : "result 시 map 크기 2";
        assert pr.get(w) == Outcome.WIN : "Winner는 WIN";
        assert pr.get(l) == Outcome.LOSE : "Loser는 LOSE";
    }

    /**
     * 다수의 승자/패자로 result 호출 시 올바른 결과 반환 테스트
     */
    private static void testResultMultipleWinnersAndLosers() {
        Player w1 = new User("W1");
        Player w2 = new User("W2");
        Player l1 = new User("L1");
        Player l2 = new User("L2");

        GameResult result = GameResult.result(
                List.of(w1, w2),
                List.of(l1, l2)
        );
        Map<Player, Outcome> pr = result.getPlayerResult();

        assert pr.size() == 4 : "다수 승/패자 result 시 map 크기";
        assert pr.get(w1) == Outcome.WIN;
        assert pr.get(w2) == Outcome.WIN;
        assert pr.get(l1) == Outcome.LOSE;
        assert pr.get(l2) == Outcome.LOSE;
    }

    /**
     * 원본 winner/loser 리스트 변경이 결과에 영향 없는지 테스트
     */
    private static void testResultIndependenceFromOriginalLists() {
        Player w1 = new User("W1");
        Player w2 = new User("W2");
        Player l1 = new User("L1");
        Player l2 = new User("L2");

        List<Player> winners = new ArrayList<>(List.of(w1, w2));
        List<Player> losers  = new ArrayList<>(List.of(l1, l2));
        GameResult result = GameResult.result(winners, losers);

        winners.clear();
        losers.clear();
        Map<Player, Outcome> pr = result.getPlayerResult();

        assert pr.size() == 4 : "원본 리스트 변경 후에도 map 크기 유지";
        assert pr.get(w1) == Outcome.WIN;
        assert pr.get(w2) == Outcome.WIN;
        assert pr.get(l1) == Outcome.LOSE;
        assert pr.get(l2) == Outcome.LOSE;
    }

    /**
     * winner와 loser 리스트에 동일 인스턴스가 있을 때 예외 처리 필요 (현재는 실패하도록 테스트)
     */
    private static void testResultOverlappingParticipants() {
        Player u = new User("Overlap");
        try {
            GameResult.result(List.of(u), List.of(u));
            assert false : "중복된 승/패자 허용은 잘못된 동작";
        } catch (IllegalArgumentException | IllegalStateException e) {
            // 의도된 예외
        }
    }

    /**
     * getPlayerResult()가 반환하는 맵이 불변인지 테스트
     */
    private static void testImmutability() {
        Player u = new User("Immutable");
        GameResult result = GameResult.draw(List.of(u));
        Map<Player, Outcome> pr = result.getPlayerResult();

        try {
            pr.put(u, Outcome.WIN);
            assert false : "불변 맵에 put 시 UnsupportedOperationException 발생해야 함";
        } catch (UnsupportedOperationException ignored) {}
        try {
            pr.remove(u);
            assert false : "불변 맵에 remove 시 UnsupportedOperationException 발생해야 함";
        } catch (UnsupportedOperationException ignored) {}
        try {
            pr.clear();
            assert false : "불변 맵에 clear 시 UnsupportedOperationException 발생해야 함";
        } catch (UnsupportedOperationException ignored) {}
    }
}
