package softeer2nd.chess.pieces;

import softeer2nd.chess.Position;
import softeer2nd.chess.exceptions.IllegalMoveException;

import static softeer2nd.chess.pieces.Piece.Type.KING;
import static softeer2nd.chess.pieces.PieceDirection.isKingDirection;

public class King extends Piece {
    public King(Color color) {
        super(color, KING);
    }

    /**
     * King은 전방위로 한 칸만 움직일 수 있다.
     * @param source 현재 King의 위치
     * @param destination King이 이동할 위치
     * @return true if king can move else false
     */
    @Override
    public void verifyMovePosition(Position source, Position destination) {
        if (!isKingDirection(source, destination)) {
            throw new IllegalMoveException("킹을 해당 위치로 이동할 수 없습니다.");
        }
    }
}