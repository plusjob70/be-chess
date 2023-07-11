package softeer2nd.chess.pieces;

import static softeer2nd.chess.pieces.Piece.Color.BLACK;
import static softeer2nd.chess.pieces.Piece.Color.WHITE;

/**
 * 기물을 생성할 수 있는 팩토리 클래스
 */
public class PieceFactory {
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
}
