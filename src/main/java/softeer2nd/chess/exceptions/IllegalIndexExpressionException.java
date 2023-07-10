package softeer2nd.chess.exceptions;

public class IllegalIndexExpressionException extends RuntimeException {
    public IllegalIndexExpressionException(String expression) {
        super("'" + expression + "'" + "은 체스 보드의 인덱스가 아닙니다.");
    }
}
