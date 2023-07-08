package softeer2nd.chess;

import softeer2nd.chess.pieces.Piece;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import static softeer2nd.chess.pieces.Piece.*;
import static softeer2nd.chess.pieces.Piece.Type.*;
import static softeer2nd.chess.utils.StringUtils.appendNewLine;

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
     * 보드를 게임하기 전 상태로 초기화
     * 먼저 전체를 빈칸으로 초기화한 후에 각 위치에 알맞은 기물을 배치한다.
     */
    public void initialize() {
        initializeRank8();
        initializePawnRank(7);
        initializePawnRank(2);
        initializeRank1();
    }

    /**
     * 보드를 빈칸으로 초기화한다.
     * ranks에 저장되는 Rank의 rankNumber는 순서대로 8, 7, ..., 2, 1이다.
     */
    public void initializeEmpty() {
        ranks.clear();
        for (int rankNumber = Board.SIZE; rankNumber > 0; rankNumber--) {
            ranks.add(new Rank(rankNumber));
        }
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
     * 특정 위치에 기물을 둔다.
     * @param position 보드의 위치좌표
     * @param piece 해당 위치에 두고자하는 기물
     */
    public void putPiece(Position position, Piece piece) {
        ranks.get(position.getX()).setPiece(position.getY(), piece);
    }

    /**
     * 특정 위치에 있는 기물을 목적 위치로 옮긴다.
     * @param source 옮기고자하는 기물의 특정 위치
     * @param destination 기물이 이동할 목적 위치
     */
    public void move(Position source, Position destination) {
        Piece sourcePiece = findPiece(source);
        putPiece(destination, sourcePiece);
        putPiece(source, Piece.createBlank());
    }

    /**
     * 특정 색상의 기물의 점수를 계산한다.
     * @param color 계산하고자하는 기물의 색상
     * @return 점수
     */
    public double calculateScores(Color color) {
        double scores = 0.0;
        boolean existKing = false;
        Piece piece;

        for (int y = 0; y < Board.SIZE; y++) {
            int pawnCount = 0;
            for (int x = 0; x < Board.SIZE; x++) {
                piece = ranks.get(x).getPiece(y);

                if (piece.getType().equals(PAWN) && piece.getColor().equals(color)) {
                    pawnCount++;
                } else if (piece.getColor().equals(color)) {
                    scores += piece.getType().getPoint();
                }

                if (piece.getType().equals(KING) && piece.getColor().equals(color)) {
                    existKing = true;
                }
            }
            scores += pawnCount > 1 ? PAWN.getPoint() * pawnCount : pawnCount;
        }

        if (existKing) {
            return scores;
        }
        return 0.0;
    }

    /**
     * 특정 색상의 기물을 점수 순으로 정렬하여 반환
     * @param color 정렬하고자하는 기물의 색상
     * @param reversed false이면 내림차순 정렬 true이면 오름차순 정렬
     * @return 점수순으로 정렬된 기물
     */
    public List<Piece> getOrderedPieces(Color color, boolean reversed) {
        PriorityQueue<Piece> heap;
        if (reversed) {
            heap = new PriorityQueue<>();
        } else {
            heap = new PriorityQueue<>(Comparator.reverseOrder());
        }

        for (int x = 0; x < Board.SIZE; x++) {
            for (int y = 0; y < Board.SIZE; y++) {
                Piece piece = ranks.get(x).getPiece(y);
                if (!piece.isBlank() && piece.getColor().equals(color)) {
                    heap.add(piece);
                }
            }
        }
        return new ArrayList<>(heap);
    }

    /**
     * 보드를 출력하기 위한 문자열을 반환
     * @return 보드를 출력하기 위한 문자열
     */
    public String showBoard() {
        StringBuilder sb = new StringBuilder();

        Rank rank;
        for (int rankNumber = Board.SIZE; rankNumber > 0; rankNumber--) {
            rank = getRankByRankNumber(rankNumber);
            sb.append(rank.getRepresentationsWithRankNumber());
            appendNewLine(sb);
        }
        appendNewLine(sb);
        sb.append("abcdefgh");
        return sb.toString();
    }

    /**
     * 게임을 시작하기 위해 Rank 8에 해당하는 기물 배치
     */
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

    /**
     * 게임을 시작하기 위해 Pawn이 있는 Rank에 Pawn을 배치
     * @param rankNumber Pawn이 놓일 Rank의 번호
     */
    private void initializePawnRank(int rankNumber) {
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

    /**
     * Rank 번호로 Rank에 접근
     * @param rankNumber Rank 번호
     * @return 해당 Rank 번호에 위치하는 Rank
     */
    private Rank getRankByRankNumber(int rankNumber) {
        return ranks.get(Board.SIZE - rankNumber);
    }
}