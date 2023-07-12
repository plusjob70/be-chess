package softeer2nd;

import softeer2nd.chess.*;

public class Main {

    public static void main(String[] args) {
        Command command;
        Board board = new Board();
        ChessGame chessGame = new ChessGame(board);
        ChessView chessView = new ChessView(board);

        while (!chessGame.isOver()) {
            try {
                command = chessView.input();
                if (command.isStart()) {
                    chessGame.initialize();
                }
                if (command.isEnd()) {
                    chessView.close();
                }
                if (command.isMove()) {
                    chessGame.move(command.getSource(), command.getDestination());
                }
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
            System.out.println(chessView.showBoard());
        }
        System.out.println(chessView.showResults(chessGame.getStatus()));
        chessView.close();
    }
}