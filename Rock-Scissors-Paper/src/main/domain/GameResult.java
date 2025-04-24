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
        if (participants.isEmpty()) throw new IllegalArgumentException("참가자 목록이 비어있음");

        Map<Player, Outcome> players = new HashMap<>();
        for (Player participant : participants) {
            players.put(participant, DRAW);
        }
        return new GameResult(players);
    }

    public static GameResult result(List<Player> winner, List<Player> loser) {
        if (winner.isEmpty()) throw new IllegalArgumentException("승리자 목록이 비어있음");
        if (loser.isEmpty()) throw new IllegalArgumentException("패배자 목록이 비어있음");

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
