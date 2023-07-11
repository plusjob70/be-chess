package softeer2nd.chess.pieces;

import softeer2nd.chess.Position;

/**
 * 기물의 이동 경로를 검사하기 위한 클래스
 */
public class PieceDirection {
    /**
     * 방향이 있는지 검사
     * @param source 출발 위치
     * @param destination 도착 위치
     * @return true if 방향이 있으면 else false
     */
    public static boolean hasDirection(Position source, Position destination) {
        return !source.equals(destination);
    }

    /**
     * 출발 위치에서 도착 위치 간의 경로가 직선 방향인지 검사
     * @param source 출발 위치
     * @param destination 도착 위치
     * @return true if 직선 방향이면 else false
     */
    public static boolean isLinearDirection(Position source, Position destination) {
        int deltaX = getDelta(destination.getX(), source.getX());
        int deltaY = getDelta(destination.getY(), source.getY());
        return (deltaX == 0 && deltaY != 0) || (deltaX != 0 && deltaY == 0);
    }

    /**
     * 출발 위치에서 도착 위치 간의 경로가 대각선 방향인지 검사
     * @param source 출발 위치
     * @param destination 도착 위치
     * @return true if 대각선 방향이면 else false
     */
    public static boolean isDiagonalDirection(Position source, Position destination) {
        int deltaX = getDelta(destination.getX(), source.getX());
        int deltaY = getDelta(destination.getY(), source.getY());
        return deltaX == deltaY;
    }

    /**
     * 도착 위치가 King의 행마 범위에 포함되는지 검사
     * @param source 출발 위치
     * @param destination 도착 위치
     * @return true if destination이 King 행마법에 포함되면 else false
     */
    public static boolean isKingDirection(Position source, Position destination) {
        int deltaX = getDelta(destination.getX(), source.getX());
        int deltaY = getDelta(destination.getY(), source.getY());
        return (deltaX == 1 && deltaY == 0) || (deltaX == 0 && deltaY == 1) || (deltaX == 1 && deltaY == 1);
    }

    /**
     * 도착 위치가 Knight의 행마 범위에 포함되는지 검사
     * @param source 출발 위치
     * @param destination 도착 위치
     * @return true if destination이 Knight 행마법에 포함되면 else false
     */
    public static boolean isKnightDirection(Position source, Position destination) {
        int deltaX = getDelta(destination.getX(), source.getX());
        int deltaY = getDelta(destination.getY(), source.getY());
        return (deltaX == 1 && deltaY == 2) || (deltaX == 2 && deltaY == 1);
    }

    /**
     * 도착 위치가 흰색 Pawn의 행마 범위에 포함되는지 검사
     * @param source 출발 위치
     * @param destination 도착 위치
     * @return true if destination이 흰색 Pawn 행마법에 포함되면 else false
     */
    public static boolean isWhitePawnDirection(Position source, Position destination) {
        int srcX = source.getX();
        int srcY = source.getY();
        int destX = destination.getX();
        int destY = destination.getY();

        if (srcX <= destX) {
            return false;
        }

        int deltaX = getDelta(srcX, destX);
        int deltaY = getDelta(srcY, destY);

        if (srcX == 6) {
            return ((deltaX <= 2 && deltaY == 0) || (deltaX == 1 && deltaY == 1));
        }
        return (deltaX == 1 && deltaY == 0) || (deltaX == 1 && deltaY == 1);
    }

    /**
     * 도착 위치가 검은색 Pawn의 행마 범위에 포함되는지 검사
     * @param source 출발 위치
     * @param destination 도착 위치
     * @return true if destination이 검은색 Pawn 행마법에 포함되면 else false
     */
    public static boolean isBlackPawnDirection(Position source, Position destination) {
        int srcX = source.getX();
        int srcY = source.getY();
        int destX = destination.getX();
        int destY = destination.getY();

        if (srcX >= destX) {
            return false;
        }

        int deltaX = getDelta(srcX, destX);
        int deltaY = getDelta(srcY, destY);

        if (srcX == 1) {
            return (deltaX <= 2 && deltaY == 0) || (deltaX == 1 && deltaY == 1);
        }
        return (deltaX == 1 && deltaY == 0) || (deltaX == 1 && deltaY == 1);
    }

    /**
     * a와 b 간의 거리
     */
    private static int getDelta(int a, int b) {
        return Math.abs(a - b);
    }
}