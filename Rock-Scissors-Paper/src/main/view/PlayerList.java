package main.view;

import main.controller.InputHandler;
import main.controller.OutputHandler;

public class PlayerList {
    public void PlayerList(String name) {
        System.out.printf("| %12s |", name);
        System.out.println();
    }

    public void inputList() {
        System.out.println("AI 추가 : add 'name'| AI 제거 : delete 'name'| 리스트 다시보기 'list'| 나가기 : exit");
    }

    public void printAddMessage(String name) {
        System.out.println(name + "이(가) 추가되었습니다.");
    }

    public void printDeleteMessage(String name) {
        System.out.println(name + "이(가) 삭제되었습니다.");
    }

    public void errorAddMessage() {
        System.out.println("해당 이름을 사용할 수 없습니다.");
    }
    public void errorDeleteMessage() {
        System.out.println("해당 AI를 찾을 수 없습니다.");
    }
}
