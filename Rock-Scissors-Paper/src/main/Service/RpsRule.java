package main.Service;

import main.member.player.User;

import java.util.List;

public interface RpsRule {
    public void execute(List<User> users);
}
