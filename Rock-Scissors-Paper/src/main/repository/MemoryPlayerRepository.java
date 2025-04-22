package main.repository;

import main.member.player.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class MemoryPlayerRepository implements PlayerRepository {
    Map<String, Player> store;

    public MemoryPlayerRepository(Map<String, Player> store) {
        this.store = store;
    }

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
