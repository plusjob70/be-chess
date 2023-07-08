package softeer2nd.chess;

import java.util.List;
import java.util.stream.Collectors;

import static softeer2nd.chess.utils.StringUtils.appendNewLine;

/**
 * 체스판 View를 담당
 */
public class ChessView {
    /**
     * View를 구성할 체스 보드
     */
    private final Board board;

    public ChessView(Board board) {
        this.board = board;
    }

    /**
     * 보드를 출력하기 위한 문자열을 반환
     * @return 보드를 출력하기 위한 문자열
     */
    public String showBoard() {
        StringBuilder sb = new StringBuilder();
        List<List<Character>> boardRepresentations = board.getRepresentations();

        for (int i = 0; i < Board.SIZE; i++) {
            sb.append(characterListToString(boardRepresentations.get(i)))
                    .append("  ").append(Board.SIZE - i);
            appendNewLine(sb);
        }
        appendNewLine(sb);
        sb.append("abcdefgh");
        return sb.toString();
    }

    /**
     * List<Character>의 각각의 요소를 공백 없는 하나의 문자열로 반환한다.
     * @param characters 변환할 Character List
     * @return 변환된 문자열
     */
    private String characterListToString(List<Character> characters) {
        return characters.stream()
                .map(String::valueOf)
                .collect(Collectors.joining());
    }
}