package softeer2nd.chess.pieces;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.game.Board;
import softeer2nd.chess.game.ChessGame;
import softeer2nd.chess.game.Position;
import softeer2nd.chess.exceptions.IllegalMoveException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static softeer2nd.chess.pieces.PieceFactory.*;
import static softeer2nd.chess.pieces.PieceTestHelper.*;

class KnightTest {
    private Piece knight;
    private Board board;
    private ChessGame chessGame;

    @BeforeEach
    void setUp() {
        knight = createWhiteKnight();
        board = new Board();
        chessGame = new ChessGame(board);
    }

    @Test
    @DisplayName("Knight는 L자형으로 움직일 수 있다.")
    void moveKing() {
        List<Position> positions = createPositions("c6", "b5", "b3", "c2", "e2", "f3", "f5", "e6");
        allNotThrow(board, knight, Position.create("d4"), positions);
    }

    @Test
    @DisplayName("Knight는 L자 이외 방향으로 움직일 수 없다.")
    void canNotMove() {
        List<Position> positions = createPositions("c5", "e5", "e3", "c3", "d3", "c4");
        allThrow(board, knight, Position.create("d4"), positions);
    }

    @Test
    @DisplayName("제자리로 움직일 수 없다.")
    void doNotMove() {
        chessGame.initialize();
        board.initializeEmpty();
        Position d5 = Position.create("d5");
        board.putPiece(d5, knight);

        assertThatThrownBy(() -> chessGame.move(d5, d5))
                .isInstanceOf(IllegalMoveException.class);
    }

    @Test
    @DisplayName("같은 색 기물을 잡아먹을 수 없다.")
    void doNotEatSameColor() {
        chessGame.initialize();
        board.initializeEmpty();

        Position b2 = Position.create("b2");
        Position c4 = Position.create("c4");

        board.putPiece(b2, createWhiteKnight());
        board.putPiece(c4, createWhitePawn());

        assertThatThrownBy(() -> chessGame.move(b2, c4))
                .isInstanceOf(IllegalMoveException.class);
    }
}