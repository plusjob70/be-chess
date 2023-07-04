package softeer2nd.chess;

import softeer2nd.chess.pieces.Pawn;

import java.util.HashMap;
import java.util.Map;

public class Board {

    private static final int ROW_SIZE = 8;
    private static final int COLUMN_SIZE = 8;
    private static final char BLANK_REPRESENTATION = '.';

    private static final int[] ROWS = {8, 7, 6, 5, 4, 3, 2, 1};
    private static final String[] COLUMNS = {"a", "b", "c", "d", "e", "f", "g", "h"};
    
    private final Map<String, Pawn> pieces;

    public Board() {
        pieces = new HashMap<>();
    }

    public void initialize() {
        initializePawns(Pawn.WHITE_COLOR, 7);
        initializePawns(Pawn.BLACK_COLOR, 2);
    }

    private void initializePawns(String color, int row) {
        for (int i = 0; i < ROW_SIZE; i++) {
            pieces.put(COLUMNS[i] + row, new Pawn(color));
        }
    }

    public String getWhitePawnsResult() {
        return getPawnsResult(Pawn.WHITE_COLOR);
    }

    public String getBlackPawnsResult() {
        return getPawnsResult(Pawn.BLACK_COLOR);
    }

    private String getPawnsResult(String color) {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<String, Pawn> entry : pieces.entrySet()) {
            if (entry.getValue().getColor().equals(color)) {
                sb.append(entry.getValue().getRepresentation());
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

    public String print() {
        String index;
        StringBuilder sb = new StringBuilder();

        for (int row : ROWS) {
            for (String column : COLUMNS) {
                index = column + row;

                if (pieces.containsKey(index)) {
                    sb.append(pieces.get(index).getRepresentation());
                } else {
                    sb.append(BLANK_REPRESENTATION);
                }
            }
            sb.append("\n");
        }
        sb.deleteCharAt(sb.length() - 1);

        return sb.toString();
    }

}
