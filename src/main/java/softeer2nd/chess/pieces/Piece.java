package softeer2nd.chess.pieces;

import softeer2nd.chess.Position;

import static softeer2nd.chess.pieces.Piece.Color.BLACK;
import static softeer2nd.chess.pieces.Piece.Color.WHITE;
import static softeer2nd.chess.pieces.Piece.Type.NO_PIECE;

/**
 * Piece 클래스는 추상 클래스이며 팩토리 메서드를 가진다.
 */
public abstract class Piece implements Comparable<Piece> {
    /**
     * 기물의 색상
     */
    private final Color color;

    /**
     * 기물의 종류
     */
    private final Type type;

    protected Piece(Color color, Type type) {
        this.color = color;
        this.type = type;
    }

    public Color getColor() {
        return color;
    }

    public Type getType() {
        return type;
    }

    public boolean isBlack() {
        return color.equals(BLACK);
    }

    public boolean isWhite() {
        return color.equals(WHITE);
    }

    public boolean isBlank() {
        return type.equals(NO_PIECE);
    }

    public boolean isType(Type type) {
        return this.type.equals(type);
    }

    public boolean isColor(Color color) {
        return this.color.equals(color);
    }

    public boolean hasSameType(Piece target) {
        return this.type.equals(target.type);
    }

    public boolean hasSameColor(Piece target) {
        return this.color.equals(target.color);
    }

    public boolean hasDifferentColor(Piece target) {
        return !hasSameColor(target);
    }

    /**
     * 해당하는 기물의 행마법 유효성 검사
     * @param source 현재 기물의 위치
     * @param destination 기물이 이동할 위치
     */
    public abstract void verifyMovePosition(Position source, Position destination);

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Piece piece = (Piece) o;

        if (color != piece.color) return false;
        return type == piece.type;
    }

    @Override
    public int hashCode() {
        int result = color != null ? color.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Piece other) {
        double diff = this.type.point - other.type.point;
        if (diff > 0) {
            return 1;
        } else if (diff < 0) {
            return -1;
        }
        return 0;
    }

    public enum Color {
        WHITE, BLACK, NO_COLOR;
    }

    public enum Type {
        PAWN('p', 0.5), ROOK('r', 5.0), KNIGHT('n', 2.5),
        BISHOP('b', 3.0), QUEEN('q', 9.0), KING('k', 0.0),
        NO_PIECE('.', 0.0);

        private final char representation;
        private final double point;

        Type(char representation, double point) {
            this.representation = representation;
            this.point = point;
        }

        public char getDefaultRepresentation() {
            return representation;
        }

        public char getWhiteRepresentation() {
            return getDefaultRepresentation();
        }

        public char getBlackRepresentation() {
            return Character.toUpperCase(representation);
        }

        public double getPoint() {
            return point;
        }
    }
}