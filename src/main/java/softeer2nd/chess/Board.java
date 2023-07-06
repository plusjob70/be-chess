package softeer2nd.chess;

import softeer2nd.chess.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

import static softeer2nd.chess.pieces.Piece.*;
import static softeer2nd.chess.utils.StringUtils.appendNewLine;

public class Board {

    private static final int RANK_SIZE = 8;
    private static final int COLUMN_SIZE = 8;
    private static final String INDEX_EXPRESSION = "[a-h][1-8]";

    private final List<Rank> ranks;
    private final int[] rows = {8, 7, 6, 5, 4, 3, 2, 1};
    private final char[] columns = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};

    public Board() {
        ranks = new ArrayList<>();
        for (int rankNumber = RANK_SIZE; rankNumber > 0; rankNumber--) {
            ranks.add(new Rank(rankNumber));
        }
    }

    public void initialize() {
        initializeRank8();
        initializePawnRank(7);
        initializePawnRank(2);
        initializeRank1();
    }

    private void initializeRank8() {
        Rank rank8 = getRank(8);
        rank8.setPiece('a', createBlackRook());
        rank8.setPiece('b', createBlackKnight());
        rank8.setPiece('c', createBlackBishop());
        rank8.setPiece('d', createBlackQueen());
        rank8.setPiece('e', createBlackKing());
        rank8.setPiece('f', createBlackBishop());
        rank8.setPiece('g', createBlackKnight());
        rank8.setPiece('h', createBlackRook());
    }

    private void initializePawnRank(int rankNumber) {
        Rank pawnRank = getRank(rankNumber);

        if (rankNumber == 7) {
            for (int i = 0; i < COLUMN_SIZE; i++) {
                pawnRank.setPiece(columns[i], createBlackPawn());
            }
        } else if (rankNumber == 2) {
            for (int i = 0; i < COLUMN_SIZE; i++) {
                pawnRank.setPiece(columns[i], createWhitePawn());
            }
        }
    }

    private void initializeRank1() {
        Rank rank1 = getRank(1);
        rank1.setPiece('a', createWhiteRook());
        rank1.setPiece('b', createWhiteKnight());
        rank1.setPiece('c', createWhiteBishop());
        rank1.setPiece('d', createWhiteQueen());
        rank1.setPiece('e', createWhiteKing());
        rank1.setPiece('f', createWhiteBishop());
        rank1.setPiece('g', createWhiteKnight());
        rank1.setPiece('h', createWhiteRook());
    }

    public int getCountPieces(Color color, Type type) {
        Rank rank;
        int count = 0;

        for (int rankNumber = RANK_SIZE; rankNumber > 0; rankNumber--) {
            rank = getRank(rankNumber);
            count += rank.getCountPieces(color, type);
        }
        return count;
    }

    public Piece findPiece(String index) {
        if (!index.matches(INDEX_EXPRESSION)) {
            throw new IllegalArgumentException("잘못된 인덱스입니다.");
        }

        int row = Character.getNumericValue(index.charAt(1));
        char column = index.charAt(0);
        return getRank(row).getPiece(column);
    }

    public String showBoard() {
        StringBuilder sb = new StringBuilder();

        for (int rankNumber = RANK_SIZE; rankNumber > 0; rankNumber--) {
            sb.append(getRank(rankNumber).toString());
            appendNewLine(sb);
        }
        appendNewLine(sb);
        appendNewLine(sb);
        sb.append("abcdefgh");
        return sb.toString();
    }

    private Rank getRank(int rankNumber) {
        return ranks.get(RANK_SIZE - rankNumber);
    }
}
