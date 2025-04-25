package test.domain.move;

import main.domain.Outcome;
import main.domain.move.Paper;
import main.domain.move.Rock;
import main.domain.move.RpsMove;
import main.domain.move.Scissors;

public class MoveTest {
    static RpsMove paper = new Paper();
    static RpsMove scissors = new Scissors();
    static RpsMove rock = new Rock();

    private static void movesWinTest() {
        assert paper.compare(rock) == Outcome.WIN : "paper win failed";
        assert scissors.compare(paper) == Outcome.WIN : "scissors win failed";
        assert rock.compare(scissors) == Outcome.WIN : "rock win failed";
        System.out.println("moveWinTest 통과");
    }

    private static void movesDrawTest() {
        assert paper.compare(paper) == Outcome.DRAW : "paper draw failed";
        assert scissors.compare(scissors) == Outcome.DRAW : "scissors draw failed";
        assert rock.compare(rock) == Outcome.DRAW : "rock draw failed";
        System.out.println("moveDrawTest 통과");
    }

    private static void movesLoseTest() {
        assert paper.compare(scissors) == Outcome.LOSE : "paper lose failed";
        assert scissors.compare(rock) == Outcome.LOSE : "scissors lose failed";
        assert rock.compare(paper) == Outcome.LOSE : "rock lose failed";
        System.out.println("moveLoseTest 통과");
    }

    public static void main(String[] args) {
        movesWinTest();
        movesLoseTest();
        movesDrawTest();
    }
}
