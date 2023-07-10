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

    public static Command createStartCommand() {
        return new Command(START, null, null);
    }

    public static Command createMoveCommand(Position source, Position destination) {
        return new Command(MOVE, source, destination);
    }

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