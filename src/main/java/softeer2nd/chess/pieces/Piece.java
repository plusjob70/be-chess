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

    /**
     * 해당하는 기물이 행마법과 일치하는지 여부 확인
     * @param source 현재 기물의 위치
     * @param destination 기물이 이동할 위치
     * @return true if piece can move else false
     */
    public abstract boolean verifyMovePosition(Position source, Position destination);

    /**
     * 빈칸을 생성하는 팩토리 메서드
     * @return 빈칸을 나타내는 기물
     */
    public static Piece createBlank() {
        return new Blank();
    }

    /**
     * 흰색 Pawn을 생성하는 팩토리 메서드
     * @return 흰색 Pawn
     */
    public static Piece createWhitePawn() {
        return new Pawn(WHITE);
    }

    /**
     * 검은색 Pawn을 생성하는 팩토리 메서드
     * @return 검은색 Pawn
     */
    public static Piece createBlackPawn() {
        return new Pawn(BLACK);
    }

    /**
     * 흰색 Knight을 생성하는 팩토리 메서드
     * @return 흰색 Knight
     */
    public static Piece createWhiteKnight() {
        return new Knight(WHITE);
    }

    /**
     * 검은색 Knight을 생성하는 팩토리 메서드
     * @return 검은색 Knight
     */
    public static Piece createBlackKnight() {
        return new Knight(BLACK);
    }

    /**
     * 흰색 Rook을 생성하는 팩토리 메서드
     * @return 흰색 Rook
     */
    public static Piece createWhiteRook() {
        return new Rook(WHITE);
    }

    /**
     * 검은색 Rook을 생성하는 팩토리 메서드
     * @return 검은색 Rook
     */
    public static Piece createBlackRook() {
        return new Rook(BLACK);
    }

    /**
     * 흰색 Bishop을 생성하는 팩토리 메서드
     * @return 흰색 Bishop
     */
    public static Piece createWhiteBishop() {
        return new Bishop(WHITE);
    }

    /**
     * 검은색 Bishop을 생성하는 팩토리 메서드
     * @return 검은색 Bishop
     */
    public static Piece createBlackBishop() {
        return new Bishop(BLACK);
    }

    /**
     * 흰색 Queen을 생성하는 팩토리 메서드
     * @return 흰색 Queen
     */
    public static Piece createWhiteQueen() {
        return new Queen(WHITE);
    }

    /**
     * 검은색 Queen을 생성하는 팩토리 메서드
     * @return 검은색 Queen
     */
    public static Piece createBlackQueen() {
        return new Queen(BLACK);
    }

    /**
     * 흰색 King을 생성하는 팩토리 메서드
     * @return 흰색 King
     */
    public static Piece createWhiteKing() {
        return new King(WHITE);
    }

    /**
     * 검은색 King을 생성하는 팩토리 메서드
     * @return 검은색 King
     */
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