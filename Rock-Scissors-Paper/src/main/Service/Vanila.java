package main.Service;

import main.controller.InputHandler;
import main.member.player.Computer;
import main.member.player.User;
import main.member.move.RpsMove;
import main.view.Display;

import java.util.List;

public class Vanila implements RpsRule {
    Display display;
    InputHandler inputHandler;
    List<RpsMove> moves;


    @Override
    public void execute(List<User> users) {
        for (User user : users) {

        }
    }

    private User defineWinner(User user1, User user2) {
        switch (user1.getMove().compare(user2.getMove())) {
            case WIN -> {
                return user1;
            }
            case LOSE -> {
                return user2;
            }
            default -> {
                return null;
            }
        }
    }
}
