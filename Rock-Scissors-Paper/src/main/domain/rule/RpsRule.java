package main.domain.rule;

import main.domain.player.User;

import java.util.List;

public interface RpsRule {
    public void execute(List<User> users);
}
