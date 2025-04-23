package main.view;

public class ConsoleViewHelper {
    public static void userPrompt(String name) {
        System.out.printf("[%9s] : ", name);
    }
    public static void errorMessage() {
        System.out.println("잘못된 입력입니다.");
    }
}
