package softeer2nd.chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.game.Board;
import softeer2nd.chess.game.ChessView;

import static org.junit.jupiter.api.Assertions.*;
import static softeer2nd.chess.utils.StringUtils.appendNewLine;

class ChessViewTest {
    private Board board;
    private ChessView chessView;

    @BeforeEach
    void setUp() {
        board = new Board();
        board.initializeEmpty();
        chessView = new ChessView(board);
    }

    @Test
    @DisplayName("체스판이 보여진다.")
    void showBoard() {
        initializeGame();

        String blankRank = "........";
        assertEquals(
                appendNewLine("   abcdefgh") +
                        appendNewLine("") +
                        appendNewLine("8  RNBQKBNR  8") +
                        appendNewLine("7  PPPPPPPP  7") +
                        appendNewLine("6  " + blankRank + "  6") +
                        appendNewLine("5  " + blankRank + "  5") +
                        appendNewLine("4  " + blankRank + "  4") +
                        appendNewLine("3  " + blankRank + "  3") +
                        appendNewLine("2  pppppppp  2") +
                        appendNewLine("1  rnbqkbnr  1") +
                        appendNewLine("") +
                        "   abcdefgh",
                chessView.showBoard());
    }

    private void initializeGame() {
        board.initializeRank8();
        board.initializePawnRank(7);
        board.initializePawnRank(2);
        board.initializeRank1();
    }
}