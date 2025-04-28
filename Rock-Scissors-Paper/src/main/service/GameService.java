package main.service;

import main.domain.GameSession;
import main.domain.move.RpsMove;
import main.domain.player.Player;
import main.domain.rule.RpsRule;
import main.domain.strategy.Strategy;
import main.exception.MoveNotFoundException;
import main.repository.MoveRepository;
import main.repository.PlayerRepository;
import main.session.SessionManager;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GameService {
    private final MoveRepository moveRepository;
    private final PlayerRepository playerRepository;
    private final SessionManager sessionManager;
//    private final MoveCatalog moveCatalog;

    public GameService(MoveRepository moveRepository, PlayerRepository playerRepository, SessionManager sessionManager) {
        if (moveRepository == null) throw new IllegalArgumentException("moveRepository is null");
        if (sessionManager == null) throw new IllegalArgumentException("sessionManager is null");
        if (playerRepository == null) throw new IllegalArgumentException("playerRepository is null");
        this.moveRepository = moveRepository;
        this.sessionManager = sessionManager;
        this.playerRepository = playerRepository;
    }


    /**
     * 새 게임을 시작하고, 생성된 세션 ID를 반환합니다.
     *
     * @param selectedMoveNames 사용자가 고른 무브 이름 리스트
     * @param rule              사용할 룰
     * @param strategy          사용할 전략
     * @return 새로 생성된 GameSession의 ID
     * @throws IllegalArgumentException 입력 파라미터가 잘못된 경우
     * @throws IllegalStateException    전제 조건이 충족되지 않거나 세션 생성에 실패한 경우
     * @throws MoveNotFoundException    무브 값이 잘못된 경우
     */
    public String start(List<String> selectedMoveNames, RpsRule rule, Strategy strategy) {
        if (selectedMoveNames == null) throw new IllegalArgumentException("selectedMoveNames is null");
        if (selectedMoveNames.size() < 3) throw new IllegalStateException("must select at least three distinct moves");
        if (rule     == null) throw new IllegalArgumentException("rule is null");
        if (strategy == null) throw new IllegalArgumentException("strategy is null");

        Map<String, RpsMove> selectedMap = new HashMap<>();
        for (String selectedMoveName : selectedMoveNames) {
            RpsMove rpsMove = moveRepository.findByName(selectedMoveName)
                    .orElseThrow(() -> new MoveNotFoundException("move not founded"));

            selectedMap.put(selectedMoveName, rpsMove);
        }

//        Map<String, RpsMove> selectedMap = selectedMoveNames.stream()
//                .collect(Collectors.toMap(
//                        Function.identity(),
//                        name -> moveRepository.findByName(name)
//                                .orElseThrow(() -> new MoveNotFoundException("move not founded"))
//                        )
//                );

        String sessionId = sessionManager.createId();
        GameSession session;
        session = new GameSession(selectedMap, rule, strategy);
        sessionManager.create(session, sessionId);
        return sessionId;
    }
}