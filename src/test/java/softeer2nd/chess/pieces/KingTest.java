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

class KingTest {
    private Piece king;
    private Board board;
    private ChessGame chessGame;

    @BeforeEach
    void setUp() {
        king = createWhiteKing();
        board = new Board();
        chessGame = new ChessGame(board);
    }

    @Test
    @DisplayName("King은 상, 하, 좌, 우, 대각선으로 한칸만 이동이 가능하다.")
    void moveKing() {
        List<Position> positions = createPositions("c5", "e5", "d4", "d6", "c6", "c4", "e6", "e4");
        allNotThrow(board, king, Position.create("d5"), positions);
    }

    @Test
    @DisplayName("King은 상, 하, 좌, 우, 대각선 한 칸 이외에 이동할 수 없다.")
    void canNotMove() {
        List<Position> positions = createPositions("f5", "g5");
        allThrow(board, king, Position.create("d5"), positions);
    }

    @Test
    @DisplayName("제자리로 움직일 수 없다.")
    void doNotMove() {
        chessGame.initialize();
        Position d5 = Position.create("d5");
        board.putPiece(d5, king);

        assertThatThrownBy(() -> chessGame.move(d5, d5))
                .isInstanceOf(IllegalMoveException.class);
    }

    @Test
    @DisplayName("같은 색 기물을 잡아먹을 수 없다.")
    void doNotEatSameColor() {
        chessGame.initialize();
        board.initializeEmpty();

        Position b2 = Position.create("b2");
        Position b3 = Position.create("b3");

        board.putPiece(b2, createWhiteKing());
        board.putPiece(b3, createWhitePawn());

        assertThatThrownBy(() -> chessGame.move(b2, b3))
                .isInstanceOf(IllegalMoveException.class);
    }
}