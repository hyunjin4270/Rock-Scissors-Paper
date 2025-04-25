package main.domain.rule;

import main.domain.GameResult;
import main.domain.Outcome;
import main.domain.move.Paper;
import main.domain.move.RpsMove;
import main.domain.player.Player;
import main.domain.player.User;

import java.util.List;
import java.util.Map;

public interface RpsRule {
    public GameResult decide(Map<Player, RpsMove> players);
    public String getName();
}
