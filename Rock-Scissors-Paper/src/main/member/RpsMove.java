package main.member;

import main.Service.Outcome;

import java.util.List;

public interface RpsMove {
    Outcome compete(RpsMove move);
    List<String> asEmoji();
}
