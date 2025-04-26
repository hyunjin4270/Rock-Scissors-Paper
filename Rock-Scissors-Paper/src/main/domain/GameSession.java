package main.domain;

import main.domain.move.RpsMove;
import main.domain.player.Computer;
import main.domain.player.Player;
import main.domain.rule.RpsRule;
import main.domain.strategy.Strategy;
import main.exception.MoveNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class GameSession {
    private final Map<String, RpsMove> moves;
    private final RpsRule rule;
    private final Map<Player, RpsMove> behaviors = new HashMap<>();
    private final Strategy strategy;
    private final String sessionId;

    public GameSession(String sessionId, Map<String, RpsMove> moves, RpsRule rule, Strategy strategy) {
        if (moves == null) throw new IllegalArgumentException("moves is null");
        if (moves.isEmpty()) throw new IllegalStateException("moves is empty");
        for (Map.Entry<String, RpsMove> e : moves.entrySet()) {
            if (e.getKey()   == null) throw new IllegalStateException("players can't get null key");
            if (e.getValue() == null) throw new IllegalStateException("players can't get null value");
        }
        if (moves.size() < 3) throw new IllegalStateException("moves cannot be less than three.");
        if (rule == null) throw new IllegalArgumentException("rule is null");
        if (strategy == null) throw new IllegalArgumentException("strategy is null");
        if (sessionId == null) throw new IllegalArgumentException("sessionId is null");
        UUID.fromString(sessionId);

        this.sessionId = sessionId;
        this.moves = moves;
        this.rule = rule;
        this.strategy = strategy;
    }

    /**
     * AI가 정해져있는 정책에 따라 결정한 무브값을 반환하는 메서드입니다
     * @param player Computer객체
     * @param strategy Computer의 전략
     * @return 전략에 따라 결정된 무브
     */
    public RpsMove computerStrategy(Player player, Strategy strategy) {
        if (player == null) throw new IllegalArgumentException("player is null");
        if (!(player instanceof Computer)) throw new IllegalStateException("player is not computer");
        if (strategy == null) throw new IllegalArgumentException("strategy is empty");

        return strategy.selectMove();
    }

    /**
     * 해당 플레이어에 대한 무브를 등록하는 메서드입니다
     * @param player 라운드 플레이어 (null 불가)
     * @param moveName 플레이어가 제시한 무브 (null 또는 존재하지 않는 무브 불가)
     */
    public void chooseMove(Player player, String moveName) {
        if (behaviors.containsKey(player)) throw new IllegalStateException(player.getName() + " is already selected");
        if (!moves.containsKey(moveName)) throw new MoveNotFoundException(moveName);

        behaviors.put(player, moves.get(moveName));
    }

    public String getSessionId() {
        return sessionId;
    }
}