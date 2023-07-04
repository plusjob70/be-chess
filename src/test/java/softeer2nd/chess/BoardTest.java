package softeer2nd.chess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.pieces.Pawn;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BoardTest {

    @Test
    public void create() throws Exception {
        Board board = new Board();

        Pawn white = new Pawn(Pawn.WHITE_COLOR);
        board.add(white);
        assertEquals(1, board.size());
        assertEquals(white, board.findPawn(0));

        Pawn black = new Pawn(Pawn.BLACK_COLOR);
        board.add(black);
        assertEquals(2, board.size());
        assertEquals(black, board.findPawn(1));
    }

    @Test
    @DisplayName("보드에는 Pawn 객체만 추가될 수 있다.")
    public void addOtherObject() {
        Board board = new Board();

        // 컴파일 에러 발생
        // board.add(new Integer("7"));
    }

    @Test
    @DisplayName("보드의 범위 밖에서 Pawn을 찾을 때 예외가 발생한다.")
    public void findPawnOutOfBounds() {
        Board board = new Board();
        board.add(new Pawn());

        assertThrows(IndexOutOfBoundsException.class,
                () -> board.findPawn(10)
        );
    }

}