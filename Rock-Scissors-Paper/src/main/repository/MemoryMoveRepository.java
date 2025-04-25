package main.repository;

import main.domain.move.RpsMove;

import java.util.*;

public class MemoryMoveRepository implements MoveRepository {
    private Map<String, RpsMove> store = new HashMap<>();


    @Override
    public RpsMove save(RpsMove move) {
        if (move == null) throw new IllegalArgumentException("move is null");
        store.put(move.getName(), move);
        return move;
    }

    public Optional<RpsMove> removeByName(String name) {
        return Optional.ofNullable(store.remove(name));
    }




    @Override
    public Optional<RpsMove> findByName(String name) {
        return Optional.ofNullable(store.get(name));
    }

    @Override
    public int count() {
        return store.size();
    }


    @Override
    public List<RpsMove> findAll() {
        return new ArrayList<>(store.values());
    }

}
