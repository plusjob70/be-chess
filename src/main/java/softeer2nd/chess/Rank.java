package softeer2nd.chess;

import softeer2nd.chess.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

import static softeer2nd.chess.pieces.Piece.Color.BLACK;
import static softeer2nd.chess.pieces.Piece.Color.WHITE;

public class Rank {

    private final int rankNumber;
    private final List<Piece> pieces;

    public Rank(int rankNumber) {
        this.rankNumber = rankNumber;
        this.pieces = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            this.pieces.add(Piece.createBlank());
        }
    }

    public Piece getPiece(char column) {
        return pieces.get(getPosition(column));
    }

    public void setPiece(char column, Piece piece) {
        pieces.set(getPosition(column), piece);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < 8; i++) {
            Piece piece = pieces.get(i);
            if (piece.getColor().equals(WHITE)) {
                sb.append(piece.getType().getWhiteRepresentation());
            } else if (piece.getColor().equals(BLACK)) {
                sb.append(piece.getType().getBlackRepresentation());
            } else {
                sb.append(piece.getType().getDefaultRepresentation());
            }
        }
        sb.append("  ").append(rankNumber);

        return sb.toString();
    }

    public int getCountPieces(Piece.Color color, Piece.Type type) {
        int count = 0;
        for (Piece piece : pieces) {
            if (piece.getColor().equals(color) && piece.getType().equals(type)) {
                count++;
            }
        }
        return count;
    }

    private int getPosition(char column) {
        int position = column - 'a';
        if (position < 0 || position >= 8) {
            throw new IllegalArgumentException("Column 범위가 아닙니다.");
        }
        return position;
    }

}
