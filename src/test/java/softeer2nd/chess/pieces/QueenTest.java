package softeer2nd.chess.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.Position;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static softeer2nd.chess.pieces.PieceTestHelper.*;

class QueenTest {
    private Piece queen;

    @BeforeEach
    void setUp() {
        queen = Piece.createBlackQueen();
    }

    @Test
    @DisplayName("queen은 상하좌우, 대각선을 칸수의 제한 없이 움직일 수 있다.")
    void moveKing() {
        List<Position> positions = createPositions(
                "b5", "a5", "e5", "f5", "g5", "h5", "d8", "d7", "d6", "d4", "d3", "d2",
                "c6", "b7", "a8", "c4", "b3", "a2", "e4", "f3", "g2", "h1", "e6", "f7", "g8"
        );
        shouldBeAllTrue(queen, Position.create("d5"), positions);
    }

    @Test
    @DisplayName("Queen은 상하좌우, 대각선 이외 방향으로 움직일 수 없다.")
    void canNotMove() {
        List<Position> positions = createPositions("e7", "f8", "e2", "b4");
        shouldBeAllFalse(queen, Position.create("d5"), positions);
    }

    @Test
    @DisplayName("제자리로 움직일 수 없다.")
    void doNotMove() {
        Position d5 = Position.create("d5");
        assertFalse(queen.verifyMovePosition(d5, d5));
    }
}