package main.exception;

public class MoveNotFoundException extends RuntimeException {
    public MoveNotFoundException(String message) {
        super("해당 무브를 찾을 수 없습니다: " + message);
    }
}
