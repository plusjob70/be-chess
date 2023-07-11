package softeer2nd.chess;

import softeer2nd.chess.exceptions.IllegalInputException;

import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import static softeer2nd.chess.Command.*;
import static softeer2nd.chess.Position.EXPRESSION_POSITION;
import static softeer2nd.chess.utils.StringUtils.appendNewLine;

/**
 * 체스 게임의 View를 담당
 */
public class ChessView {
    /**
     * View를 구성할 체스 보드
     */
    private final Board board;
    private final Scanner scanner;

    public ChessView(Board board) {
        this.board = board;
        this.scanner = new Scanner(System.in);
    }

    /**
     * 보드를 출력하기 위한 문자열을 반환
     * @return 보드를 출력하기 위한 문자열
     */
    public String showBoard() {
        StringBuilder sb = new StringBuilder();
        List<List<Character>> boardRepresentations = board.getRepresentations();

        for (int i = 0; i < Board.SIZE; i++) {
            int rankNumber = Board.SIZE - i;
            sb.append(characterListToString(boardRepresentations.get(i)))
                    .append("  ").append(rankNumber);
            appendNewLine(sb);
        }
        appendNewLine(sb);
        sb.append("abcdefgh");
        return sb.toString();
    }

    /**
     * 사용자로부터 입력을 받아 파싱 후 Command 객체 리턴
     * @return Command 객체
     */
    public Command input() {
        String string = scanner.nextLine();
        if (isStartCommand(string)) {
            return createStartCommand();
        } else if (isEndCommand(string)) {
            return createEndCommand();
        } else if (isMoveCommand(string)) {
            String[] stringParts = string.split(" ");
            Position source = Position.create(stringParts[1]);
            Position destination = Position.create(stringParts[2]);
            return createMoveCommand(source, destination);
        }
        throw new IllegalInputException();
    }

    /**
     * 스캐너를 close 하고 프로그램을 종료한다.
     */
    public void close() {
        scanner.close();
        System.exit(0);
    }

    /**
     * 문자열이 게임 시작 명령어인지 확인한다.
     * @param string 문자열
     * @return true if 게임 시작 명령어 else false
     */
    private boolean isStartCommand(String string) {
        return string.matches("start");
    }

    /**
     * 문자열이 기물 이동 명령어인지 학인한다.
     * @param string 문자열
     * @return true if 기물 이동 명령어 else false
     */
    private boolean isMoveCommand(String string) {
        return string.matches("move\\s" + EXPRESSION_POSITION + "\\s" + EXPRESSION_POSITION);
    }

    /**
     * 문자열이 게임 종료 명령어인지 확인한다.
     * @param string 문자열
     * @return true if 게임 종료 명령어 else false
     */
    private boolean isEndCommand(String string) {
        String END_COMMAND_FORMAT = "end";
        return string.matches(END_COMMAND_FORMAT);
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