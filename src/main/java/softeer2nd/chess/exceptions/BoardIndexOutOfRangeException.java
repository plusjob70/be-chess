package softeer2nd.chess.exceptions;

/**
 * 체스 보드 범위 외에 인덱스에 접근
 */
public class BoardIndexOutOfRangeException extends RuntimeException {
    public BoardIndexOutOfRangeException(String index) {
        super("'" + index + "'" + "는 체스보드 좌표 범위가 아닙니다.");
    }
    public BoardIndexOutOfRangeException(int x, int y) {
        super("'(" + x + ", " + y + ")'" + "는 체스보드 좌표 범위가 아닙니다.");
    }
}