package softeer2nd.chess.pieces;

import softeer2nd.chess.Position;
import softeer2nd.chess.exceptions.IllegalMoveException;

import static softeer2nd.chess.pieces.PieceDirection.*;
import static softeer2nd.chess.pieces.Piece.Type.ROOK;

public class Rook extends Piece {
    public Rook(Color color) {
        super(color, ROOK);
    }

    /**
     * Rook은 상하좌우 방향으로 칸수의 제한 없이 움직일 수 있다.
     * @param source 현재 Rook의 위치
     * @param destination Rook이 이동할 위치
     * @return true if rook can move else false
     */
    @Override
    public void verifyMovePosition(Position source, Position destination) {
        if (!isLinearDirection(source, destination)) {
            throw new IllegalMoveException("룩을 해당 위치로 이동할 수 없습니다.");
        }
    }
}