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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class GameService {
    private final MoveRepository moveRepository;
    private final PlayerRepository playerRepository;
    private final SessionManager sessionManager;

    public GameService(MoveRepository moveRepository, PlayerRepository playerRepository, SessionManager sessionManager) {
        if (moveRepository == null) throw new IllegalArgumentException("moveRepository is null");
        if (sessionManager == null) throw new IllegalArgumentException("sessionManager is null");
        if (playerRepository == null) throw new IllegalArgumentException("playerRepository is null");
        this.moveRepository = moveRepository;
        this.sessionManager = sessionManager;
        this.playerRepository = playerRepository;
    }


    /**
     * 유효성 사전검증
     */
    public void validatePreconditions() {
        if (moveRepository.count() < 3) throw new IllegalStateException("MoveRepository element cannot be less than three.");
        if (playerRepository.count() < 2) throw new IllegalStateException("You can't have only one player.");
    }




}