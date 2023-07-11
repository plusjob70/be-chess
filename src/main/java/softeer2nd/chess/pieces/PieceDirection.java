package softeer2nd.chess.pieces;

import softeer2nd.chess.Position;

public class PieceDirection {
    public static boolean hasDirection(Position source, Position destination) {
        return !source.equals(destination);
    }

    public static boolean isLinearDirection(Position source, Position destination) {
        int deltaX = getDelta(destination.getX(), source.getX());
        int deltaY = getDelta(destination.getY(), source.getY());
        return (deltaX == 0 && deltaY != 0) || (deltaX != 0 && deltaY == 0);
    }

    public static boolean isDiagonalDirection(Position source, Position destination) {
        int deltaX = getDelta(destination.getX(), source.getX());
        int deltaY = getDelta(destination.getY(), source.getY());
        return deltaX == deltaY;
    }

    public static boolean isKingDirection(Position source, Position destination) {
        int deltaX = getDelta(destination.getX(), source.getX());
        int deltaY = getDelta(destination.getY(), source.getY());
        return (deltaX == 1 && deltaY == 0) || (deltaX == 0 && deltaY == 1) || (deltaX == 1 && deltaY == 1);
    }

    public static boolean isKnightDirection(Position source, Position destination) {
        int deltaX = getDelta(destination.getX(), source.getX());
        int deltaY = getDelta(destination.getY(), source.getY());
        return (deltaX == 1 && deltaY == 2) || (deltaX == 2 && deltaY == 1);
    }

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

    private static int getDelta(int a, int b) {
        return Math.abs(a - b);
    }
}
