package softeer2nd.chess.pieces;

import softeer2nd.chess.Position;

import static softeer2nd.chess.pieces.Piece.Type.*;

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
    public boolean verifyMovePosition(Position source, Position destination) {
        if (source.equals(destination)) {
            return false;
        }
        int deltaX = Math.abs(destination.getX() - source.getX());
        int deltaY = Math.abs(destination.getY() - source.getY());
        return deltaX == deltaY;
    }
}