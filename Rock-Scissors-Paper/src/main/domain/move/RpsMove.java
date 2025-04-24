package main.domain.move;

import main.domain.Outcome;

import java.util.List;

public interface RpsMove {
    String getName();
    Outcome compare(RpsMove other);
    List<String> asEmoji();
}
