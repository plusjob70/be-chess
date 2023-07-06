package softeer2nd.chess;

import softeer2nd.chess.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

import static softeer2nd.chess.pieces.Piece.*;
import static softeer2nd.chess.utils.StringUtils.appendNewLine;

public class Board {

    private static final int RANK_SIZE = 8;
    private static final int COLUMN_SIZE = 8;
    private static final String COLUMN_REPRESENTATION = "abcdefgh";

    private final List<Rank> ranks;

    public Board() {
        ranks = new ArrayList<>();
        initializeEmpty();
    }

    public void initialize() {
        initializeEmpty();

        initializeRank8();
        initializePawnRank(7);
        initializePawnRank(2);
        initializeRank1();
    }

    public void initializeEmpty() {
        ranks.clear();

        for (int rankNumber = RANK_SIZE; rankNumber > 0; rankNumber--) {
            ranks.add(new Rank(rankNumber));
        }
    }

    private void initializeRank8() {
        Rank rank8 = getRankByRankNumber(8);
        rank8.setPiece(0, createBlackRook());
        rank8.setPiece(1, createBlackKnight());
        rank8.setPiece(2, createBlackBishop());
        rank8.setPiece(3, createBlackQueen());
        rank8.setPiece(4, createBlackKing());
        rank8.setPiece(5, createBlackBishop());
        rank8.setPiece(6, createBlackKnight());
        rank8.setPiece(7, createBlackRook());
    }

    private void initializePawnRank(int rankNumber) {
        Rank pawnRank = getRankByRankNumber(rankNumber);

        if (rankNumber == 7) {
            for (int y = 0; y < COLUMN_SIZE; y++) {
                pawnRank.setPiece(y, createBlackPawn());
            }
        } else if (rankNumber == 2) {
            for (int y = 0; y < COLUMN_SIZE; y++) {
                pawnRank.setPiece(y, createWhitePawn());
            }
        }
    }

    private void initializeRank1() {
        Rank rank1 = getRankByRankNumber(1);
        rank1.setPiece(0, createWhiteRook());
        rank1.setPiece(1, createWhiteKnight());
        rank1.setPiece(2, createWhiteBishop());
        rank1.setPiece(3, createWhiteQueen());
        rank1.setPiece(4, createWhiteKing());
        rank1.setPiece(5, createWhiteBishop());
        rank1.setPiece(6, createWhiteKnight());
        rank1.setPiece(7, createWhiteRook());
    }

    public int getCountPieces(Color color, Type type) {
        Rank rank;
        int count = 0;

        for (int index = 0; index < RANK_SIZE; index++) {
            rank = ranks.get(index);
            count += rank.getCountPieces(color, type);
        }
        return count;
    }

    public Piece findPiece(Position position) {
        return ranks.get(position.getX()).getPiece(position.getY());
    }

    public void move(Position position, Piece piece) {
        ranks.get(position.getX()).setPiece(position.getY(), piece);
    }

    public String showBoard() {
        StringBuilder sb = new StringBuilder();



        Rank rank;
        for (int rankNumber = RANK_SIZE; rankNumber > 0; rankNumber--) {
            rank = getRankByRankNumber(rankNumber);
            sb.append(rank.toString());
            appendNewLine(sb);
        }
        appendNewLine(sb);
        sb.append(COLUMN_REPRESENTATION);
        return sb.toString();
    }

    private Rank getRankByRankNumber(int rankNumber) {
        return ranks.get(RANK_SIZE - rankNumber);
    }
}
