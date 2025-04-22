package main.member.move;

import main.Service.Outcome;

import java.util.List;

public interface RpsMove {
    String getName();
    Outcome compare(RpsMove other);
    List<String> asEmoji();
}
