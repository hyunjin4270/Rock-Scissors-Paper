package main.repository;

import main.domain.move.RpsMove;

import java.util.*;

public class MemoryMoveRepository implements MoveRepository {
    private Map<String, RpsMove> store = new HashMap<>();


    @Override
    public boolean save(RpsMove move) {
        if (move == null) return false;
        store.put(move.getName(), move);
        return true;
    }

    @Override
    public boolean removeByName(String name) {
        return store.remove(name) != null;
    }


    @Override
    public Optional<RpsMove> findByName(String name) {
        return Optional.ofNullable(store.get(name));
    }


    @Override
    public List<RpsMove> findAll() {
        return new ArrayList<>(store.values());
    }

}
