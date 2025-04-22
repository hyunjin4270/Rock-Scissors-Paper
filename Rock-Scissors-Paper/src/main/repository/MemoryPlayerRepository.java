package main.repository;

import main.domain.player.Player;

import java.util.*;

public class MemoryPlayerRepository implements PlayerRepository {
    Map<String, Player> store = new HashMap<>();


    @Override
    public boolean save(Player player) {
        if (player == null) return false;
        store.put(player.getname(), player);
        return true;
    }

    @Override
    public boolean removeByName(String name) {
        return store.remove(name) != null;
    }

    @Override
    public Optional<Player> findByName(String name) {
        return Optional.ofNullable(store.get(name));
    }

    @Override
    public List<Player> findAll() {
        return new ArrayList<>(store.values());
    }
}
