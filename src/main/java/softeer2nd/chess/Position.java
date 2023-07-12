package softeer2nd.chess;

import softeer2nd.chess.exceptions.BoardIndexOutOfRangeException;
import softeer2nd.chess.exceptions.IllegalIndexExpressionException;

/**
 * 체스 보드에 사용되는 위치 좌표
 */
public class Position {
    /**
     * 체스보드 칸에 접근하는 표현식은 <알파벳 소문자 + 숫자>이다.
     */
    public static final String EXPRESSION_POSITION = "[a-z]\\d+";

    /**
     * 체스 보드의 x 좌표(row)
     */
    private final int x;

    /**
     * 체스 보드의 y 좌표(column)
     */
    private final int y;

    /**
     * Position은 외부에서 생성자로 생성할 수 없다.
     * @param x  x 좌표
     * @param y  y 좌표
     */
    private Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    /**
     * Position 인스턴스 생성 팩토리 메서드
     * @param expression 체스 보드판의 위치 좌표
     * @return Position
     */
    public static Position create(String expression) {
        validateExpression(expression);
        int x = Board.SIZE - Character.getNumericValue(expression.charAt(1));
        int y = expression.charAt(0) - 'a';
        return new Position(x, y);
    }

    /**
     * Position 인스턴스 생성 팩토리 메서드
     * @param x x 좌표
     * @param y y 좌표
     * @return Position
     */
    public static Position create(int x, int y) {
        validateIndex(x, y);
        return new Position(x, y);
    }

    /**
     * x좌표, y좌표가 체스 보드의 인덱스에 유효한지 검사
     * @param x x좌표
     * @param y y좌표
     * @return true if valid else false
     */
    public static boolean inRange(int x, int y) {
        return 0 <= x && x < Board.SIZE && 0 <= y && y < Board.SIZE;
    }

    /**
     * 체스 보드 위치 표현식 유효성 검사
     * @param expression 표현식
     * @throws IllegalIndexExpressionException 위치 표현식이 아닐 때
     * @throws BoardIndexOutOfRangeException 위치 표현식이 체스 보드의 인덱스 범위를 벗어날 때
     */
    private static void validateExpression(String expression) {
        if (!expression.matches(EXPRESSION_POSITION)) {
            throw new IllegalIndexExpressionException(expression);
        }
        if (!expression.matches("[a-h][1-8]")) {
            throw new BoardIndexOutOfRangeException(expression);
        }
    }

    /**
     * 체스 보드 인덱스 유효성 검사
     * @param x x 좌표
     * @param y y 좌표
     * @throws BoardIndexOutOfRangeException 인덱스가 범위를 벗어날 때
     */
    private static void validateIndex(int x, int y) {
        if (inRange(x, y)) {
            return;
        }
        throw new BoardIndexOutOfRangeException(x, y);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (x != position.x) return false;
        return y == position.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}