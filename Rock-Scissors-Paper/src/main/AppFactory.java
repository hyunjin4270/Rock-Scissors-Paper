package main;

import main.controller.ConsoleInputHandler;
import main.controller.InputHandler;
import main.domain.rule.Vanilla;
import main.repository.MemoryMoveRepository;
import main.repository.MemoryPlayerRepository;
import main.repository.MoveRepository;
import main.repository.PlayerRepository;
import main.domain.rule.RpsRule;

public class AppFactory {
    private static final MoveRepository moveRepository = new MemoryMoveRepository();
    private static final PlayerRepository playerRepository = new MemoryPlayerRepository();
    private static final InputHandler inputHandler = new ConsoleInputHandler();
    private static RpsRule rpsRule = new Vanilla();

}
