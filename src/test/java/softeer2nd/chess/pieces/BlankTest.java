package softeer2nd.chess.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.Board;
import softeer2nd.chess.Position;

import java.util.List;

import static softeer2nd.chess.pieces.PieceFactory.*;
import static softeer2nd.chess.pieces.PieceTestHelper.createPositions;

class BlankTest {
    private Piece blank;
    private Board board;

    @BeforeEach
    void setUp() {
        blank = createBlank();
        board = new Board();
    }

    @Test
    @DisplayName("Blank는 어디로도 움직일 수 없다.")
    void canNotMove() {
        List<Position> positions = createPositions("d4", "c5", "d6", "e5", "f6", "d5");
        PieceTestHelper.allThrow(board, blank, Position.create("d5"), positions);
    }
}