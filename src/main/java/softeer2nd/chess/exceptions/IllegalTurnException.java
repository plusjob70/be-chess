package softeer2nd.chess.exceptions;

public class IllegalTurnException extends RuntimeException {
    public IllegalTurnException() {
    }

    public IllegalTurnException(String message) {
        super(message);
    }
}