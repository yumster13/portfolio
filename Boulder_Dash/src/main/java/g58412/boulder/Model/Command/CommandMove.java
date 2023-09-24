package g58412.boulder.model.Command;

import g58412.boulder.model.Board;
import g58412.boulder.model.Objects.Position;

import java.util.Stack;

public class CommandMove implements Command{

    Stack<Board> boards = new Stack<Board>();

    private String direction;

    private Board board;

    public CommandMove(Stack<Board> boards, Board board, String direction) {
        this.direction = direction;
        this.board = board;
        this.boards = boards;
    }


    @Override
    public void execute() {
        board.move(direction);
    }

    @Override
    public void unexecute() {
        board.setBoard(boards.pop().getBoard());
    }
}
