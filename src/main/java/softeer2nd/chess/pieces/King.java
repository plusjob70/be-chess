package softeer2nd.chess.pieces;

import softeer2nd.chess.Position;

import static softeer2nd.chess.pieces.Piece.Type.KING;

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
    public boolean verifyMovePosition(Position source, Position destination) {
        int deltaX = Math.abs(destination.getX() - source.getX());
        int deltaY = Math.abs(destination.getY() - source.getY());
        return (deltaX == 1 && deltaY == 0) ||
                (deltaX == 0 && deltaY == 1) ||
                (deltaX == 1 && deltaY == 1);
    }
}