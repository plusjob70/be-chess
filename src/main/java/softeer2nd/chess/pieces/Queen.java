package softeer2nd.chess.pieces;

import softeer2nd.chess.Position;

import static softeer2nd.chess.pieces.Piece.Type.QUEEN;

public class Queen extends Piece {
    public Queen(Color color) {
        super(color, QUEEN);
    }

    /**
     * Queen은 상하좌우, 대각선으로 칸수의 제한 없이 움직일 수 있다.
     * @param source 현재 Queen의 위치
     * @param destination Queen이 이동할 위치
     * @return true if queen can move else false
     */
    @Override
    public boolean verifyMovePosition(Position source, Position destination) {
        if (source.equals(destination)) {
            return false;
        }
        int deltaX = Math.abs(destination.getX() - source.getX());
        int deltaY = Math.abs(destination.getY() - source.getY());
        return (source.getX() == destination.getX()) ||
                (source.getY() == destination.getY()) ||
                (deltaX == deltaY);
    }
}