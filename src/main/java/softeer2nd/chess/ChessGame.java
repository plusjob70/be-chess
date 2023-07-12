package softeer2nd.chess;

import softeer2nd.chess.exceptions.IllegalMoveException;
import softeer2nd.chess.exceptions.IllegalTurnException;
import softeer2nd.chess.pieces.Piece;

import static softeer2nd.chess.pieces.Piece.Type.KING;
import static softeer2nd.chess.pieces.Piece.Type.PAWN;
import static softeer2nd.chess.pieces.PieceDirection.*;

/**
 * 체스 게임의 규칙과 관련된 로직을 담당
 */
public class ChessGame {
    /**
     * 8 * 8 체스 보드
     */
    private final Board board;

    /**
     * 게임의 진행 상태를 기록
     */
    private GameStatus gameStatus;

    public ChessGame(Board board) {
        this.board = board;
        this.gameStatus = new GameStatus();
    }

    public GameStatus getStatus() {
        return gameStatus;
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
        this.gameStatus = new GameStatus();
    }

    /**
     * 특정 위치에 있는 기물을 목적 위치로 옮긴다.
     * @param source 옮기고자하는 기물의 특정 위치
     * @param destination 기물이 이동할 목적 위치
     */
    public void move(Position source, Position destination) {
        Piece srcPiece = board.findPiece(source);
        Piece destPiece = board.findPiece(destination);

        // 기물의 이동이 기본 행마법을 따르는지 검증한다.
        srcPiece.verifyPieceMovementRule(source, destination);

        // 해당 기물의 차례인지 검증한다.
        verifyTurn(srcPiece);

        // 기물이 자신의 팀을 공격하는 것은 아닌지 검증한다.
        verifyTeamKill(source, destination);

        // 기물이 같은 위치로 이동하는지 검증한다.
        verifyMoveSamePosition(source, destination);

        // 기물의 이동경로 상에 다른 기물이 있는지 검증한다.
        verifyAnyPieceOnLinearPath(source, destination);
        verifyAnyPieceOnDiagonalPath(source, destination);
        verifyPawnPath(srcPiece, source, destination);

        // 기물이 상대방 킹을 공격하는지 검증한다.
        verifyAttackKing(destPiece);

        // 기물을 이동시키고 공격권을 넘긴다.
        board.putPiece(destination, srcPiece);
        board.putBlank(source);
        gameStatus.turnOver();
    }

    /**
     * 게임이 종료되었는지 검사한다.
     * @return true if 게임 종료 else false
     */
    public boolean isOver() {
        return gameStatus.isWhiteKingDead() || gameStatus.isBlackKingDead();
    }

    /**
     * 같은 위치로 이동하는지 검사
     * @param source 출발 위치
     * @param destination 도착 위치
     */
    private void verifyMoveSamePosition(Position source, Position destination) {
        if (hasDirection(source, destination)) {
            return;
        }
        throw new IllegalMoveException("같은 위치로 이동할 수 없습니다.");
    }

    /**
     * 직선 이동 시 경로 상에 기물이 가로막고 있는지 검사
     * @param source 출발 위치
     * @param destination 도착 위치
     */
    private void verifyAnyPieceOnLinearPath(Position source, Position destination) {
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
                if (board.findPiece(srcX, y).isBlank()) {
                    continue;
                }
                throw new IllegalMoveException("이동경로 상에 다른 기물이 존재하여 해당 위치로 이동할 수 없습니다.");
            }
        }
        if (srcY == destY) {
            int minX = Math.min(srcX, destX);
            int maxX = Math.max(srcX, destX);
            for (int x = minX + 1; x < maxX; x++) {
                if (board.findPiece(x, srcY).isBlank()) {
                    continue;
                }
                throw new IllegalMoveException("이동경로 상에 다른 기물이 존재하여 해당 위치로 이동할 수 없습니다.");
            }
        }
    }

    /**
     * 대각선 이동 시 경로 상에 기물이 가로막고 있는지 검사
     * @param source 출발 위치
     * @param destination 도착 위치
     */
    private void verifyAnyPieceOnDiagonalPath(Position source, Position destination) {
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
            if (board.findPiece(currentX, currentY).isBlank()) {
                currentX += directionX;
                currentY += directionY;
                continue;
            }
            throw new IllegalMoveException("이동경로 상에 다른 기물이 존재하여 해당 위치로 이동할 수 없습니다.");
        }
    }

    /**
     * Pawn 이동 시 움직일 수 있는지 검사
     * @param sourcePiece 움직일 기물
     * @param source 출발 위치
     * @param destination 도착 위치
     */
    private void verifyPawnPath(Piece sourcePiece, Position source, Position destination) {
        if (!sourcePiece.isTypeOf(PAWN)) {
            return;
        }
        int deltaX = Math.abs(source.getX() - destination.getX());
        int deltaY = Math.abs(source.getY() - destination.getY());

        boolean straight = deltaX > 0 && deltaY == 0;
        boolean diagonal = deltaX == 1 && deltaY == 1;

        Piece destPiece = board.findPiece(destination);
        if (diagonal && destPiece.isBlank()) {
            throw new IllegalMoveException("폰을 해당 위치로 이동할 수 없습니다.");
        }
        if (straight && !destPiece.isBlank()) {
            throw new IllegalMoveException("폰을 해당 위치로 이동할 수 없습니다.");
        }
    }

    /**
     * 현재 기물의 차례가 맞는지 검사한다.
     * @param piece 기물
     */
    private void verifyTurn(Piece piece) {
        if (gameStatus.isTurnOf(piece.getColor())) {
            return;
        }
        throw new IllegalTurnException(piece.getColor());
    }

    /**
     * 기물 이동 시 서로 같은 색상의 기물로 이동하는지 검사한다.
     * @param source 출발 위치
     * @param destination 도착 위치
     */
    private void verifyTeamKill(Position source, Position destination) {
        Piece srcPiece = board.findPiece(source);
        Piece destPiece = board.findPiece(destination);
        if (srcPiece.isColorOf(destPiece)) {
            throw new IllegalMoveException("같은 팀을 공격할 수 없습니다.");
        }
    }

    /**
     * 공격을 당하는 기물이 킹이면 승자가 결정된다.
     * @param destPiece 공격을 당하는 기물
     */
    private void verifyAttackKing(Piece destPiece) {
        if (destPiece.isTypeOf(KING)) {
            if (destPiece.isWhite()) {
                gameStatus.killWhiteKing();
            }
            if (destPiece.isBlack()) {
                gameStatus.killBlackKing();
            }
        }
    }
}