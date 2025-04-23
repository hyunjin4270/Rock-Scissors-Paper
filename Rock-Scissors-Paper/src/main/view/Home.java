package main.view;

import main.controller.InputHandler;
import main.controller.OutputHandler;
import main.domain.player.User;

public class Home {
    private final InputHandler inputHandler;
    private final OutputHandler outputHandler;

    public Home(InputHandler inputHandler, OutputHandler outputHandler) {
        this.inputHandler = inputHandler;
        this.outputHandler = outputHandler;
    }

    public void home() {
        System.out.println("==================================================================");
        System.out.println("1. 시작 | 2. 플레이어 목록 | 3. 게임 변경 | 4. 사용자 정보 변경 | 5. 종료");
        System.out.println("==================================================================");
    }

    public void userInputMessage() {
        System.out.printf("[%9s] : ", outputHandler.sendUserName());
    }
}
