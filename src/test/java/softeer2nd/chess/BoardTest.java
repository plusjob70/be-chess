package softeer2nd.chess;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import softeer2nd.chess.pieces.Piece;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static softeer2nd.chess.pieces.Piece.Color.*;
import static softeer2nd.chess.pieces.Piece.Type.*;
import static softeer2nd.chess.utils.StringUtils.appendNewLine;

class BoardTest {

    private Board board;

    @BeforeEach
    public void createBoard() {
        board = new Board();
    }

    @Test
    @DisplayName("보드에는 Piece 객체만 추가될 수 있다.")
    public void addOtherObject() {
        // 컴파일 에러 발생
        // board.add(new Integer("7"));
    }

    @Test
    @DisplayName("체스판이 초기화 된다.")
    public void initialize() throws Exception {
        board.initialize();
        String blankRank = "........";
        assertEquals(
                appendNewLine("RNBQKBNR  8") +
                        appendNewLine("PPPPPPPP  7") +
                        appendNewLine(blankRank + "  6") +
                        appendNewLine(blankRank + "  5") +
                        appendNewLine(blankRank + "  4") +
                        appendNewLine(blankRank + "  3") +
                        appendNewLine("pppppppp  2") +
                        appendNewLine("rnbqkbnr  1") +
                        appendNewLine("") +
                        "abcdefgh",
                board.showBoard());
    }

    @Test
    @DisplayName("보드 초기화 후에 보드를 출력한다.")
    public void printBoardAfterInitialize() {
        board.initialize();
        System.out.println(board.showBoard());
    }

    @Test
    @DisplayName("기물과 색에 해당하는 기물의 개수를 반환한다.")
    public void countOfPiece() {
        board.initialize();
        assertEquals(8, board.getCountPieces(WHITE, PAWN));
        assertEquals(8, board.getCountPieces(BLACK, PAWN));
        assertEquals(2, board.getCountPieces(WHITE, ROOK));
        assertEquals(2, board.getCountPieces(BLACK, ROOK));
        assertEquals(2, board.getCountPieces(WHITE, KNIGHT));
        assertEquals(2, board.getCountPieces(BLACK, KNIGHT));
        assertEquals(2, board.getCountPieces(WHITE, BISHOP));
        assertEquals(2, board.getCountPieces(BLACK, BISHOP));
        assertEquals(1, board.getCountPieces(WHITE, QUEEN));
        assertEquals(1, board.getCountPieces(BLACK, QUEEN));
        assertEquals(1, board.getCountPieces(WHITE, KING));
        assertEquals(1, board.getCountPieces(BLACK, KING));
        assertEquals(32, board.getCountPieces(NO_COLOR, NO_PIECE));
    }

    @Test
    @DisplayName("주어진 위치의 기물을 조회한다.")
    public void findPiece() {
        board.initialize();

        assertEquals(Piece.createBlackRook(), board.findPiece(Position.create("a8")));
        assertEquals(Piece.createBlackRook(), board.findPiece(Position.create("h8")));
        assertEquals(Piece.createWhiteRook(), board.findPiece(Position.create("a1")));
        assertEquals(Piece.createWhiteRook(), board.findPiece(Position.create("h1")));
    }

    @Test
    @DisplayName("잘못된 인덱스로 접근시 오류가 발생한다.")
    public void IllegalIndex() {
        assertThatThrownBy(() -> board.findPiece(Position.create("a9")))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> board.findPiece(Position.create("i1")))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> board.findPiece(Position.create("1a")))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("임의의 기물을 체스판 위에 추가한다.")
    public void move() {
        board.initializeEmpty();

        String input = "b5";
        Position position = Position.create(input);
        Piece piece = Piece.createBlackKing();
        board.move(position, piece);

        assertEquals(piece, board.findPiece(position));
        System.out.println(board.showBoard());
    }

    @Test
    @DisplayName("현재 놓인 기물로 점수를 계산한다.")
    public void calculateScores() {
        board.initializeEmpty();

        addPiece("b6", Piece.createBlackPawn());
        addPiece("e6", Piece.createBlackQueen());
        addPiece("b8", Piece.createBlackKing());
        addPiece("c8", Piece.createBlackRook());

        addPiece("f2", Piece.createWhitePawn());
        addPiece("g2", Piece.createWhitePawn());
        addPiece("e1", Piece.createWhiteRook());
        addPiece("f1", Piece.createWhiteKing());

        assertEquals(15.0, board.calculateScores(BLACK), 0.01);
        assertEquals(7.0, board.calculateScores(WHITE), 0.01);

        System.out.println(board.showBoard());
    }

    @Test
    @DisplayName("Pawn은 같은 세로줄에 존재하는 개수에 따라 다른 점수가 매겨진다.")
    public void calculatePawnScores() {
        board.initializeEmpty();

        addPiece("a8", Piece.createWhitePawn());
        addPiece("a7", Piece.createWhitePawn());
        addPiece("a6", Piece.createWhitePawn());

        addPiece("a5", Piece.createWhiteKing());

        addPiece("b5", Piece.createWhitePawn());
        addPiece("b7", Piece.createBlackKing());

        addPiece("c8", Piece.createBlackPawn());
        addPiece("c7", Piece.createBlackPawn());
        addPiece("c6", Piece.createBlackPawn());
        addPiece("c5", Piece.createBlackPawn());
        addPiece("c4", Piece.createBlackPawn());
        addPiece("c3", Piece.createBlackPawn());
        addPiece("c2", Piece.createBlackPawn());
        addPiece("c1", Piece.createBlackPawn());

        assertEquals(2.5, board.calculateScores(WHITE), 0.01);
        assertEquals(4.0, board.calculateScores(BLACK), 0.01);
    }

    @Test
    @DisplayName("KING이 없거나 KING만 있다면 점수는 없다.")
    public void calculateNoKing() {
        board.initializeEmpty();

        addPiece("a1", Piece.createBlackQueen());
        addPiece("a2", Piece.createWhiteKing());

        assertEquals(0.0, board.calculateScores(BLACK), 0.01);
        assertEquals(0.0, board.calculateScores(WHITE), 0.01);
    }

    private void addPiece(String expression, Piece piece) {
        board.move(Position.create(expression), piece);
    }
}