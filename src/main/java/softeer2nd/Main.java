package softeer2nd;

import softeer2nd.chess.Board;
import softeer2nd.chess.ChessGame;
import softeer2nd.chess.ChessView;
import softeer2nd.chess.Command;

public class Main {

    public static void main(String[] args) {
        Command command;
        Board board = new Board();
        ChessGame chessGame = new ChessGame(board);
        ChessView chessView = new ChessView(board);

        while (true) {
            try {
                command = chessView.input();
                if (command.isStart()) {
                    chessGame.initialize();
                } else if (command.isEnd()) {
                    chessView.close();
                } else if (command.isMove()) {
                    chessGame.move(command.getSource(), command.getDestination());
                }
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
            System.out.println(chessView.showBoard());
        }
    }
}