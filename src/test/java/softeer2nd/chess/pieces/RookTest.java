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
import static softeer2nd.chess.pieces.PieceFactory.*;
import static softeer2nd.chess.pieces.PieceTestHelper.*;

class RookTest {
    private Piece rook;
    private Board board;
    private ChessGame chessGame;

    @BeforeEach
    void setUp() {
        rook = createBlackRook();
        board = new Board();
        chessGame = new ChessGame(board);
    }

    @Test
    @DisplayName("Rook은 상하좌우 칸수의 제한 없이 움직일 수 있다.")
    void moveKing() {
        List<Position> positions = createPositions(
                "a5", "b5", "c5", "e5", "f5", "g5", "h5",
                "d8", "d7", "d6", "d4", "d3", "d2", "d1"
        );
        allNotThrow(board, rook, Position.create("d5"), positions);
    }

    @Test
    @DisplayName("Rook은 상하좌우 이외 방향으로 움직일 수 없다.")
    void canNotMove() {
        List<Position> positions = createPositions("e6", "c6", "c4", "e4");
        allThrow(board, rook, Position.create("d5"), positions);
    }

    @Test
    @DisplayName("제자리로 움직일 수 없다.")
    void doNotMove() {
        Position d5 = Position.create("d5");
        assertThatThrownBy(() -> rook.verifyMovePosition(d5, d5))
                .isInstanceOf(IllegalMoveException.class);
    }

    @Test
    @DisplayName("같은 색 기물을 잡아먹을 수 없다.")
    void doNotEatSameColor() {
        board.initializeEmpty();
        Position d5 = Position.create("d5");
        Position d2 = Position.create("d2");

        board.putPiece(d5, createWhiteRook());
        board.putPiece(d2, createWhitePawn());

        assertThatThrownBy(() -> rook.verifyMovePosition(d5, d5))
                .isInstanceOf(IllegalMoveException.class);
    }

    @Test
    @DisplayName("수평 이동 중간에 가로막고 있는 기물이 있으면 이동하지 못한다.")
    void cannotMoveHorizontalIfPieceExistOnPath() {
        board.initializeEmpty();
        Position source = Position.create("d5");
        Position obstacle = Position.create("c5");
        Position destination = Position.create("b5");

        board.putPiece(source, createWhiteRook());
        board.putPiece(obstacle, createWhitePawn());
        board.putPiece(destination, createBlackQueen());

        assertThatThrownBy(() -> chessGame.move(source, destination))
                .isInstanceOf(IllegalMoveException.class);
    }

    @Test
    @DisplayName("수직 이동 중간에 가로막고 있는 기물이 있으면 이동하지 못한다.")
    void cannotMoveVerticalIfPieceExistOnPath() {
        board.initializeEmpty();
        Position source = Position.create("d5");
        Position obstacle = Position.create("d3");
        Position destination = Position.create("d1");

        board.putPiece(source, createWhiteRook());
        board.putPiece(obstacle, createWhitePawn());
        board.putPiece(destination, createBlackQueen());

        assertThatThrownBy(() -> chessGame.move(source, destination))
                .isInstanceOf(IllegalMoveException.class);
    }
}