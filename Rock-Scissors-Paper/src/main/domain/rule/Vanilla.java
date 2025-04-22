package main.domain.rule;

import main.controller.InputHandler;
import main.domain.player.User;
import main.repository.MoveRepository;
import main.repository.PlayerRepository;
import main.view.Display;

import java.util.List;

public class Vanilla implements RpsRule {
    Display display;
    InputHandler inputHandler;
    MoveRepository moveRepository;
    PlayerRepository playerRepository;

    public Vanilla(
            Display display,
            InputHandler inputHandler,
            MoveRepository moveRepository,
            PlayerRepository playerRepository) {

        this.display = display;
        this.inputHandler = inputHandler;
        this.moveRepository = moveRepository;
        this.playerRepository = playerRepository;
    }

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
