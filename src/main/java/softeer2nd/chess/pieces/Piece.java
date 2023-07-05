package softeer2nd.chess.pieces;

import static softeer2nd.chess.pieces.Piece.Color.*;
import static softeer2nd.chess.pieces.Piece.Type.*;

public class Piece {

    public enum Color {
        WHITE, BLACK, NO_COLOR;
    }

    public enum Type {
        PAWN('p'), ROOK('r'), KNIGHT('n'), BISHOP('b'),
        QUEEN('q'), KING('k'), NO_PIECE('.');

        private char representation;

        Type(char representation) {
            this.representation = representation;
        }

        public char getWhiteRepresentation() {
            return representation;
        }

        public char getBlackRepresentation() {
            return Character.toUpperCase(representation);
        }
    }

    private final Color color;
    private final Type type;

    private Piece(Color color, Type type) {
        this.color = color;
        this.type = type;
    }

    public static Piece createWhitePawn() {
        return new Piece(WHITE, PAWN);
    }

    public static Piece createBlackPawn() {
        return new Piece(BLACK, PAWN);
    }

    public static Piece createWhiteKnight() {
        return new Piece(WHITE, KNIGHT);
    }

    public static Piece createBlackKnight() {
        return new Piece(BLACK, KNIGHT);
    }

    public static Piece createWhiteRook() {
        return new Piece(WHITE, ROOK);
    }

    public static Piece createBlackRook() {
        return new Piece(BLACK, ROOK);
    }

    public static Piece createWhiteBishop() {
        return new Piece(WHITE, BISHOP);
    }

    public static Piece createBlackBishop() {
        return new Piece(BLACK, BISHOP);
    }

    public static Piece createWhiteQueen() {
        return new Piece(WHITE, QUEEN);
    }

    public static Piece createBlackQueen() {
        return new Piece(BLACK, QUEEN);
    }

    public static Piece createWhiteKing() {
        return new Piece(WHITE, KING);
    }

    public static Piece createBlackKing() {
        return new Piece(BLACK, KING);
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
}
