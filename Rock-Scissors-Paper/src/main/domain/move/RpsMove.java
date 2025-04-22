package main.domain.move;

import main.Outcome;

import java.util.List;

public interface RpsMove {
    String getName();
    Outcome compare(RpsMove other);
    List<String> asEmoji();
}
