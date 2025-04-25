package main.repository;

import main.domain.player.Player;

import java.util.*;

public class MemoryPlayerRepository implements PlayerRepository {
    Map<String, Player> store = new HashMap<>();


    @Override
    public boolean save(Player player) {
        if (player == null) return false;
        store.put(player.getName(), player);
        return true;
    }

    public Optional<Player> removeByName(String name) {
        return Optional.ofNullable(store.remove(name));
    }


    @Override
    public Optional<Player> findByName(String name) {
        return Optional.ofNullable(store.get(name));
    }

    @Override
    public int count() {
        return store.size();
    }

    @Override
    public List<Player> findAll() {
        return new ArrayList<>(store.values());
    }
}
