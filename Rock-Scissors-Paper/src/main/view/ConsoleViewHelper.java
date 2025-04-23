package main.view;

public class ConsoleViewHelper {
    public void userPrompt(String name) {
        System.out.printf("[%9s] : ", name);
    }
    public void errorMessage() {
        System.out.println("잘못된 입력입니다.");
    }
}
