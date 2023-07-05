package softeer2nd;

import softeer2nd.chess.Board;

import java.util.Scanner;

public class Main {

    public static final String START_COMMAND = "start";
    public static final String END_COMMAND = "end";

    public static void main(String[] args) {
        String command;
        Board board = new Board();
        Scanner scanner = new Scanner(System.in);

        try {
            while (true) {
                command = scanner.next();

                if (command.equals(START_COMMAND)) {
                    board.initialize();
                    System.out.println(board.showBoard());
                } else if (command.equals(END_COMMAND)) {
                    break;
                }
            }
        } catch (Exception ignored) {

        } finally {
            scanner.close();
        }
    }
}