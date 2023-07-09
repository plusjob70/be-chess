package softeer2nd.chess.pieces;

import softeer2nd.chess.Position;

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
     * @return true if queen can move else false
     */
    @Override
    public boolean verifyMovePosition(Position source, Position destination) {
        if (this.isWhite()) {
            return validateWhitePawn(source.getX(), source.getY(), destination.getX(), destination.getY());
        } else if (this.isBlack()) {
            return validateBlackPawn(source.getX(), source.getY(), destination.getX(), destination.getY());
        }
        return false;
    }

    /**
     * src -> dest로 이동하는 흰색 Pawn에 대한 유효성 검사
     * @param srcX Pawn의 출발지점의 x좌표
     * @param srcY Pawn의 출발지점의 y좌표
     * @param destX Pawn의 도착지점의 x좌표
     * @param destY Pawn의 도착지점의 y좌표
     * @return true if 흰색 Pawn의 움직임이 유효 else false
     */
    private boolean validateWhitePawn(int srcX, int srcY, int destX, int destY) {
        if (srcX <= destX) {
            return false;
        }
        int diffX = Math.abs(srcX - destX);
        int diffY = Math.abs(srcY - destY);

        if (srcX == 6) {
            return validateFirstStep(diffX, diffY);
        }
        return validateNotFirstStep(diffX, diffY);
    }

    /**
     * src -> dest로 이동하는 검은색 Pawn에 대한 유효성 검사
     * @param srcX Pawn의 출발지점의 x좌표
     * @param srcY Pawn의 출발지점의 y좌표
     * @param destX Pawn의 도착지점의 x좌표
     * @param destY Pawn의 도착지점의 y좌표
     * @return true if 검은색 Pawn의 움직임이 유효 else false
     */
    private boolean validateBlackPawn(int srcX, int srcY, int destX, int destY) {
        if (srcX >= destX) {
            return false;
        }
        int diffX = Math.abs(destX - srcX);
        int diffY = Math.abs(destY - srcY);

        if (srcX == 1) {
            return validateFirstStep(diffX, diffY);
        }
        return validateNotFirstStep(diffX, diffY);
    }

    /**
     * 첫 움직임일 때 해당 움직임의 유효성 검사
     * @param diffX 출발지와 도착지 간의 x좌표 차이
     * @param diffY 출발지와 도착지 간의 y좌표 차이
     * @return true if (앞으로 두 칸이내) 또는 (대각선 오른쪽 앞 한 칸) 또는 (대각선 왼쪽 앞 한 칸) 이동 가능 else false
     */
    private boolean validateFirstStep(int diffX, int diffY) {
        return isWithinTwoStep(diffX, diffY) || isDiagonalOneStepForward(diffX, diffY);
    }

    /**
     * 첫 움직임이 아닐 때 해당 움직임의 유효성 검사
     * @param diffX 출발지와 도착지 간의 x좌표 차이
     * @param diffY 출발지와 도착지 간의 y좌표 차이
     * @return true if (앞으로 한 칸) 또는 (대각선 오른쪽 앞 한 칸) 또는 (대각선 왼쪽 앞 한 칸) 이동 가능 else false
     */
    private boolean validateNotFirstStep(int diffX, int diffY) {
        return isOneStepForward(diffX, diffY) || isDiagonalOneStepForward(diffX, diffY);
    }

    /**
     * 앞으로 두 칸 이내의 움직임인지 검사
     * @param diffX 출발지와 도착지 간의 x좌표 차이
     * @param diffY 출발지와 도착지 간의 y좌표 차이
     * @return true 앞으로 두 칸 이내 이동 else false
     */
    private boolean isWithinTwoStep(int diffX, int diffY) {
        return diffX <= 2 && diffY == 0;
    }

    /**
     * 앞으로 한 칸 앞으로의 움직임인지 검사
     * @param diffX 출발지와 도착지 간의 x좌표 차이
     * @param diffY 출발지와 도착지 간의 y좌표 차이
     * @return true if 앞으로 한 칸 앞으로의 움직임 else false
     */
    private boolean isOneStepForward(int diffX, int diffY) {
        return diffX == 1 && diffY == 0;
    }

    /**
     * 대각선 한 칸 앞으로의 움직임인지 검사
     * @param diffX 출발지와 도착지 간의 x좌표 차이
     * @param diffY 출발지와 도착지 간의 y좌표 차이
     * @return true if 대각선 한 칸 앞으로의 움직임 else false
     */
    private boolean isDiagonalOneStepForward(int diffX, int diffY) {
        return diffX == 1 && diffY == 1;
    }
}