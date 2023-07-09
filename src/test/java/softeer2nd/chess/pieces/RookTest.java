package softeer2nd.chess.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.Position;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static softeer2nd.chess.pieces.PieceTestHelper.*;

class RookTest {
    private Piece rook;

    @BeforeEach
    void setUp() {
        rook = Piece.createBlackRook();
    }

    @Test
    @DisplayName("Rook은 상하좌우 칸수의 제한 없이 움직일 수 있다.")
    void moveKing() {
        List<Position> positions = createPositions(
                "b5", "a5", "e5", "f5", "g5", "h5",
                "d8", "d7", "d6", "d4", "d3", "d2", "d1"
        );
        shouldBeAllTrue(rook, Position.create("d5"), positions);
    }

    @Test
    @DisplayName("Rook은 상하좌우 이외 방향으로 움직일 수 없다.")
    void canNotMove() {
        List<Position> positions = createPositions("e6", "c6", "c4", "e4");
        shouldBeAllFalse(rook, Position.create("d5"), positions);
    }

    @Test
    @DisplayName("제자리로 움직일 수 없다.")
    void doNotMove() {
        Position d5 = Position.create("d5");
        assertFalse(rook.verifyMovePosition(d5, d5));
    }
}