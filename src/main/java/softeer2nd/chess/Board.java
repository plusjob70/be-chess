package softeer2nd.chess;

import softeer2nd.chess.pieces.Piece;

import java.util.ArrayList;
import java.util.List;

import static softeer2nd.chess.pieces.Piece.Color;
import static softeer2nd.chess.pieces.Piece.Type;
import static softeer2nd.chess.pieces.PieceFactory.*;

/**
 * 체스 보드
 */
public class Board {
    /**
     * 체스 보드의 크기(가로 크기 == 세로 크기)
     */
    public static final int SIZE = 8;

    /**
     * 보드의 가로줄인 Rank를 가지는 자료구조
     */
    private final List<Rank> ranks;

    /**
     * 보드의 생성자
     * 보드는 생성되자마자 빈칸으로 초기화된다.
     */
    public Board() {
        ranks = new ArrayList<>();
        initializeEmpty();
    }

    /**
     * 보드를 빈칸으로 초기화한다.
     */
    public void initializeEmpty() {
        ranks.clear();
        for (int i = 0; i < Board.SIZE; i++) {
            ranks.add(new Rank());
        }
    }

    /**
     * 게임을 시작하기 위해 Rank 8에 해당하는 기물 배치
     */
    public void initializeRank8() {
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

    /**
     * 게임을 시작하기 위해 Pawn이 있는 Rank에 Pawn을 배치
     * @param rankNumber Pawn이 놓일 Rank의 번호
     */
    public void initializePawnRank(int rankNumber) {
        Rank pawnRank = getRankByRankNumber(rankNumber);

        if (rankNumber == 7) {
            for (int y = 0; y < Board.SIZE; y++) {
                pawnRank.setPiece(y, createBlackPawn());
            }
        } else if (rankNumber == 2) {
            for (int y = 0; y < Board.SIZE; y++) {
                pawnRank.setPiece(y, createWhitePawn());
            }
        }
    }

    /**
     * 게임을 시작하기 위해 Rank 1에 해당하는 기물 배치
     */
    public void initializeRank1() {
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

    /**
     * 기물 색상과 종류와 일치하는 기물의 개수를 반환한다.
     * @param color 기물의 색상
     * @param type 기물의 종류
     * @return 기물의 개수
     */
    public int getCountPieces(Color color, Type type) {
        Rank rank;
        int count = 0;

        for (int index = 0; index < Board.SIZE; index++) {
            rank = ranks.get(index);
            count += rank.getCountPieces(color, type);
        }
        return count;
    }

    /**
     * 특정 위치에 있는 기물을 반환한다.
     * @param position 보드의 위치좌표
     * @return 해당 위치에 있는 기물
     */
    public Piece findPiece(Position position) {
        return ranks.get(position.getX()).getPiece(position.getY());
    }

    /**
     * 특정 인덱스에 있는 기물을 반환한다.
     * @param x 보드의 x 인덱스
     * @param y 보드의 y 인덱스
     * @return 해당 인덱스에 있는 기물
     */
    public Piece findPiece(int x, int y) {
        return ranks.get(x).getPiece(y);
    }

    /**
     * 특정 위치에 기물을 둔다.
     * @param position 보드의 위치좌표
     * @param piece 해당 위치에 두고자하는 기물
     */
    public void putPiece(Position position, Piece piece) {
        ranks.get(position.getX()).setPiece(position.getY(), piece);
    }

    /**
     * 특정 위치에 빈칸을 둔다.
     * @param position 보드의 위치좌표
     */
    public void putBlank(Position position) {
        ranks.get(position.getX()).setPiece(position.getY(), createBlank());
    }

    /**
     * 각 Rank의 representation을 반환
     * @return 각 Rank의 representation
     */
    public List<List<Character>> getRepresentations() {
        List<List<Character>> boardRepresentations = new ArrayList<>();
        for (Rank rank : ranks) {
            boardRepresentations.add(rank.getRepresentations());
        }
        return boardRepresentations;
    }

    /**
     * Rank 번호로 Rank에 접근
     * @param rankNumber Rank 번호
     * @return 해당 Rank 번호에 위치하는 Rank
     */
    private Rank getRankByRankNumber(int rankNumber) {
        return ranks.get(Board.SIZE - rankNumber);
    }
}