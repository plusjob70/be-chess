package softeer2nd.chess;

import static softeer2nd.chess.Command.Action.*;

public class Command {
    private final Action action;
    private final Position source;
    private final Position destination;

    private Command(Action action, Position source, Position destination) {
        this.action = action;
        this.source = source;
        this.destination = destination;
    }

    public Position getSource() {
        return source;
    }

    public Position getDestination() {
        return destination;
    }

    /**
     * 게임 시작 명령을 만든다.
     * @return 게임 시작 Command
     */
    public static Command createStartCommand() {
        return new Command(START, null, null);
    }

    /**
     * 기물 이동 명령을 만든다.
     * @param source 출발 위치
     * @param destination 도착 위치
     * @return 기물 이동 Command
     */
    public static Command createMoveCommand(Position source, Position destination) {
        return new Command(MOVE, source, destination);
    }

    /**
     * 게임 종료 명령을 만든다.
     * @return 게임 종료 Command
     */
    public static Command createEndCommand() {
        return new Command(END, null, null);
    }

    public boolean isStart() {
        return action.equals(START);
    }

    public boolean isMove() {
        return action.equals(MOVE);
    }

    public boolean isEnd() {
        return action.equals(END);
    }

    public enum Action {
        START, MOVE, END;
    }
}