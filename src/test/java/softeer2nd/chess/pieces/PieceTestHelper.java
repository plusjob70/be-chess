package softeer2nd.chess.pieces;

import softeer2nd.chess.Position;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PieceTestHelper {
    public static List<Position> createPositions(String... pos) {
        List<Position> positions = new ArrayList<>();
        for (String s : pos) {
            positions.add(Position.create(s));
        }
        return positions;
    }

    public static void shouldBeAllTrue(Piece piece, Position source, List<Position> destinations) {
        for (Position destination : destinations) {
            assertTrue(piece.verifyMovePosition(source, destination));
        }
    }

    public static void shouldBeAllFalse(Piece piece, Position source, List<Position> destinations) {
        for (Position destination : destinations) {
            assertFalse(piece.verifyMovePosition(source, destination));
        }
    }
}