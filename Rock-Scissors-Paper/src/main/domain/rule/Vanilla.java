package main.domain.rule;

import main.domain.GameResult;
import main.domain.Outcome;
import main.domain.move.RpsMove;
import main.domain.player.Player;
import main.domain.player.User;
import main.repository.MoveRepository;

import java.util.*;

import static main.domain.Outcome.WIN;

/**
 * 기본적인 가위바위보입니다. 2명 이상의 플레이어 무브의 승패를 가려 승자와 패자를 가립니다.
 */
public class Vanilla implements RpsRule {
    @Override
    public GameResult decide(Map<Player, RpsMove> players) {
        Set<RpsMove> unique = Set.copyOf(players.values());
        if(isDraw(unique)) return GameResult.draw(new ArrayList<>(players.keySet()));
        return determineWinner(unique, players);
    }

    private boolean isDraw(Set<RpsMove> unique) {
        return unique.size() != 2;
    }

    private GameResult determineWinner(Set<RpsMove> unique, Map<Player, RpsMove> players) {
        Iterator<RpsMove> moves = unique.iterator();
        List<Player> winners = new ArrayList<>();
        List<Player> losers = new ArrayList<>();
        RpsMove winningMove;

        RpsMove m1 = moves.next();
        RpsMove m2 = moves.next();
        winningMove = (m1.compare(m2) == WIN) ? m1 : m2;

        for (Map.Entry<Player, RpsMove> entry : players.entrySet()) {
            if (entry.getValue().equals(winningMove)) {
                winners.add(entry.getKey());
            } else {
                losers.add(entry.getKey());
            }
        }

        return GameResult.result(winners, losers);
    }
}
