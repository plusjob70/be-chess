package softeer2nd.chess;

import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.Piece.Color;

import java.util.HashMap;
import java.util.Map;

import static softeer2nd.chess.pieces.Piece.Color.BLACK;
import static softeer2nd.chess.pieces.Piece.Color.WHITE;
import static softeer2nd.chess.utils.StringUtils.appendNewLine;

public class Board {

    private static final int ROW_SIZE = 8;
    private static final int COLUMN_SIZE = 8;
    private static final char BLANK_REPRESENTATION = '.';

    private static final int[] ROWS = {8, 7, 6, 5, 4, 3, 2, 1};
    private static final String[] COLUMNS = {"a", "b", "c", "d", "e", "f", "g", "h"};
    
    private final Map<String, Piece> pieces;

    public Board() {
        pieces = new HashMap<>();
    }

    public void initialize() {
        initializePieces(BLACK);
        initializePieces(Color.WHITE);
    }

    private void initializePieces(Color color) {
        initializeBottomPieces(color);
        initializeTopPieces(color);
    }

    private void initializeBottomPieces(Color color) {
        if (color.equals(BLACK)) {
            pieces.put("a8", Piece.createBlackRook());
            pieces.put("b8", Piece.createBlackKnight());
            pieces.put("c8", Piece.createBlackBishop());
            pieces.put("d8", Piece.createBlackQueen());
            pieces.put("e8", Piece.createBlackKing());
            pieces.put("f8", Piece.createBlackBishop());
            pieces.put("g8", Piece.createBlackKnight());
            pieces.put("h8", Piece.createBlackRook());
        } else if (color.equals(WHITE)) {
            pieces.put("a1", Piece.createWhiteRook());
            pieces.put("b1", Piece.createWhiteKnight());
            pieces.put("c1", Piece.createWhiteBishop());
            pieces.put("d1", Piece.createWhiteQueen());
            pieces.put("e1", Piece.createWhiteKing());
            pieces.put("f1", Piece.createWhiteBishop());
            pieces.put("g1", Piece.createWhiteKnight());
            pieces.put("h1", Piece.createWhiteRook());
        }
    }

    private void initializeTopPieces(Color color) {
        if (color.equals(BLACK)) {
            for (int i = 0; i < ROW_SIZE; i++) {
                pieces.put(COLUMNS[i] + 7, Piece.createBlackPawn());
            }
        } else if (color.equals(WHITE)) {
            for (int i = 0; i < ROW_SIZE; i++) {
                pieces.put(COLUMNS[i] + 2, Piece.createWhitePawn());
            }
        }
    }

    public int pieceCount() {
        return pieces.size();
    }

    public String showBoard() {
        String index;
        StringBuilder sb = new StringBuilder();

        for (int row : ROWS) {
            for (String column : COLUMNS) {
                index = column + row;

                if (pieces.containsKey(index)) {
                    Piece piece = pieces.get(index);
                    if (piece.getColor().equals(WHITE)) {
                        sb.append(piece.getType().getWhiteRepresentation());
                    } else {
                        sb.append(piece.getType().getBlackRepresentation());
                    }
                } else {
                    sb.append(BLANK_REPRESENTATION);
                }
            }
            appendNewLine(sb);
        }

        return sb.toString();
    }

}
