package main.domain.player;

import main.domain.move.RpsMove;

import java.util.Map;

public class User implements Player {
    private String name;

    public User(String name) {
        this.name = name;
    }

    public User() {
        this.name = name;
    }


    @Override
    public String getName() {
        return name;
    }

}
