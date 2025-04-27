package main.domain.move;

import java.util.Map;

public final class DefaultMoves {
    private DefaultMoves() {
    }

    private static final Map<String, RpsMove> DEFAULT_MOVE_MAP = Map.of(
            "rock", new Rock(),
            "paper", new Paper(),
            "scissors", new Scissors()
    );

    public static Map<String, RpsMove> get() {
        return DEFAULT_MOVE_MAP;
    }
}
