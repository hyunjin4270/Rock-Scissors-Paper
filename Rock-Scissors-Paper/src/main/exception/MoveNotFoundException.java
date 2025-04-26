package main.exception;

public class MoveNotFoundException extends RuntimeException {
    public MoveNotFoundException(String message) {
        super("can`t find move: " + message);
    }
}
