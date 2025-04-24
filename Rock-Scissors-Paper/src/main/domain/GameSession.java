package main.domain;

import main.domain.move.RpsMove;
import main.domain.player.Player;
import main.domain.rule.RpsRule;
import main.domain.strategy.RpsMoves;
import main.domain.strategy.Strategy;
import main.exception.MoveNotFoundException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameSession {
    private final List<Player> players;
    private final Map<String, RpsMove> moves;
    private final RpsRule rule;
    private final Map<Player, RpsMove> behaviors = new HashMap<>();
    private final Strategy strategy;

    public GameSession(List<Player> players, Map<String, RpsMove> moves, RpsRule rule, Strategy strategy) {
        this.players = players;
        this.moves = moves;
        this.rule = rule;
        this.strategy = strategy;
    }

    public RpsMove computerStrategy(Player player, Map<String, RpsMove> moves, Strategy strategy) {

    }

    /**
     * 해당 플레이어에 대한 무브 등록
     * @throws IllegalStateException 플레이어 선택이 이미 이루어짐
     * @throws MoveNotFoundException 존재하지 않는 무브를 고름
     * @param player 라운드 플레이어 (null 불가)
     * @param moveName 플레이어가 제시한 무브 (null 또는 존재하지 않는 무브 불가)
     */
    public void chooseMove(Player player, String moveName) {
        if (players.contains(player)) throw new IllegalStateException(player.getname() + "는 이미 선택을 완료했음");
        if (!moves.containsKey(moveName)) throw new MoveNotFoundException(moveName);
        behaviors.put(player, moves.get(moveName));
    }
}