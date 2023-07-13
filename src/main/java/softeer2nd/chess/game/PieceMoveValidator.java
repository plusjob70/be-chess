package softeer2nd.chess.game;

import softeer2nd.chess.exceptions.IllegalMoveException;
import softeer2nd.chess.pieces.Piece;

import static softeer2nd.chess.pieces.Piece.Type.PAWN;
import static softeer2nd.chess.pieces.PieceDirection.*;

public class PieceMoveValidator {
    private final Board board;

    public PieceMoveValidator(Board board) {
        this.board = board;
    }

    /**
     * 기물의 이동이 올바른지 검증한다.
     * @param source 기물의 출발 위치
     * @param destination 기물의 도착 위치
     * @param srcPiece 출발 위치의 기물
     * @param destPiece 도착 위치의 기물
     */
    public void validate(Position source, Position destination, Piece srcPiece, Piece destPiece) {
        // 기물의 이동이 기본 행마법을 따르는지 검증한다.
        srcPiece.verifyPieceMovementRule(source, destination);

        // 기물이 자신의 팀을 공격하는 것은 아닌지 검증한다.
        verifyTeamKill(srcPiece, destPiece);

        // 기물이 같은 위치로 이동하는지 검증한다.
        verifyMoveSamePosition(source, destination);

        // 기물의 이동경로 상에 다른 기물이 있는지 검증한다.
        verifyAnyPieceOnLinearPath(source, destination);
        verifyAnyPieceOnDiagonalPath(source, destination);

        // 움직일 기물이 폰인 경우 폰의 행마법을 추가적으로 검증한다.
        verifyPawnPath(source, destination, srcPiece, destPiece);
    }

    /**
     * 기물 이동 시 서로 같은 팀의 기물로 이동하는지 검사한다.
     * @param srcPiece 출발 위치의 기물
     * @param destPiece 도착 위치의 기물
     */
    private void verifyTeamKill(Piece srcPiece, Piece destPiece) {
        if (srcPiece.isColorOf(destPiece)) {
            throw new IllegalMoveException("같은 팀을 공격할 수 없습니다.");
        }
    }

    /**
     * 같은 위치로 이동하는지 검사
     * @param source 출발 위치
     * @param destination 도착 위치
     */
    private void verifyMoveSamePosition(Position source, Position destination) {
        if (hasDirection(source, destination)) {
            return;
        }
        throw new IllegalMoveException("같은 위치로 이동할 수 없습니다.");
    }

    /**
     * 직선 이동 시 경로 상에 기물이 가로막고 있는지 검사
     * @param source 출발 위치
     * @param destination 도착 위치
     */
    private void verifyAnyPieceOnLinearPath(Position source, Position destination) {
        if (!isLinearDirection(source, destination)) {
            return;
        }
        int srcX = source.getX();
        int srcY = source.getY();
        int destX = destination.getX();
        int destY = destination.getY();

        if (srcX == destX) {
            int minY = Math.min(srcY, destY);
            int maxY = Math.max(srcY, destY);
            for (int y = minY + 1; y < maxY; y++) {
                if (board.findPiece(srcX, y).isBlank()) {
                    continue;
                }
                throw new IllegalMoveException("이동경로 상에 다른 기물이 존재하여 해당 위치로 이동할 수 없습니다.");
            }
        }
        if (srcY == destY) {
            int minX = Math.min(srcX, destX);
            int maxX = Math.max(srcX, destX);
            for (int x = minX + 1; x < maxX; x++) {
                if (board.findPiece(x, srcY).isBlank()) {
                    continue;
                }
                throw new IllegalMoveException("이동경로 상에 다른 기물이 존재하여 해당 위치로 이동할 수 없습니다.");
            }
        }
    }

    /**
     * 대각선 이동 시 경로 상에 기물이 가로막고 있는지 검사
     * @param source 출발 위치
     * @param destination 도착 위치
     */
    private void verifyAnyPieceOnDiagonalPath(Position source, Position destination) {
        if (!isDiagonalDirection(source, destination)) {
            return;
        }
        int srcX = source.getX();
        int srcY = source.getY();
        int destX = destination.getX();
        int destY = destination.getY();

        int directionX = (destX > srcX) ? 1 : -1;
        int directionY = (destY > srcY) ? 1 : -1;
        int currentX = srcX + directionX;
        int currentY = srcY + directionY;

        while (currentX != destX && currentY != destY) {
            if (board.findPiece(currentX, currentY).isBlank()) {
                currentX += directionX;
                currentY += directionY;
                continue;
            }
            throw new IllegalMoveException("이동경로 상에 다른 기물이 존재하여 해당 위치로 이동할 수 없습니다.");
        }
    }

    /**
     * Pawn 이동 시 움직일 수 있는지 검사
     * @param srcPiece 움직일 기물
     * @param source 출발 위치
     * @param destination 도착 위치
     */
    private void verifyPawnPath(Position source, Position destination, Piece srcPiece, Piece destPiece) {
        if (!srcPiece.isTypeOf(PAWN)) {
            return;
        }
        int deltaX = Math.abs(source.getX() - destination.getX());
        int deltaY = Math.abs(source.getY() - destination.getY());

        boolean straight = deltaX > 0 && deltaY == 0;
        boolean diagonal = deltaX == 1 && deltaY == 1;

        if (diagonal && destPiece.isBlank()) {
            throw new IllegalMoveException("폰을 해당 위치로 이동할 수 없습니다.");
        }
        if (straight && !destPiece.isBlank()) {
            throw new IllegalMoveException("폰을 해당 위치로 이동할 수 없습니다.");
        }
    }
}