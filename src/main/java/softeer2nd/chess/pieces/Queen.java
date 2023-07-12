package softeer2nd.chess.pieces;

import softeer2nd.chess.Position;
import softeer2nd.chess.exceptions.IllegalMoveException;

import static softeer2nd.chess.pieces.PieceDirection.*;
import static softeer2nd.chess.pieces.Piece.Type.QUEEN;

public class Queen extends Piece {
    public Queen(Color color) {
        super(color, QUEEN);
    }

    /**
     * Queen은 상하좌우, 대각선으로 칸수의 제한 없이 움직일 수 있다.
     * @param source 현재 Queen의 위치
     * @param destination Queen이 이동할 위치
     */
    @Override
    public void verifyPieceMovementRule(Position source, Position destination) {
        if (isDiagonalDirection(source, destination) || isLinearDirection(source, destination)) {
            return;
        }
        throw new IllegalMoveException("퀸을 해당 위치로 이동할 수 없습니다.");
    }
}