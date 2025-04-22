package main.repository;

import main.member.player.Player;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository {
    boolean save(Player player);
    boolean removeByName(String name);
    Optional<Player> findByName(String name);
    List<Player> findAll();
}
