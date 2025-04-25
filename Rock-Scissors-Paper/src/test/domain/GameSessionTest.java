package test.domain;

import main.domain.GameSession;
import main.domain.GameResult;
import main.domain.move.RpsMove;
import main.domain.move.Rock;
import main.domain.move.Paper;
import main.domain.move.Scissors;
import main.domain.rule.RpsRule;
import main.domain.player.Player;
import main.domain.player.User;
import main.domain.player.Computer;
import main.domain.rule.Vanilla;
import main.domain.strategy.Strategy;
import main.exception.MoveNotFoundException;

import java.lang.reflect.Field;
import java.util.*;

public class GameSessionTest {

    private static final RpsMove STUB_ROCK = new Rock();
    private static final Strategy STUB_STRATEGY = () -> STUB_ROCK;
    private static final RpsRule STUB_RULE = new Vanilla();
    private static final Map<String, RpsMove> VALID_MOVES = createValidMoves();
    private static final List<Player> VALID_PLAYERS = List.of(
            new User("Alice"), new User("Bob")
    );

    private static Map<String, RpsMove> createValidMoves() {
        Map<String, RpsMove> m = new LinkedHashMap<>();
        m.put("Rock",     new Rock());
        m.put("Paper",    new Paper());
        m.put("Scissors", new Scissors());
        return m;
    }

    public static void main(String[] args) throws Exception {
        testConstructorNullPlayers();
        testConstructorEmptyPlayers();
        testConstructorSinglePlayer();
        testConstructorNullMoves();
        testConstructorEmptyMoves();
        testConstructorMovesNullKey();
        testConstructorMovesNullValue();
        testConstructorMovesLessThanThree();
        testConstructorNullRule();
        testConstructorNullStrategy();
        testConstructorValid();

        testComputerStrategyNullPlayer();
        testComputerStrategyNotComputer();
        testComputerStrategyRegisteredComputer();
        testComputerStrategyNullStrategy();
        testComputerStrategyValid();

        testChooseMoveAlreadySelected();
        testChooseMoveMoveNotFound();
        testChooseMoveValid();

        System.out.println("✔︎ 모든 GameSessionTest 통과");
    }

    /** players가 null일 때 IllegalArgumentException 발생 테스트 */
    private static void testConstructorNullPlayers() {
        try {
            new GameSession(null, VALID_MOVES, STUB_RULE, STUB_STRATEGY);
            assert false : "null players 시 IllegalArgumentException";
        } catch (IllegalArgumentException e) {
            assert "players is null".equals(e.getMessage());
        }
    }

    /** players가 비어있을 때 IllegalStateException 발생 테스트 */
    private static void testConstructorEmptyPlayers() {
        try {
            new GameSession(Collections.emptyList(), VALID_MOVES, STUB_RULE, STUB_STRATEGY);
            assert false : "empty players 시 IllegalStateException";
        } catch (IllegalStateException e) {
            assert "players is empty".equals(e.getMessage());
        }
    }

    /** players 목록에 1명만 있을 때 IllegalStateException 발생 테스트 */
    private static void testConstructorSinglePlayer() {
        try {
            new GameSession(List.of(new User("Solo")), VALID_MOVES, STUB_RULE, STUB_STRATEGY);
            assert false : "single player 시 IllegalStateException";
        } catch (IllegalStateException e) {
            assert "players can't have only one player.".equals(e.getMessage());
        }
    }

    /** moves가 null일 때 IllegalArgumentException 발생 테스트 */
    private static void testConstructorNullMoves() {
        try {
            new GameSession(VALID_PLAYERS, null, STUB_RULE, STUB_STRATEGY);
            assert false : "null moves 시 IllegalArgumentException";
        } catch (IllegalArgumentException e) {
            assert "moves is null".equals(e.getMessage());
        }
    }

    /** moves가 비어있을 때 IllegalStateException 발생 테스트 */
    private static void testConstructorEmptyMoves() {
        try {
            new GameSession(VALID_PLAYERS, Collections.emptyMap(), STUB_RULE, STUB_STRATEGY);
            assert false : "empty moves 시 IllegalStateException";
        } catch (IllegalStateException e) {
            assert "moves is empty".equals(e.getMessage());
        }
    }

    /** moves에 null key가 있을 때 IllegalStateException 발생 테스트 */
    private static void testConstructorMovesNullKey() {
        Map<String, RpsMove> m = new HashMap<>(VALID_MOVES);
        m.put(null, new Rock());
        try {
            new GameSession(VALID_PLAYERS, m, STUB_RULE, STUB_STRATEGY);
            assert false : "moves null key 시 IllegalStateException";
        } catch (IllegalStateException e) {
            assert "players can't get null key".equals(e.getMessage());
        }
    }

    /** moves에 null value가 있을 때 IllegalStateException 발생 테스트 */
    private static void testConstructorMovesNullValue() {
        Map<String, RpsMove> m = new HashMap<>(VALID_MOVES);
        m.put("Lizard", null);
        try {
            new GameSession(VALID_PLAYERS, m, STUB_RULE, STUB_STRATEGY);
            assert false : "moves null value 시 IllegalStateException";
        } catch (IllegalStateException e) {
            assert "players can't get null value".equals(e.getMessage());
        }
    }

    /** moves 크기가 3 미만일 때 IllegalStateException 발생 테스트 */
    private static void testConstructorMovesLessThanThree() {
        Map<String, RpsMove> m = new HashMap<>();
        m.put("Rock", new Rock());
        m.put("Paper", new Paper());
        try {
            new GameSession(VALID_PLAYERS, m, STUB_RULE, STUB_STRATEGY);
            assert false : "moves size <3 시 IllegalStateException";
        } catch (IllegalStateException e) {
            assert "moves cannot be less than three.".equals(e.getMessage());
        }
    }

    /** rule이 null일 때 IllegalArgumentException 발생 테스트 */
    private static void testConstructorNullRule() {
        try {
            new GameSession(VALID_PLAYERS, VALID_MOVES, null, STUB_STRATEGY);
            assert false : "null rule 시 IllegalArgumentException";
        } catch (IllegalArgumentException e) {
            assert "rule is null".equals(e.getMessage());
        }
    }

    /** strategy가 null일 때 IllegalArgumentException 발생 테스트 */
    private static void testConstructorNullStrategy() {
        try {
            new GameSession(VALID_PLAYERS, VALID_MOVES, STUB_RULE, null);
            assert false : "null strategy 시 IllegalArgumentException";
        } catch (IllegalArgumentException e) {
            assert "strategy is null".equals(e.getMessage());
        }
    }

    /** 유효한 파라미터로 생성 시 예외가 발생하지 않아야 한다. */
    private static void testConstructorValid() {
        new GameSession(VALID_PLAYERS, VALID_MOVES, STUB_RULE, STUB_STRATEGY);
    }

    /** player가 null일 때 IllegalArgumentException 발생 테스트 */
    private static void testComputerStrategyNullPlayer() {
        GameSession session = new GameSession(VALID_PLAYERS, VALID_MOVES, STUB_RULE, STUB_STRATEGY);
        try {
            session.computerStrategy(null, STUB_STRATEGY);
            assert false : "null player 시 IllegalArgumentException";
        } catch (IllegalArgumentException e) {
            assert "player is null".equals(e.getMessage());
        }
    }

    /** Player가 Computer가 아닐 때 IllegalStateException 발생 테스트 */
    private static void testComputerStrategyNotComputer() {
        GameSession session = new GameSession(VALID_PLAYERS, VALID_MOVES, STUB_RULE, STUB_STRATEGY);
        Player user = new User("Alice");
        try {
            session.computerStrategy(user, STUB_STRATEGY);
            assert false : "User 인스턴스는 IllegalStateException";
        } catch (IllegalStateException e) {
            assert "player is not computer".equals(e.getMessage());
        }
    }

    /** 등록된 Computer 인스턴스를 넘기면 IllegalArgumentException 발생 테스트 */
    private static void testComputerStrategyRegisteredComputer() {
        Computer comp = new Computer("CPU");
        List<Player> playersWithComp = new ArrayList<>(VALID_PLAYERS);
        playersWithComp.add(comp);
        GameSession session = new GameSession(playersWithComp, VALID_MOVES, STUB_RULE, STUB_STRATEGY);
        try {
            session.computerStrategy(comp, STUB_STRATEGY);
            assert false : "등록된 Computer 인스턴스는 IllegalArgumentException";
        } catch (IllegalArgumentException e) {
            assert "player is not registered".equals(e.getMessage());
        }
    }


    /** strategy가 null일 때 IllegalArgumentException 발생 테스트 */
    private static void testComputerStrategyNullStrategy() {
        GameSession session = new GameSession(VALID_PLAYERS, VALID_MOVES, STUB_RULE, STUB_STRATEGY);
        Computer comp = new Computer("CPU");
        try {
            session.computerStrategy(comp, null);
            assert false : "null strategy 시 IllegalArgumentException";
        } catch (IllegalArgumentException e) {
            assert "strategy is empty".equals(e.getMessage());
        }
    }

    /** 유효한 파라미터로 computerStrategy 호출 시 stubStrategy 결과 반환 테스트 */
    private static void testComputerStrategyValid() {
        GameSession session = new GameSession(VALID_PLAYERS, VALID_MOVES, STUB_RULE, STUB_STRATEGY);
        Computer comp = new Computer("CPU");
        RpsMove move = session.computerStrategy(comp, STUB_STRATEGY);
        assert move == STUB_ROCK : "유효한 호출은 stubStrategy에서 반환된 객체를 그대로 반환해야 함";
    }

    /** 이미 등록된 Player에 대해 chooseMove 호출 시 IllegalStateException 발생 테스트 */
    private static void testChooseMoveAlreadySelected() {
        Player a = VALID_PLAYERS.get(0);
        GameSession session = new GameSession(VALID_PLAYERS, VALID_MOVES, STUB_RULE, STUB_STRATEGY);
        try {
            session.chooseMove(a, "Rock");
            assert false : "registered player 시 IllegalStateException";
        } catch (IllegalStateException e) {
            assert (a.getName() + " is already selected").equals(e.getMessage());
        }
    }

    /** 존재하지 않는 moveName 으로 chooseMove 호출 시 MoveNotFoundException 발생 테스트 */
    private static void testChooseMoveMoveNotFound() {
        Player c = new User("Charlie");
        GameSession session = new GameSession(VALID_PLAYERS, VALID_MOVES, STUB_RULE, STUB_STRATEGY);
        try {
            session.chooseMove(c, "Lizard");
            assert false : "없는 moveName 시 MoveNotFoundException";
        } catch (MoveNotFoundException e) {
            assert "Lizard".equals(e.getMessage());
        }
    }

    /** 유효한 파라미터로 chooseMove 호출 시 behaviors 맵에 올바르게 저장되는지 테스트 */
    private static void testChooseMoveValid() throws Exception {
        Player c = new User("Charlie");
        GameSession session = new GameSession(VALID_PLAYERS, VALID_MOVES, STUB_RULE, STUB_STRATEGY);
        session.chooseMove(c, "Rock");

        Field f = GameSession.class.getDeclaredField("behaviors");
        f.setAccessible(true);
        @SuppressWarnings("unchecked")
        Map<Player, RpsMove> behaviors = (Map<Player, RpsMove>) f.get(session);

        assert behaviors.size() == 1;
        assert behaviors.get(c) == VALID_MOVES.get("Rock");
    }
}