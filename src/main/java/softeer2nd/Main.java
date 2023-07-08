package softeer2nd;

import softeer2nd.chess.Board;
import softeer2nd.chess.ChessGame;
import softeer2nd.chess.ChessView;
import softeer2nd.chess.Position;

import java.util.Scanner;

public class Main {

    public static final String START_COMMAND = "start";
    public static final String MOVE_COMMAND = "move";
    public static final String END_COMMAND = "end";

    public static void main(String[] args) {
        String input;
        Board board = new Board();
        ChessGame chessGame = new ChessGame(board);
        ChessView chessView = new ChessView(board);
        Scanner scanner = new Scanner(System.in);

        try {
            while (true) {
                input = scanner.nextLine();
                if (input.equals(START_COMMAND)) {
                    chessGame.initialize();
                } else if (input.startsWith(MOVE_COMMAND)) {
                    String[] inputParts = input.split(" ");
                    Position source = Position.create(inputParts[1]);
                    Position destination = Position.create(inputParts[2]);
                    chessGame.move(source, destination);
                } else if (input.equals(END_COMMAND)) {
                    break;
                }
                System.out.println(chessView.showBoard());
            }
        } catch (Exception ignored) {

        } finally {
            scanner.close();
        }
    }
}