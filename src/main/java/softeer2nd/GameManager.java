package softeer2nd;

import softeer2nd.chess.Board;

import java.util.Scanner;

public class GameManager {

    public static final String START_COMMAND = "start";
    public static final String END_COMMAND = "end";

    public void main() {
        String command;
        Scanner scanner = new Scanner(System.in);
        Board board = new Board();

        while (true) {
            command = scanner.next();

            if (command.equals(START_COMMAND)) {
                startGame(board);
            } else if (command.equals(END_COMMAND)) {
                break;
            }
        }

        scanner.close();
    }

    private void startGame(Board board) {
        board.initialize();
        System.out.println(board.print());
    }

}
