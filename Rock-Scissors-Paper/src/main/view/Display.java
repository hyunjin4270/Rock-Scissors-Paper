package main.view;

import main.member.RpsMove;
import main.member.User;

public interface Display {
    void printTurn(User user);

    void printWinner(User user);
}
