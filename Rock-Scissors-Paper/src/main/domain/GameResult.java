package main.domain;

import main.domain.player.Player;

import java.util.*;

import static main.domain.Outcome.*;

public class GameResult {
    private final Map<Player, Outcome> playerResult;

    private GameResult(Map<Player, Outcome> playerResult) {
        this.playerResult = Collections.unmodifiableMap(playerResult);
    }

    public static GameResult draw(List<Player> participants) {
        if (participants == null) throw new IllegalArgumentException("participants is null");
        if (participants.isEmpty()) throw new IllegalStateException("participants is empty");

        Map<Player, Outcome> players = new HashMap<>();
        for (Player participant : participants) {
            players.put(participant, DRAW);
        }
        return new GameResult(players);
    }

    public static GameResult result(List<Player> winner, List<Player> loser) {
        if (winner == null) throw new IllegalArgumentException("winner is null");
        if (loser == null) throw new IllegalArgumentException("loser is null");
        if (winner.isEmpty()) throw new IllegalStateException("winner is empty");
        if (loser.isEmpty()) throw new IllegalStateException("loser is empty");

        Map<Player, Outcome> players = new HashMap<>();
        for (Player player : winner) {
                players.put(player, WIN);
        }
        for (Player player : loser) {
            players.put(player, LOSE);
        }
        return new GameResult(players);
    }
    public Map<Player, Outcome> getPlayerResult() {
        return playerResult;
    }
}
