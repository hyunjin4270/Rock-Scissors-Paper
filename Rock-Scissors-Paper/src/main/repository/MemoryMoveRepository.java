package main.repository;

import main.member.move.RpsMove;

import java.util.*;

public class MemoryMoveRepository implements MoveRepository {
    private Map<String, RpsMove> store;

    @Override
    public RpsMove save(RpsMove move) {
        return null;
    }

    @Override
    public boolean removeByName(String name) {
        for (String key : store.keySet()) {
            if (!key.equals(name)) continue;

            store.remove(key);
            return true;
        }
        return false;
    }

    @Override
    public Optional<RpsMove> findByName(String name) {
        for (String key : store.keySet()) {
            if (!key.equals(name)) continue;
            return Optional.of(store.get(key));
        }
        return Optional.empty();
    }

    @Override
    public List<RpsMove> findAll() {
        return new ArrayList<>(store.values());
    }

}
