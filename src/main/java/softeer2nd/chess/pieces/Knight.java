package softeer2nd.chess.pieces;

import softeer2nd.chess.Position;

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
    public boolean verifyMovePosition(Position source, Position destination) {
        int deltaX = Math.abs(destination.getX() - source.getX());
        int deltaY = Math.abs(destination.getY() - source.getY());
        return (deltaX == 1 && deltaY == 2) || (deltaX == 2 && deltaY == 1);
    }
}