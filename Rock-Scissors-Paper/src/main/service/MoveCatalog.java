package main.service;

import main.domain.move.DefaultMoves;
import main.domain.move.RpsMove;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MoveCatalog {
    private final Map<String, RpsMove> moves;

    public MoveCatalog(Map<String, RpsMove> moves) {
        this.moves = moves;
    }

    public static MoveCatalog withDefaults() {
        return new MoveCatalog(DefaultMoves.get());
    }

    public Optional<RpsMove> findByName(String key) {
        return Optional.ofNullable(moves.get(key));
    }

    public List<RpsMove> findAll() {
        return new ArrayList<>(moves.values());
    }

    public Map<String, RpsMove> findAllAsMap() {
        return moves;
    }

    public RpsMove addMove(RpsMove move) {
        if (move == null) throw new IllegalArgumentException("move is null");
        if (moves.containsKey(move.getName())) throw new IllegalStateException("move is already contain");
        return moves.put(move.getName(), move);
    }

    public Optional<RpsMove> removeByName(String name) {
        return Optional.ofNullable(moves.remove(name));
    }


}
