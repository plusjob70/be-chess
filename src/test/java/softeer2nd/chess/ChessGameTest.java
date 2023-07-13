package softeer2nd.chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.game.Board;
import softeer2nd.chess.game.ChessGame;
import softeer2nd.chess.game.Position;

import static org.junit.jupiter.api.Assertions.*;
import static softeer2nd.chess.pieces.PieceFactory.*;

class ChessGameTest {

    private Board board;
    private ChessGame chessGame;

    @BeforeEach
    void setUp() {
        board = new Board();
        chessGame = new ChessGame(board);
    }

    @Test
    @DisplayName("기물이 A -> B로 이동하면 B에 기물이 있다.")
    void moveSource() {
        chessGame.initialize();
        Position source = Position.create("a2");
        Position destination = Position.create("a3");

        chessGame.move(source, destination);

        assertEquals(board.findPiece(destination), createWhitePawn());
    }

    @Test
    @DisplayName("기물이 A -> B로 이동하면 A에는 NO_PIECE 기물이 존재한다.")
    void addedNoPiece() {
        chessGame.initialize();
        Position source = Position.create("a2");
        Position destination = Position.create("a3");

        chessGame.move(source, destination);

        assertEquals(board.findPiece(source), createBlank());
    }

    @Test
    @DisplayName("흰색 킹이 죽으면 게임이 종료된다.")
    void whiteKingDie() {
        // given
        chessGame.initialize();
        Position e1 = Position.create("e1");
        Position e2 = Position.create("e2");
        Position e4 = Position.create("e4");
        chessGame.move(e2, e4);              // 흰색 턴 소비

        board.putPiece(e2, createBlackRook());

        // when
        chessGame.move(e2, e1);

        // then
        assertTrue(chessGame.isOver());
    }

    @Test
    @DisplayName("검은색 킹이 죽으면 게임이 종료된다.")
    void blackKingDie() {
        // given
        chessGame.initialize();
        Position e7 = Position.create("e7");
        Position e8 = Position.create("e8");
        board.putPiece(e7, createWhiteRook());

        // when
        chessGame.move(e7, e8);

        // then
        assertTrue(chessGame.isOver());
    }

    @Test
    @DisplayName("양쪽 킹이 죽지 않으면 게임이 종료되지 않는다.")
    void anyKingDoesNotDie() {
        chessGame.initialize();
        Position g2 = Position.create("g2");
        Position g3 = Position.create("g3");

        chessGame.move(g2, g3);

        assertFalse(chessGame.isOver());
    }
}