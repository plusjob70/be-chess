package softeer2nd.chess;

import softeer2nd.chess.pieces.Piece;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import static softeer2nd.chess.pieces.Piece.*;
import static softeer2nd.chess.pieces.Piece.Type.KING;
import static softeer2nd.chess.pieces.Piece.Type.PAWN;

/**
 * 체스 게임의 규칙과 관련된 로직을 담당
 */
public class ChessGame {
    private final Board board;

    public ChessGame(Board board) {
        this.board = board;
    }

    /**
     * 보드를 게임하기 전 상태로 초기화
     * 먼저 전체를 빈칸으로 초기화한 후에 각 위치에 알맞은 기물을 배치한다.
     */
    public void initialize() {
        board.initializeEmpty();
        board.initializeRank8();
        board.initializePawnRank(7);
        board.initializePawnRank(2);
        board.initializeRank1();
    }

    /**
     * 특정 위치에 있는 기물을 목적 위치로 옮긴다.
     * @param source 옮기고자하는 기물의 특정 위치
     * @param destination 기물이 이동할 목적 위치
     */
    public void move(Position source, Position destination) {
        Piece sourcePiece = board.findPiece(source);
        board.putPiece(destination, sourcePiece);
        board.putPiece(source, Piece.createBlank());
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
                piece = board.findPiece(x, y);

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
                Piece piece = board.findPiece(x, y);
                if (!piece.isBlank() && piece.getColor().equals(color)) {
                    heap.add(piece);
                }
            }
        }
        return new ArrayList<>(heap);
    }
}