package main.Service;

import main.member.RpsMove;
import main.member.User;
import main.view.DefaultDisplay;
import main.view.Display;

import static main.Service.Outcome.*;

public class Vanila implements RpsRule {
    Display display = new DefaultDisplay();
    @Override
    public void execute() {
        display.printTurn()
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
