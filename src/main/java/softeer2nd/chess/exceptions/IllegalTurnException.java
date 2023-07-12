package softeer2nd.chess.exceptions;

import static softeer2nd.chess.pieces.Piece.Color;

public class IllegalTurnException extends RuntimeException {
    public IllegalTurnException(Color color) {
        super(getTotalMessage(color));
    }

    private static String getTotalMessage(Color color) {
        return color.getRepresentation() + "의 차례가 아닙니다.";
    }
}