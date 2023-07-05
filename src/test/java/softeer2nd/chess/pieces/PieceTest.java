package softeer2nd.chess.pieces;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static softeer2nd.chess.pieces.Piece.*;
import static softeer2nd.chess.pieces.Piece.Type.*;

class PieceTest {

    @Test
    @DisplayName("기물이 생성되어야 한다.")
    public void create_piece() {
        verifyPiece(createWhitePawn(), createBlackPawn(), PAWN);
        verifyPiece(createWhiteKnight(), createBlackKnight(), KNIGHT);
        verifyPiece(createWhiteRook(), createBlackRook(), ROOK);
        verifyPiece(createWhiteBishop(), createBlackBishop(), BISHOP);
        verifyPiece(createWhiteQueen(), createBlackQueen(), QUEEN);
        verifyPiece(createWhiteKing(), createBlackKing(), KING);

        Piece blank = createBlank();
        assertFalse(blank.isWhite());
        assertFalse(blank.isBlack());
        assertEquals(NO_PIECE, blank.getType());
    }

    @Test
    @DisplayName("기물은 색깔별로 각각의 표현을 가진다.")
    public void getRepresentationPerPiece() {
        assertEquals('p', PAWN.getWhiteRepresentation());
        assertEquals('P', PAWN.getBlackRepresentation());

        assertEquals('r', ROOK.getWhiteRepresentation());
        assertEquals('R', ROOK.getBlackRepresentation());

        assertEquals('n', KNIGHT.getWhiteRepresentation());
        assertEquals('N', KNIGHT.getBlackRepresentation());

        assertEquals('b', BISHOP.getWhiteRepresentation());
        assertEquals('B', BISHOP.getBlackRepresentation());

        assertEquals('q', QUEEN.getWhiteRepresentation());
        assertEquals('Q', QUEEN.getBlackRepresentation());

        assertEquals('k', KING.getWhiteRepresentation());
        assertEquals('K', KING.getBlackRepresentation());

        assertEquals('.', NO_PIECE.getWhiteRepresentation());
        assertEquals('.', NO_PIECE.getBlackRepresentation());
    }

    @Test
    @DisplayName("기물의 색깔을 확인할 수 있다.")
    public void checkColor() {
        Piece blackKing = createBlackKing();
        assertTrue(blackKing.isBlack());
        assertFalse(blackKing.isWhite());

        Piece whiteKing = createWhiteKing();
        assertTrue(whiteKing.isWhite());
        assertFalse(whiteKing.isBlack());
    }

    private void verifyPiece(final Piece whitePiece, final Piece blackPiece, final Type type) {
        assertTrue(whitePiece.isWhite());
        assertEquals(type, whitePiece.getType());

        assertTrue(blackPiece.isBlack());
        assertEquals(type, blackPiece.getType());
    }

}