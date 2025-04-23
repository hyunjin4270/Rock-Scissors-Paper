package main.service;

import main.domain.move.RpsMove;
import main.repository.MoveRepository;

import java.util.List;

public class HomeService {
    private final MoveRepository moveRepository;

    public HomeService(MoveRepository moveRepository) {
        this.moveRepository = moveRepository;
    }

    public List<RpsMove> findMoveMembers() {
        return moveRepository.findAll();
    }
}
