package softeer2nd.chess.exceptions;

public class IllegalInputException extends RuntimeException {
    public IllegalInputException() {
        super("잘못된 입력 형식입니다.");
    }
}