package main.repository;

import main.domain.move.RpsMove;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface MoveRepository {
    RpsMove save(RpsMove move);
    Optional<RpsMove> removeByName(String name);
    Optional<RpsMove> findByName(String name);
    int count();
    List<RpsMove> findAll();
}
