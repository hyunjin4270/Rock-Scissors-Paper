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
     * 해당 플레이어에 대한 무브 등록
     * @throws IllegalStateException 플레이어 선택이 이미 이루어짐
     * @throws MoveNotFoundException 존재하지 않는 무브를 고름
     * @param player 라운드 플레이어 (null 불가)
     * @param moveName 플레이어가 제시한 무브 (null 또는 존재하지 않는 무브 불가)
     */
    public void chooseMove(Player player, String moveName) {
        if (participants.containsKey(player)) throw new IllegalStateException(player.getname() + "는 이미 선택을 완료했음");
        RpsMove move = moveRepository.findByName(moveName)
                .orElseThrow(() -> new MoveNotFoundException(moveName));

        participants.put(player, move);
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