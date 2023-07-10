package softeer2nd.chess;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.exceptions.BoardIndexOutOfRangeException;
import softeer2nd.chess.exceptions.IllegalIndexExpressionException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PositionTest {
    @Test
    @DisplayName("잘못된 입력은 오류를 발생시킨다.")
    void illegalInput() {
        assertThatThrownBy(() -> Position.create("1a"))
                .isInstanceOf(IllegalIndexExpressionException.class);
        assertThatThrownBy(() -> Position.create("a1_"))
                .isInstanceOf(IllegalIndexExpressionException.class);
        assertThatThrownBy(() -> Position.create("aa"))
                .isInstanceOf(IllegalIndexExpressionException.class);
    }

    @Test
    @DisplayName("체스판 범위에 벗어나는 위치는 만들어지지 않는다.")
    void outOfIndex() {
        assertThatThrownBy(() -> Position.create("a9"))
                .isInstanceOf(BoardIndexOutOfRangeException.class);
        assertThatThrownBy(() -> Position.create("i1"))
                .isInstanceOf(BoardIndexOutOfRangeException.class);
    }

    @Test
    @DisplayName("같은 좌표를 가지는 position은 equality를 가진다.")
    void equality() {
        Position p1 = Position.create("a1");
        Position p2 = Position.create("a1");

        assertEquals(p1, p2);
    }
}