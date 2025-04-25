package main.domain.rule;

import main.domain.GameResult;
import main.domain.Outcome;
import main.domain.move.RpsMove;
import main.domain.player.Player;
import main.domain.player.User;
import main.repository.MoveRepository;

import java.io.IOException;
import java.util.*;

import static main.domain.Outcome.WIN;

/**
 * 기본적인 가위바위보입니다. 2명 이상의 플레이어 무브의 승패를 가려 승자와 패자를 가립니다.
 */
public class Vanilla implements RpsRule {
    @Override
    public GameResult decide(Map<Player, RpsMove> players) {
        if (players == null) throw new IllegalArgumentException("players is null");
        if (players.isEmpty()) throw new IllegalStateException("players is empty");
        for (Map.Entry<Player, RpsMove> e : players.entrySet()) {
            if (e.getKey()   == null) throw new IllegalStateException("players can't get null key");
            if (e.getValue() == null) throw new IllegalStateException("players can't get null value");
        }
        Set<RpsMove> unique = new HashSet<>(players.values());
        if(isDraw(unique)) return GameResult.draw(new ArrayList<>(players.keySet()));
        return determineWinner(unique, players);
    }

    /**
     * 플레이어 무브들을 판별하여 무승부 상황인지 결정합니다.
     * @param unique 중복이 제거된 무브들의 종류
     * @return 무승부 상황인지 반환
     */
    private boolean isDraw(Set<RpsMove> unique) {
        return unique.size() != 2;
    }

    /**
     * 플레이어들 중 승자와 패자를 판별
     * @param unique 중복이 제거된 무브들의 종류
     * @param players 플레이어 무브
     * @return 게임결과값
     */
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