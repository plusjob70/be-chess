package softeer2nd.chess.pieces;

import static softeer2nd.chess.pieces.Piece.Color.*;
import static softeer2nd.chess.pieces.Piece.Type.*;

public abstract class Piece implements Comparable<Piece> {

    private final Color color;
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

    public static Piece createBlank() {
        return new NoPiece();
    }

    public static Piece createWhitePawn() {
        return new Pawn(WHITE);
    }

    public static Piece createBlackPawn() {
        return new Pawn(BLACK);
    }

    public static Piece createWhiteKnight() {
        return new Knight(WHITE);
    }

    public static Piece createBlackKnight() {
        return new Knight(BLACK);
    }

    public static Piece createWhiteRook() {
        return new Rook(WHITE);
    }

    public static Piece createBlackRook() {
        return new Rook(BLACK);
    }

    public static Piece createWhiteBishop() {
        return new Bishop(WHITE);
    }

    public static Piece createBlackBishop() {
        return new Bishop(BLACK);
    }

    public static Piece createWhiteQueen() {
        return new Queen(WHITE);
    }

    public static Piece createBlackQueen() {
        return new Queen(BLACK);
    }

    public static Piece createWhiteKing() {
        return new King(WHITE);
    }

    public static Piece createBlackKing() {
        return new King(BLACK);
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
