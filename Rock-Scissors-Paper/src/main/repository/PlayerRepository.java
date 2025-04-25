package main.repository;

import main.domain.player.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository {
    Player save(Player player);
    Optional<Player> removeByName(String name);
    Optional<Player> findByName(String name);
    int count();
    List<Player> findAll();
}
