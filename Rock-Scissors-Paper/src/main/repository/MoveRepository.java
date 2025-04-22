package main.repository;

import main.member.move.RpsMove;

import java.util.List;
import java.util.Optional;

public interface MoveRepository {
    RpsMove save(RpsMove move);
    boolean removeByName(String name);
    Optional<RpsMove> findByName(String name);
    List<RpsMove> findAll();
}
