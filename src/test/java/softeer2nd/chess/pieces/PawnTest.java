package softeer2nd.chess.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.Board;
import softeer2nd.chess.ChessGame;
import softeer2nd.chess.Position;
import softeer2nd.chess.exceptions.IllegalMoveException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static softeer2nd.chess.pieces.PieceFactory.createBlackPawn;
import static softeer2nd.chess.pieces.PieceFactory.createWhitePawn;

class PawnTest {
    private Piece whitePawn;
    private Piece blackPawn;
    private Board board;
    private ChessGame chessGame;

    @BeforeEach
    void setUp() {
        whitePawn = createWhitePawn();
        blackPawn = createBlackPawn();
        board = new Board();
        chessGame = new ChessGame(board);
    }

    @Test
    @DisplayName("흰색 pawn은 처음 움직임에서 두 칸 앞으로 움직일 수 있다.")
    void whitePawnFirstTwoStep() {
        Position b2 = Position.create("b2");
        Position b4 = Position.create("b4");
        assertDoesNotThrow(() -> whitePawn.verifyMovePosition(b2, b4));
    }

    @Test
    @DisplayName("흰색 pawn은 처음 움직임이 아닐 때 두 칸 앞으로 움직일 수 없다.")
    void whitePawnNotFirstTwoStep() {
        Position b3 = Position.create("b3");
        Position b5 = Position.create("b5");
        assertThatThrownBy(() -> whitePawn.verifyMovePosition(b3, b5))
                .isInstanceOf(IllegalMoveException.class);
    }

    @Test
    @DisplayName("흰색 pawn은 처음 움직임에서 한 칸 앞으로 움직일 수 있다.")
    void whitePawnFirstOneStep() {
        Position b2 = Position.create("b2");
        Position b4 = Position.create("b3");
        assertDoesNotThrow(() -> whitePawn.verifyMovePosition(b2, b4));
    }

    @Test
    @DisplayName("흰색 pawn은 처음 움직임이 아닐 때 한 칸 앞으로 움직일 수 있다.")
    void whitePawnNotFirstOneStep() {
        Position b3 = Position.create("b3");
        Position b4 = Position.create("b4");
        assertDoesNotThrow(() -> whitePawn.verifyMovePosition(b3, b4));
    }

    @Test
    @DisplayName("흰색 pawn은 처음 움직임에서 한 칸 대각선 위로 움직일 수 있다.")
    void whitePawnFirstDiagonalStep() {
        Position b2 = Position.create("b2");
        Position a3 = Position.create("a3");
        Position c3 = Position.create("c3");
        assertDoesNotThrow(() -> whitePawn.verifyMovePosition(b2, a3));
        assertDoesNotThrow(() -> whitePawn.verifyMovePosition(b2, c3));
    }

    @Test
    @DisplayName("흰색 pawn은 처음 움직임이 아닐 때 한 칸 대각선 위로 움직일 수 있다.")
    void whitePawnNotFirstDiagonalStep() {
        Position b3 = Position.create("b3");
        Position a4 = Position.create("a4");
        Position c4 = Position.create("c4");
        assertDoesNotThrow(() -> whitePawn.verifyMovePosition(b3, a4));
        assertDoesNotThrow(() -> whitePawn.verifyMovePosition(b3, c4));
    }

    @Test
    @DisplayName("흰색 pawn은 뒤로 후퇴할 수 없다.")
    void whitePawnCannotMoveBackward() {
        Position b2 = Position.create("b2");
        Position b1 = Position.create("b1");
        assertThatThrownBy(() -> whitePawn.verifyMovePosition(b2, b1))
                .isInstanceOf(IllegalMoveException.class);
    }

    @Test
    @DisplayName("흰색 pawn은 처음 움직임에서 다른 기물을 뛰어넘을 수 없다.")
    void whitePawnCannotJump() {
        chessGame.initialize();

        Position d2 = Position.create("d2");
        Position d3 = Position.create("d3");
        board.putPiece(d3, createBlackPawn());

        assertThatThrownBy(() -> chessGame.move(d2, d3))
                .isInstanceOf(IllegalMoveException.class);
    }

    @Test
    @DisplayName("흰색 pawn은 앞에 기물이 있으면 그쪽으로 갈 수 없다.")
    void whitePawnCannotMove1() {
        chessGame.initialize();

        Position d2 = Position.create("d2");
        Position d3 = Position.create("d3");
        board.putPiece(d3, createBlackPawn());

        assertThatThrownBy(() -> chessGame.move(d2, d3))
                .isInstanceOf(IllegalMoveException.class);
    }

    @Test
    @DisplayName("흰색 pawn은 대각선에 기물이 없으면 그쪽으로 이동할 수 없다.")
    void whitePawnCannotMove2() {
        chessGame.initialize();
        board.initializeEmpty();

        Position d2 = Position.create("d2");
        Position e3 = Position.create("e3");

        board.putPiece(d2, whitePawn);

        assertThatThrownBy(() -> chessGame.move(d2, e3))
                .isInstanceOf(IllegalMoveException.class);
    }

    @Test
    @DisplayName("검은색 pawn은 처음 움직임에서 두 칸 앞으로 움직일 수 있다.")
    void blackPawnFirstTwoStep() {
        Position b7 = Position.create("b7");
        Position b5 = Position.create("b5");
        assertDoesNotThrow(() -> blackPawn.verifyMovePosition(b7, b5));
    }

    @Test
    @DisplayName("검은색 pawn은 처음 움직임이 아닐 때 두 칸 앞으로 움직일 수 없다.")
    void blackPawnNotFirstTwoStep() {
        Position b6 = Position.create("b6");
        Position b4 = Position.create("b4");
        assertThatThrownBy(() -> blackPawn.verifyMovePosition(b6, b4))
                .isInstanceOf(IllegalMoveException.class);
    }

    @Test
    @DisplayName("검은색 pawn은 처음 움직임에서 한 칸 앞으로 움직일 수 있다.")
    void blackPawnFirstOneStep() {
        Position b7 = Position.create("b7");
        Position b6 = Position.create("b6");
        assertDoesNotThrow(() -> blackPawn.verifyMovePosition(b7, b6));
    }

    @Test
    @DisplayName("검은색 pawn은 처음 움직임이 아닐 때 한 칸 앞으로 움직일 수 있다.")
    void blackPawnNotFirstOneStep() {
        Position b6 = Position.create("b6");
        Position b5 = Position.create("b5");
        assertDoesNotThrow(() -> blackPawn.verifyMovePosition(b6, b5));
    }

    @Test
    @DisplayName("검은색 pawn은 처음 움직임에서 한 칸 대각선 아래로 움직일 수 있다.")
    void blackPawnFirstDiagonalStep() {
        Position b7 = Position.create("b7");
        Position a6 = Position.create("a6");
        Position c6 = Position.create("c6");
        assertDoesNotThrow(() -> blackPawn.verifyMovePosition(b7, a6));
        assertDoesNotThrow(() -> blackPawn.verifyMovePosition(b7, c6));
    }

    @Test
    @DisplayName("검은색 pawn은 처음 움직임이 아닐 때 한 칸 대각선 아래로 움직일 수 있다.")
    void blackPawnNotFirstDiagonalStep() {
        Position b6 = Position.create("b6");
        Position a5 = Position.create("a5");
        Position c5 = Position.create("c5");
        assertDoesNotThrow(() -> blackPawn.verifyMovePosition(b6, a5));
        assertDoesNotThrow(() -> blackPawn.verifyMovePosition(b6, c5));
    }

    @Test
    @DisplayName("검은색 pawn은 뒤로 후퇴할 수 없다.")
    void blackPawnCannotMoveBackward() {
        Position b7 = Position.create("b7");
        Position b8 = Position.create("b8");
        assertThatThrownBy(() -> blackPawn.verifyMovePosition(b7, b8))
                .isInstanceOf(IllegalMoveException.class);
    }

    @Test
    @DisplayName("검은색 pawn은 처음 움직임에서 다른 기물을 뛰어넘을 수 없다.")
    void blackPawnCannotJump() {
        chessGame.initialize();

        Position d2 = Position.create("d2");
        Position d3 = Position.create("d3");
        board.putPiece(d3, createBlackPawn());

        assertThatThrownBy(() -> chessGame.move(d2, d3))
                .isInstanceOf(IllegalMoveException.class);
    }

    @Test
    @DisplayName("검은색 pawn은 앞에 기물이 있으면 그쪽으로 갈 수 없다.")
    void blackPawnCannotMove1() {
        chessGame.initialize();

        Position d2 = Position.create("d2");
        Position d3 = Position.create("d3");
        board.putPiece(d3, createBlackPawn());

        assertThatThrownBy(() -> chessGame.move(d2, d3))
                .isInstanceOf(IllegalMoveException.class);
    }

    @Test
    @DisplayName("검은색 pawn은 대각선에 기물이 없으면 그쪽으로 이동할 수 없다.")
    void blackPawnCannotMove2() {
        chessGame.initialize();
        board.initializeEmpty();

        Position d2 = Position.create("d2");
        Position e3 = Position.create("e3");

        board.putPiece(d2, blackPawn);

        assertThatThrownBy(() -> chessGame.move(d2, e3))
                .isInstanceOf(IllegalMoveException.class);
    }
}