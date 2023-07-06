package softeer2nd.chess;

public class Position {

    private static final String LEGAL_EXPRESSION = "[a-h][1-8]";
    private static final int MAX_POSITION = 8;

    private final int x;
    private final int y;

    private Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Position create(String expression) {
        if (!expression.matches(LEGAL_EXPRESSION)) {
            throw new IllegalArgumentException("잘못된 위치입니다.");
        }

        int x = MAX_POSITION - Character.getNumericValue(expression.charAt(1));
        int y = expression.charAt(0) - 'a';
        return new Position(x, y);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
