package softeer2nd.chess.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.Position;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static softeer2nd.chess.pieces.PieceTestHelper.*;

class BishopTest {
    private Piece bishop;

    @BeforeEach
    void setUp() {
        bishop = Piece.createBlackBishop();
    }

    @Test
    @DisplayName("Bishop은 대각선 방향으로 칸수의 제한 없이 움직일 수 있다.")
    void moveKing() {
        List<Position> positions = createPositions(
                "c6", "b7", "a8", "c4", "b3", "a2",
                "e6", "f7", "g8", "e4", "f3", "g2", "h1");
        shouldBeAllTrue(bishop, Position.create("d5"), positions);
    }

    @Test
    @DisplayName("Bishop은 대각선 이외 방향으로 움직일 수 없다.")
    void canNotMove() {
        List<Position> positions = createPositions("d4", "c5", "d6", "e5", "f6");
        shouldBeAllFalse(bishop, Position.create("d5"), positions);
    }

    @Test
    @DisplayName("제자리로 움직일 수 없다.")
    void doNotMove() {
        Position d5 = Position.create("d5");
        assertFalse(bishop.verifyMovePosition(d5, d5));
    }
}