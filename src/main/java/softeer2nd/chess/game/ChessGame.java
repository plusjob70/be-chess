package softeer2nd.chess.game;

import softeer2nd.chess.exceptions.IllegalTurnException;
import softeer2nd.chess.pieces.Piece;

import static softeer2nd.chess.pieces.Piece.Type.KING;

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
    /**
     * 기물 이동 검증기
     */
    private final PieceMoveValidator pieceMoveValidator;

    public ChessGame(Board board) {
        this.board = board;
        this.gameStatus = new GameStatus();
        this.pieceMoveValidator = new PieceMoveValidator(board);
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

        // 해당 기물의 차례인지 검증한다.
        verifyTurn(srcPiece);

        // 기물이 이동할 수 있는지 검증한다.
        pieceMoveValidator.validate(source, destination, srcPiece, destPiece);

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