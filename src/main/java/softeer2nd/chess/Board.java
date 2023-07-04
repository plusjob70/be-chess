package softeer2nd.chess;

import softeer2nd.chess.pieces.Pawn;

import java.util.HashMap;
import java.util.Map;

public class Board {

    private static final int ROW_SIZE = 8;
    private static final int COLUMN_SIZE = 8;

    private static final int[] ROWS = {8, 7, 6, 5, 4, 3, 2, 1};
    private static final String[] COLUMNS = {"a", "b", "c", "d", "e", "f", "g", "h"};
    
    private final Map<String, Pawn> pieces;

    public Board() {
        pieces = new HashMap<>();
    }

    public void initialize() {
        for (int i = 0; i < ROW_SIZE; i++) {
            pieces.put(COLUMNS[i] + "6", new Pawn(Pawn.WHITE_COLOR));
        }

        for (int i = 0; i < ROW_SIZE; i++) {
            pieces.put(COLUMNS[i] + "3", new Pawn(Pawn.BLACK_COLOR));
        }
    }

    public String getWhitePawnsResult() {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, Pawn> entry : pieces.entrySet()) {
            if (entry.getValue().getColor().equals(Pawn.WHITE_COLOR)) {
                sb.append(Pawn.WHITE_REPRESENTATION);
            }
        }

        return sb.toString();
    }

    public String getBlackPawnsResult() {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, Pawn> entry : pieces.entrySet()) {
            if (entry.getValue().getColor().equals(Pawn.BLACK_COLOR)) {
                sb.append(Pawn.BLACK_REPRESENTATION);
            }
        }

        return sb.toString();
    }

    public void add(Pawn pawn) {
        String index;

        for (int row : ROWS) {
            for (String column : COLUMNS) {
                index = column + row;
                if (!pieces.containsKey(index)) {
                    pieces.put(index, pawn);
                    return;
                }
            }
        }
    }

    public int size() {
        return pieces.size();
    }

    public Pawn findPawn(String index) {
        if (pieces.containsKey(index)) {
            return pieces.get(index);
        }
        throw new IllegalArgumentException();
    }

}
