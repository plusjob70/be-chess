package softeer2nd.chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.pieces.Pawn;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BoardTest {

    private Board board;

    @BeforeEach
    public void createBoard() {
        board = new Board();
    }

    @Test
    @DisplayName("보드에 Pawn을 추가한다.")
    public void addPawn() {
        verifyAdd(new Pawn(Pawn.WHITE_COLOR), 1, "a8");
        verifyAdd(new Pawn(Pawn.BLACK_COLOR), 2, "b8");
    }

    @Test
    @DisplayName("보드에는 Pawn 객체만 추가될 수 있다.")
    public void addOtherObject() {
        // 컴파일 에러 발생
        // board.add(new Integer("7"));
    }

    @Test
    @DisplayName("보드의 범위 밖에서 Pawn을 찾을 때 예외가 발생한다.")
    public void findPawnOutOfBounds() {
        board.add(new Pawn());

        assertThrows(IllegalArgumentException.class,
                () -> board.findPawn("a1")
        );
    }

    @Test
    @DisplayName("보드 초기화 후에 흰색, 검은색 Pawn이 각각 8개씩 있다.")
    public void initialize() throws Exception {
        board.initialize();
        assertEquals("pppppppp", board.getWhitePawnsResult());
        assertEquals("PPPPPPPP", board.getBlackPawnsResult());
    }

    private void verifyAdd(Pawn pawn, int expectedBoardSize, String pawnIndex) {
        board.add(pawn);
        assertEquals(expectedBoardSize, board.size());
        assertEquals(pawn, board.findPawn(pawnIndex));
    }

}