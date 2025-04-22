package main.view;

import main.member.player.User;

public interface Display {
    void printTurn(User user);

    void printWinner(User user);
}
