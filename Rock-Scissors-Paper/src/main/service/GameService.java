package main.service;

import main.domain.GameSession;
import main.domain.move.RpsMove;
import main.domain.player.Player;
import main.domain.rule.RpsRule;
import main.domain.strategy.Strategy;
import main.exception.MoveNotFoundException;
import main.repository.MoveRepository;
import main.repository.PlayerRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class GameService {
    private final MoveRepository moveRepository;
    private final PlayerRepository playerRepository;
    private GameSession gameSession;
    private Strategy strategy;
    private RpsRule rule;
    public GameService(MoveRepository moveRepository, PlayerRepository playerRepository) {
        if (moveRepository == null) throw new IllegalArgumentException("moveRepository is null");
        if (playerRepository == null) throw new IllegalArgumentException("playerRepository is null");
        this.moveRepository = moveRepository;
        this.playerRepository = playerRepository;
    }


    /**
     * 유효성 사전검증
     */
    public void validatePreconditions() {
        if (moveRepository.count() < 3) throw new IllegalStateException("MoveRepository element cannot be less than three.");
        if (playerRepository.count() < 2) throw new IllegalStateException("You can't have only one player.");
    }

    /**
     * 플레이어 리스트 생성
     * @return 리스트 형태의 플레이어
     */
    public List<Player> createPlayerList() {
        return ;
    }

    /**
     * 무브 리스트 생성
     * @return 맵 형태의 무브
     */
    public Map<String, RpsMove> createMoveMap() {
        ;
    }

    public void start() {
        List<Player> playerList = playerRepository.findAll();
        List<RpsMove> moves = moveRepository.findAll();
        Map<String, RpsMove> moveMap = new HashMap<>();
        for (RpsMove move : moves) {
            moveMap.put(move.getName(), move);
        }
        gameSession = new GameSession(playerList, moveMap, rule, strategy);
    }

    public void choose(Player player, String moveName) {
        if (player == null) throw new IllegalArgumentException("player is null");
        if (moveName == null) throw new IllegalArgumentException("moveName is null");
        gameSession.chooseMove(player, moveName);
    }

    public void autoChoose(Player computer) {
        RpsMove selected = gameSession.computerStrategy(computer);
    }

}