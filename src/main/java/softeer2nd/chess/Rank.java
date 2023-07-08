package softeer2nd.chess;

import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.Piece.Color;
import softeer2nd.chess.pieces.Piece.Type;

import java.util.ArrayList;
import java.util.List;

/**
 * 체스판의 가로줄에 해당
 */
public class Rank {
    /**
     * 하나의 Rank에 들어가는 기물의 수
     */
    public static final int SIZE_PIECES = 8;

    /**
     * 기물을 저장하는 자료구조
     */
    private final List<Piece> pieces;

    /**
     * Rank 번호
     */
    private final int rankNumber;

    /**
     * Rank 생성자는 Rank 번호를 인자로 받는다.
     *
     * @param rankNumber Rank 번호
     */
    public Rank(int rankNumber) {
        this.pieces = new ArrayList<>();
        this.rankNumber = rankNumber;
        for (int i = 0; i < SIZE_PIECES; i++) {
            this.pieces.add(Piece.createBlank());
        }
    }

    /**
     * 특정 위치의 기물을 반환
     * @param index  해당 Rank의 특정 위치
     * @return 해당 위치에 있는 기물(Piece)
     * @throws IndexOutOfBoundsException 범위를 넘어섰을 때
     */
    public Piece getPiece(int index) {
        validateOutOfIndex(index);
        return pieces.get(index);
    }

    /**
     * 특정 위치에 기물을 둔다.
     * @param index  해당 Rank의 특정 위치
     * @param piece  index에 놓을 기물
     * @throws IndexOutOfBoundsException 범위를 넘어섰을 때
     */
    public void setPiece(int index, Piece piece) {
        validateOutOfIndex(index);
        pieces.set(index, piece);
    }

    /**
     * 특정 색상과 기물의 종류의 개수를 반환
     * @param color  색상
     * @param type  기물의 종류
     * @return  특정 색상과 기물의 종류
     */
    public int getCountPieces(Color color, Type type) {
        int count = 0;
        for (Piece piece : pieces) {
            if (piece.getColor().equals(color) && piece.getType().equals(type)) {
                count++;
            }
        }
        return count;
    }

    /**
     * pieces에 있는 기물들의 representation을 String으로 반환
     * @return pieces에 있는 기물들의 representation
     */
    public String getRepresentations() {
        StringBuilder sb = new StringBuilder();
        for (Piece piece : pieces) {
            if (piece.isWhite()) {
                sb.append(piece.getType().getWhiteRepresentation());
            } else if (piece.isBlack()) {
                sb.append(piece.getType().getBlackRepresentation());
            } else if (piece.isBlank()) {
                sb.append(piece.getType().getDefaultRepresentation());
            }
        }
        return sb.toString();
    }

    /**
     * pieces에 있는 기물들의 representation을 Rank 번호와 함께 String으로 반환
     * @return pieces에 있는 기물들의 representation + Rank 번호
     */
    public String getRepresentationsWithRankNumber() {
        return getRepresentations() + "  " + rankNumber;
    }

    /**
     * 인덱스 범위 초과시 IndexOutOfBoundsException 발생
     * @param index pieces의 위치
     */
    private void validateOutOfIndex(int index) {
        if (index < 0 || index >= SIZE_PIECES) {
            throw new IndexOutOfBoundsException("인덱스 범위 초과");
        }
    }
}