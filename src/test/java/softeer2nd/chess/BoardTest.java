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
        verifyAdd(new Pawn(Pawn.WHITE_COLOR), 1, 0);
        verifyAdd(new Pawn(Pawn.BLACK_COLOR), 2, 1);
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

        assertThrows(IndexOutOfBoundsException.class,
                () -> board.findPawn(10)
        );
    }

    private void verifyAdd(Pawn pawn, int expectedBoardSize, int pawnIndex) {
        board.add(pawn);
        assertEquals(expectedBoardSize, board.size());
        assertEquals(pawn, board.findPawn(pawnIndex));
    }

}