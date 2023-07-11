package softeer2nd.chess;

import softeer2nd.chess.exceptions.IllegalMoveException;
import softeer2nd.chess.exceptions.IllegalTurnException;
import softeer2nd.chess.pieces.Piece;
import softeer2nd.chess.pieces.Piece.Type;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import static softeer2nd.chess.pieces.PieceDirection.*;
import static softeer2nd.chess.pieces.Piece.Color;
import static softeer2nd.chess.pieces.Piece.Color.*;
import static softeer2nd.chess.pieces.Piece.Type.*;

/**
 * 체스 게임의 규칙과 관련된 로직을 담당
 */
public class ChessGame {
    /**
     * 8 * 8 체스 보드
     */
    private final Board board;

    /**
     * 공격할 기물의 색상
     */
    private Color attackTurnColor;

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
        this.attackTurnColor = WHITE;
    }

    /**
     * 특정 위치에 있는 기물을 목적 위치로 옮긴다.
     * @param source 옮기고자하는 기물의 특정 위치
     * @param destination 기물이 이동할 목적 위치
     */
    public void move(Position source, Position destination) {
        Piece piece = board.findPiece(source);

        // 해당 기물이 이동할 수 있는 경로인지 검증한다.
        piece.verifyMovePosition(source, destination);

        // 이동경로 상에 기물이 있는지 검증한다.
        verifyMovePath(piece, source, destination);

        // 이동하고자 하는 기물의 차례가 아니면 이동시킬 수 없다.
        verifyTurn(piece);

        // source 기물과 destination 기물의 색상은 달라야한다.
        verifyColor(source, destination);

        // 기물을 이동시키고 차례를 넘긴다.
        board.putPiece(destination, piece);
        board.putBlank(source);
        flipAttackTurn();
    }

    private void verifyMovePath(Piece targetPiece, Position source, Position destination) {
        Type type = targetPiece.getType();
        switch (targetPiece.getType()) {
            case QUEEN:
            case KING:
                verifyLinearPath(source, destination);
                verifyDiagonalPath(source, destination);
                break;
            case ROOK:
                verifyLinearPath(source, destination);
                break;
            case BISHOP:
                verifyDiagonalPath(source, destination);
                break;
            case PAWN:
                verifyPawnPath(targetPiece, source, destination);
                break;
        }
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

                if (piece.isType(PAWN) && piece.isColor(color)) {
                    pawnCount++;
                } else if (piece.isColor(color)) {
                    scores += piece.getType().getPoint();
                }

                if (piece.isType(KING) && piece.isColor(color)) {
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
                if (!piece.isBlank() && piece.isColor(color)) {
                    heap.add(piece);
                }
            }
        }
        return new ArrayList<>(heap);
    }

    /**
     * 공격 차례를 바뀐다.
     */
    private void flipAttackTurn() {
        this.attackTurnColor = this.attackTurnColor.isWhite() ? BLACK : WHITE;
    }

    /**
     * 해당 기물이 현재 움직일 수 있는 차례인지 확인
     * @param piece 움직일 기물
     * @return true if 해당 색깔의 차례 else false
     */
    private boolean isTurn(Piece piece) {
        return piece.isColor(this.attackTurnColor);
    }

    private void verifyDiagonalPath(Position source, Position destination) {
        if (!isDiagonalDirection(source, destination)) {
            return;
        }
        int srcX = source.getX();
        int srcY = source.getY();
        int destX = destination.getX();
        int destY = destination.getY();

        int directionX = (destX > srcX) ? 1 : -1;
        int directionY = (destY > srcY) ? 1 : -1;
        int currentX = srcX + directionX;
        int currentY = srcY + directionY;

        while (currentX != destX && currentY != destY) {
            if (!board.findPiece(currentX, currentY).isBlank()) {
                throw new IllegalMoveException();
            }
            currentX += directionX;
            currentY += directionY;
        }
    }

    private void verifyLinearPath(Position source, Position destination) {
        if (!isLinearDirection(source, destination)) {
            return;
        }
        int srcX = source.getX();
        int srcY = source.getY();
        int destX = destination.getX();
        int destY = destination.getY();

        if (srcX == destX) {
            int minY = Math.min(srcY, destY);
            int maxY = Math.max(srcY, destY);
            for (int y = minY + 1; y < maxY; y++) {
                if (!board.findPiece(srcX, y).isBlank()) {
                    throw new IllegalMoveException();
                }
            }
        } else if (srcY == destY) {
            int minX = Math.min(srcX, destX);
            int maxX = Math.max(srcX, destX);
            for (int x = minX + 1; x < maxX; x++) {
                if (!board.findPiece(x, srcY).isBlank()) {
                    throw new IllegalMoveException();
                }
            }
        }
    }

    private void verifyPawnPath(Piece pawn, Position source, Position destination) {
        if (pawn.isWhite()) {

        } else if (pawn.isBlack()) {

        }
    }

    private void verifyTurn(Piece piece) {
        if (!isTurn(piece)) {
            throw new IllegalTurnException(piece.getColor() + "의 차례가 아닙니다.");
        }
    }

    private void verifyColor(Position source, Position destination) {
        Piece srcPiece = board.findPiece(source);
        Piece destPiece = board.findPiece(destination);
        if (srcPiece.hasSameColor(destPiece)) {
            throw new IllegalMoveException();
        }
    }
}