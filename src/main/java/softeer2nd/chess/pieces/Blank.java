package softeer2nd.chess.pieces;

import softeer2nd.chess.Position;

import static softeer2nd.chess.pieces.Piece.Color.*;
import static softeer2nd.chess.pieces.Piece.Type.NO_PIECE;

public class Blank extends Piece {
    public Blank() {
        super(NO_COLOR, NO_PIECE);
    }

    /**
     * 빈칸은 어디에도 움직일 수 없다.
     * @param source 현재 빈칸의 위치
     * @param destination 빈칸이 이동할 위치
     * @return false
     */
    @Override
    public boolean verifyMovePosition(Position source, Position destination) {
        return false;
    }
}