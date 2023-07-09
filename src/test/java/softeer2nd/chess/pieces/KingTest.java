package softeer2nd.chess.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.Position;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static softeer2nd.chess.pieces.PieceTestHelper.*;

class KingTest {
    private Piece king;

    @BeforeEach
    void setUp() {
        king = Piece.createBlackKing();
    }

    @Test
    @DisplayName("King은 상, 하, 좌, 우, 대각선으로 한칸만 이동이 가능하다.")
    void moveKing() {
        List<Position> positions = createPositions("c5", "e5", "d4", "d6", "c6", "c4", "e6", "e4");
        shouldBeAllTrue(king, Position.create("d5"), positions);
    }

    @Test
    @DisplayName("King은 상, 하, 좌, 우, 대각선 한 칸 이외에 이동할 수 없다.")
    void canNotMove() {
        List<Position> positions = createPositions("f5", "g5");
        shouldBeAllFalse(king, Position.create("d5"), positions);
    }

    @Test
    @DisplayName("제자리로 움직일 수 없다.")
    void doNotMove() {
        Position d5 = Position.create("d5");
        assertFalse(king.verifyMovePosition(d5, d5));
    }
}