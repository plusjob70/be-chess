package softeer2nd.chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static softeer2nd.chess.utils.StringUtils.appendNewLine;

class BoardTest {

    private Board board;

    @BeforeEach
    public void createBoard() {
        board = new Board();
    }

    @Test
    @DisplayName("보드에는 Piece 객체만 추가될 수 있다.")
    public void addOtherObject() {
        // 컴파일 에러 발생
        // board.add(new Integer("7"));
    }

    @Test
    @DisplayName("체스판이 초기화 된다.")
    public void initialize() throws Exception {
        board.initialize();
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
                        appendNewLine("") + appendNewLine("") +
                        "abcdefgh",
                board.showBoard());
    }

    @Test
    @DisplayName("보드를 출력한다.")
    public void printBoard() {
        System.out.println(board.showBoard());
    }

    @Test
    @DisplayName("보드 초기화 후에 보드를 출력한다.")
    public void printBoardAfterInitialize() {
        board.initialize();
        System.out.println(board.showBoard());
    }

}