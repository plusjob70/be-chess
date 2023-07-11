package softeer2nd.chess;

import softeer2nd.chess.pieces.Piece.Color;

import static softeer2nd.chess.pieces.Piece.Color.*;
import static softeer2nd.chess.pieces.Piece.Color.WHITE;

/**
 * 게임 진행 상태를 기록하는 클래스
 */
public class GameStatus {
    private boolean whiteKingAlive;
    private boolean blackKingAlive;
    private Color winner;
    private Color attackTurn;
    private int round;

    public GameStatus() {
        this.whiteKingAlive = true;
        this.blackKingAlive = true;
        this.winner = NO_COLOR;
        this.attackTurn = WHITE;
        this.round = 1;
    }

    public Color getWinner() {
        return winner;
    }

    public int getRound() {
        return round;
    }

    /**
     * 흰색 킹이 살아있는지 검사한다.
     * @return true if alive else false
     */
    public boolean isWhiteKingAlive() {
        return whiteKingAlive;
    }

    /**
     * 검은색 킹이 살아있는지 검사한다.
     * @return true if alive else false
     */
    public boolean isBlackKingAlive() {
        return blackKingAlive;
    }

    /**
     * 흰색 킹이 죽는다.
     * 승자가 검은색으로 결정된다.
     */
    public void killWhiteKing() {
        whiteKingAlive = false;
        winner = BLACK;
    }

    /**
     * 검은색 킹이 죽는다.
     * 승자가 흰색으로 결정된다.
     */
    public void killBlackKing() {
        blackKingAlive = false;
        winner = WHITE;
    }

    /**
     * 차례를 종료하고 공수를 바꾼다.
     * 라운드의 수를 하나 올린다.
     */
    public void turnOver() {
        attackTurn = attackTurn.equals(WHITE) ? BLACK : WHITE;
        round++;
    }

    /**
     * 해당 기물이 현재 움직일 수 있는 차례인지 확인
     * @param color 움직일 기물의 색상
     * @return true if 해당 색깔의 차례 else false
     */
    public boolean isTurnOf(Color color) {
        return color.equals(attackTurn);
    }
}