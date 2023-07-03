package softeer2nd.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PawnTest {

    @Test
    @DisplayName("폰이 생성되어야 한다.")
    public void create() {
        Pawn pawn1 = new Pawn("white");
        assertThat(pawn1.getColor()).isEqualTo("white");

        Pawn pawn2 = new Pawn("black");
        assertThat(pawn2.getColor()).isEqualTo("black");
    }

}