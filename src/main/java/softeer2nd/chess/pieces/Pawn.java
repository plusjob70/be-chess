package softeer2nd.chess.pieces;

import softeer2nd.chess.game.Position;
import softeer2nd.chess.exceptions.IllegalMoveException;

import static softeer2nd.chess.pieces.PieceDirection.*;
import static softeer2nd.chess.pieces.Piece.Type.PAWN;

public class Pawn extends Piece {
    public Pawn(Color color) {
        super(color, PAWN);
    }

    /**
     * Pawn의 행마법은 다음과 같다.
     * <li>pawn은 후퇴할 수 없다.</li>
     * <li>처음 움직이는 pawn은 두 칸 앞으로 전진할 수 있다.</li>
     * <li>앞에 기물이 없다면 한칸 전진할 수 있다.</li>
     * <li>전방 대각선에 기물이 있다면 대각선으로 한 칸 이동할 수 있다.</li>
     * @param source 현재 기물의 위치
     * @param destination 기물이 이동할 위치
     */
    @Override
    public void verifyPieceMovementRule(Position source, Position destination) {
        if (this.isWhite() && isWhitePawnDirection(source, destination)) {
            return;
        }
        if (this.isBlack() && isBlackPawnDirection(source, destination)) {
            return;
        }
        throw new IllegalMoveException("폰을 해당 위치로 이동할 수 없습니다.");
    }
}