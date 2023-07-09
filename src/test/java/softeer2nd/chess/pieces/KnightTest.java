package softeer2nd.chess.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.Position;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static softeer2nd.chess.pieces.PieceTestHelper.*;

class KnightTest {
    private Piece knight;

    @BeforeEach
    void setUp() {
        knight = Piece.createBlackKnight();
    }

    @Test
    @DisplayName("Knight는 L자형으로 움직일 수 있다.")
    void moveKing() {
        List<Position> positions = createPositions("c6", "b5", "b3", "c2", "e2", "f3", "f5", "e6");
        shouldBeAllTrue(knight, Position.create("d4"), positions);
    }

    @Test
    @DisplayName("Knight는 L자 이외 방향으로 움직일 수 없다.")
    void canNotMove() {
        List<Position> positions = createPositions("c5", "e5", "e3", "c3", "d3", "c4");
        shouldBeAllFalse(knight, Position.create("d4"), positions);
    }

    @Test
    @DisplayName("제자리로 움직일 수 없다.")
    void doNotMove() {
        Position d5 = Position.create("d5");
        assertFalse(knight.verifyMovePosition(d5, d5));
    }
}