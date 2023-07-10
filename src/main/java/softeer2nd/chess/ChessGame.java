package softeer2nd.chess;

import softeer2nd.chess.exceptions.IllegalMoveException;
import softeer2nd.chess.exceptions.IllegalTurnException;
import softeer2nd.chess.pieces.Piece;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import static softeer2nd.chess.pieces.Piece.Color;
import static softeer2nd.chess.pieces.Piece.Color.BLACK;
import static softeer2nd.chess.pieces.Piece.Color.WHITE;
import static softeer2nd.chess.pieces.Piece.Type.KING;
import static softeer2nd.chess.pieces.Piece.Type.PAWN;

/**
 * 체스 게임의 규칙과 관련된 로직을 담당
 */
public class ChessGame {
    private final Board board;

    /**
     * 기물을 움직일 차례의 색상
     */
    private Color turn;

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
        this.turn = WHITE;
    }

    /**
     * 특정 위치에 있는 기물을 목적 위치로 옮긴다.
     * @param source 옮기고자하는 기물의 특정 위치
     * @param destination 기물이 이동할 목적 위치
     */
    public void move(Position source, Position destination) {
        Piece piece = board.findPiece(source);
        if (piece.isBlank()) {
            throw new IllegalMoveException("빈칸은 옮길 수 없습니다.");
        }
        if (!isTurn(piece)) {
            throw new IllegalTurnException(piece.getColor() + "의 차례가 아닙니다.");
        }
        if (piece.verifyMovePosition(source, destination)) {
            board.putPiece(destination, piece);
            board.putPiece(source, Piece.createBlank());
            flipTurn();
            return;
        }
        throw new IllegalMoveException("해당 기물을 옮길 수 없습니다.");
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

    /**
     * 턴이 종료되면 차례가 바뀐다.
     */
    private void flipTurn() {
        this.turn = this.turn.equals(WHITE) ? BLACK : WHITE;
    }

    /**
     * 해당 기물이 현재 움직일 수 있는 차례인지 확인
     * @param piece 움직일 기물
     * @return true if 해당 색깔의 차례 else false
     */
    private boolean isTurn(Piece piece) {
        return piece.getColor().equals(this.turn);
    }
}