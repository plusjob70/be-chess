package softeer2nd.chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

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
                appendNewLine("RNBQKBNR  8") +
                        appendNewLine("PPPPPPPP  7") +
                        appendNewLine(blankRank + "  6") +
                        appendNewLine(blankRank + "  5") +
                        appendNewLine(blankRank + "  4") +
                        appendNewLine(blankRank + "  3") +
                        appendNewLine("pppppppp  2") +
                        appendNewLine("rnbqkbnr  1") +
                        appendNewLine("") +
                        "abcdefgh",
                chessView.showBoard());
    }

    private void initializeGame() {
        board.initializeRank8();
        board.initializePawnRank(7);
        board.initializePawnRank(2);
        board.initializeRank1();
    }
}