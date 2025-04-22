package main.view;

import main.member.User;

public class DefaultDisplay implements Display {
    @Override
    public void printTurn(User user) {
        System.out.println(user.getName() + " turn");
        System.out.printf("[%9s] : ", user.getName());
    }

    @Override
    public void printWinner(User user) {
        System.out.printf("Winner is %s!", user.getName());
        System.out.println();
        System.out.println();
    }
}
