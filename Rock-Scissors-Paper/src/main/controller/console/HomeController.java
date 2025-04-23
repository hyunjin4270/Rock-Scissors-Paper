package main.controller.console;

import main.domain.player.Player;
import main.service.GameService;
import main.service.HomeService;


import java.util.Scanner;

import static main.view.ConsoleViewHelper.*;
import static main.view.Home.*;

public class HomeController {
    private final HomeService homeService;
    private final Scanner scanner;
    private final Player host;
    public HomeController(Scanner scanner, HomeService homeService, Player host) {
        this.scanner = scanner;
        this.homeService = homeService;
        this.host = host;
    }

    public void execute() {
        while (true) {
            home();
            userPrompt(host.getname());
            String input = scanner.nextLine();

            if (!isVaild(input)) {
                errorMessage();
                continue;
            }

            //homeService 적용
        }
    }

    private boolean isVaild(String input) {
        if (!isInteger(input)) return false;
        return true;
    }

    private boolean isInteger(String input) {
        return input.matches("^-?\\d+$");
    }









}
