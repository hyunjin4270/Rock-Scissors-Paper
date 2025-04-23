package main.service;

import main.domain.move.RpsMove;
import main.domain.player.Player;
import main.repository.MoveRepository;
import main.repository.PlayerRepository;

import java.util.List;
import java.util.NoSuchElementException;

public class GameService {
    private final MoveRepository moveRepository;
    private final PlayerRepository playerRepository;
    public GameService(MoveRepository moveRepository, PlayerRepository playerRepository) {
        this.moveRepository = moveRepository;
        this.playerRepository = playerRepository;
    }

    public List<RpsMove> findMoveMembers() {
        return moveRepository.findAll();
    }
    public RpsMove findMoveOne(String key) {
        return moveRepository.findByName(key)
                .orElseThrow(() -> new NoSuchElementException("해당 이름의 RpsMove가 존재하지 않습니다: " + key));
    }
}