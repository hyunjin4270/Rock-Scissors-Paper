package test.session;

import main.session.SessionManager;
import main.domain.GameSession;
import main.domain.GameResult;
import main.domain.Outcome;
import main.domain.move.Rock;
import main.domain.move.Paper;
import main.domain.move.Scissors;
import main.domain.move.RpsMove;
import main.domain.player.User;
import main.domain.player.Player;
import main.domain.rule.RpsRule;
import main.domain.strategy.Strategy;

import java.util.*;
import java.util.NoSuchElementException;

public class SessionManagerTest {

    public static void main(String[] args) {
        testCreateNull();
        testCreateValid();
        testGetNull();
        testGetUnknown();
        testRemoveNull();
        testRemoveUnknown();
        testRemoveThenGet();
        System.out.println("✔︎ 모든 SessionManagerTest 통과");
    }

    /**
     * 유효한 GameSession 인스턴스를 생성하기 위한 헬퍼 메서드
     */
    private static GameSession createDummySession() {
        List<Player> players = List.of(new User("A"), new User("B"));
        Map<String, RpsMove> moves = Map.of(
                "Rock", new Rock(),
                "Paper", new Paper(),
                "Scissors", new Scissors()
        );
        RpsRule rule = participants ->
                GameResult.draw(new ArrayList<>(participants.keySet()));
        Strategy strategy = () -> new Rock();
        return new GameSession(players, moves, rule, strategy);
    }

    /**
     * create(null) 호출 시 IllegalArgumentException("session is null") 발생 테스트
     */
    private static void testCreateNull() {
        SessionManager sm = new SessionManager();
        try {
            sm.create(null);
            assert false : "create(null) 시 IllegalArgumentException이 발생해야 함";
        } catch (IllegalArgumentException e) {
            assert "session is null".equals(e.getMessage());
        }
    }

    /**
     * 유효한 세션으로 create 호출 시, non-null id 반환 및 get(id)로 동일 인스턴스 조회 테스트
     */
    private static void testCreateValid() {
        SessionManager sm = new SessionManager();
        GameSession sess = createDummySession();
        String id = sm.create(sess);
        assert id != null && !id.isEmpty() : "생성된 id는 null 또는 빈 문자열이 아니어야 함";

        GameSession fetched = sm.get(id);
        assert fetched == sess : "get(id)로 원본 GameSession 인스턴스를 반환해야 함";
    }

    /**
     * get(null) 호출 시 IllegalArgumentException("id is null") 발생 테스트
     */
    private static void testGetNull() {
        SessionManager sm = new SessionManager();
        try {
            sm.get(null);
            assert false : "get(null) 시 IllegalArgumentException이 발생해야 함";
        } catch (IllegalArgumentException e) {
            assert "id is null".equals(e.getMessage());
        }
    }

    /**
     * get(unknownId) 호출 시 NoSuchElementException("not a valid id") 발생 테스트
     */
    private static void testGetUnknown() {
        SessionManager sm = new SessionManager();
        try {
            sm.get("unknown");
            assert false : "get(unknown) 시 NoSuchElementException이 발생해야 함";
        } catch (NoSuchElementException e) {
            assert "not a valid id".equals(e.getMessage());
        }
    }

    /**
     * remove(null) 호출 시 IllegalArgumentException("id is null") 발생 테스트
     */
    private static void testRemoveNull() {
        SessionManager sm = new SessionManager();
        try {
            sm.remove(null);
            assert false : "remove(null) 시 IllegalArgumentException이 발생해야 함";
        } catch (IllegalArgumentException e) {
            assert "id is null".equals(e.getMessage());
        }
    }

    /**
     * remove(unknownId) 호출 시 NoSuchElementException("not a valid id") 발생 테스트
     */
    private static void testRemoveUnknown() {
        SessionManager sm = new SessionManager();
        try {
            sm.remove("unknown");
            assert false : "remove(unknown) 시 NoSuchElementException이 발생해야 함";
        } catch (NoSuchElementException e) {
            assert "not a valid id".equals(e.getMessage());
        }
    }

    /**
     * create 후 remove(id) 실행하고 get(id) 호출 시 NoSuchElementException 발생 테스트
     */
    private static void testRemoveThenGet() {
        SessionManager sm = new SessionManager();
        GameSession sess = createDummySession();
        String id = sm.create(sess);
        sm.remove(id);
        try {
            sm.get(id);
            assert false : "remove 후 get(id) 시 NoSuchElementException이 발생해야 함";
        } catch (NoSuchElementException e) {
            assert "not a valid id".equals(e.getMessage());
        }
    }
}
