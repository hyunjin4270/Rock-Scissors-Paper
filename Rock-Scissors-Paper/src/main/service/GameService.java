package main.service;

import main.domain.move.RpsMove;
import main.domain.player.Player;
import main.exception.MoveNotFoundException;
import main.repository.MoveRepository;
import main.repository.PlayerRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class GameService {
    private final MoveRepository moveRepository;
    private final PlayerRepository playerRepository;
    public GameService(MoveRepository moveRepository, PlayerRepository playerRepository) {
        this.moveRepository = moveRepository;
        this.playerRepository = playerRepository;
    }



    /**
     * 유효성 사전검증
     * @throws IllegalStateException 등록된 무브가 1개도 없거나 플레이어가 2명보다 적음
     */
    private void validatePreconditions() {
        if (moveRepository.count() == 0) throw new IllegalStateException("등록된 move 없음");
        if (playerRepository.count() < 2) throw new IllegalStateException("플레이어가 최소 2명 이상이여야 함");
    }
}