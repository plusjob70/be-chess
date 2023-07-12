package softeer2nd.chess.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.Board;
import softeer2nd.chess.ChessGame;
import softeer2nd.chess.Position;
import softeer2nd.chess.exceptions.IllegalMoveException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static softeer2nd.chess.pieces.PieceFactory.*;
import static softeer2nd.chess.pieces.PieceTestHelper.*;

class BishopTest {
    private Piece bishop;
    private Board board;
    private ChessGame chessGame;

    @BeforeEach
    void setUp() {
        bishop = createWhiteBishop();
        board = new Board();
        chessGame = new ChessGame(board);
    }

    @Test
    @DisplayName("Bishop은 대각선 방향으로 칸수의 제한 없이 움직일 수 있다.")
    void moveKing() {
        List<Position> positions = createPositions(
                "c6", "b7", "a8", "c4", "b3", "a2",
                "e6", "f7", "g8", "e4", "f3", "g2", "h1");
        allNotThrow(board, bishop, Position.create("d5"), positions);
    }

    @Test
    @DisplayName("Bishop은 대각선 이외 방향으로 움직일 수 없다.")
    void canNotMove() {
        List<Position> positions = createPositions("d4", "c5", "d6", "e5", "f6");
        allThrow(board, bishop, Position.create("d5"), positions);
    }

    @Test
    @DisplayName("제자리로 움직일 수 없다.")
    void doNotMove() {
        chessGame.initialize();
        board.initializeEmpty();

        Position d5 = Position.create("d5");
        board.putPiece(d5, bishop);
        assertThatThrownBy(() -> chessGame.move(d5, d5))
                .isInstanceOf(IllegalMoveException.class);
    }

    @Test
    @DisplayName("같은 색 기물을 잡아먹을 수 없다.")
    void doNotEatSameColor() {
        chessGame.initialize();
        board.initializeEmpty();
        Position d5 = Position.create("d5");
        Position g2 = Position.create("g2");
        board.putPiece(d5, createWhiteBishop());
        board.putPiece(g2, createWhitePawn());

        assertThatThrownBy(() -> chessGame.move(d5, g2))
                .isInstanceOf(IllegalMoveException.class);
    }

    @Test
    @DisplayName("대각선에 있는 기물을 잡아먹는다.")
    void eat() {
        chessGame.initialize();
        board.initializeEmpty();
        Position d5 = Position.create("d5");
        Position g2 = Position.create("g2");
        board.putPiece(d5, createWhiteBishop());
        board.putPiece(g2, createBlackPawn());

        chessGame.move(d5, g2);

        assertEquals(board.findPiece(g2), createWhiteBishop());
        assertEquals(board.findPiece(d5), createBlank());
    }

    @Test
    @DisplayName("오른쪽 아래 대각선 이동 중간에 가로막고 있는 기물이 있으면 이동하지 못한다.")
    void cannotMoveIfPieceExistOnPath1() {
        board.initializeEmpty();
        Position source = Position.create("d5");
        Position obstacle = Position.create("f3");
        Position destination = Position.create("g2");

        board.putPiece(source, createWhiteBishop());
        board.putPiece(obstacle, createWhitePawn());
        board.putPiece(destination, createBlackQueen());

        assertThatThrownBy(() -> chessGame.move(source, destination))
                .isInstanceOf(IllegalMoveException.class);
    }

    @Test
    @DisplayName("오른쪽 위 대각선 이동 중간에 가로막고 있는 기물이 있으면 이동하지 못한다.")
    void cannotMoveIfPieceExistOnPath2() {
        board.initializeEmpty();
        Position source = Position.create("d5");
        Position obstacle = Position.create("e6");
        Position destination = Position.create("g8");

        board.putPiece(source, createWhiteBishop());
        board.putPiece(obstacle, createWhitePawn());
        board.putPiece(destination, createBlackQueen());

        assertThatThrownBy(() -> chessGame.move(source, destination))
                .isInstanceOf(IllegalMoveException.class);
    }

    @Test
    @DisplayName("왼쪽 위 대각선 이동 중간에 가로막고 있는 기물이 있으면 이동하지 못한다.")
    void cannotMoveIfPieceExistOnPath3() {
        board.initializeEmpty();
        Position source = Position.create("d5");
        Position obstacle = Position.create("c6");
        Position destination = Position.create("a8");

        board.putPiece(source, createWhiteBishop());
        board.putPiece(obstacle, createWhitePawn());
        board.putPiece(destination, createBlackQueen());

        assertThatThrownBy(() -> chessGame.move(source, destination))
                .isInstanceOf(IllegalMoveException.class);
    }

    @Test
    @DisplayName("왼쪽 아래 대각선 이동 중간에 가로막고 있는 기물이 있으면 이동하지 못한다.")
    void cannotMoveIfPieceExistOnPath4() {
        board.initializeEmpty();
        Position source = Position.create("d5");
        Position obstacle = Position.create("b3");
        Position destination = Position.create("a2");

        board.putPiece(source, createWhiteBishop());
        board.putPiece(obstacle, createWhitePawn());
        board.putPiece(destination, createBlackQueen());

        assertThatThrownBy(() -> chessGame.move(source, destination))
                .isInstanceOf(IllegalMoveException.class);
    }
}