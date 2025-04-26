package main.service;

import main.domain.move.RpsMove;

import java.util.Map;

public class MoveCatalog {
    private final Map<String, RpsMove> moves;

    public MoveCatalog(Map<String, RpsMove> moves) {
        this.moves = moves;
    }
}
