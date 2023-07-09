package softeer2nd.chess.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.Position;

import java.util.List;

import static softeer2nd.chess.pieces.PieceTestHelper.createPositions;
import static softeer2nd.chess.pieces.PieceTestHelper.shouldBeAllFalse;

class BlankTest {
    private Piece blank;

    @BeforeEach
    void setUp() {
        blank = Piece.createBlank();
    }

    @Test
    @DisplayName("Blank는 어디로도 움직일 수 없다.")
    void canNotMove() {
        List<Position> positions = createPositions("d4", "c5", "d6", "e5", "f6", "d5");
        shouldBeAllFalse(blank, Position.create("d5"), positions);
    }
}