package softeer2nd.chess;

/**
 * 체스 보드에 사용되는 위치 좌표
 */
public class Position {
    /**
     * 체스 보드의 위치 좌표로 사용될 수 있는 정규식
     */
    private static final String LEGAL_EXPRESSION_POSITION = "[a-h][1-8]";

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

    /**
     * Position 인스턴스 생성 팩토리 메서드
     * @param expression 체스 보드판의 위치 좌표
     * @return Position
     */
    public static Position create(String expression) {
        if (!expression.matches(LEGAL_EXPRESSION_POSITION)) {
            throw new IllegalArgumentException("잘못된 위치입니다.");
        }
        int x = Board.SIZE - Character.getNumericValue(expression.charAt(1));
        int y = expression.charAt(0) - 'a';
        return new Position(x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
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