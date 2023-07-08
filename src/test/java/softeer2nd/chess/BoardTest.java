package softeer2nd.chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.Piece.Type;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static softeer2nd.chess.pieces.Piece.Color.*;
import static softeer2nd.chess.pieces.Piece.Type.*;

class BoardTest {
    private Board board;

    @BeforeEach
    void setUp() {
        board = new Board();
    }

    @Test
    @DisplayName("보드를 빈칸으로 초기화한다.")
    void initializeEmpty() {
        board.initializeEmpty();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                assertEquals(Piece.createBlank(), board.findPiece(i, j));
            }
        }
    }

    @Test
    @DisplayName("보드의 랭크8이 초기화된다.")
    void initializeRank8() {
        board.initializeRank8();

        validateBlackPiece("a8", ROOK);
        validateBlackPiece("b8", KNIGHT);
        validateBlackPiece("c8", BISHOP);
        validateBlackPiece("d8", QUEEN);
        validateBlackPiece("e8", KING);
        validateBlackPiece("f8", BISHOP);
        validateBlackPiece("g8", KNIGHT);
        validateBlackPiece("h8", ROOK);
    }

    @Test
    @DisplayName("보드의 랭크7이 초기화된다.")
    void initializeRank7() {
        board.initializePawnRank(7);

        validateBlackPiece("a7", PAWN);
        validateBlackPiece("b7", PAWN);
        validateBlackPiece("c7", PAWN);
        validateBlackPiece("d7", PAWN);
        validateBlackPiece("e7", PAWN);
        validateBlackPiece("f7", PAWN);
        validateBlackPiece("g7", PAWN);
        validateBlackPiece("h7", PAWN);
    }

    @Test
    @DisplayName("보드의 랭크2가 초기화된다.")
    void initializeRank2() {
        board.initializePawnRank(2);

        validateWhitePiece("a2", PAWN);
        validateWhitePiece("b2", PAWN);
        validateWhitePiece("c2", PAWN);
        validateWhitePiece("d2", PAWN);
        validateWhitePiece("e2", PAWN);
        validateWhitePiece("f2", PAWN);
        validateWhitePiece("g2", PAWN);
        validateWhitePiece("h2", PAWN);
    }

    @Test
    @DisplayName("보드의 랭크1이 초기화된다.")
    void initializeRank1() {
        board.initializeRank1();

        validateWhitePiece("a1", ROOK);
        validateWhitePiece("b1", KNIGHT);
        validateWhitePiece("c1", BISHOP);
        validateWhitePiece("d1", QUEEN);
        validateWhitePiece("e1", KING);
        validateWhitePiece("f1", BISHOP);
        validateWhitePiece("g1", KNIGHT);
        validateWhitePiece("h1", ROOK);
    }

    @Test
    @DisplayName("보드에 놓인 기물의 개수가 반환된다.")
    void getCountPiece() {
        initializeGame();

        assertEquals(8, board.getCountPieces(WHITE, PAWN));
        assertEquals(8, board.getCountPieces(BLACK, PAWN));
        assertEquals(2, board.getCountPieces(WHITE, ROOK));
        assertEquals(2, board.getCountPieces(BLACK, ROOK));
        assertEquals(2, board.getCountPieces(WHITE, KNIGHT));
        assertEquals(2, board.getCountPieces(BLACK, KNIGHT));
        assertEquals(2, board.getCountPieces(WHITE, BISHOP));
        assertEquals(2, board.getCountPieces(BLACK, BISHOP));
        assertEquals(1, board.getCountPieces(WHITE, QUEEN));
        assertEquals(1, board.getCountPieces(BLACK, QUEEN));
        assertEquals(1, board.getCountPieces(WHITE, KING));
        assertEquals(1, board.getCountPieces(BLACK, KING));
        assertEquals(32, board.getCountPieces(NO_COLOR, NO_PIECE));
    }

    @Test
    @DisplayName("기물을 조회한다.")
    void findPiece() {
        initializeGame();

        assertEquals(Piece.createBlackRook(), board.findPiece(Position.create("a8")));
        assertEquals(Piece.createBlackRook(), board.findPiece(Position.create("h8")));
        assertEquals(Piece.createWhiteRook(), board.findPiece(Position.create("a1")));
        assertEquals(Piece.createWhiteRook(), board.findPiece(Position.create("h1")));
    }

    @Test
    @DisplayName("잘못된 인덱스로 접근시 오류가 발생한다.")
    void IllegalIndex() {
        assertThatThrownBy(() -> board.findPiece(Position.create("a9")))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> board.findPiece(Position.create("i1")))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> board.findPiece(Position.create("1a")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("임의의 기물을 체스판 위에 추가한다.")
    void putPiece() {
        Position position = Position.create("b5");
        Piece piece = Piece.createBlackKing();

        board.putPiece(position, piece);

        assertEquals(piece, board.findPiece(position));
    }

   @Test
   @DisplayName("Board의 Representation을 얻을 수 있다.")
   void RepresentationBoard() {
        initializeGame();

       List<List<Character>> representations = board.getRepresentations();

       assertEquals(List.of(
               initializedRank8Representation(),
               initializedBlackPawnRankRepresentation(),
               initializedBlankRepresentation(),
               initializedBlankRepresentation(),
               initializedBlankRepresentation(),
               initializedBlankRepresentation(),
               initializedWhitePawnRankRepresentation(),
               initializedRank1Representation()
       ), representations);
   }

    private List<Character> initializedRank8Representation() {
        return List.of(
                ROOK.getBlackRepresentation(),
                KNIGHT.getBlackRepresentation(),
                BISHOP.getBlackRepresentation(),
                QUEEN.getBlackRepresentation(),
                KING.getBlackRepresentation(),
                BISHOP.getBlackRepresentation(),
                KNIGHT.getBlackRepresentation(),
                ROOK.getBlackRepresentation()
        );
    }

    private List<Character> initializedBlackPawnRankRepresentation() {
        return List.of(
                PAWN.getBlackRepresentation(),
                PAWN.getBlackRepresentation(),
                PAWN.getBlackRepresentation(),
                PAWN.getBlackRepresentation(),
                PAWN.getBlackRepresentation(),
                PAWN.getBlackRepresentation(),
                PAWN.getBlackRepresentation(),
                PAWN.getBlackRepresentation()
        );
    }

    private List<Character> initializedWhitePawnRankRepresentation() {
        return List.of(
                PAWN.getWhiteRepresentation(),
                PAWN.getWhiteRepresentation(),
                PAWN.getWhiteRepresentation(),
                PAWN.getWhiteRepresentation(),
                PAWN.getWhiteRepresentation(),
                PAWN.getWhiteRepresentation(),
                PAWN.getWhiteRepresentation(),
                PAWN.getWhiteRepresentation()
        );
    }

    private List<Character> initializedBlankRepresentation() {
        return List.of(
                NO_PIECE.getDefaultRepresentation(),
                NO_PIECE.getDefaultRepresentation(),
                NO_PIECE.getDefaultRepresentation(),
                NO_PIECE.getDefaultRepresentation(),
                NO_PIECE.getDefaultRepresentation(),
                NO_PIECE.getDefaultRepresentation(),
                NO_PIECE.getDefaultRepresentation(),
                NO_PIECE.getDefaultRepresentation()
        );
    }

    private List<Character> initializedRank1Representation() {
        return List.of(
                ROOK.getWhiteRepresentation(),
                KNIGHT.getWhiteRepresentation(),
                BISHOP.getWhiteRepresentation(),
                QUEEN.getWhiteRepresentation(),
                KING.getWhiteRepresentation(),
                BISHOP.getWhiteRepresentation(),
                KNIGHT.getWhiteRepresentation(),
                ROOK.getWhiteRepresentation()
        );
    }

    private void validateBlackPiece(String positionString, Type type) {
        switch (type) {
            case ROOK:
                assertEquals(board.findPiece(Position.create(positionString)), Piece.createBlackRook());
                break;
            case KING:
                assertEquals(board.findPiece(Position.create(positionString)), Piece.createBlackKing());
                break;
            case PAWN:
                assertEquals(board.findPiece(Position.create(positionString)), Piece.createBlackPawn());
                break;
            case KNIGHT:
                assertEquals(board.findPiece(Position.create(positionString)), Piece.createBlackKnight());
                break;
            case BISHOP:
                assertEquals(board.findPiece(Position.create(positionString)), Piece.createBlackBishop());
                break;
            case QUEEN:
                assertEquals(board.findPiece(Position.create(positionString)), Piece.createBlackQueen());
                break;
        }
    }

    private void validateWhitePiece(String positionString, Type type) {
        switch (type) {
            case ROOK:
                assertEquals(board.findPiece(Position.create(positionString)), Piece.createWhiteRook());
                break;
            case KING:
                assertEquals(board.findPiece(Position.create(positionString)), Piece.createWhiteKing());
                break;
            case PAWN:
                assertEquals(board.findPiece(Position.create(positionString)), Piece.createWhitePawn());
                break;
            case KNIGHT:
                assertEquals(board.findPiece(Position.create(positionString)), Piece.createWhiteKnight());
                break;
            case BISHOP:
                assertEquals(board.findPiece(Position.create(positionString)), Piece.createWhiteBishop());
                break;
            case QUEEN:
                assertEquals(board.findPiece(Position.create(positionString)), Piece.createWhiteQueen());
                break;
        }
    }

    private void initializeGame() {
        board.initializeRank8();
        board.initializePawnRank(7);
        board.initializePawnRank(2);
        board.initializeRank1();
    }
}