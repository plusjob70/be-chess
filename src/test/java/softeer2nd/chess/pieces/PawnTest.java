package softeer2nd.chess.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.Position;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PawnTest {
    private Piece whitePawn;
    private Piece blackPawn;

    @BeforeEach
    void setUp() {
        whitePawn = Piece.createWhitePawn();
        blackPawn = Piece.createBlackPawn();
    }

    @Test
    @DisplayName("흰색 pawn은 처음 움직임에서 두 칸 앞으로 움직일 수 있다.")
    void whitePawnFirstTwoStep() {
        Position b2 = Position.create("b2");
        Position b4 = Position.create("b4");
        assertTrue(whitePawn.verifyMovePosition(b2, b4));
    }

    @Test
    @DisplayName("흰색 pawn은 처음 움직임이 아닐 때 두 칸 앞으로 움직일 수 없다.")
    void whitePawnNotFirstTwoStep() {
        Position b3 = Position.create("b3");
        Position b5 = Position.create("b5");
        assertFalse(whitePawn.verifyMovePosition(b3, b5));
    }

    @Test
    @DisplayName("흰색 pawn은 처음 움직임에서 한 칸 앞으로 움직일 수 있다.")
    void whitePawnFirstOneStep() {
        Position b2 = Position.create("b2");
        Position b4 = Position.create("b3");
        assertTrue(whitePawn.verifyMovePosition(b2, b4));
    }

    @Test
    @DisplayName("흰색 pawn은 처음 움직임이 아닐 때 한 칸 앞으로 움직일 수 있다.")
    void whitePawnNotFirstOneStep() {
        Position b3 = Position.create("b3");
        Position b4 = Position.create("b4");
        assertTrue(whitePawn.verifyMovePosition(b3, b4));
    }

    @Test
    @DisplayName("흰색 pawn은 처음 움직임에서 한 칸 대각선 위로 움직일 수 있다.")
    void whitePawnFirstDiagonalStep() {
        Position b2 = Position.create("b2");
        Position a3 = Position.create("a3");
        Position c3 = Position.create("c3");
        assertTrue(whitePawn.verifyMovePosition(b2, a3));
        assertTrue(whitePawn.verifyMovePosition(b2, c3));
    }

    @Test
    @DisplayName("흰색 pawn은 처음 움직임이 아닐 때 한 칸 대각선 위로 움직일 수 있다.")
    void whitePawnNotFirstDiagonalStep() {
        Position b3 = Position.create("b3");
        Position a4 = Position.create("a4");
        Position c4 = Position.create("c4");
        assertTrue(whitePawn.verifyMovePosition(b3, a4));
        assertTrue(whitePawn.verifyMovePosition(b3, c4));
    }

    @Test
    @DisplayName("흰색 pawn은 뒤로 후퇴할 수 없다.")
    void whitePawnCannotMoveBackward() {
        Position b2 = Position.create("b2");
        Position b1 = Position.create("b1");
        assertFalse(whitePawn.verifyMovePosition(b2, b1));
    }

    @Test
    @DisplayName("검은색 pawn은 처음 움직임에서 두 칸 앞으로 움직일 수 있다.")
    void blackPawnFirstTwoStep() {
        Position b7 = Position.create("b7");
        Position b5 = Position.create("b5");
        assertTrue(blackPawn.verifyMovePosition(b7, b5));
    }

    @Test
    @DisplayName("검은색 pawn은 처음 움직임이 아닐 때 두 칸 앞으로 움직일 수 없다.")
    void blackPawnNotFirstTwoStep() {
        Position b6 = Position.create("b6");
        Position b4 = Position.create("b4");
        assertFalse(blackPawn.verifyMovePosition(b6, b4));
    }

    @Test
    @DisplayName("검은색 pawn은 처음 움직임에서 한 칸 앞으로 움직일 수 있다.")
    void blackPawnFirstOneStep() {
        Position b7 = Position.create("b7");
        Position b6 = Position.create("b6");
        assertTrue(blackPawn.verifyMovePosition(b7, b6));
    }

    @Test
    @DisplayName("검은색 pawn은 처음 움직임이 아닐 때 한 칸 앞으로 움직일 수 있다.")
    void blackPawnNotFirstOneStep() {
        Position b6 = Position.create("b6");
        Position b5 = Position.create("b5");
        assertTrue(blackPawn.verifyMovePosition(b6, b5));
    }

    @Test
    @DisplayName("검은색 pawn은 처음 움직임에서 한 칸 대각선 아래로 움직일 수 있다.")
    void blackPawnFirstDiagonalStep() {
        Position b7 = Position.create("b7");
        Position a6 = Position.create("a6");
        Position c6 = Position.create("c6");
        assertTrue(blackPawn.verifyMovePosition(b7, a6));
        assertTrue(blackPawn.verifyMovePosition(b7, c6));
    }

    @Test
    @DisplayName("검은색 pawn은 처음 움직임이 아닐 때 한 칸 대각선 아래로 움직일 수 있다.")
    void blackPawnNotFirstDiagonalStep() {
        Position b6 = Position.create("b6");
        Position a5 = Position.create("a5");
        Position c5 = Position.create("c5");
        assertTrue(blackPawn.verifyMovePosition(b6, a5));
        assertTrue(blackPawn.verifyMovePosition(b6, c5));
    }

    @Test
    @DisplayName("검은색 pawn은 뒤로 후퇴할 수 없다.")
    void blackPawnCannotMoveBackward() {
        Position b7 = Position.create("b7");
        Position b8 = Position.create("b8");
        assertFalse(blackPawn.verifyMovePosition(b7, b8));
    }
}