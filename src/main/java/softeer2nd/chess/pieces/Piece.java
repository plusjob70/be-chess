package softeer2nd.chess.pieces;

import softeer2nd.chess.game.Position;

import static softeer2nd.chess.pieces.Piece.Color.BLACK;
import static softeer2nd.chess.pieces.Piece.Color.WHITE;
import static softeer2nd.chess.pieces.Piece.Type.NO_PIECE;

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

    public boolean isTypeOf(Type type) {
        return this.type.equals(type);
    }

    public boolean isColorOf(Piece target) {
        return this.color.equals(target.color);
    }

    /**
     * 기물의 이동이 기본 행마법과 일치하는지 검사
     * @param source 현재 기물의 위치
     * @param destination 기물이 이동할 위치
     */
    public abstract void verifyPieceMovementRule(Position source, Position destination);

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
        return Double.compare(this.type.point, other.type.point);
    }

    public enum Color {
        WHITE("소문자"), BLACK("대문자"), NO_COLOR(".");

        private final String representation;

        Color(String representation) {
            this.representation = representation;
        }

        public String getRepresentation() {
            return representation;
        }
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
    }
}