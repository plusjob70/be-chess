package softeer2nd.chess.pieces;

import softeer2nd.chess.Position;
import softeer2nd.chess.exceptions.IllegalMoveException;

import static softeer2nd.chess.pieces.PieceDirection.hasDirection;
import static softeer2nd.chess.pieces.PieceDirection.isDiagonalDirection;
import static softeer2nd.chess.pieces.Piece.Type.BISHOP;

public class Bishop extends Piece {
    public Bishop(Color color) {
        super(color, BISHOP);
    }

    /**
     * Bishop은 대각선 방향으로 칸수의 제한 없이 움직일 수 있다.
     * @param source 현재 Bishop의 위치
     * @param destination Bishop이 이동할 위치
     * @return true if bishop can move else false
     */
    @Override
    public void verifyMovePosition(Position source, Position destination) {
        if (!hasDirection(source, destination) || !isDiagonalDirection(source, destination)) {
            throw new IllegalMoveException("이동할 수 없습니다.");
        }
    }
}