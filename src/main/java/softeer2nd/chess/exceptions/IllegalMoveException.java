package softeer2nd.chess.exceptions;

public class IllegalMoveException extends RuntimeException {
    public IllegalMoveException() {
    }

    public IllegalMoveException(String message) {
        super(message);
    }
}