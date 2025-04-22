package main.view;

import main.domain.player.User;

public interface Display {
    void printTurn(User user);

    void printWinner(User user);
}
