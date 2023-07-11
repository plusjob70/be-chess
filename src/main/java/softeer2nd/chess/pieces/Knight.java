package softeer2nd.chess.pieces;

import softeer2nd.chess.Position;
import softeer2nd.chess.exceptions.IllegalMoveException;

import static softeer2nd.chess.pieces.PieceDirection.*;
import static softeer2nd.chess.pieces.Piece.Type.KNIGHT;

public class Knight extends Piece {
    public Knight(Color color) {
        super(color, KNIGHT);
    }

    /**
     * Knight는 L자 모양으로 움직일 수 있다.
     * @param source 현재 Knight의 위치
     * @param destination Knight가 이동할 위치
     * @return true if knight can move else false
     */
    @Override
    public void verifyMovePosition(Position source, Position destination) {
        if (!isKnightDirection(source, destination)) {
            throw new IllegalMoveException("나이트를 해당 위치로 이동할 수 없습니다.");
        }
    }
}