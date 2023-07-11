package softeer2nd.chess.pieces;

import org.assertj.core.api.Assertions;
import softeer2nd.chess.Board;
import softeer2nd.chess.Position;
import softeer2nd.chess.exceptions.IllegalMoveException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PieceTestHelper {
    public static List<Position> createPositions(String... pos) {
        List<Position> positions = new ArrayList<>();
        for (String s : pos) {
            positions.add(Position.create(s));
        }
        return positions;
    }

    public static void allNotThrow(Board board, Piece piece, Position source, List<Position> destinations) {
        board.putPiece(source, piece);
        for (Position destination : destinations) {
            assertDoesNotThrow(() -> piece.verifyMovePosition(source, destination));
        }
    }

    public static void allThrow(Board board, Piece piece, Position source, List<Position> destinations) {
        board.putPiece(source, piece);
        for (Position destination : destinations) {
            Assertions.assertThatThrownBy(() -> piece.verifyMovePosition(source, destination))
                    .isInstanceOf(IllegalMoveException.class);
        }
    }
}