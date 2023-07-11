package softeer2nd.chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.pieces.Piece;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static softeer2nd.chess.pieces.Piece.Color.BLACK;
import static softeer2nd.chess.pieces.Piece.Color.WHITE;
import static softeer2nd.chess.pieces.PieceFactory.*;

class ChessGameTest {

    private Board board;
    private ChessGame chessGame;

    @BeforeEach
    void setUp() {
        board = new Board();
        chessGame = new ChessGame(board);
    }

    @Test
    @DisplayName("현재 놓인 기물로 점수를 계산한다.")
    void calculateScores() {
        board.initializeEmpty();
        addPiece("b6", createBlackPawn());
        addPiece("e6", createBlackQueen());
        addPiece("b8", createBlackKing());
        addPiece("c8", createBlackRook());
        addPiece("f2", createWhitePawn());
        addPiece("g2", createWhitePawn());
        addPiece("e1", createWhiteRook());
        addPiece("f1", createWhiteKing());

        assertEquals(15.0, chessGame.calculateScores(BLACK), 0.01);
        assertEquals(7.0, chessGame.calculateScores(WHITE), 0.01);
    }

    @Test
    @DisplayName("Pawn은 같은 세로줄에 존재하는 개수에 따라 다른 점수가 매겨진다.")
    void calculatePawnScores() {
        board.initializeEmpty();
        addPiece("a8", createWhitePawn());
        addPiece("a7", createWhitePawn());
        addPiece("a6", createWhitePawn());
        addPiece("a5", createWhiteKing());
        addPiece("b5", createWhitePawn());
        addPiece("b7", createBlackKing());
        addPiece("c8", createBlackPawn());
        addPiece("c7", createBlackPawn());
        addPiece("c6", createBlackPawn());
        addPiece("c5", createBlackPawn());
        addPiece("c4", createBlackPawn());
        addPiece("c3", createBlackPawn());
        addPiece("c2", createBlackPawn());
        addPiece("c1", createBlackPawn());

        assertEquals(2.5, chessGame.calculateScores(WHITE), 0.01);
        assertEquals(4.0, chessGame.calculateScores(BLACK), 0.01);
    }

    @Test
    @DisplayName("KING이 없거나 KING만 있다면 점수는 없다.")
    void calculateNoKing() {
        board.initializeEmpty();
        addPiece("a1", createBlackQueen());
        addPiece("a2", createWhiteKing());

        assertEquals(0.0, chessGame.calculateScores(BLACK), 0.01);
        assertEquals(0.0, chessGame.calculateScores(WHITE), 0.01);
    }

    @Test
    @DisplayName("기물의 점수가 높은 순으로 정렬한다.")
    void orderPieceNaturalOrder() {
        board.initializeEmpty();
        addPiece("a1", createBlackKing());
        addPiece("b1", createBlackPawn());
        addPiece("c1", createBlackBishop());
        addPiece("d1", createBlackQueen());

        List<Piece> orderedPieces = chessGame.getOrderedPieces(BLACK, false);

        List<Piece> expected = Arrays.asList(
                createBlackQueen(),
                createBlackBishop(),
                createBlackPawn(),
                createBlackKing()
        );
        assertEquals(expected, orderedPieces);
    }

    @Test
    @DisplayName("기물의 점수가 낮은 순으로 정렬한다.")
    void orderPieceReversedOrder() {
        board.initializeEmpty();
        addPiece("a1", createBlackKing());
        addPiece("b1", createBlackPawn());
        addPiece("c1", createBlackBishop());
        addPiece("d1", createBlackQueen());

        List<Piece> orderedPieces = chessGame.getOrderedPieces(BLACK, true);

        List<Piece> expected = Arrays.asList(
                createBlackKing(),
                createBlackPawn(),
                createBlackBishop(),
                createBlackQueen()
        );
        assertEquals(expected, orderedPieces);
    }

    @Test
    @DisplayName("기물이 A -> B로 이동하면 B에 기물이 있다.")
    void moveSource() {
        chessGame.initialize();
        Position source = Position.create("a2");
        Position destination = Position.create("a3");

        chessGame.move(source, destination);

        assertEquals(board.findPiece(destination), createWhitePawn());
    }

    @Test
    @DisplayName("기물이 A -> B로 이동하면 A에는 NO_PIECE 기물이 존재한다.")
    void addedNoPiece() {
        chessGame.initialize();
        Position source = Position.create("a2");
        Position destination = Position.create("a3");

        chessGame.move(source, destination);

        assertEquals(board.findPiece(source), createBlank());
    }

    private void addPiece(String expression, Piece piece) {
        board.putPiece(Position.create(expression), piece);
    }
}