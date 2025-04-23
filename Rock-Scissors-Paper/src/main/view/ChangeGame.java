package main.view;

public class ChangeGame {
    public void ruleList(String name, String subMessage) {
        System.out.println(name + " : " + subMessage);
    }

    public void inputList() {
        System.out.println("게임 변경 : change 'rule name' | 나가기 : exit");
    }

    public void changeMessage(String ruleName) {
        System.out.println("게임모드 가 " + ruleName + "로 변경되었습니다.");
    }

    public void currentRule(String ruleName) {
        System.out.println("현재 게임모드 : " + ruleName);
    }
}
